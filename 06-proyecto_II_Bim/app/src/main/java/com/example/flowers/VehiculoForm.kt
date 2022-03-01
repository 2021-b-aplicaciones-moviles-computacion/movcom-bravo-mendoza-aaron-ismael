package com.example.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VehiculoForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehiculo_form)

        val btnGuardarVehiculo = findViewById<Button>(R.id.btn_guardar_vehiculo)
        btnGuardarVehiculo.setOnClickListener {
            val nuevoVehiculo = hashMapOf<String, Any>(
                "marca" to findViewById<EditText>(R.id.txt_marca_vehiculo).text.toString()
            )
            val db = Firebase.firestore
            val referencia = db.collection("vehiculo")
                .document(findViewById<EditText>(R.id.txt_placa_vehiculo).text.toString())
            referencia.set(nuevoVehiculo).addOnSuccessListener {
                Toast.makeText(
                    this,
                    "Se creó ${findViewById<EditText>(R.id.txt_placa_vehiculo).text.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, Home::class.java))
            }
        }
    }
}