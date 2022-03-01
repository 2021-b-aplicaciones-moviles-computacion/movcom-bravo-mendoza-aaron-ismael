package com.example.flowers

import android.os.Parcel
import android.os.Parcelable

class Detalle(
    val id: Int,
    val producto: String,
    val cantidad: Int,
    val totalDetalle: Double
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readDouble()
    ) {
    }

    override fun toString(): String {
        return "${id}: ${producto}: ${totalDetalle}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(producto)
        parcel.writeInt(cantidad)
        parcel.writeDouble(totalDetalle)
    }

    companion object CREATOR : Parcelable.Creator<Detalle> {
        override fun createFromParcel(parcel: Parcel): Detalle {
            return Detalle(parcel)
        }

        override fun newArray(size: Int): Array<Detalle?> {
            return arrayOfNulls(size)
        }
    }
}