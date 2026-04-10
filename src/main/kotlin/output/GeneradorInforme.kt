package org.iesra.report

class GeneradorInforme {

    fun generar(stats: Map<String, Any?>): String {

        return """
        INFORME DE LOGS
        ===============
        
        Resumen:
        - Líneas procesadas: ${stats["total"]}
        - Líneas válidas: ${stats["validas"]}
        - Líneas inválidas: ${stats["invalidas"]}
        
        Conteo por nivel:
        - INFO: ${stats["info"]}
        - WARNING: ${stats["warning"]}
        - ERROR: ${stats["error"]}
        
        Periodo:
        - Primera entrada: ${stats["primera"]}
        - Última entrada: ${stats["ultima"]}
        """.trimIndent()
    }
}