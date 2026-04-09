import org.iesra.data.LogRepository
import org.iesra.model.EntradaLog
import org.iesra.parser.ParserLog

fun main() {
    val repository = LogRepository()
    val parser = ParserLog()

    // Ruta del fichero de prueba
    val ruta = "app.log"

    val lineas = try {
        repository.obtenerLineas(ruta)
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
        return
    }

    val logsValidos = mutableListOf<EntradaLog>()
    var invalidas = 0

    for (linea in lineas) {
        val entrada = parser.parsear(linea)
        if (entrada != null) {
            logsValidos.add(entrada)
        } else {
            invalidas++
        }
    }

    println("--- RESULTADO DEL PARSEO ---")
    println("Líneas procesadas: ${lineas.size}")
    println("Líneas válidas: ${logsValidos.size}")
    println("Líneas inválidas: $invalidas")
    println("\nLogs válidos:")
    logsValidos.forEach { println(it) }
}