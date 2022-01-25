package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    //val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    Log.i("intents", "${data?.getStringExtra("nombreModificado")}")
                    Log.i("intents", "${data?.getIntExtra("edadModificado", 0)}")
                }
            }
            if (result.resultCode == Activity.RESULT_CANCELED) {
                Log.i("intents", "Cancelado")
            }
        }
    val resultlauncher2 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val uri: Uri = data.data!!
                    val cursor = contentResolver.query(
                        uri,
                        null,
                        null,
                        null,
                        null,
                        null
                    )
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(indiceTelefono!!)
                    cursor?.close()
                    Log.i("intents", "Telefono: ${telefono}")
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //BASE DE DATOS SQLITE
        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)



        if (EBaseDeDatos.TablaUsuario != null) {
            val idQuemado = 0
            EBaseDeDatos.TablaUsuario?.crearUsuarioFormulario(
                "Adrian",
                "Adrian desc"
            )
            var consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
            EBaseDeDatos.TablaUsuario?.actualizarUsuarioFormulario(
                "Vicente",
                "Vicenet desc",
                idQuemado
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
            EBaseDeDatos.TablaUsuario?.eliminarUsuarioFormulario(
                idQuemado
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
        }

        val botonCicloVida = findViewById<Button>(R.id.btn_ir_ciclo_vida)
        botonCicloVida.setOnClickListener {
            irActividad(ACicloVida::class.java)
        }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener {
            irActividad(BListView::class.java)
        }
        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta =
                Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
            //resultLauncher.launch(intentConRespuesta)
            resultlauncher2.launch(intentConRespuesta)
        }

        val botonRecyclerView = findViewById<Button>(R.id.btn_ir_recycler_view)
        botonRecyclerView.setOnClickListener {
            abrirActividadConParametros(GRecyclerView::class.java)
        }

        val botonHttp = findViewById<Button>(R.id.btn_ir_http)
        botonHttp.setOnClickListener {
            abrirActividadConParametros(HHttpActivity::class.java)
        }

    }

    fun abrirActividadConParametros(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        //solo variables primitivas
        intentExplicito.putExtra("nombre", "Aaron")
        intentExplicito.putExtra("apellido", "Bravo")
        intentExplicito.putExtra("edad", 29)
        intentExplicito.putExtra("Entrenador", BEntrenador("a", "b"))
        resultLauncher.launch(intentExplicito)
        //startActivityForResult(intent, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }


}