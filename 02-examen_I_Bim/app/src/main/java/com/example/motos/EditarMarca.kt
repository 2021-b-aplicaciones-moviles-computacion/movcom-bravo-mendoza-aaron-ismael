package com.example.motos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast

class EditarMarca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_marca)
        val objMarca = intent.getParcelableExtra<BMarca>("marca")

        val id = objMarca!!.id.toString()
        val nombre = objMarca!!.nombre.toString()
        val competencia = objMarca!!.esCompetencia.toString()
        val promedioVentas = objMarca!!.promedioVentas.toString()

        Log.i("marca", "${objMarca}")
        findViewById<EditText>(R.id.txt_id_marca_editar).setText(id)
        findViewById<EditText>(R.id.txt_nombre_marca_editar).setText(nombre)
        findViewById<EditText>(R.id.txt_promedio_marca_editar).setText(promedioVentas)
        findViewById<Switch>(R.id.sw_competencia_editar).setChecked(competencia.toBoolean())

        val botonEditarMarca = findViewById<Button>(R.id.btn_crear_marca_editar)
        botonEditarMarca.setOnClickListener {
            editarMarca(id.toInt())
            Toast.makeText(this, "Se editó ${nombre}", Toast.LENGTH_SHORT).show()
            irActividad(Marca::class.java)
        }
    }

    fun editarMarca(id: Int) {
        BBaseDatosMemoria.arregloMarca.filter { it.id == id }
            .map {
                it.nombre = findViewById<EditText>(R.id.txt_nombre_marca_editar).text.toString()
                it.esCompetencia = findViewById<Switch>(R.id.sw_competencia_editar).isChecked
                it.promedioVentas =
                    findViewById<EditText>(R.id.txt_promedio_marca_editar).text.toString()
                        .toDouble()
            }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}