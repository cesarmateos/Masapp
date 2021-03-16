package com.example.masapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textoEstadoConexion: TextView? = findViewById(R.id.estadoConexion) as TextView
        var btHandler: BTHandler = BTHandler(textoEstadoConexion, this)
        var posicionListaImpresora: Int = 0;


        //Menú desplegable de dispositivos
        val spinner: Spinner = findViewById(R.id.Impresoras)
        ArrayAdapter(this, android.R.layout.simple_spinner_item, btHandler.dispositivosEmparejados
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                posicionListaImpresora = position;
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        //Botón Conectar
        val button: Button = findViewById(R.id.botConectar)
        button.setOnClickListener {
            btHandler.conectar(posicionListaImpresora)
        }


    }

}