import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.cos

fun main() {

    val scanner = Scanner(System.`in`)
    MarcaMoto.leerArchivoMarca()
    ModeloMoto.leerArchivoModelo()

    do {
        print(
            "Bienvenidos\n" +
                    "Seleccione la entidad\n" +
                    "1: Marca Motocicleta\n" +
                    "2: Modelo Motocicleta\n" +
                    "0: Salir\n" +
                    "Opcion: "
        )
        var opcionMenu = scanner.nextLine().toInt()
        when (opcionMenu) {
            1 -> {
                MarcaMoto.mostrarMarca(MarcaMoto.listaMarcaMotos)
                do {
                    print(
                        "Seleccione la operación a realizar\n" +
                                "1: Ingresar Marca\n" +
                                "2: Consultar Marca\n" +
                                "3: Actualizar Marca\n" +
                                "4: Eliminar Marca\n" +
                                "0: Salir\n" +
                                "Opcion: "
                    )
                    var opcionMarca = scanner.nextLine().toInt()
                    when (opcionMarca) {
                        1 -> {
                            println("Ingrese el id de la Marca")
                            var id = scanner.nextLine().toInt()
                            println("Ingrese el nombre de la Marca")
                            var nombre = scanner.nextLine()
                            var fechaCreacion = Date()
                            var esCompetencia = true
                            do {
                                print(
                                    "Seleccione si es de competencia o no\n" +
                                            "1: Si\n" +
                                            "2: No\n" +
                                            "Opcion: "
                                )
                                var opcionCompetencia = scanner.nextLine().toInt()
                                if (opcionCompetencia == 1) {
                                    esCompetencia = true
                                } else if (opcionCompetencia == 2) {
                                    esCompetencia = false
                                } else {
                                    println("Seleccione la opción correcta")
                                }
                            } while (opcionCompetencia != 1 && opcionCompetencia != 2)
                            println("Ingrese el promedio de ventas de la Marca")
                            var promedioVentas = scanner.nextLine().toDouble()
                            var nuevaMarcaMoto = MarcaMoto(id, nombre, fechaCreacion, esCompetencia, promedioVentas)
                            MarcaMoto.insertarMarca(nuevaMarcaMoto)
                            break
                        }
                        2 -> {
                            do {
                                print(
                                    "1: Consultar por id\n" +
                                            "2: Consultar por nombre\n" +
                                            "3: Consultar por fecha\n" +
                                            "4: Consultar Marcas de competencia\n" +
                                            "5: Consultar por promedio de ventas\n" +
                                            "0: Salir\n" +
                                            "Opcion: "
                                )
                                var opcionConsulta = scanner.nextLine().toInt()
                                when (opcionConsulta) {
                                    1 -> {
                                        print("Ingrese el id: ")
                                        var id = scanner.nextLine().toInt()
                                        MarcaMoto.mostrarMarca(MarcaMoto.buscarMarcaId(id))
                                        break
                                    }
                                    2 -> {
                                        print("Ingrese el nombre: ")
                                        var nombre = scanner.nextLine()
                                        MarcaMoto.mostrarMarca(MarcaMoto.buscarMarcaNombre(nombre))
                                        break
                                    }
                                    3 -> {
                                        print("Ingrese la fecha: ")
                                        var fecha = Date()
                                        MarcaMoto.mostrarMarca(MarcaMoto.buscarMarcaFecha(fecha))
                                        break
                                    }
                                    4 -> {

                                        do {
                                            print(
                                                "Escoja la opcion: \n" +
                                                        "1: Si\n" +
                                                        "2: No\n" +
                                                        "0: Salir\n" +
                                                        "Opcion: "
                                            )
                                            var consultaComptencia = scanner.nextLine().toInt()
                                            if (consultaComptencia == 1) {
                                                MarcaMoto.mostrarMarca(MarcaMoto.buscarMarcaCompetencia(true))
                                                break
                                            } else if (consultaComptencia == 2) {
                                                MarcaMoto.mostrarMarca(MarcaMoto.buscarMarcaCompetencia(false))
                                                break
                                            } else {
                                                println("Escoja la opción correcta")
                                            }
                                        } while (consultaComptencia != 0)
                                        break
                                    }
                                    5 -> {
                                        print("Ingrese el promedio de ventas: ")
                                        var promedioVentas = scanner.nextLine().toDouble()
                                        MarcaMoto.mostrarMarca(MarcaMoto.buscarMarcaPromedioVentas(promedioVentas))
                                        break
                                    }
                                    0 -> {

                                    }
                                    else -> {
                                        println("Ingrese la opcion correcta")
                                    }
                                }
                            } while (opcionConsulta != 0)
                        }
                        3 -> {
                            do {
                                print(
                                    "1: Actualizar nombre\n" +
                                            "2: Actualizar fecha\n" +
                                            "3: Actualizar si es marca de competencia\n" +
                                            "4: Actualizar promedio de ventas\n" +
                                            "0: Salir\n" +
                                            "Opcion: "
                                )
                                var opcionActualizar = scanner.nextLine().toInt()
                                when (opcionActualizar) {
                                    1 -> {
                                        print("Ingrese el id de la marca a actualizar: ")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el nombre: ")
                                        var nombre = scanner.nextLine().toString()
                                        MarcaMoto.actualizarMarcaNombre(id, nombre)
                                        break
                                    }
                                    2 -> {
                                        print("Ingrese el id de la marca a actualizar: ")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese la fecha: ")
                                        var fecha = Date()
                                        MarcaMoto.actualizarMarcaFecha(id, fecha)
                                        break
                                    }
                                    3 -> {
                                        print("Ingrese el id de la marca a actualizar: ")
                                        var id = scanner.nextLine().toInt()
                                        do {
                                            print(
                                                "1: Si\n" +
                                                        "2: No\n" +
                                                        "0: Salir\n" +
                                                        "Opcion: "
                                            )
                                            var opcionActualizarCompetencia = scanner.nextLine().toInt()
                                            if (opcionActualizarCompetencia == 1) {
                                                MarcaMoto.actualizarMarcaCompetencia(id, true)
                                                break
                                            } else if (opcionActualizarCompetencia == 2) {
                                                MarcaMoto.actualizarMarcaCompetencia(id, false)
                                                break
                                            } else {
                                                println("Ingrese la opcion correcta")
                                            }
                                        } while (opcionActualizarCompetencia != 0)
                                        break
                                    }
                                    4 -> {
                                        print("Ingrese el id de la marca a actualizar: ")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el promedio de ventas: ")
                                        var promedioVentas = scanner.nextLine().toDouble()
                                        MarcaMoto.actualizarMarcaPromedioVentas(id, promedioVentas)
                                        break
                                    }
                                    0 -> {

                                    }
                                    else -> {
                                        println("Ingrese la opcion correcta")
                                    }
                                }
                            } while (opcionActualizar != 0)
                        }
                        4 -> {
                            println("Ingrese el id de la marca a eliminar")
                            var id = scanner.nextLine().toInt()
                            MarcaMoto.eliminarMarca(id)
                            break
                        }
                        0 -> {

                        }
                        else -> println("Ingrese la opcion correcta")
                    }

                } while (opcionMarca != 0)
            }
            2 -> {
                ModeloMoto.mostrarModelo(ModeloMoto.listaModeloMotos)
                do {
                    print(
                        "Selecciona la operación a realizar\n" +
                                "1: Ingresar Modelo\n" +
                                "2: Consultar Modelo\n" +
                                "3: Actualizar Modelo\n" +
                                "4: Eliminar Modelo\n" +
                                "0: Salir\n" +
                                "Opcion: "
                    )
                    var opcionModelo = scanner.nextLine().toInt()
                    when (opcionModelo) {
                        1 -> {
                            println("Ingrese el id del modelo")
                            var id = scanner.nextLine().toInt()
                            println("Ingrese el nombre del modelo")
                            var nombre = scanner.nextLine()
                            var fechaLanzamiento = Date()
                            var estaDisponible = true
                            do {
                                print(
                                    "Seleccione si esta disponible o no\n" +
                                            "1: Si\n" +
                                            "2: No\n" +
                                            "Opcion: "
                                )
                                var opcionDisponible = scanner.nextLine().toInt()
                                if (opcionDisponible == 1) {
                                    estaDisponible = true
                                } else if (opcionDisponible == 2) {
                                    estaDisponible = false
                                } else {
                                    println("Seleccione la opción correcta")
                                }
                            } while (opcionDisponible != 1 && opcionDisponible != 2)
                            println("Inrese el costo de la motocicleta")
                            var costo = scanner.nextLine().toDouble()
                            var idMarca: Int
                            do {
                                println("Seleccione el id de la Marca de la Moto")
                                MarcaMoto.listaMarcaMotos.forEach { println("${it.id}: " + "${it.nombre}") }
                                idMarca = scanner.nextLine().toInt()
                                var bandera = false
                                MarcaMoto.listaMarcaMotos.forEach {
                                    if (it.id == idMarca) {
                                        bandera = true
                                    }
                                }
                            } while (!bandera)
                            var nuevoModeloMoto = ModeloMoto(id, nombre, Date(), estaDisponible, costo, idMarca)
                            ModeloMoto.insertarModelo(nuevoModeloMoto)
                            break
                        }
                        2 -> {
                            do {
                                print(
                                    "1: Consultar por id\n" +
                                            "2: Consultar por nombre\n" +
                                            "3: Consultar por fecha\n" +
                                            "4: Consultar por disponibilidad\n" +
                                            "5: Consultar por costo\n" +
                                            "6: Consultar por id de Marca\n" +
                                            "0: Salir" +
                                            "Opcion: "
                                )
                                var opcionConsulta = scanner.nextLine().toInt()
                                when (opcionConsulta) {
                                    1 -> {
                                        print("Ingrese el id: ")
                                        var id = scanner.nextLine().toInt()
                                        ModeloMoto.mostrarModelo(ModeloMoto.buscarModeloId(id))
                                        break
                                    }
                                    2 -> {
                                        print("Ingrese el nombre: ")
                                        var nombre = scanner.nextLine()
                                        ModeloMoto.mostrarModelo(ModeloMoto.buscarModeloNombre(nombre))
                                        break
                                    }
                                    3 -> {
                                        print("Ingrese la fecha: ")
                                        var fecha = Date()
                                        ModeloMoto.mostrarModelo(ModeloMoto.buscarModeloFechaLanzamiento(fecha))
                                        break
                                    }
                                    4 -> {
                                        do {
                                            print(
                                                "Escoja la opcion: \n" +
                                                        "1: Si\n" +
                                                        "2: No\n" +
                                                        "0: Salir\n" +
                                                        "Opcion: "
                                            )
                                            var consultaDisponibilidad = scanner.nextLine().toInt()
                                            if (consultaDisponibilidad == 1) {
                                                ModeloMoto.mostrarModelo(ModeloMoto.buscarModeloDisponible(true))
                                                break
                                            } else if (consultaDisponibilidad == 2) {
                                                ModeloMoto.mostrarModelo(ModeloMoto.buscarModeloDisponible(false))
                                                break
                                            } else {
                                                println("Escoja la opción correcta")
                                            }
                                        } while (consultaDisponibilidad != 0)
                                        break
                                    }
                                    5 -> {
                                        print("Ingrese el costo de la moto: ")
                                        var costo = scanner.nextLine().toDouble()
                                        ModeloMoto.mostrarModelo(ModeloMoto.buscarModeloCosto(costo))
                                        break
                                    }
                                    6 -> {
                                        var idMarca: Int
                                        do {
                                            println("Seleccione el id de la Marca de la Moto")
                                            MarcaMoto.listaMarcaMotos.forEach { println("${it.id}: " + "${it.nombre}") }
                                            idMarca = scanner.nextLine().toInt()
                                            var bandera = false
                                            MarcaMoto.listaMarcaMotos.forEach {
                                                if (it.id == idMarca) {
                                                    bandera = true
                                                }
                                            }
                                        } while (!bandera)
                                        ModeloMoto.mostrarModelo(ModeloMoto.buscarModeloMarca(idMarca))
                                        break
                                    }
                                    0 -> {

                                    }
                                    else -> {
                                        println("Escoja la opción correcta")
                                    }
                                }
                            } while (opcionConsulta != 0)
                        }
                        3 -> {
                            do {
                                print(
                                    "1: Actualizar nombre\n" +
                                            "2: Actualizar fecha de lanzamiento\n" +
                                            "3: Actualizar si está diponible o no\n" +
                                            "4: Actualizar costo\n" +
                                            "5: Actualizar id Marca\n" +
                                            "0: Salir\n" +
                                            "Opcion: "
                                )
                                var opcionActualizar = scanner.nextLine().toInt()
                                when(opcionActualizar){
                                    1 -> {
                                        print("Ingrese el id del modelo a actualiazr")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el nombre: ")
                                        var nombre = scanner.nextLine().toString()
                                        ModeloMoto.actualizarModeloNombre(id, nombre)
                                        break
                                    }
                                    2 -> {
                                        print("Ingrese el id del modelo a actualiazr")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese la fecha: ")
                                        var fecha = Date()
                                        ModeloMoto.actualizarModeloFecha(id, fecha)
                                        break
                                    }
                                    3 -> {
                                        print("Ingrese el id del modelo a actualiazr")
                                        var id = scanner.nextLine().toInt()
                                        do{
                                            print("1: Si\n" +
                                                    "2: No\n" +
                                                    "0: Salir\n" +
                                                    "Opcion: ")
                                            var opcionActualizarDisponbilidad = scanner.nextLine().toInt()
                                            if(opcionActualizarDisponbilidad == 1){
                                                ModeloMoto.actualizarModeloDisponible(id, true)
                                                break
                                            }
                                            else if(opcionActualizarDisponbilidad == 2){
                                                ModeloMoto.actualizarModeloDisponible(id, false)
                                                break
                                            }
                                            else{
                                                println("Ingrese la opcion correcta")
                                            }
                                        }while (opcionActualizarDisponbilidad != 0)
                                    }
                                    4 -> {
                                        print("Ingrese el id del modelo a actualiazr")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el costo: ")
                                        var costo = scanner.nextLine().toDouble()
                                        ModeloMoto.actualizarModeloCosto(id, costo)
                                        break
                                    }
                                    5 -> {
                                        print("Ingrese el id del modelo a actualiazr")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el id de la marca: ")
                                        var idMarca = scanner.nextLine().toInt()
                                        ModeloMoto.actualizarModeloIdMarca(id, idMarca)
                                        break
                                    }
                                    0 -> {

                                    }
                                    else -> {
                                        println("Ingrese la opcion correcta")
                                    }
                                }
                            }while(opcionActualizar != 0)
                        }
                    }
                } while (opcionModelo != 0)
            }
            4 -> {
                println("Ingrese el id del modelo a eliminar")
                var id = scanner.nextLine().toInt()
                ModeloMoto.eliminarModelo(id)
            }
            0 -> {

            }
            else -> {
                println("Escoja la opción correcta")
            }
        }
    } while (opcionMenu != 0)
}

class MarcaMoto(
    id: Int,
    nombre: String,
    fechaCreacion: Date,
    esCompetencia: Boolean,
    promedioVentas: Double
) {
    var id: Int = id
    var nombre: String = nombre
    var fechaCreacion: Date = fechaCreacion
    var esCompetencia: Boolean = esCompetencia
    var promedioVentas: Double = promedioVentas

    init {
        //println("Inicializarse")
    }

    companion object {
        var listaMarcaMotos = ArrayList<MarcaMoto>()

        fun insertarMarca(nuevaMarca: MarcaMoto) {
            listaMarcaMotos.add(nuevaMarca)
            actualizarArchivoMarca()
            mostrarMarca(listaMarcaMotos)

        }

        fun buscarMarcaId(id: Int): ArrayList<MarcaMoto> {
            return listaMarcaMotos.filter { it.id == id } as ArrayList<MarcaMoto>
        }

        fun buscarMarcaNombre(nombre: String): ArrayList<MarcaMoto> {
            return listaMarcaMotos.filter { it.nombre == nombre } as ArrayList<MarcaMoto>
        }

        fun buscarMarcaFecha(fecha: Date): ArrayList<MarcaMoto> {
            return listaMarcaMotos.filter { it.fechaCreacion == fecha } as ArrayList<MarcaMoto>
        }

        fun buscarMarcaCompetencia(competencia: Boolean): ArrayList<MarcaMoto> {
            return listaMarcaMotos.filter { it.esCompetencia == competencia } as ArrayList<MarcaMoto>
        }

        fun buscarMarcaPromedioVentas(promedioVentas: Double): ArrayList<MarcaMoto> {
            return listaMarcaMotos.filter { it.promedioVentas == promedioVentas } as ArrayList<MarcaMoto>
        }

        fun actualizarMarcaNombre(id: Int, nombre: String) {
            listaMarcaMotos.filter { it.id == id }.map { it.nombre = nombre }
            actualizarArchivoMarca()
            mostrarMarca(listaMarcaMotos)
        }

        fun actualizarMarcaFecha(id: Int, fecha: Date) {
            listaMarcaMotos.filter { it.id == id }.map { it.fechaCreacion = fecha }
            actualizarArchivoMarca()
            mostrarMarca(listaMarcaMotos)
        }

        fun actualizarMarcaCompetencia(id: Int, competencia: Boolean) {
            listaMarcaMotos.filter { it.id == id }.map { it.esCompetencia = competencia }
            actualizarArchivoMarca()
            mostrarMarca(listaMarcaMotos)
        }

        fun actualizarMarcaPromedioVentas(id: Int, promedioVentas: Double) {
            listaMarcaMotos.filter { it.id == id }.map { it.promedioVentas = promedioVentas }
            actualizarArchivoMarca()
            mostrarMarca(listaMarcaMotos)
            mostrarMarca(listaMarcaMotos)
        }


        fun eliminarMarca(id: Int) {
            listaMarcaMotos = listaMarcaMotos.filter { it.id != id } as ArrayList<MarcaMoto>
            actualizarArchivoMarca()
            mostrarMarca(listaMarcaMotos)
        }

        fun mostrarMarca(ArregloMotos: ArrayList<MarcaMoto>) {

            println("Id,Nombre,Fecha de Lanzamiento,MotoGP,Promedio de Ventas")
            ArregloMotos.forEach { motoActual: MarcaMoto ->
                println(
                    "${motoActual.id}," +
                            "${motoActual.nombre}," +
                            "${motoActual.fechaCreacion}," +
                            "${motoActual.esCompetencia}," +
                            "${motoActual.promedioVentas}"
                )
            }
            println("")
        }

        fun actualizarArchivoMarca() {
            val ruta = "src/marca.csv"
            try {
                FileWriter(ruta, false).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            out.print("ID,Nombre,Fecha de Lanzamiento, MotoGP, Promedio de Ventas\n")
                        }
                    }
                }
            } catch (e: IOException) {
                //exception handling left as an exercise for the reader
            }
            try {
                FileWriter(ruta, true).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            //out.print(listaMarcaMotos.forEach { motoActual: MarcaMoto -> "${motoActual.id}" })
                            listaMarcaMotos.forEach { motoActual: MarcaMoto -> out.print("${motoActual.id},${motoActual.nombre},${motoActual.fechaCreacion},${motoActual.esCompetencia},${motoActual.promedioVentas}\n") }
                        }
                    }
                }
            } catch (e: IOException) {
                //exception handling left as an exercise for the reader
            }
        }

        fun leerArchivoMarca() {

            var miLector = Scanner(File("src/marca.csv"))
            var cabecera = miLector.hasNextLine()
            var fila = miLector.nextLine()
            var datos = StringTokenizer(fila, ",")
            while (datos.hasMoreTokens()) {
                datos.nextToken()
            }
            while (miLector.hasNextLine()) {
                fila = miLector.nextLine()
                datos = StringTokenizer(fila, ",")
                while (datos.hasMoreTokens()) {
                    var id = datos.nextToken().toInt()
                    var nombre = datos.nextToken()
                    var fecha = datos.nextToken()
                    var competencia = datos.nextToken().toBoolean()
                    var promedio = datos.nextToken().toDouble()
                    var miMarcaMoto = MarcaMoto(id, nombre, Date(), competencia, promedio)
                    listaMarcaMotos.add(miMarcaMoto)

                }
                println("")
            }
        }
    }
}

class ModeloMoto(
    id: Int,
    nombre: String,
    fechaLanzamiento: Date,
    disponible: Boolean,
    costo: Double,
    idMarca: Int
) {
    var id = id
    var nombre = nombre
    var fechaLanzamiento = fechaLanzamiento
    var disponible = disponible
    var costo = costo
    var idMarca = idMarca

    init {
        //println("Inicializarse")
    }

    companion object {
        var listaModeloMotos = ArrayList<ModeloMoto>()

        fun insertarModelo(nuevoModelo: ModeloMoto) {
            listaModeloMotos.add(nuevoModelo)
            actualizarArchivoModelo()
            mostrarModelo(listaModeloMotos)
        }

        fun buscarModeloId(id: Int): ArrayList<ModeloMoto> {
            return listaModeloMotos.filter { it.id == id } as ArrayList<ModeloMoto>
        }

        fun buscarModeloNombre(nombre: String): ArrayList<ModeloMoto> {
            return listaModeloMotos.filter { it.nombre == nombre } as ArrayList<ModeloMoto>
        }

        fun buscarModeloFechaLanzamiento(fecha: Date): ArrayList<ModeloMoto> {
            return listaModeloMotos.filter { it.fechaLanzamiento == fecha } as ArrayList<ModeloMoto>
        }

        fun buscarModeloDisponible(disponible: Boolean): ArrayList<ModeloMoto> {
            return listaModeloMotos.filter { it.disponible == disponible } as ArrayList<ModeloMoto>
        }

        fun buscarModeloCosto(costo: Double): ArrayList<ModeloMoto> {
            return listaModeloMotos.filter { it.costo == costo } as ArrayList<ModeloMoto>
        }

        fun buscarModeloMarca(idMarca: Int): ArrayList<ModeloMoto> {
            return listaModeloMotos.filter { it.idMarca == idMarca } as ArrayList<ModeloMoto>
        }

        fun actualizarModeloNombre(id: Int, nombre: String) {
            listaModeloMotos.filter { it.id == id }.map { it.nombre = nombre }
            actualizarArchivoModelo()
            mostrarModelo(listaModeloMotos)
        }

        fun actualizarModeloFecha(id: Int, fecha: Date) {
            listaModeloMotos.filter { it.id == id }.map { it.fechaLanzamiento = fecha }
            actualizarArchivoModelo()
            mostrarModelo(listaModeloMotos)
        }

        fun actualizarModeloDisponible(id: Int, disponible: Boolean) {
            listaModeloMotos.filter { it.id == id }.map { it.disponible = disponible }
            actualizarArchivoModelo()
            mostrarModelo(listaModeloMotos)
        }

        fun actualizarModeloCosto(id: Int, costo: Double) {
            listaModeloMotos.filter { it.id == id }.map { it.costo = costo }
            actualizarArchivoModelo()
            mostrarModelo(listaModeloMotos)
        }

        fun actualizarModeloIdMarca(id: Int, idMarca: Int) {
            listaModeloMotos.filter { it.id == id }.map { it.idMarca = idMarca }
            actualizarArchivoModelo()
            mostrarModelo(listaModeloMotos)
        }

        fun eliminarModelo(id: Int) {
            listaModeloMotos = listaModeloMotos.filter { it.id != id } as ArrayList<ModeloMoto>
            actualizarArchivoModelo()
            mostrarModelo(listaModeloMotos)
        }

        fun mostrarModelo(arregloModelos: ArrayList<ModeloMoto>) {
            println("Id,Nombre,Fecha de Lanzamiento, Disponibilidad, Costo, Id Marca")
            arregloModelos.forEach { modeloActual: ModeloMoto ->
                println(
                    "${modeloActual.id}," +
                            "${modeloActual.nombre}," +
                            "${modeloActual.fechaLanzamiento}," +
                            "${modeloActual.disponible}," +
                            "${modeloActual.costo}," +
                            "${modeloActual.idMarca}"
                )
            }
            println("")
        }

        fun actualizarArchivoModelo() {
            val ruta = "src/modelo.csv"
            try {
                FileWriter(ruta, false).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            out.print("ID,Nombre,Fecha de Lanzamiento,Disponibilidad,Costo,idMarca\n")
                        }
                    }
                }
            } catch (e: IOException) {
                //exception handling left as an exercise for the reader
            }
            try {
                FileWriter(ruta, true).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            listaModeloMotos.forEach { motoActual: ModeloMoto ->
                                out.print(
                                    "${motoActual.id},${motoActual.nombre},${motoActual.fechaLanzamiento}," +
                                            "${motoActual.disponible},${motoActual.costo},${motoActual.idMarca}\n"
                                )
                            }
                        }
                    }
                }
            } catch (e: IOException) {
                //exception handling left as an exercise for the reader
            }

        }

        fun leerArchivoModelo() {
            var miLector = Scanner(File("src/modelo.csv"))
            var cabecera = miLector.hasNextLine()
            var fila = miLector.nextLine()
            var datos = StringTokenizer(fila, ",")
            while (datos.hasMoreTokens()) {
                datos.nextToken()
            }
            while (miLector.hasNextLine()) {
                fila = miLector.nextLine()
                datos = StringTokenizer(fila, ",")
                while (datos.hasMoreTokens()) {
                    var id = datos.nextToken().toInt()
                    var nombre = datos.nextToken()
                    var fecha = datos.nextToken()
                    var disponible = datos.nextToken().toBoolean()
                    var costo = datos.nextToken().toDouble()
                    var idMarca = datos.nextToken().toInt()
                    var miModeloMoto = ModeloMoto(id, nombre, Date(), disponible, costo, idMarca)
                    listaModeloMotos.add(miModeloMoto)

                }
                println("")
            }
        }
    }
}
