package com.example.supermaxi

import android.os.Parcel
import android.os.Parcelable

class BProductos(
    val nombre: String?,
    val descripcion: String?,
    val precio: Double,
    var cantidad: Int
):Parcelable {
    override fun toString(): String{
        return "${nombre}: $ ${precio}"
    }


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }
    fun añadirItem(){
        cantidad++
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeDouble(precio)
        parcel.writeInt(cantidad)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BProductos> {
        override fun createFromParcel(parcel: Parcel): BProductos {
            return BProductos(parcel)
        }

        override fun newArray(size: Int): Array<BProductos?> {
            return arrayOfNulls(size)
        }
    }



}