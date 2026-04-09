package org.iesra.io

import java.io.File

class EscritorArchivo {

    fun escribir(ruta: String, contenido: String) {
        try {
            File(ruta).writeText(contenido)
        } catch (e: Exception) {
            throw IllegalArgumentException("Error al escribir el fichero: $ruta")
        }
    }
}