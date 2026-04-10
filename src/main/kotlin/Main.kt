import org.iesra.controller.ArgumentosParser
import org.iesra.data.LogRepository
import org.iesra.model.EntradaLog
import org.iesra.parser.ParserLog
import org.iesra.service.FiltroLog
import org.iesra.service.EstadisticasLog
import org.iesra.report.GeneradorInforme

fun main(args: Array<String>) {

    // 1. Parsear argumentos (controller)
    val config = try {
        ArgumentosParser().parsear(args)
    } catch (e: Exception) {
        println("Error: ${e.message}")
        return
    }

    val repository = LogRepository()
    val parser = ParserLog()
    val filtro = FiltroLog()
    val estadisticasServicio = EstadisticasLog()
    val generadorInforme = GeneradorInforme()

    // 2. Leer fichero
    val lineas = try {
        repository.obtenerLineas(config.ficheroEntrada)
    } catch (e: Exception) {
        println("Error: ${e.message}")
        return
    }

    // 3. Parsear líneas
    val logsValidos = mutableListOf<EntradaLog>()
    var invalidas = 0

    for (linea in lineas) {
        val entrada = parser.parsear(linea)

        if (entrada != null) {
            logsValidos.add(entrada)
        } else {
            if (!config.ignorarInvalidas) {
                invalidas++
            }
        }
    }

    // 4. Filtrar logs
    val logsFiltrados = filtro.filtrar(
        logsValidos,
        config.fechaInicio,
        config.fechaFin,
        config.niveles
    )

    // 5. Calcular estadísticas
    val stats = estadisticasServicio.calcular(
        lineas.size,
        logsFiltrados,
        invalidas
    )

    // 6. Generar informe
    val informe = generadorInforme.generar(stats)

    // 7. Salida
    if (config.salidaConsola) {
        println(informe)
    }

    if (config.ficheroSalida != null) {
        repository.guardarLineas(config.ficheroSalida, informe)
    }
}