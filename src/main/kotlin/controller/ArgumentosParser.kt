package org.iesra.controller

import org.iesra.model.NivelLog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ArgumentosParser {

    private val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun parsear(args: Array<String>): Configuracion {

        var input: String? = null
        var from: LocalDateTime? = null
        var to: LocalDateTime? = null
        var niveles: Set<NivelLog>? = null
        var stdout = false
        var output: String? = null
        var ignoreInvalid = false

        var i = 0
        while (i < args.size) {
            when (args[i]) {

                "-i", "--input" -> {
                    input = args.getOrNull(i + 1)
                    i++
                }

                "-f", "--from" -> {
                    from = LocalDateTime.parse(args[i + 1], formato)
                    i++
                }

                "-t", "--to" -> {
                    to = LocalDateTime.parse(args[i + 1], formato)
                    i++
                }

                "-l", "--level" -> {
                    niveles = args[i + 1]
                        .split(",")
                        .mapNotNull { NivelLog.desdeTexto(it.trim()) }
                        .toSet()
                    i++
                }

                "-p", "--stdout" -> stdout = true

                "-o", "--output" -> {
                    output = args[i + 1]
                    i++
                }

                "--ignore-invalid" -> ignoreInvalid = true
            }
            i++
        }

        if (input == null) {
            throw IllegalArgumentException("Debe indicar el fichero de entrada (-i)")
        }

        if (!stdout && output == null) {
            throw IllegalArgumentException("Debe indicar salida (-p o -o)")
        }

        return Configuracion(
            ficheroEntrada = input,
            fechaInicio = from,
            fechaFin = to,
            niveles = niveles,
            salidaConsola = stdout,
            ficheroSalida = output,
            ignorarInvalidas = ignoreInvalid
        )
    }
}