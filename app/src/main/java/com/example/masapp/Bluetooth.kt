@file:Suppress("SpellCheckingInspection")

package com.example.masapp

import android.bluetooth.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.util.*


class Bluetooth : AppCompatActivity() {


    var textoEstadoConexion: TextView? = null
    lateinit var bluetoothAdapter: BluetoothAdapter
    private var bluetoothManager: BluetoothManager? = null
    private var pairedDevices: Set<BluetoothDevice>? = null
    private lateinit var impresoraSeleccionada: BluetoothDevice

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)
        textoEstadoConexion = findViewById(R.id.estadoConexion) as TextView


        //Variables
        var dispositivosEmparejados: MutableList<String> = mutableListOf()
        var dispositivosConectados: MutableList<BluetoothDevice>  = mutableListOf()

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        //Consulta si tiene adaptador Bluetooth
        if (bluetoothAdapter == null) {
            textoEstadoConexion!!.text = "No hay Bluetooth"
        } else {

            //Habilita el adaptador si está deshabilitado
            if (bluetoothAdapter?.isEnabled == false) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                val REQUEST_ENABLE_BT = 1;
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }

            bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

            //Lleno Lista de Dispositivos Conectados
            if (bluetoothManager != null) {
                dispositivosConectados = bluetoothManager!!.getConnectedDevices(BluetoothProfile.GATT)
            }

            //Imprimo Estado de conexion en Pantalla
            if (dispositivosConectados != null) {
                if (dispositivosConectados.isEmpty()){
                    textoEstadoConexion!!.text = "Desconectado"
                } else{
                    textoEstadoConexion!!.text = "Conectado"
                }
            }

        }

        //Obtengo la lista de los dispositovos
        pairedDevices = bluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            dispositivosEmparejados.add(device.name)
        }

        //Menú desplegable de dispositivos
        val spinner: Spinner = findViewById(R.id.Impresoras)
        ArrayAdapter(this, android.R.layout.simple_spinner_item, dispositivosEmparejados
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                impresoraSeleccionada= pairedDevices!!.elementAt(position)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }
        }

        //BOTON
        val button: Button = findViewById(R.id.botConectar)
        button.setOnClickListener {
            val hiloConexion = ConnectThread(impresoraSeleccionada)
            hiloConexion.start()
        }

    }




    private inner class ConnectThread(device: BluetoothDevice) : Thread() {

        val miUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

        private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
            device.createRfcommSocketToServiceRecord(miUUID)
        }

        public override fun run() {
            // Cancel discovery because it otherwise slows down the connection.
            bluetoothAdapter?.cancelDiscovery()
            textoEstadoConexion!!.text = "Conectando.."
            /*
            mmSocket?.use { socket ->
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                socket.connect()

                // The connection attempt succeeded. Perform work associated with
                // the connection in a separate thread.
                //manageMyConnectedSocket(socket)

                textoEstadoConexion!!.text = "Conectado"
            }
             */
            mmSocket?.connect()
            if(mmSocket!!.isConnected){
                textoEstadoConexion!!.text = "Conectado"
            } else{
                textoEstadoConexion!!.text = "Fallo al conectar"
            }
        }

        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                mmSocket?.close()
            } catch (e: IOException) {
                //Log.e(TAG, "Could not close the client socket", e)
            }
        }
    }




}