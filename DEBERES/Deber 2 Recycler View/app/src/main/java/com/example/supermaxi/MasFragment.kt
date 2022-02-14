package com.example.supermaxi

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView


class MasFragment : Fragment(R.layout.fragment_mas) {
    val lista = arrayListOf(
        "Cadena de beneficios",
        "Encuentra un producto",
        "Turnos y pedidos Deli",
        "Video Recetas/Libros de cocina",
        "Ayúdenos a mejorar",
        "Sugerencias y quejas",
        "Información locales",
        "Maxiplus",
        "Acerca de la aplicación",
        "Scan & Go"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_mas, container, false)
        val lvMas = view.findViewById<ListView>(R.id.lv_mas)
        val adaptador = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, lista)
        lvMas.adapter = adaptador
        adaptador.notifyDataSetChanged()

        return view
    }

}