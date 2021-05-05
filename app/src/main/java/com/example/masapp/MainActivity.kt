package com.example.masapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private lateinit var textoEstadoConexion : TextView
    private var estadoBotonConectar = true
    private lateinit var spinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textoEstadoConexion = findViewById(R.id.estadoConexion) as TextView
        val btHandler: BTHandler = BTHandler(this)

        var posicionListaImpresora: Int = 0;

        //Sistema de Tabs
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Despacho"))
        tabLayout.addTab(tabLayout.newTab().setText("Predespacho"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = Adaptador(this, supportFragmentManager,
                tabLayout.tabCount, btHandler)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        //Menú desplegable de dispositivos
        spinner = findViewById(R.id.Impresoras)
        cargarDispositivos(btHandler)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                posicionListaImpresora = position
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        //Botón conectar
        val botonConectar: Button = findViewById(R.id.botConectar)
        if(btHandler.existeBT){
            botonConectar.setOnClickListener {
                if(estadoBotonConectar){
                    btHandler.conectar(posicionListaImpresora)
                    botonConectar.text = "Desconectar"
                    botonConectar.textSize = 12.0F
                    estadoBotonConectar = false
                }else{
                    btHandler.desconectar()
                    botonConectar.text = "Conectar"
                    botonConectar.textSize = 14.0F
                    estadoBotonConectar = true

                }
            }
        }


        //Botón exit
        val botonExit: ImageButton = findViewById(R.id.cerrar)
        botonExit.setOnClickListener {
            finish()
            exitProcess(0)
        }

        //Botón ayuda
        val botonAyuda: ImageButton = findViewById(R.id.ayuda)
        botonAyuda.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            alertDialogBuilder.setPositiveButton("Cerrar")
            { dialog, id ->
                dialog.cancel();
            }
            alertDialogBuilder.setView(inflater.inflate(R.layout.ayuda, null))
            val dialogo = alertDialogBuilder.create()
            dialogo.show()
            val positiveButton = dialogo.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setTextColor(Color.parseColor("#FFAB0F"))

        }


    }

    fun cargarDispositivos(btHandler:BTHandler){
        ArrayAdapter(this, android.R.layout.simple_spinner_item, btHandler.dispositivosEmparejados
        ).also { adaptador ->
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adaptador
        }
    }

    fun cambiarTexto(texto: String){
        textoEstadoConexion.text = texto
    }
}