package com.example.pagocolegiatura

class clsColegiaturaModelo {

    fun calcularPromedio(p1: Float, p2: Float, p3: Float): Float {
        return (p1 + p2 + p3) / 3
    }

    // Reglas de negocio basadas en la diapositiva
    fun calcularDescuento(promedio: Float): Float {
        return when {
            promedio <= 7.0f -> 0.0f    // <= 7 no hay descuento
            promedio <= 9.0f -> 0.30f   // 7.1 a 9 -> 30% descuento
            else -> 0.50f               // > 9 -> 50% descuento
        }
    }

    fun obtenerEstatus(promedio: Float): String {
        return when {
            promedio <= 7.0f -> "Sin descuento"
            promedio <= 9.0f -> "30% de descuento"
            else -> "50% de descuento"
        }
    }

    fun calcularColegiaturaFinal(costoBase: Float, descuento: Float): Float {
        return costoBase - (costoBase * descuento)
    }
}

// ==========================================
// 2. EL PRESENTADOR (Intermediario)
// ==========================================
class clsColegiaturaPresenter(private val vista: MainActivity) {
    private val modelo = clsColegiaturaModelo()

    fun procesarPromedio(txtP1: String, txtP2: String, txtP3: String) {
        val p1 = txtP1.toFloatOrNull() ?: 0f
        val p2 = txtP2.toFloatOrNull() ?: 0f
        val p3 = txtP3.toFloatOrNull() ?: 0f

        val promedio = modelo.calcularPromedio(p1, p2, p3)
        vista.mostrarPromedio(promedio)
    }

    fun procesarEstatus(matricula: String, nombre: String, txtP1: String, txtP2: String, txtP3: String) {
        val p1 = txtP1.toFloatOrNull() ?: 0f
        val p2 = txtP2.toFloatOrNull() ?: 0f
        val p3 = txtP3.toFloatOrNull() ?: 0f

        val promedio = modelo.calcularPromedio(p1, p2, p3)
        val estatus = modelo.obtenerEstatus(promedio)

        vista.mostrarMatriculaNombre("Matrícula: $matricula | Nombre: $nombre - ($estatus)")
    }

    fun procesarColegiatura(txtP1: String, txtP2: String, txtP3: String, txtCosto: String) {
        val p1 = txtP1.toFloatOrNull() ?: 0f
        val p2 = txtP2.toFloatOrNull() ?: 0f
        val p3 = txtP3.toFloatOrNull() ?: 0f
        val costoBase = txtCosto.toFloatOrNull() ?: 0f

        val promedio = modelo.calcularPromedio(p1, p2, p3)
        val descuento = modelo.calcularDescuento(promedio)
        val total = modelo.calcularColegiaturaFinal(costoBase, descuento)

        vista.mostrarColegiaturaActual(costoBase)
        vista.mostrarColegiaturaFinal(total)
    }
}