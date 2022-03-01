package com.example.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductoForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto_form)

        val btnGuardarProducto = findViewById<Button>(R.id.btn_guardar_producto)
        btnGuardarProducto.setOnClickListener {
            val nuevoProducto = hashMapOf<String, Any>(
                "precio" to findViewById<EditText>(R.id.txt_precio_producto).text.toString()
            )
            val db = Firebase.firestore
            val referencia = db.collection("producto")
                .document(findViewById<EditText>(R.id.txt_nombre_producto).text.toString())
            referencia.set(nuevoProducto).addOnSuccessListener {
                Toast.makeText(
                    this,
                    "Se creó ${findViewById<EditText>(R.id.txt_nombre_producto).text.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, Home::class.java))
            }
        }
    }
}
