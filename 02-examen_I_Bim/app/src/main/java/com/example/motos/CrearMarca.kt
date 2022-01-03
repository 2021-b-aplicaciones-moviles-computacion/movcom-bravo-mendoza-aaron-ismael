package com.example.motos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch


class CrearMarca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_marca)
        val btnGuardarMarca = findViewById<Button>(R.id.btn_crear_marca)

        btnGuardarMarca.setOnClickListener {
            val nuevaMarca = BMarca(
                findViewById<EditText>(R.id.txt_id_marca).text.toString().toInt(),
                findViewById<EditText>(R.id.txt_nombre_marca).text.toString(),
                findViewById<Switch>(R.id.sw_disponible).isChecked,
                findViewById<EditText>(R.id.txt_promedio_marca).text.toString().toDouble()
            )
            BBaseDatosMemoria.arregloMarca.add(nuevaMarca)
            Log.i("Marca", "${BBaseDatosMemoria.arregloMarca}")

            startActivity(Intent(
                this,
                Marca::class.java
            ))
        }
    }

}