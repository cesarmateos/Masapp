package com.example.masapp
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class Predespacho(btHandler: BTHandler): Fragment() {

    private val blueTooth: BTHandler = btHandler
    private val multiplicador = 3
    private var ejeX: Int = 0
    private var ejeY: Int = 0

    private lateinit var textoX : TextView
    private lateinit var textoY : TextView

    private var hayRolloPapel: Boolean = false
    private var hayRolloEtiqueta: Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vistaRetorno = inflater.inflate(R.layout.predespacho, container, false)
        textoX = vistaRetorno.findViewById(R.id.PredesTextoX)
        textoY = vistaRetorno.findViewById(R.id.PredesTextoY)
        textoX.text = ejeX.toString()
        textoY.text = ejeY.toString()

        //Botón Cargar e Imprimir Papel Continuo
        val botImprimeE11Papel: Button = vistaRetorno.findViewById(R.id.botImprimeE11Papel)
        botImprimeE11Papel.setOnClickListener {
            if (blueTooth.btDevice?.name?.take(4).equals("SATO")) {
                if (!hayRolloPapel) {
                    cambiarPapel()
                }
                cargarFormatoSatoContinuo()
            } else {
                cargarFormatoGeneral()
            }
            imprmirPrueba()
        }

        //Botón Cargar e Imprimir Etiqueta Autoadhesiva
        val botImprimeE11Etiqueta: Button = vistaRetorno.findViewById(R.id.botImprimeE11Etiqueta)
        botImprimeE11Etiqueta.setOnClickListener {
            if (blueTooth.btDevice?.name?.take(4).equals("SATO")){
                if(!hayRolloEtiqueta){
                    cambiarEtiqueta()
                }
            }
            cargarFormatoGeneral()
            imprmirPrueba()
        }

        //Botón Cargar e Imprimir Emergencia
        val botImprimeE11Emergencia: Button = vistaRetorno.findViewById(R.id.botImprimeE11Emergencia)
        botImprimeE11Emergencia.setOnClickListener {
            if (blueTooth.btDevice?.name?.take(4).equals("SATO")) {
                if (!hayRolloPapel) {
                    cambiarPapel()
                }
            }
            cargarFormatoEmergencia()
            imprmirPrueba()
        }

        //Botonera Cursores
        val botArriba: ImageButton = vistaRetorno.findViewById(R.id.PredesBotArriba)
        botArriba.setOnClickListener {
            modificarX(1)
        }
        val botAbajo: ImageButton = vistaRetorno.findViewById(R.id.PredesBotAbajo)
        botAbajo.setOnClickListener {
            modificarX(-1)
        }
        val botDerecha: ImageButton = vistaRetorno.findViewById(R.id.PredesBotDer)
        botDerecha.setOnClickListener {
            modificarY(1)
        }

        val botIzquierda: ImageButton = vistaRetorno.findViewById(R.id.PredesBotIzq)
        botIzquierda.setOnClickListener {
            modificarY(-1)
        }

        return vistaRetorno
    }

    private fun imprmirPrueba(){
        blueTooth.imprimir("<STX>R<ETX>\n" +
                "<STX><ESC>E11<CAN><ETX>\n" +
                "<STX><ESC>F6<DEL>DESP.COPIA 734-D<ETX>\n" +
                "<STX><ESC>F8<DEL>04/11/2020<ETX>\n" +
                "<STX><ESC>F10<DEL>125@#<ETX>\n" +
                "<STX><ESC>F11<DEL>0408017097<ETX>\n" +
                "<STX><ESC>F12<DEL>(2676901)<ETX>\n" +
                "<STX><ESC>F17<DEL>Bul: 001 Fmbx.=200692  Doc.=FC 1116-05939381<ETX>\n" +
                "<STX><ESC>F15<DEL>Av. Mitre 2471<ETX>\n" +
                "<STX><ESC>F16<DEL>Avellaneda<ETX>\n" +
                "<STX><ESC>F13<DEL>FCIA ANTIGUA POZUELO SCS <ETX>\n" +
                "<STX><ESC>F21<DEL>01  A5 13:20  22<ETX>\n" +
                "<STX><RS>1<ETB><FF><ETX>")

    }

    private fun cargarFormatoGeneral(){
        blueTooth.mandarLogo()
        blueTooth.imprimir("<STX><ESC>C<ETX>\n" +
                "<STX><ESC>P<ETX>\n" +
                "<STX>E11;F11;<ETX>\n" +
                "<STX>U31;f3;o" + (475 + ejeX * multiplicador) + "," + (35 + ejeY * multiplicador) + ";c2;w1;h1;<ETX>\n" +
                "<STX>H26;f3;o" + (425 + ejeX * multiplicador) + "," + (62 + ejeY * multiplicador) + ";c26;b0;k6;d3,Monroe Americana<ETX>\n" +
                "<STX>H4;f3;o" + (465 + ejeX * multiplicador) + "," + (250 + ejeY * multiplicador) + ";c26;b0;k15;d3,Ped:<ETX>\n" +
                "<STX>H10;f3;o" + (465 + ejeX * multiplicador) + "," + (345 + ejeY * multiplicador) + ";c26;b0;k15;d0,10;<ETX>\n" +
                "<STX>H8;f3;o" + (465 + ejeX * multiplicador) + "," + (570 + ejeY * multiplicador) + ";c26;b0;k12;d0,10;<ETX>n" +
                "<STX>H17;f3;o" + (370 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k10;d0,50;<ETX>n" +
                "<STX>H27;f3;o" + (290 + ejeX * multiplicador) + "," + (55 + ejeY * multiplicador) + ";c26;b0;k7;d3,Entrega<ETX>n" +
                "<STX>H28;f3;o" + (290 + ejeX * multiplicador) + "," + (235 + ejeY * multiplicador) + ";c26;b0;k7;d3,Radio<ETX>n" +
                "<STX>H29;f3;o" + (290 + ejeX * multiplicador) + "," + (440 + ejeY * multiplicador) + ";c26;b0;k7;d3,Salida<ETX>n" +
                "<STX>H30;f3;o" + (290 + ejeX * multiplicador) + "," + (680 + ejeY * multiplicador) + ";c26;b0;k7;d3,Orden<ETX>n" +
                "<STX>H21;f3;o" + (285 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k29;d0,30;<ETX>n" +
                "<STX>H13;f3;o" + (160 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k12;d0,26;<ETX>n" +
                "<STX>H15;f3;o" + (120 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k9;d0,26;<ETX>n" +
                "<STX>H16;f3;o" + (90 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k9;d0,26;<ETX>n" +
                "<STX>H12;f3;o" + (100 + ejeX * multiplicador) + "," + (420 + ejeY * multiplicador) + ";c26;b0;k12;d0,10;<ETX>n" +
                "<STX>H6;f3;o" + (55 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k18;d0,16;<ETX>n" +
                "<STX>B11;f3;o" + (170 + ejeX * multiplicador) + "," + (620 + ejeY * multiplicador) + ";c17,200,0;w11;h11;i1;d0,10;<ETX>n" +
                "<STX>I11;f3;o" + (30 + ejeX * multiplicador) + "," + (620 + ejeY * multiplicador) + ";c20;h1;w1;b0<ETX>n" +
                "<STX>H25;f3;o" + (40 + ejeX * multiplicador) + "," + (640 + ejeY * multiplicador) + ";c26;b0;k7;d0,30;<ETX>n" +
                "<STX>R<ETX>")
    }

    private fun cargarFormatoSatoContinuo(){
        blueTooth.mandarLogo()
        blueTooth.imprimir("<STX><ESC>C<ETX>\n" +
                "<STX><ESC>P<ETX>\n" +
                "<STX>E11;F11;<ETX>\n" +
                "<STX>H38;f3;o" + (700 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k6;d3,.<ETX>\n" +
                "<STX>U31;f3;o" + (475 + ejeX * multiplicador) + "," + (35 + ejeY * multiplicador) + ";c2;w1;h1;<ETX>\n" +
                "<STX>H26;f3;o" + (425 + ejeX * multiplicador) + "," + (62 + ejeY * multiplicador) + ";c26;b0;k6;d3,Monroe Americana<ETX>\n" +
                "<STX>H4;f3;o" + (465 + ejeX * multiplicador) + "," + (250 + ejeY * multiplicador) + ";c26;b0;k15;d3,Ped:<ETX>\n" +
                "<STX>H10;f3;o" + (465 + ejeX * multiplicador) + "," + (345 + ejeY * multiplicador) + ";c26;b0;k15;d0,10;<ETX>\n" +
                "<STX>H8;f3;o" + (465 + ejeX * multiplicador) + "," + (570 + ejeY * multiplicador) + ";c26;b0;k12;d0,10;<ETX>n" +
                "<STX>H17;f3;o" + (370 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k10;d0,50;<ETX>n" +
                "<STX>H27;f3;o" + (290 + ejeX * multiplicador) + "," + (55 + ejeY * multiplicador) + ";c26;b0;k7;d3,Entrega<ETX>n" +
                "<STX>H28;f3;o" + (290 + ejeX * multiplicador) + "," + (235 + ejeY * multiplicador) + ";c26;b0;k7;d3,Radio<ETX>n" +
                "<STX>H29;f3;o" + (290 + ejeX * multiplicador) + "," + (440 + ejeY * multiplicador) + ";c26;b0;k7;d3,Salida<ETX>n" +
                "<STX>H30;f3;o" + (290 + ejeX * multiplicador) + "," + (680 + ejeY * multiplicador) + ";c26;b0;k7;d3,Orden<ETX>n" +
                "<STX>H21;f3;o" + (285 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k30;d0,30;<ETX>n" +
                "<STX>H13;f3;o" + (160 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k12;d0,26;<ETX>n" +
                "<STX>H15;f3;o" + (120 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k9;d0,26;<ETX>n" +
                "<STX>H16;f3;o" + (90 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k9;d0,26;<ETX>n" +
                "<STX>H12;f3;o" + (100 + ejeX * multiplicador) + "," + (420 + ejeY * multiplicador) + ";c26;b0;k12;d0,10;<ETX>n" +
                "<STX>H6;f3;o" + (55 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k18;d0,16;<ETX>n" +
                "<STX>B11;f3;o" + (170 + ejeX * multiplicador) + "," + (620 + ejeY * multiplicador) + ";c17,200,0;w11;h11;i1;d0,10;<ETX>n" +
                "<STX>I11;f3;o" + (30 + ejeX * multiplicador) + "," + (620 + ejeY * multiplicador) + ";c20;h1;w1;b0<ETX>n" +
                "<STX>H25;f3;o" + (40 + ejeX * multiplicador) + "," + (640 + ejeY * multiplicador) + ";c26;b0;k7;d0,30;<ETX>n" +
                "<STX>R<ETX>")
    }

    private fun cargarFormatoEmergencia(){
        blueTooth.mandarLogo()
        blueTooth.imprimir("<STX><ESC>C<ETX>\n" +
                "<STX><ESC>P<ETX>\n" +
                "<STX>E11;F11;<ETX>\n" +
                "<STX>H38;f3;o" + (1300 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k6;d3,.<ETX>\n" +
                "<STX>U31;f3;o" + (475 + ejeX * multiplicador) + "," + (35 + ejeY * multiplicador) + ";c2;w1;h1;<ETX>\n" +
                "<STX>H26;f3;o" + (425 + ejeX * multiplicador) + "," + (62 + ejeY * multiplicador) + ";c26;b0;k6;d3,Monroe Americana<ETX>\n" +
                "<STX>H4;f3;o" + (465 + ejeX * multiplicador) + "," + (250 + ejeY * multiplicador) + ";c26;b0;k15;d3,Ped:<ETX>\n" +
                "<STX>H10;f3;o" + (465 + ejeX * multiplicador) + "," + (345 + ejeY * multiplicador) + ";c26;b0;k15;d0,10;<ETX>\n" +
                "<STX>H8;f3;o" + (465 + ejeX * multiplicador) + "," + (570 + ejeY * multiplicador) + ";c26;b0;k12;d0,10;<ETX>n" +
                "<STX>H17;f3;o" + (370 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k10;d0,50;<ETX>n" +
                "<STX>H27;f3;o" + (290 + ejeX * multiplicador) + "," + (55 + ejeY * multiplicador) + ";c26;b0;k7;d3,Entrega<ETX>n" +
                "<STX>H28;f3;o" + (290 + ejeX * multiplicador) + "," + (235 + ejeY * multiplicador) + ";c26;b0;k7;d3,Radio<ETX>n" +
                "<STX>H29;f3;o" + (290 + ejeX * multiplicador) + "," + (440 + ejeY * multiplicador) + ";c26;b0;k7;d3,Salida<ETX>n" +
                "<STX>H30;f3;o" + (290 + ejeX * multiplicador) + "," + (680 + ejeY * multiplicador) + ";c26;b0;k7;d3,Orden<ETX>n" +
                "<STX>H21;f3;o" + (285 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k30;d0,30;<ETX>n" +
                "<STX>H13;f3;o" + (160 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k12;d0,26;<ETX>n" +
                "<STX>H15;f3;o" + (120 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k9;d0,26;<ETX>n" +
                "<STX>H16;f3;o" + (90 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k9;d0,26;<ETX>n" +
                "<STX>H12;f3;o" + (100 + ejeX * multiplicador) + "," + (420 + ejeY * multiplicador) + ";c26;b0;k12;d0,10;<ETX>n" +
                "<STX>H6;f3;o" + (55 + ejeX * multiplicador) + "," + (40 + ejeY * multiplicador) + ";c26;b0;k18;d0,16;<ETX>n" +
                "<STX>B11;f3;o" + (170 + ejeX * multiplicador) + "," + (620 + ejeY * multiplicador) + ";c17,200,0;w11;h11;i1;d0,10;<ETX>n" +
                "<STX>I11;f3;o" + (30 + ejeX * multiplicador) + "," + (620 + ejeY * multiplicador) + ";c20;h1;w1;b0<ETX>n" +
                "<STX>H25;f3;o" + (40 + ejeX * multiplicador) + "," + (640 + ejeY * multiplicador) + ";c26;b0;k7;d0,30;<ETX>n" +
                "<STX>R<ETX>")
    }

    private fun cambiarPapel(){
        blueTooth.imprimir("A"+
        "IG2" +
        "PM0" +
        "Z")
        hayRolloPapel = true
    }

    private fun cambiarEtiqueta(){
        blueTooth.imprimir("A"+
                "IG1" +
                "PM1" +
                "Z")
        hayRolloEtiqueta = true
    }

    private fun modificarX(cantidad: Int){
        ejeX += cantidad
        textoX.text = ejeX.toString()
    }

    private fun modificarY(cantidad: Int){
        ejeY += cantidad
        textoY.text = ejeY.toString()
    }

}