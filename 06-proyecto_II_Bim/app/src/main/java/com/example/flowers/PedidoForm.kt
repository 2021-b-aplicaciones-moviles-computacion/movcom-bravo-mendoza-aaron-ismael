package com.example.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class PedidoForm : AppCompatActivity() {
    var totalAPagar = 0.00
    var adaptador: ArrayAdapter<Detalle>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_form)

        val txtIdPedido = findViewById<TextView>(R.id.txtIdPedido)
        val spinnerClientes = findViewById<Spinner>(R.id.spn_clientes)
        val listaClientes = arrayListOf<String>()
        listaClientes.add("Cliente")
        var db = Firebase.firestore
        var documentoCliente: (MutableList<DocumentSnapshot>)
        var referencia1 = db.collection("cliente")
            .get().addOnSuccessListener {
                documentoCliente = it.documents
                documentoCliente.forEach { iteracion ->
                    listaClientes.add(
                        Cliente(
                            iteracion.id.toInt(),
                            iteracion.get("nombre").toString()
                        ).toString()
                    )
                }
            }
        val adaptadorClientes =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaClientes)

        spinnerClientes.adapter = adaptadorClientes
        val spinnerVehiculos = findViewById<Spinner>(R.id.spn_vehiculo)
        val listaVehiculos = arrayListOf<String>()
        listaVehiculos.add("Vehiculo")
        db = Firebase.firestore
        var documentoVehiculo: MutableList<DocumentSnapshot>
        var referencia2 = db.collection("vehiculo")
            .get().addOnSuccessListener {
                documentoVehiculo = it.documents
                documentoVehiculo.forEach { iteracion ->
                    listaVehiculos.add(
                        Vehiculo(
                            iteracion.id.toString(),
                            iteracion.get("marca").toString(),
                        ).toString()
                    )
                }
            }
        val adaptadorVehiculos =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                listaVehiculos
            )
        spinnerVehiculos.adapter = adaptadorVehiculos

        val txtFechaEntrega = findViewById<EditText>(R.id.txt_fecha_entrega)
        txtFechaEntrega.setOnClickListener {
            showDatePickerDialog()
        }

        val rdBtnPendiente = findViewById<RadioButton>(R.id.rb_estado_pendiente)
        val rdBtnEnviado = findViewById<RadioButton>(R.id.rb_estado_enviado)
        val rdBtnEntregado = findViewById<RadioButton>(R.id.rb_estado_entregado)

        val fbMasProductosPedido = findViewById<FloatingActionButton>(R.id.fb_mas_productos_pedido)
        fbMasProductosPedido.setOnClickListener {
            if (intent.getParcelableExtra<Pedido>("pedido") != null) {
                irActividadConPedido(
                    DetalleForm::class.java,
                    intent.getParcelableExtra<Pedido>("pedido")!!
                )
            } else {
                mostrarAlerta("Guarde el pedido")
            }

        }


        val txtTotalPedido = findViewById<TextView>(R.id.tv_total_pedido)
        txtTotalPedido.setText(totalAPagar.toString())



        if (intent.getParcelableExtra<Pedido>("pedido") != null) {
            txtIdPedido.setText(intent.getParcelableExtra<Pedido>("pedido")!!.id.toString())
            txtIdPedido.isEnabled = false
            txtFechaEntrega.setText(intent.getParcelableExtra<Pedido>("pedido")!!.fechaEntrega)
            if (intent.getParcelableExtra<Pedido>("pedido")!!.estado == "Pendiente") {
                rdBtnPendiente.isChecked = true
            }
            if (intent.getParcelableExtra<Pedido>("pedido")!!.estado == "Enviado") {
                rdBtnEnviado.isChecked = true
            }
            if (intent.getParcelableExtra<Pedido>("pedido")!!.estado == "Entregado") {
                rdBtnEntregado.isChecked = true
            }
            txtTotalPedido.setText(intent.getParcelableExtra<Pedido>("pedido")?.totalAPagar.toString())

            var lista_detalle = arrayListOf<Detalle>()
            val db = Firebase.firestore
            var documentoDetalle: MutableList<DocumentSnapshot>
            var detalle = db.collection("pedido")
                .document(txtIdPedido.text.toString()).collection("detalle")
            detalle.get().addOnSuccessListener {
                documentoDetalle = it.documents
                documentoDetalle.forEach { iteracion ->
                    lista_detalle.add(
                        Detalle(
                            iteracion.id.toInt(),
                            iteracion.get("producto").toString(),
                            iteracion.get("cantidad").toString().toInt(),
                            iteracion.get("totalDetalle").toString().toDouble()
                        )
                    )

                }
                if (lista_detalle.size > 0) {
                    val list_V_detalle = findViewById<ListView>(R.id.lv_pedidos_productos)
                    adaptador =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, lista_detalle)
                    list_V_detalle.adapter = adaptador
                    adaptador!!.notifyDataSetChanged()

                } else {
                    Log.i("hola", "No hay id de documento")
                }
            }

        }

        val btnGuardarPedido = findViewById<Button>(R.id.btn_guardar_pedido)
        btnGuardarPedido.setOnClickListener {
            var txtEstado = ""
            if (rdBtnPendiente.isChecked == true) {
                txtEstado = rdBtnPendiente.text.toString()
            }
            if (rdBtnEnviado.isChecked == true) {
                txtEstado = rdBtnEnviado.text.toString()
            }
            if (rdBtnEntregado.isChecked == true) {
                txtEstado = rdBtnEntregado.text.toString()
            }

            val nuevoPedido = hashMapOf<String, Any>(
                "id" to txtIdPedido.text.toString(),
                "cliente" to spinnerClientes.selectedItem.toString(),
                "estado" to txtEstado,
                "fechaEntrega" to txtFechaEntrega.text.toString(),
                "fechaPedido" to SimpleDateFormat("dd/MM/yyyy").format(Date()),
                "vehiculo" to spinnerVehiculos.selectedItem.toString(),
                "totalAPagar" to totalAPagar.toInt()

            )
            val db = Firebase.firestore
            val referencia = db.collection("pedido")
            if (intent.getParcelableExtra<Pedido>("pedido") != null) {
                referencia.document("${txtIdPedido.text}")
                    .update(nuevoPedido).addOnSuccessListener {
                        startActivity(Intent(this, Home::class.java))
                    }
            } else {
                referencia.document("${txtIdPedido.text}")
                    .set(nuevoPedido).addOnSuccessListener {
                        startActivity(Intent(this, Home::class.java))
                    }
            }

        }


    }

    fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> OnDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun OnDateSelected(day: Int, month: Int, year: Int) {
        val txtFechaEntrega = findViewById<EditText>(R.id.txt_fecha_entrega)
        txtFechaEntrega.setText("${day}/${month}/${year}")
    }

    fun mostrarAlerta(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(mensaje)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun irActividadConPedido(clase: Class<*>, pedido: Pedido) {
        val intent = Intent(this, clase)
        intent.putExtra("pedido", pedido)
        startActivity(intent)
    }
}