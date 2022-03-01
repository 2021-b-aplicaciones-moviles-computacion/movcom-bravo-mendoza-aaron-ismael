package com.example.flowers

import android.os.Parcel
import android.os.Parcelable

class Vehiculo(
    val placa: String,
    val marca: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun toString(): String {
        return "${placa}: ${marca}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(placa)
        parcel.writeString(marca)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vehiculo> {
        override fun createFromParcel(parcel: Parcel): Vehiculo {
            return Vehiculo(parcel)
        }

        override fun newArray(size: Int): Array<Vehiculo?> {
            return arrayOfNulls(size)
        }
    }
}