package org.iesra.controller

import org.iesra.model.NivelLog
import java.time.LocalDateTime

data class Configuracion(
    val ficheroEntrada: String,
    val fechaInicio: LocalDateTime?,
    val fechaFin: LocalDateTime?,
    val niveles: Set<NivelLog>?,
    val salidaConsola: Boolean,
    val ficheroSalida: String?,
    val ignorarInvalidas: Boolean
)