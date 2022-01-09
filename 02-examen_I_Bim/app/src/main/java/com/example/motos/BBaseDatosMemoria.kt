package com.example.motos

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

class BBaseDatosMemoria {

    companion object {

        val arregloMarca = arrayListOf<BMarca>()
        val arregloModelo = arrayListOf<BModelo>()

        init {
            arregloMarca.add(BMarca(1, "Yamaha", true, 1500.60))
            arregloMarca.add(BMarca(2, "Suzuki", false, 1600.60))
            arregloMarca.add(BMarca(3, "Ducatti", true, 1500.60))
            arregloModelo.add(BModelo(1, "fz25", true, 1000.0, 1))
            arregloModelo.add(BModelo(2, "fz26", true, 1000.1, 1))
            arregloModelo.add(BModelo(3, "fz27", true, 1000.2, 1))
            arregloModelo.add(BModelo(4, "fz28", true, 1000.3, 1))
            arregloModelo.add(BModelo(5, "fz28", true, 1000.3, 2))
        }
    }
}