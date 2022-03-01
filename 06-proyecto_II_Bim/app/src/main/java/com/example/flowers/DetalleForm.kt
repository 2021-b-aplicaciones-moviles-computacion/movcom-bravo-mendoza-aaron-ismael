package com.example.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetalleForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_form)

        val totalDetalle = 0

        val txtIdDetalle = findViewById<EditText>(R.id.txt_id_detalle)
        val txtCantidadDetalle = findViewById<EditText>(R.id.txt_cantidad_detalle)
        val spinnerProducto = findViewById<Spinner>(R.id.sp_detalle_producto)
        var listaProductos = arrayListOf<String>()
        listaProductos.add("Producto")
        val db = Firebase.firestore
        var documentoPrtoducto: MutableList<DocumentSnapshot>
        var referencia = db.collection("producto")
            .get().addOnSuccessListener {
                documentoPrtoducto = it.documents
                documentoPrtoducto.forEach { iteracion ->
                    listaProductos.add(
                        Producto(
                            iteracion.id.toString(),
                            iteracion.get("precio").toString().toDouble()
                        ).toString()
                    )
                }
            }

        val adaptadorProductos = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaProductos)
        spinnerProducto.adapter = adaptadorProductos

        val txtTotaletalle = findViewById<EditText>(R.id.txt_total_detalle)

        val btnGuardarDetalle = findViewById<Button>(R.id.btn_guardar_detalle)
        btnGuardarDetalle.setOnClickListener {
            val nuevoDetalle = hashMapOf<String, Any>(
                "cantidad" to txtCantidadDetalle.text.toString(),
                "producto" to spinnerProducto.selectedItem.toString(),
                "totalDetalle" to 0//txtTotaletalle.text.toString()
            )
            val db = Firebase.firestore
            val referencia = db.collection("pedido").
            document(intent.getParcelableExtra<Pedido>("pedido")!!.id.toString())
                .collection("detalle").document("${txtIdDetalle.text}")
            referencia.set(nuevoDetalle).addOnSuccessListener {
                mostrarAlerta("Detalle creado")
                startActivity(Intent(this, Home::class.java))
            }
        }
    }
    fun mostrarAlerta(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(mensaje)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}