package com.example.pagocolegiatura

class clsColgiaturaPresenter(private val vista: MainActivity) {
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