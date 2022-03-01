package com.example.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ClienteForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente_form)

        val btnGuardarCliente = findViewById<Button>(R.id.btn_guardarÇ_cliente)
        btnGuardarCliente.setOnClickListener {
            val nuevoCliente = hashMapOf<String, Any>(
                "nombre" to findViewById<EditText>(R.id.txt_nombre_cliente).text.toString()
            )
            val db = Firebase.firestore
            val referencia = db.collection("cliente")
                .document(findViewById<EditText>(R.id.txt_cedula_cliente).text.toString())
            referencia.set(nuevoCliente).addOnSuccessListener {
                Toast.makeText(
                    this,
                    "Se creó ${findViewById<EditText>(R.id.txt_nombre_cliente).text.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, Home::class.java))
            }
        }
    }
}