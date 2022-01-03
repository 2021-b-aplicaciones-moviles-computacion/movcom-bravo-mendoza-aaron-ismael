package com.example.motos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch

class EditarMarca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_marca)
        val id = intent.getStringExtra("id")
        val nombre = intent.getStringExtra("nombre")
        val competencia = intent.getStringExtra("competencia")
        val promedioVentas = intent.getStringExtra("ventas")

        Log.i("marca", "${id} ${nombre} ${competencia} ${promedioVentas}")
        findViewById<EditText>(R.id.txt_id_marca_editar).setText(id)
        findViewById<EditText>(R.id.txt_nombre_marca_editar).setText(nombre)
        findViewById<EditText>(R.id.txt_promedio_marca_editar).setText(promedioVentas)
        findViewById<Switch>(R.id.sw_disponible_editar).setChecked(competencia.toBoolean())

        val botonEditar = findViewById<Button>(R.id.btn_crear_marca_editar)
        botonEditar.setOnClickListener {
            editarMarca(id.toString().toInt())
            startActivity(Intent(this, Marca::class.java))
        }


    }

    fun editarMarca(id: Int){
        BBaseDatosMemoria.arregloMarca.filter { it.id == id }
            .map {
                it.nombre = findViewById<EditText>(R.id.txt_nombre_marca_editar).text.toString()
                it.esCompetencia = findViewById<Switch>(R.id.sw_disponible_editar).isChecked
                it.promedioVentas = findViewById<EditText>(R.id.txt_promedio_marca_editar).text.toString().toDouble()
            }
    }
}