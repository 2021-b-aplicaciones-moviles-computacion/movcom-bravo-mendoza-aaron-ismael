package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario(contexto: Context?) : SQLiteOpenHelper(contexto, "moviles", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
                CREATE TABLE USUARIO (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        Log.i("bdd", "Creando la tabla usuario")
        db?.execSQL(scriptCrearTablaUsuario)
    }

    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String
    ): Boolean {
        //val baseDatosEscritura = this.writableDatabase
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion", descripcion)
        val resultadoEscritura: Long = baseDatosEscritura.insert(
            "USUARIO",
            null,
            valoresAGuardar
        )
        baseDatosEscritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun consultarUsuarioPorId(id: Int): EUsuarioBDD {
        val baseDatosLectura = readableDatabase
        //val scriptConsultaUsuario = "SELECT * FROM USUARIO WHERE ID = ${id}"
        val scriptConsultaUsuario = "SELECT * FROM USUARIO WHERE ID = ?"
        //val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaUsuario, null)
        val resultadoConsultaLectura =
            baseDatosLectura.rawQuery(scriptConsultaUsuario, arrayOf(id.toString()))
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = EUsuarioBDD(0, "", "")
        if (existeUsuario) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val descripcion = resultadoConsultaLectura.getString(2)
                if (id != null) {
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        //baseDatosLectura.close()
        return usuarioEncontrado
    }

    fun eliminarUsuarioFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion =
            conexionEscritura.delete("USUARIO", "id=?", arrayOf(id.toString()))
        //conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarUsuarioFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("descripcion", descripcion)
        val resultadoActualizacion = conexionEscritura.update(
            "USUARIO",
            valoresActualizar,
            "id=?",
            arrayOf(idActualizar.toString())
        )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}