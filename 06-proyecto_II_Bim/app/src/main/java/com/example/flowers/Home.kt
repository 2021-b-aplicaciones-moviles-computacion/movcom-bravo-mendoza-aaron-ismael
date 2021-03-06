package com.example.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnHPedidos = findViewById<Button>(R.id.btn_h_pedidos)
        btnHPedidos.setOnClickListener {
            startActivity(Intent(this, PedidosLista::class.java))
        }

        val btnHSalir = findViewById<Button>(R.id.btn_h_salir)
        btnHSalir.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, Login::class.java))
        }

        val btnHCliente = findViewById<Button>(R.id.btn_h_clientes)
        btnHCliente.setOnClickListener {
            startActivity(Intent(this, ClienteForm::class.java))
        }

        val btnHProducto = findViewById<Button>(R.id.btn_h_productos)
        btnHProducto.setOnClickListener {
            startActivity(Intent(this, ProductoForm::class.java))
        }

        val btnHVehiculo = findViewById<Button>(R.id.btn_h_vehiculos)
        btnHVehiculo.setOnClickListener {
            startActivity(Intent(this, VehiculoForm::class.java))
        }


    }
}