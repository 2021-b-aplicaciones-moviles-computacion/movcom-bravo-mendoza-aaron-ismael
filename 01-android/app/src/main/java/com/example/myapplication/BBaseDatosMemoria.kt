package com.example.myapplication

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()


        init {
            arregloBEntrenador
                .add(
                    BEntrenador("Adrian", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Vicente", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Vicente", "b@b.com")
                )
        }
    }
}