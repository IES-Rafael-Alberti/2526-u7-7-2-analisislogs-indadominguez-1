package org.iesra.service

import org.iesra.model.EntradaLog
import org.iesra.model.NivelLog
import java.time.LocalDateTime

class FiltroLog {

    fun filtrar(
        logs: List<EntradaLog>,
        fechaInicio: LocalDateTime?,
        fechaFin: LocalDateTime?,
        niveles: Set<NivelLog>?
    ): List<EntradaLog> {

        return logs.filter { entrada ->

            val cumpleFecha =
                (fechaInicio == null || !entrada.fechaHora.isBefore(fechaInicio)) &&
                        (fechaFin == null || !entrada.fechaHora.isAfter(fechaFin))

            val cumpleNivel =
                niveles == null || niveles.contains(entrada.nivel)

            cumpleFecha && cumpleNivel
        }
    }
}