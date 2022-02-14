package com.example.supermaxi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ListasFragment : Fragment(R.layout.fragment_listas) {
    var totalLista = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val listaProductos = arrayListOf<BProductos>()
        listaProductos.add(BProductos("Gaseosa", "Cocacola 500ml", 3.00, 0))
        listaProductos.add(BProductos("Arroz", "Arroz Extra 2kg", 6.00, 0))
        listaProductos.add(BProductos("Atun", "Atun Vancamps 300g", 2.50, 0))
        listaProductos.add(BProductos("Gelatina", "Gelatina Toni 250g", 10.00, 0))
        listaProductos.add(BProductos("Cereal", "Cereal Zucaritas 500g", 15.00, 0))
        listaProductos.add(BProductos("Sardina", "Sardina Real 250g", 0.50, 0))
        listaProductos.add(BProductos("Barra Energética", "Barra Yapa 200g", 2.00, 0))
        listaProductos.add(BProductos("Azucar", "Azucar valdez 2kg", 7.00, 0))
        listaProductos.add(BProductos("Panela", "Panela Valdez 1kg", 6.00, 0))
        listaProductos.add(BProductos("Palmito", "Pamlito La Moderna 250g", 3.00, 0))
        listaProductos.add(BProductos("Jabon", "Jabon Lava Todo 200g", 4.60, 0))
        listaProductos.add(BProductos("Detergente", "Detergente Deja 5kg", 7.00, 0))
        listaProductos.add(BProductos("Escoba", "Escoba Original plastico", 8.50, 0))
        val view = inflater.inflate(R.layout.fragment_listas, container, false)
        val recyclerViewProductos = view.findViewById<RecyclerView>(R.id.rv_productos)
        inicializarRecyclerView(listaProductos, this, recyclerViewProductos)
        val tv_totalArticulos = view.findViewById<TextView>(R.id.tv_total_articulos)
        tv_totalArticulos.text = totalLista.toString()





        return view

    }


    fun inicializarRecyclerView(
        lista: List<BProductos>,
        fragmento: ListasFragment,
        recyclerView: RecyclerView
    ) {
        val adaptador = FRecyclerViewAdaptadorProducto(fragmento, lista, recyclerView)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(fragmento.context)
        //adaptador.notifyDataSetChanged()
    }

    fun aumentarTotalLista() {
        totalLista = totalLista + 1
    }


}