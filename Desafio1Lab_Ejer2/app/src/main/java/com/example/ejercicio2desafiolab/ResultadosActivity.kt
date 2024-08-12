package com.example.ejercicio2desafiolab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class ResultadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val txtNombre = findViewById<TextView>(R.id.txt_nombre_resultado)
        val txtSalario = findViewById<TextView>(R.id.txt_salario_resultado)
        val txtISSS = findViewById<TextView>(R.id.txt_isss)
        val txtAFP = findViewById<TextView>(R.id.txt_afp)
        val txtRenta = findViewById<TextView>(R.id.txt_renta)
        val txtSalarioAPagar = findViewById<TextView>(R.id.txt_salario_a_pagar)
        val btnRegresar = findViewById<Button>(R.id.btn_regresar)

        // Obtener datos
        val nombre = intent.getStringExtra("nombre")
        val salario = intent.getDoubleExtra("salario", 0.0)

        // Mostrar el nombre
        txtNombre.text = "Nombre: $nombre"

        // Mostrar el salario
        val decimalFormat = DecimalFormat("#.00")
        txtSalario.text = "Salario: ${decimalFormat.format(salario)}"

        // Calcular descuentos
        val isss = salario * 0.03
        val afp = salario * 0.0725
        val salarioSinrenta = salario - isss - afp

        // Calcular renta
        val renta = when {
            salarioSinrenta <= 472 -> 0.0
            salarioSinrenta <= 895.24 -> (salarioSinrenta - 472) * 0.1
            salarioSinrenta <= 2038.10 -> (salarioSinrenta - 895.24) * 0.2 + 42.524
            else -> (salarioSinrenta - 2038.10) * 0.3 + 349.524
        }

        // Calcular salario
        val salarioAPagar = salarioSinrenta - renta

        // Mostrar los resultado de los descuentos
        txtISSS.text = "Descuento de ISSS: ${decimalFormat.format(isss)}"
        txtAFP.text = "Descuento de AFP: ${decimalFormat.format(afp)}"
        txtRenta.text = "Descuento de Renta: ${decimalFormat.format(renta)}"
        txtSalarioAPagar.text = "Salario Neto : ${decimalFormat.format(salarioAPagar)}"

        // boton para regresar
        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
