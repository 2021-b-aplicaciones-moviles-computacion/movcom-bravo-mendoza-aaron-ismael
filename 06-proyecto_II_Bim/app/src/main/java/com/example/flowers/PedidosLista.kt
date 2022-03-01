package com.example.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class PedidosLista : AppCompatActivity() {
    var posicionLista = 0
    var listaPedido = arrayListOf<Pedido>()
    var adaptador: ArrayAdapter<Pedido>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_lista)

        val fbMasPedidos = findViewById<FloatingActionButton>(R.id.fb_mas_pedidos)
        fbMasPedidos.setOnClickListener {
            startActivity(Intent(this, PedidoForm::class.java))
        }
        val db = Firebase.firestore
        var documentoPedido: (MutableList<DocumentSnapshot>)
        val pedidos = db.collection("pedido").orderBy("id")
        pedidos.get().addOnSuccessListener {
            documentoPedido = it.documents
            documentoPedido.forEach {iteracion ->

                listaPedido.add(
                    Pedido(
                        iteracion.get("id").toString().toInt(),
                        iteracion.get("fechaPedido").toString(),
                        iteracion.get("fechaEntrega").toString(),
                        iteracion.get("totalAPagar").toString().toDouble(),
                        iteracion.get("vehiculo").toString(),
                        iteracion.get("cliente").toString(),
                        iteracion.get("estado").toString()
                    )
                )
            }
            if (listaPedido.size > 0) {
                adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPedido)
                val listViewPedidos = findViewById<ListView>(R.id.lv_pedidos)
                listViewPedidos.adapter = adaptador
                registerForContextMenu(listViewPedidos)
                adaptador!!.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objPedido = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.mi_editar_pedido -> {
                if (objPedido != null) {
                    irActividadConPedido(PedidoForm::class.java, objPedido)
                }
                return true
            }

            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    fun irActividad(clase: Class<*>){
        startActivity(Intent(this, clase::class.java))
    }

    fun irActividadConPedido(clase: Class<*>, pedido: Pedido){
        val intent = Intent(this, clase)
        intent.putExtra("pedido", pedido)
        startActivity(intent)
    }


}