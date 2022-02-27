package com.example.motos

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class BModelo(

    val id: Int,
    var nombre: String?,
    var disponible: Boolean,
    var costo: Double,
    val idMarca: Int
) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "${id}: ${nombre}, ${disponible} ${costo} ${idMarca}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeByte(if (disponible) 1 else 0)
        parcel.writeDouble(costo)
        parcel.writeInt(idMarca)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BModelo> {
        override fun createFromParcel(parcel: Parcel): BModelo {
            return BModelo(parcel)
        }

        override fun newArray(size: Int): Array<BModelo?> {
            return arrayOfNulls(size)
        }
    }

}