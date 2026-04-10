package org.iesra.data

import org.iesra.io.LectorArchivo
import org.iesra.io.EscritorArchivo


class LogRepository {

    private val lector = LectorArchivo()
    private val escritor = EscritorArchivo()

    fun obtenerLineas(ruta: String) : List<String> {
        return lector.leerLineas(ruta)
    }

    fun guardarLineas(ruta: String, contenido: String) {
        escritor.escribir(ruta, contenido)
    }
}