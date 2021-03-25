package com.example.masapp

import android.bluetooth.*
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent

import android.util.Log
import android.widget.TextView
import java.io.OutputStream
import java.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainActivity"

class BTHandler(var textoEstadoConexion: TextView?, activity: MainActivity) {

    var bluetoothAdapter: BluetoothAdapter? = null
    lateinit var pairedDevices: Set<BluetoothDevice>
    private var bluetoothManager: BluetoothManager? = null

    var dispositivosEmparejados: MutableList<String> = mutableListOf()

    var existeBT: Boolean = false

    private var outputStream: OutputStream? = null

    init{
        textoEstadoConexion!!.text = "Buscando Adaptador..."
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        existeBT = bluetoothAdapter != null

        if (!existeBT) {
            textoEstadoConexion!!.text = "No hay Bluetooth"
        } else {

            //Habilita el adaptador si está deshabilitado
            if (bluetoothAdapter?.isEnabled == false) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                val REQUEST_ENABLE_BT = 1
                activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            }
            bluetoothManager = activity.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager


            //Obtengo la lista de los dispositivos apareados
            textoEstadoConexion!!.text = "Buscando Dispositivos.."
            pairedDevices = bluetoothAdapter?.bondedDevices as Set<BluetoothDevice>
            pairedDevices?.forEach { device ->
                dispositivosEmparejados.add(device.name)
            }

            evaluoConectados()
        }

    }

    fun evaluoConectados(){
        //Lleno Lista de Dispositivos Conectados
        var dispositivosConectados: MutableList<BluetoothDevice>  = mutableListOf()
        if (bluetoothManager != null) {
            dispositivosConectados = bluetoothManager!!.getConnectedDevices(BluetoothProfile.GATT)
        }
        //Imprimo Estado de conexion en Pantalla
        if (dispositivosConectados != null) {
            if (dispositivosConectados.isEmpty()){
                textoEstadoConexion!!.text = "Desconectado"
            } else{
                textoEstadoConexion!!.text = "Lista3"
            }
        }
    }


    private suspend fun connect(device:BluetoothDevice): OutputStream? {
        return withContext(Dispatchers.IO) {
            var outputStream: OutputStream? = null
            if (existeBT && bluetoothAdapter!!.isEnabled) {
                try {
                    val bluetoothSocket = device.createRfcommSocketToServiceRecord(
                            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
                    )
                    bluetoothAdapter!!.cancelDiscovery()
                    bluetoothSocket?.connect()
                    if (bluetoothSocket!!.isConnected) {
                        outputStream = bluetoothSocket!!.outputStream
                    }
                } catch (e: Exception){
                    Log.d(TAG, "connect: ${e.message}")
                }
            }
            outputStream
        }
    }


    fun imprimir(datos: String){
        textoEstadoConexion!!.text = "Imprimiendo"
        outputStream?.run {
            write(datos.toByteArray())
            write(byteArrayOf(10))                  // Feed line
            textoEstadoConexion!!.text = "Lista2"
        }
    }

    fun conectar(posicion: Int){
        if(existeBT){
            val btDevice : BluetoothDevice = pairedDevices!!.elementAt(posicion)
            GlobalScope.launch (Dispatchers.Main) {
                if(outputStream == null) {
                    textoEstadoConexion!!.text = "Conectando a " + btDevice.name.take(8)
                    outputStream = connect(btDevice)?.also {
                        textoEstadoConexion!!.text = "Lista"
                    }
                }
            }
        }
    }
}
