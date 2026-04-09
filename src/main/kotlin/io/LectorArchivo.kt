package org.iesra.io


import java.io.File

class LectorArchivo {

    fun leerLineas(ruta: String) : List<String> {
        val archivo = File(ruta)

        if (!archivo.exists()) {
            throw IllegalArgumentException("El fichero no existe: $ruta")
        }
        return archivo.readLines()
    }
}