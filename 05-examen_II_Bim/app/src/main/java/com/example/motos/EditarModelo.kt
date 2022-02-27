package com.example.motos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarModelo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_modelo)
        val objModelo = intent.getParcelableExtra<BModelo>("modelo")

        val id = objModelo!!.id.toString()
        val nombre = objModelo!!.nombre.toString()
        val disponible = objModelo!!.disponible.toString()
        val costo = objModelo!!.costo.toString()

        findViewById<EditText>(R.id.txt_id_modelo_editar).setText(id)
        findViewById<EditText>(R.id.txt_nombre_modelo_editar).setText(nombre)
        findViewById<EditText>(R.id.txt_costo_modelo_editar).setText(costo)
        findViewById<Switch>(R.id.sw_disponible_editar).setChecked(disponible.toBoolean())

        val botonEditarModelo = findViewById<Button>(R.id.btn_crear_modelo_editar)
        botonEditarModelo.setOnClickListener {
            val nuevoModelo = hashMapOf<String, Any>(
                "id" to findViewById<EditText>(R.id.txt_id_modelo_editar).text.toString().toInt(),
                "nombre" to findViewById<EditText>(R.id.txt_nombre_modelo_editar).text.toString(),
                "disponible" to findViewById<Switch>(R.id.sw_disponible_editar).isChecked,
                "costo" to findViewById<EditText>(R.id.txt_costo_modelo_editar).text.toString().toDouble(),
                "idMarca" to objModelo.idMarca.toString().toInt()
            )
            val db = Firebase.firestore
            val referencia = db.collection("modelo").document("${id}-${nombre}")
            referencia.update(nuevoModelo).addOnSuccessListener {
                Toast.makeText(this, "Se editó ${nombre}", Toast.LENGTH_SHORT).show()
                irActividad(Marca::class.java)
            }

        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}