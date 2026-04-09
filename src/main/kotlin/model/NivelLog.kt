package org.iesra.model

enum class NivelLog {
    INFO, WARNING, ERROR;

    companion object {
        fun desdeTexto(texto: String): NivelLog? {
            return try {
                valueOf(texto)
            } catch (e: Exception) {
                null
            }
        }
    }
}