package com.example.pagocolegiatura

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Declaración de las cajas de texto (Entradas)
    private lateinit var txtMatricula: EditText
    private lateinit var txtNombre: EditText
    private lateinit var txtP1: EditText
    private lateinit var txtP2: EditText
    private lateinit var txtP3: EditText
    private lateinit var txtCosto: EditText

    // Declaración de los botones
    private lateinit var btnPromedio: Button
    private lateinit var btnEstatus: Button
    private lateinit var btnColegiatura: Button

    // Declaración de los textos de salida
    private lateinit var txtResMatriculaNombre: TextView
    private lateinit var txtResPromedio: TextView
    private lateinit var txtResCostoActual: TextView
    private lateinit var txtResCostoFinal: TextView

    // Instancia del presentador
    private lateinit var presenter: clsColegiaturaPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enlazar la vista lógica con la vista de diseño XML
        txtMatricula = findViewById(R.id.txtMatricula)
        txtNombre = findViewById(R.id.txtNombre)
        txtP1 = findViewById(R.id.txtP1)
        txtP2 = findViewById(R.id.txtP2)
        txtP3 = findViewById(R.id.txtP3)
        txtCosto = findViewById(R.id.txtCosto)

        btnPromedio = findViewById(R.id.btnPromedio)
        btnEstatus = findViewById(R.id.btnEstatus)
        btnColegiatura = findViewById(R.id.btnColegiatura)

        txtResMatriculaNombre = findViewById(R.id.txtResMatriculaNombre)
        txtResPromedio = findViewById(R.id.txtResPromedio)
        txtResCostoActual = findViewById(R.id.txtResCostoActual)
        txtResCostoFinal = findViewById(R.id.txtResCostoFinal)

        // Inicializar el presentador enviándole esta vista
        presenter = clsColegiaturaPresenter(this)

        // Asignar los eventos de clic a cada botón
        btnPromedio.setOnClickListener { ejecutarAccionPromedio(it) }
        btnEstatus.setOnClickListener { ejecutarAccionEstatus(it) }
        btnColegiatura.setOnClickListener { ejecutarAccionColegiatura(it) }
    }

    // --- Métodos que envían los datos al Presentador ---
    private fun ejecutarAccionPromedio(v: View) {
        presenter.procesarPromedio(txtP1.text.toString(), txtP2.text.toString(), txtP3.text.toString())
    }

    private fun ejecutarAccionEstatus(v: View) {
        presenter.procesarEstatus(
            txtMatricula.text.toString(),
            txtNombre.text.toString(),
            txtP1.text.toString(),
            txtP2.text.toString(),
            txtP3.text.toString()
        )
    }

    private fun ejecutarAccionColegiatura(v: View) {
        presenter.procesarColegiatura(
            txtP1.text.toString(),
            txtP2.text.toString(),
            txtP3.text.toString(),
            txtCosto.text.toString()
        )
    }

    // --- Métodos de salida (Ordenados por el Presentador) ---
    fun mostrarPromedio(promedio: Float) {
        // String.format redondea a 2 decimales para que se vea estético
        txtResPromedio.text = "Promedio Global: " + String.format("%.2f", promedio)
    }

    fun mostrarMatriculaNombre(datos: String) {
        txtResMatriculaNombre.text = datos
    }

    fun mostrarColegiaturaActual(costoBase: Float) {
        txtResCostoActual.text = "Colegiatura Actual: $" + String.format("%.2f", costoBase)
    }

    fun mostrarColegiaturaFinal(costoFinal: Float) {
        txtResCostoFinal.text = "Colegiatura Final: $" + String.format("%.2f", costoFinal)
    }
}