package com.example.motos

class BBaseDatosMemoria {
    companion object{
        val arregloMarca = arrayListOf<BMarca>()
        val arregloModelo = arrayListOf<BModelo>()

        init {
            arregloMarca.add(BMarca(1,"Yamaha", true, 1500.60))
            arregloMarca.add(BMarca(2,"Suzuki", false, 1600.60))
            arregloMarca.add(BMarca(3,"Ducatti", true, 1500.60))
        }
    }
}