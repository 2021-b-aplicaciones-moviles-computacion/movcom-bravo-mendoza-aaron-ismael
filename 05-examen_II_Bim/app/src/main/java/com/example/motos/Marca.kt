package com.example.motos

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Marca : AppCompatActivity() {
    var posicionLista = 0
    var listaMarca = arrayListOf<BMarca>()
    var adaptador: ArrayAdapter<BMarca>? = null


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marca)

        val btnIrCrearMarca = findViewById<Button>(R.id.btn_ir_crear_marca)
        btnIrCrearMarca.setOnClickListener { irActividad(CrearMarca::class.java) }

        val db = Firebase.firestore
        var documentoMarca: (MutableList<DocumentSnapshot>)
        val marcas = db.collection("marca").orderBy("id")
        marcas.get().addOnSuccessListener {
            documentoMarca = it.documents
            documentoMarca.forEach { iteracion ->
                listaMarca.add(
                    BMarca(
                        iteracion.get("id").toString().toInt(),
                        iteracion.get("nombre").toString(),
                        iteracion.get("esCompetencia").toString().toBoolean(),
                        iteracion.get("promedioVentas").toString().toDouble()
                    )
                )
            }
            if (listaMarca.size > 0) {
                val listV_motos = findViewById<ListView>(R.id.lv_motos)
                adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaMarca)
                listV_motos.adapter = adaptador
                registerForContextMenu(listV_motos)
                adaptador!!.notifyDataSetChanged()
            }
        }.addOnFailureListener { }
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
        var objMarca = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.mi_editar_marca -> {
                if (objMarca != null) {
                    irActividadConMarca(EditarMarca::class.java, objMarca)
                }
                return true
            }
            R.id.mi_verModelo -> {
                if (objMarca != null) {
                    irActividadConMarca(Modelo::class.java, objMarca)
                    Log.i("marca1", "${objMarca}")
                }
                return true
            }
            else -> {
                super.onContextItemSelected(item)
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

    fun eliminarMarca(marca: BMarca) {
        listaMarca.removeIf { marcas -> (marcas == marca) }
        Log.i("eliminar", "${listaMarca}")
    }

//    fun irModelos(clase: Class<*>, posicion: Int) {
//        val intent = Intent(this, clase)
//        intent.putExtra("idMarca", lista[posicion].id.toString())
//        Log.i("idMarca", "${lista[posicion].id.toString()}")
//        startActivity(intent)
//    }

//    fun cargarDatos(lista: ArrayList<BMarca>) {
//        val db = Firebase.firestore
//        val marcas = db.collection("marca").orderBy("id")
//        marcas.get().addOnSuccessListener {
//            for (marca in it) {
//                lista.add(BMarca(marca.data.toString()))
//                Log.i("consulta", "${marca.data}")
//            }
//        }.addOnFailureListener { }
//    }

}

