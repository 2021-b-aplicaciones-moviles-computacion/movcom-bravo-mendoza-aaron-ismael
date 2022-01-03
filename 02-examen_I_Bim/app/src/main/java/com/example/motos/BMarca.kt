package com.example.motos

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class BMarca(
    val id: Int,
    var nombre: String?,
    var esCompetencia: Boolean,
    var promedioVentas: Double
): Parcelable{

    override fun toString(): String{
        return "${id}: ${nombre} ${esCompetencia} ${promedioVentas}"
    }
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeByte(if (esCompetencia) 1 else 0)
        parcel.writeDouble(promedioVentas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BMarca> {
        override fun createFromParcel(parcel: Parcel): BMarca {
            return BMarca(parcel)
        }

        override fun newArray(size: Int): Array<BMarca?> {
            return arrayOfNulls(size)
        }
    }

}