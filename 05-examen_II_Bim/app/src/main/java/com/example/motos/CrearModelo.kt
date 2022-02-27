package com.example.motos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearModelo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_modelo)

        val objMarca = intent.getParcelableExtra<BMarca>("marca")
        val btnGuardarModelo = findViewById<Button>(R.id.btn_crear_modelo)
        btnGuardarModelo.setOnClickListener {

            if (objMarca != null) {

                val txtIdModelo = findViewById<EditText>(R.id.txt_id_modelo)
                val txtNombreModelo = findViewById<EditText>(R.id.txt_nombre_modelo)
                val txtDisponibleModelo = findViewById<Switch>(R.id.sw_disponible)
                val txtCostoModelo = findViewById<EditText>(R.id.txt_costo_modelo)
                val nuevoModelo = hashMapOf<String, Any>(
                    "id" to txtIdModelo.text.toString().toInt(),
                    "nombre" to txtNombreModelo.text.toString(),
                    "disponible" to txtDisponibleModelo.isChecked,
                    "costo" to txtCostoModelo.text.toString().toDouble(),
                    "idMarca" to objMarca.id
                )
                val db = Firebase.firestore
                var referencia =
                    db.collection("modelo").document("${txtIdModelo.text}-${txtNombreModelo.text}")
                referencia.set(nuevoModelo).addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Se creó ${findViewById<EditText>(R.id.txt_nombre_modelo).text}",
                        Toast.LENGTH_SHORT
                    ).show()
                    irActividadConMarca(Modelo::class.java, objMarca)
                }


            }
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irActividadConMarca(clase: Class<*>, marca: BMarca) {
        val intent = Intent(this, clase)
        intent.putExtra("marca", marca)
        startActivity(intent)
    }
}