package com.example.supermaxi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

import java.util.*


class TarjetaFragment : Fragment(R.layout.fragment_tarjeta) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tarjeta, container, false)
        val tv1 = view.findViewById<TextView>(R.id.textView)
        val tv2 = view.findViewById<TextView>(R.id.tv_codigo_acceso)
        val tv3 = view.findViewById<TextView>(R.id.tv_msg_codigo)
        val botonGenerar = view.findViewById<Button>(R.id.btn_generar)
        botonGenerar.setOnClickListener {
            val accesCode = Random().nextInt(99999)
            tv1.isVisible = false
            tv2.text = accesCode.toString()
            tv2.isVisible = true
            tv3.isVisible = true
        }
        return view
    }

}