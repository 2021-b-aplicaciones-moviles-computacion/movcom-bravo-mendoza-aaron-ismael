package com.example.supermaxi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorProducto(
    private val contexto: ListasFragment,
    private var listaProductos: List<BProductos>,
    private val recyclerView: RecyclerView
):RecyclerView.Adapter<FRecyclerViewAdaptadorProducto.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val precioTextView: TextView
        val cantidadTextView: TextView
        val accionButton: Button


        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            precioTextView = view.findViewById(R.id.tv_precio)
            cantidadTextView = view.findViewById(R.id.tv_cantidad)
            accionButton = view.findViewById(R.id.btn_añadir)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_vista, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.nombreTextView.text = producto.descripcion
        holder.precioTextView.text = "$ "+(producto.precio*producto.cantidad).toString()
        holder.cantidadTextView.text = producto.cantidad.toString()
        holder.accionButton.setOnClickListener {
            producto.cantidad++
            holder.cantidadTextView.text = producto.cantidad.toString()
            holder.precioTextView.text = "$ "+(producto.precio*producto.cantidad).toString()
            contexto.aumentarTotalLista()

        }
    }

    override fun getItemCount(): Int {

        return listaProductos.size
    }




}