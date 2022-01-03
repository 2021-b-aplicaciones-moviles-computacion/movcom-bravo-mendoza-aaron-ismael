package com.example.motos

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

class Marca : AppCompatActivity() {
    var idItemSeleccionado = 0
    val lista = BBaseDatosMemoria.arregloMarca
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listV_motos = findViewById<ListView>(R.id.lv_motos)
        //val lista = BBaseDatosMemoria.arregloMarca

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        listV_motos.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listV_motos)

        val btnIrCrearMarca = findViewById<Button>(R.id.btn_ir_crear_marca)
        btnIrCrearMarca.setOnClickListener { irActividad(CrearMarca::class.java) }


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
        idItemSeleccionado = id
        Log.i("context-menu", "Position: ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar -> {
                irEditarMarca(EditarMarca::class.java, idItemSeleccionado)
                return true
            }
            R.id.mi_eliminar -> {
                return true
            }
            R.id.mi_verModelo -> {
                irActividad(Modelo::class.java)
                return true
            }
            else -> {
                super.onContextItemSelected(item)
            }

        }
    }

    fun irActividad(clase: Class<*>,){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun irEditarMarca(clase: Class<*>,posicion: Int){
        val intent = Intent(this, clase)
        intent.putExtra("id", lista[posicion].id.toString())
        intent.putExtra("nombre", lista[posicion].nombre.toString())
        intent.putExtra("competencia", lista[posicion].esCompetencia.toString())
        intent.putExtra("ventas", lista[posicion].promedioVentas.toString())

        startActivity(intent)
    }



}

