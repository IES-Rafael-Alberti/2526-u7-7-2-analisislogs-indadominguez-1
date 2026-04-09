package org.iesra.parser

import org.iesra.model.NivelLog
import org.iesra.model.EntradaLog

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ParserLog {

    private val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    private val regex = Regex("""\[(.*?)\]\s+(INFO|WARNING|ERROR)\s+(.*)""")

    fun parsear(linea: String): EntradaLog? {
        val match = regex.matchEntire(linea) ?: return null

        return try {
            val fecha = LocalDateTime.parse(match.groupValues[1], formato)
            val nivel = NivelLog.desdeTexto(match.groupValues[2]) ?: return null
            val mensaje = match.groupValues[3]

            EntradaLog(fecha, nivel, mensaje)
        } catch (e: Exception) {
            null
        }
    }
}