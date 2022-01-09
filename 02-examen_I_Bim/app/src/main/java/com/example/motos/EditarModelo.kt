package com.example.motos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast

class EditarModelo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_modelo)
        val objModelo = intent.getParcelableExtra<BModelo>("modelo")

        val id = objModelo!!.id.toString()
        val nombre = objModelo!!.nombre.toString()
        val disponible = objModelo!!.disponible.toString()
        val costo = objModelo!!.costo.toString()
        val objMarca = BBaseDatosMemoria.arregloMarca.filter { it.id == objModelo.idMarca }
        Log.i("mijardin", "${objModelo} ${objMarca[0]}")

        findViewById<EditText>(R.id.txt_id_modelo_editar).setText(id)
        findViewById<EditText>(R.id.txt_nombre_modelo_editar).setText(nombre)
        findViewById<EditText>(R.id.txt_costo_modelo_editar).setText(costo)
        findViewById<Switch>(R.id.sw_disponible_editar).setChecked(disponible.toBoolean())

        val botonEditarModelo = findViewById<Button>(R.id.btn_crear_modelo_editar)
        botonEditarModelo.setOnClickListener {
            editarModelo(id.toInt())
            Log.i("mijardin", "${objMarca[0]}")
            Toast.makeText(this, "Se editó ${nombre}", Toast.LENGTH_SHORT).show()
            irActividadConMarca(Modelo::class.java, objMarca[0])

        }


    }

    fun editarModelo(id: Int) {
        BBaseDatosMemoria.arregloModelo.filter { it.id == id }
            .map {
                it.nombre = findViewById<EditText>(R.id.txt_nombre_modelo_editar).text.toString()
                it.costo =
                    findViewById<EditText>(R.id.txt_costo_modelo_editar).text.toString().toDouble()
                it.disponible = findViewById<Switch>(R.id.sw_disponible_editar).isChecked
            }
    }

    fun irActividadConMarca(clase: Class<*>, marca: BMarca) {
        val intent = Intent(this, clase)
        intent.putExtra("marca", marca)
        startActivity(intent)
    }
}