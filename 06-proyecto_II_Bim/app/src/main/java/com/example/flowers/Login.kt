package com.example.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val txtCorreo = findViewById<EditText>(R.id.txt_email)
        val txtContraseña = findViewById<EditText>(R.id.txt_passwd)
        startActivity(Intent(this, Home::class.java))
//        btnLogin.setOnClickListener {
//            if (txtCorreo.text.toString() != "" && txtContraseña.text.toString() != "") {
//                FirebaseAuth.getInstance().signInWithEmailAndPassword(
//                    txtCorreo.text.toString(),
//                    txtContraseña.text.toString()
//                ).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        startActivity(Intent(this, Home::class.java))
//                    } else {
//                        mostrarAlertra("Error en la autenticación")
//                        txtCorreo.setText("")
//                        txtContraseña.setText("")
//                    }
//                }
//            } else {
//                mostrarAlertra("Verifique su correo y contraseña")
//            }
//        }
    }

    fun mostrarAlertra(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}