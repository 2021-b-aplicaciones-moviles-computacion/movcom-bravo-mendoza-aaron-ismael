package com.example.motos

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class EditarMarca : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_marca)
        val objMarca = intent.getParcelableExtra<BMarca>("marca")

        val id = objMarca!!.id.toString()
        val nombre = objMarca!!.nombre.toString()
        val competencia = objMarca!!.esCompetencia.toString()
        val promedioVentas = objMarca!!.promedioVentas.toString()

        findViewById<EditText>(R.id.txt_id_marca_editar).setText(id)
        findViewById<EditText>(R.id.txt_nombre_marca_editar).setText(nombre)
        findViewById<Switch>(R.id.sw_competencia_editar).setChecked(competencia.toBoolean())
        findViewById<EditText>(R.id.txt_promedio_marca_editar).setText(promedioVentas)

        val botonEditarMarca = findViewById<Button>(R.id.btn_crear_marca_editar)
        botonEditarMarca.setOnClickListener {
            val nuevaMarca = hashMapOf<String, Any>(
                "id" to findViewById<EditText>(R.id.txt_id_marca_editar).text.toString().toInt(),
                "nombre" to findViewById<EditText>(R.id.txt_nombre_marca_editar).text.toString(),
                "esCompetencia" to findViewById<Switch>(R.id.sw_competencia_editar).isChecked,
                "promedioVentas" to findViewById<EditText>(R.id.txt_promedio_marca_editar).text.toString()
                    .toDouble()
            )
            val db = Firebase.firestore
            val referencia =
                db.collection("marca").document("${id}-${nombre}")
            referencia.update(nuevaMarca)
                .addOnSuccessListener {
                    Toast.makeText(this, "Se editó ${nombre}", Toast.LENGTH_SHORT).show()
                    irActividad(Marca::class.java)
                }
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}