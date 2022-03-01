package com.example.flowers

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class Pedido(
    val id: Int,
    val fechaPedido: String,
    val fechaEntrega: String,
    val totalAPagar: Double,
    val vehiculo: String,
    val cliente: String,
    val estado: String

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun toString(): String {
        return "${id} - ${cliente}\n${fechaPedido}\n${estado}"
        //${SimpleDateFormat("dd/MM/yyyy").format(fechaPedido)}
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(fechaPedido)
        parcel.writeString(fechaEntrega)
        parcel.writeDouble(totalAPagar)
        parcel.writeString(vehiculo)
        parcel.writeString(cliente)
        parcel.writeString(estado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pedido> {
        override fun createFromParcel(parcel: Parcel): Pedido {
            return Pedido(parcel)
        }

        override fun newArray(size: Int): Array<Pedido?> {
            return arrayOfNulls(size)
        }
    }

}