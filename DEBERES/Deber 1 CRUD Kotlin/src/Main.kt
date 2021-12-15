import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    val scanner = Scanner(System.`in`)
    MarcaMoto.leerArchivoMarca()

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
                                        do{
                                            print("1: Si\n" +
                                                    "2: No\n" +
                                                    "0: Salir\n" +
                                                    "Opcion: ")
                                            var opcionActualizarCompetencia = scanner.nextLine().toInt()
                                            if(opcionActualizarCompetencia == 1){
                                                MarcaMoto.actualizarMarcaCompetencia(id, true)
                                                break
                                            }
                                            else if(opcionActualizarCompetencia == 2){
                                                MarcaMoto.actualizarMarcaCompetencia(id, false)
                                                break
                                            }
                                            else{
                                                println("Ingrese la opcion correcta")
                                            }
                                        }while(opcionActualizarCompetencia != 0)
                                        break
                                    }
                                    4 -> {
                                        print("Ingrese el id de la marca a actualizar: ")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el promedio de ventas: ")
                                        var promedioVentas = scanner.nextLine().toDouble()
                                        MarcaMoto.actualizarMarcaPromedioVentas(id, promedioVentas)
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
                        }
                        0 -> {

                        }
                        else -> println("Ingrese la opcion correcta")
                    }

                } while (opcionMarca != 0)
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

        fun leerArchivoMarca(){

            var miLector = Scanner(File("src/marca.csv"))
            var cabecera = miLector.hasNextLine()
            var fila = miLector.nextLine()
            var datos = StringTokenizer(fila, ",")
            while(datos.hasMoreTokens()){
                datos.nextToken()
            }
            while (miLector.hasNextLine()){
                fila = miLector.nextLine()
                datos = StringTokenizer(fila, ",")
                while(datos.hasMoreTokens()){
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
    cilindraje: Int,
    produccion: Boolean,
    costo: Double,
    idMarca: Int
) {
    var id = id
    var nombre = nombre
    var fechaLanzamiento = fechaLanzamiento
    var cilindraje = cilindraje
    var produccion = produccion
    var costo = costo
    var idMarca = idMarca

    init {
        //println("Inicializarse")
    }
}
