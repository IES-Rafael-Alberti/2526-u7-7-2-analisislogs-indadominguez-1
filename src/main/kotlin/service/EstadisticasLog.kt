package org.iesra.service

import org.iesra.model.EntradaLog
import org.iesra.model.NivelLog

class EstadisticasLog {

    fun calcular(
        totalLineas: Int,
        logsValidos: List<EntradaLog>,
        lineasInvalidas: Int
    ): Map<String, Any?> {

        val info = logsValidos.count { it.nivel == NivelLog.INFO }
        val warning = logsValidos.count { it.nivel == NivelLog.WARNING }
        val error = logsValidos.count { it.nivel == NivelLog.ERROR }

        val primera = logsValidos.minByOrNull { it.fechaHora }?.fechaHora
        val ultima = logsValidos.maxByOrNull { it.fechaHora }?.fechaHora

        return mapOf(
            "total" to totalLineas,
            "validas" to logsValidos.size,
            "invalidas" to lineasInvalidas,
            "info" to info,
            "warning" to warning,
            "error" to error,
            "primera" to primera,
            "ultima" to ultima
        )
    }
}