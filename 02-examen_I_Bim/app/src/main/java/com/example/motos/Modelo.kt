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

class Modelo : AppCompatActivity() {
    var posicionLista = 0
    var lista = BBaseDatosMemoria.arregloModelo
    var adaptador: ArrayAdapter<BModelo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelo)

        val objMarca = intent.getParcelableExtra<BMarca>("marca")
        Log.i("marca2", "${objMarca}")
        val listV_modelo_motos = findViewById<ListView>(R.id.lv_modelos)

        if (objMarca != null) {
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                lista.filter { it.idMarca == objMarca.id })
        }
        listV_modelo_motos.adapter = adaptador
        registerForContextMenu(listV_modelo_motos)
        adaptador!!.notifyDataSetChanged()

        val btnIrCrearModelo = findViewById<Button>(R.id.btn_ir_crear_modelo)
        btnIrCrearModelo.setOnClickListener {
            if (objMarca != null) {
                irActividadConMarca(CrearModelo::class.java, objMarca)
                Log.i("marca3", "${objMarca}")
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
        inflater.inflate(R.menu.menu2, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionLista = id
        Log.i("context-menu", "Position: ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var objModelo = adaptador!!.getItem(posicionLista)
        return when (item.itemId) {
            R.id.mi_editar_modelo -> {
                if (objModelo != null) {
                    irActividadConModelo(EditarModelo::class.java, objModelo)
                    Log.i("modelo", "${objModelo}")
                }
                return true
            }
            R.id.mi_eliminar_modelo -> {
                AlertDialog.Builder(this).apply {
                    setTitle("Eliminar")
                    setMessage("Realmente quiere eliminar el modelo")
                    setPositiveButton("Si") { _: DialogInterface, _: Int ->
                        if (objModelo != null) {
                            eliminarModelo(objModelo)
                            adaptador!!.remove(adaptador!!.getItem(posicionLista))
                        }
                        adaptador?.notifyDataSetChanged()
                    }
                    setNegativeButton("No", null)
                }.show()
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

    fun irActividadConModelo(clase: Class<*>, modelo: BModelo) {
        val intent = Intent(this, clase)
        intent.putExtra("modelo", modelo)
        startActivity(intent)
    }

    fun eliminarModelo(modelo: BModelo) {
        lista.removeIf { modelos -> (modelos == modelo) }
    }
}