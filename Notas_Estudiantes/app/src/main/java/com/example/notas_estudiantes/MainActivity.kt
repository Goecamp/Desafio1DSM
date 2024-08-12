package com.example.notas_estudiantes

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerMateria = findViewById<Spinner>(R.id.spinnerMateria)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etNota1 = findViewById<EditText>(R.id.etNota1)
        val etNota2 = findViewById<EditText>(R.id.etNota2)
        val etNota3 = findViewById<EditText>(R.id.etNota3)
        val etNota4 = findViewById<EditText>(R.id.etNota4)
        val etNota5 = findViewById<EditText>(R.id.etNota5)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        // Configurar el Spinner con las opciones de materias
        val materias = arrayOf("Materia 1", "Materia 2", "Materia 3", "Materia 4")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, materias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMateria.adapter = adapter

        btnCalcular.setOnClickListener {
            val nombre = etNombre.text.toString()
            val materiaSeleccionada = spinnerMateria.selectedItem.toString()

            // Validar que los campos no estén vacíos y que las notas estén entre 0 y 10
            if (nombre.isEmpty() || etNota1.text.isEmpty() || etNota2.text.isEmpty() ||
                etNota3.text.isEmpty() || etNota4.text.isEmpty() || etNota5.text.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nota1 = etNota1.text.toString().toDoubleOrNull()
            val nota2 = etNota2.text.toString().toDoubleOrNull()
            val nota3 = etNota3.text.toString().toDoubleOrNull()
            val nota4 = etNota4.text.toString().toDoubleOrNull()
            val nota5 = etNota5.text.toString().toDoubleOrNull()

            if (nota1 == null || nota2 == null || nota3 == null || nota4 == null || nota5 == null ||
                nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10 ||
                nota3 < 0 || nota3 > 10 || nota4 < 0 || nota4 > 10 || nota5 < 0 || nota5 > 10) {
                Toast.makeText(this, "Ingrese notas válidas entre 0 y 10", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val promedio = nota1 * 0.15 + nota2 * 0.15 + nota3 * 0.20 + nota4 * 0.25 + nota5 * 0.25
            val resultado = if (promedio >= 6.0) "Aprobado" else "Reprobado"

            tvResultado.text = "Materia: $materiaSeleccionada\nNombre: $nombre\nNota Final: %.2f\nResultado: %s".format(promedio, resultado)
        }

        btnLimpiar.setOnClickListener {
            etNombre.text.clear()
            etNota1.text.clear()
            etNota2.text.clear()
            etNota3.text.clear()
            etNota4.text.clear()
            etNota5.text.clear()
            spinnerMateria.setSelection(0)
            tvResultado.text = "Resultado:"
        }
    }
}
