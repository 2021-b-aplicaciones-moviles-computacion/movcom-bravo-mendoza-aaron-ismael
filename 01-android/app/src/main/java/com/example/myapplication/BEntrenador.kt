package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

class BEntrenador(
    val nombre: String?,
    val descripcion: String?,
// val numeros: Unit = null,
):Parcelable {

    override fun toString(): String{
        return "${nombre} ${descripcion}"
    }
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }
    
    override fun writeToParcel(p0: Parcel, flags: Int) {
        p0.writeString(nombre)
        p0.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }
}