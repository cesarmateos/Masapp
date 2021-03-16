package com.example.masapp

import android.bluetooth.*
import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.getSystemService
import java.io.IOException
import java.util.*

class BTHandler(var textoEstadoConexion: TextView?, activity: MainActivity) {

    var bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    lateinit var pairedDevices: Set<BluetoothDevice>
    private var bluetoothManager: BluetoothManager? = null

    var dispositivosEmparejados: MutableList<String> = mutableListOf()
    var dispositivosConectados: MutableList<BluetoothDevice>  = mutableListOf()
    val existeBT: Boolean = bluetoothAdapter != null

    init{

        if (!existeBT) {
            textoEstadoConexion!!.text = "No hay Bluetooth"
        } else {
            textoEstadoConexion!!.text = "Desconectado"

            //Habilita el adaptador si está deshabilitado
            if (bluetoothAdapter?.isEnabled == false) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                val REQUEST_ENABLE_BT = 1
                activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
            bluetoothManager = activity.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager


            //Obtengo la lista de los dispositivos apareados
            pairedDevices = bluetoothAdapter?.bondedDevices
            pairedDevices?.forEach { device ->
                dispositivosEmparejados.add(device.name)
            }

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

    }

    fun conectar(posicion: Int){
        if(existeBT){
            val hiloConexion = ConnectThread(pairedDevices!!.elementAt(posicion))
            hiloConexion.start()
        }
    }

    private inner class ConnectThread(device: BluetoothDevice) : Thread() {

        val miUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        val seleccionado: BluetoothDevice = device

    /*
        private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
            device.createInsecureRfcommSocketToServiceRecord(miUUID)
        }
*/
        public override fun run() {
            // Cancel discovery because it otherwise slows down the connection.
            //bluetoothAdapter?.cancelDiscovery()
            textoEstadoConexion!!.text = "Conect " + seleccionado.name
            //mmSocket!!.connect()
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



            mmSocket?.connect()

            if(mmSocket!!.isConnected){
                textoEstadoConexion!!.text = "Conectado"
            } else{
                textoEstadoConexion!!.text = "Fallo al conectar"
            }

             */

        }
/*
        // Closes the client socket and causes the thread to finish.
        fun cancel() {
            try {
                mmSocket?.close()
            } catch (e: IOException) {
                //Log.e(TAG, "Could not close the client socket", e)
            }
        }

 */
    }
}