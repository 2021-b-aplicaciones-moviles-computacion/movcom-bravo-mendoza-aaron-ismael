package com.example.motos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast

class CrearModelo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_modelo)
        //val objMarca = intent.getParcelableExtra<BMarca>("marca")
        val objMarca = intent.getParcelableExtra<BMarca>("marca")
        Log.i("marca4", "${objMarca}")
        val btnGuardarModelo = findViewById<Button>(R.id.btn_crear_modelo)
        btnGuardarModelo.setOnClickListener {

            if (objMarca != null) {
                val nuevoModelo = BModelo(
                    findViewById<EditText>(R.id.txt_id_modelo).text.toString().toInt(),
                    findViewById<EditText>(R.id.txt_nombre_modelo).text.toString(),
                    findViewById<Switch>(R.id.sw_disponible).isChecked,
                    findViewById<EditText>(R.id.txt_costo_modelo).text.toString().toDouble(),
                    objMarca.id
                )
                BBaseDatosMemoria.arregloModelo.add(nuevoModelo)
                Toast.makeText(
                    this,
                    "Se creó ${findViewById<EditText>(R.id.txt_nombre_modelo).text}",
                    Toast.LENGTH_SHORT
                ).show()
                irActividadConMarca(Modelo::class.java, objMarca)
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
}