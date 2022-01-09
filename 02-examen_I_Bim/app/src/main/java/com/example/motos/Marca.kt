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

class Marca : AppCompatActivity() {
    var posicionLista = 0
    var lista = BBaseDatosMemoria.arregloMarca
    var adaptador: ArrayAdapter<BMarca>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marca)

        val listV_motos = findViewById<ListView>(R.id.lv_motos)
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
        listV_motos.adapter = adaptador
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
        posicionLista = id
        Log.i("context-menu", "Position: ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objMarca = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.mi_editar_marca -> {
                if (objMarca != null) {
                    irActividadConMarca(EditarMarca::class.java, objMarca)
                    Log.i("marca", "${objMarca}")
                }
                return true
            }
            R.id.mi_eliminar_marca -> {
                AlertDialog.Builder(this).apply {
                    setTitle("Eliminar")
                    setMessage("Realmente quiere eliminar la marca")
                    setPositiveButton("Si") { _: DialogInterface, _: Int ->
                        if (objMarca != null) {
                            eliminarMarca(objMarca)
                        }
                        adaptador?.notifyDataSetChanged()
                    }
                    setNegativeButton("No", null)
                }.show()
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
        lista.removeIf { marcas -> (marcas == marca) }
        Log.i("eliminar", "${lista}")
    }

    fun irModelos(clase: Class<*>, posicion: Int) {
        val intent = Intent(this, clase)
        intent.putExtra("idMarca", lista[posicion].id.toString())
        Log.i("idMarca", "${lista[posicion].id.toString()}")
        startActivity(intent)
    }
}

