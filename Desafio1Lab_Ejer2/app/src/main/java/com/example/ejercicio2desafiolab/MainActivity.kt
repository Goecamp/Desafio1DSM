package com.example.ejercicio2desafiolab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val txtNombre = findViewById<EditText>(R.id.txt_nombre)
        val txtSalario = findViewById<EditText>(R.id.txt_salario)

        btnCalcular.setOnClickListener {
            val nombre = txtNombre.text.toString()
            val salario = txtSalario.text.toString().toDoubleOrNull()

            if (nombre.isNotEmpty() && salario != null) {
                // Crear un Intent para iniciar ResultadosActivity
                val intent = Intent(this, ResultadosActivity::class.java)
                intent.putExtra("nombre", nombre)
                intent.putExtra("salario", salario)
                startActivity(intent)
            } else {
                // Manejar errores
            }
        }
    }
}
