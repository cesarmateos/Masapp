package com.example.masapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Arrancar Bluetooth
        val arrancaBluetooth = Intent(this, Bluetooth::class.java)
        startActivity(arrancaBluetooth)
    }

}