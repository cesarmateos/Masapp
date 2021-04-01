package com.example.masapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private lateinit var textoEstadoConexion : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textoEstadoConexion = findViewById(R.id.estadoConexion) as TextView

        val btHandler: BTHandler = BTHandler(this)
        var posicionListaImpresora: Int = 0;

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Despacho"))
        tabLayout.addTab(tabLayout.newTab().setText("Predespacho"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = Adaptador(this, supportFragmentManager,
                tabLayout.tabCount,btHandler)
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
        val botonConectar: Button = findViewById(R.id.botConectar)
        botonConectar.setOnClickListener {
            btHandler.conectar(posicionListaImpresora)
        }

        //Botón exit
        val botonExit: ImageButton = findViewById(R.id.cerrar)
        botonExit.setOnClickListener {
            finish()
            System.exit(0)
        }
    }

    fun cambiarTexto(texto: String){
        textoEstadoConexion.text = texto
    }
}