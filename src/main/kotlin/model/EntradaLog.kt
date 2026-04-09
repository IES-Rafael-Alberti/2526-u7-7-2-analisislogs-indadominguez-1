package org.iesra.model

import java.time.LocalDate

data class EntradaLog(
    val fechaHora: LocalDate,
    val nivel: NivelLog,
    val mensaje: String
) {

}