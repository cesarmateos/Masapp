package com.example.masapp

import android.R.attr.gravity
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.Fragment


class Despacho(btHandler: BTHandler):Fragment() {

    private val blueTooth: BTHandler = btHandler
    private val multiplicador = 4
    private var ejeX: Int = 0
    private var ejeY: Int = 0

    private lateinit var textoX : TextView
    private lateinit var textoY : TextView
    private lateinit var switchGire : ToggleButton

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vistaRetorno = inflater.inflate(R.layout.despacho, container, false)
        textoX = vistaRetorno.findViewById(R.id.DesTextoX)
        textoY = vistaRetorno.findViewById(R.id.DesTextoY)
        textoX.text = ejeX.toString()
        textoY.text = ejeY.toString()


        //Botón Imprimir Pack
        val botImprimePack: Button = vistaRetorno.findViewById(R.id.botImprimePack)
        botImprimePack.setOnClickListener {
            cargarFormato()
            blueTooth.imprimir("<STX>R<ETX> \n" +
                    "<STX><ESC>E1<CAN><ETX>\n" +
                    "<STX><ESC>F6<DEL>Bulto 351874129<ETX>\n" +
                    "<STX><ESC>F8<DEL>11/02/2021<ETX>\n" +
                    "<STX><ESC>F10<DEL>1<ETX>\n" +
                    "<STX><ESC>F11<DEL>09663168<ETX>\n" +
                    "<STX><ESC>F12<DEL>( 5113101)<ETX>\n" +
                    "<STX><ESC>F13<DEL>FCIA. NOBEL ESPERANZA<ETX>\n" +
                    "<STX><ESC>F15<DEL>TAFIROL 500MG                <ETX>\n" +
                    "<STX><ESC>F16<DEL>EXHIB. X10X40 BLISTERS<ETX>\n" +
                    "<STX><ESC>F17<DEL>2   (Desc  1) [ #E1C16]<ETX>\n" +
                    "<STX><ESC>F21<DEL>0001   11/02  A1 14:10   025<ETX>\n" +
                    "<STX><ESC>F25<DEL>Fal. 0/ 2<ETX>\n" +
                    "<STX><RS>1<ETB><FF><ETX>")
        }

        val botImprimeRef: Button = vistaRetorno.findViewById(R.id.botImprimeRef)
        botImprimeRef.setOnClickListener {
            cargarFormato()
            blueTooth.imprimir("<STX>R<ETX> \n" +
                    "<STX><ESC>E1<CAN><ETX>\n" +
                    "<STX><ESC>F6<DEL>Etiq. de DESPACHO<ETX>\n" +
                    "<STX><ESC>F8<DEL>17/03/2021<ETX>\n" +
                    "<STX><ESC>F10<DEL>1169<ETX>\n" +
                    "<STX><ESC>F11<DEL>0351014150<ETX>\n" +
                    "<STX><ESC>F12<DEL>( 1472101)<ETX>\n" +
                    "<STX><ESC>F13<DEL>DESCALZI <ETX>\n" +
                    "<STX><ESC>F15<DEL>Estrada 2500              <ETX>\n" +
                    "<STX><ESC>F16<DEL>Villa Maipu<ETX>\n" +
                    "<STX><ESC>F17<DEL>***PRODUCTOS A REFRIGERAR ENTRE 2 y 8 GRADOS<ETX>\n" +
                    "<STX><ESC>F21<DEL>0001   17/03  U7 23:40   007<ETX>\n" +
                    "<STX><RS>1<ETB><FF><ETX>")
        }

        val botImprimeIOMA: Button = vistaRetorno.findViewById(R.id.botImprimeIOMA)
        botImprimeIOMA.setOnClickListener {
            cargarFormato()
            blueTooth.imprimir("<STX>R<ETX> \n" +
                    "<STX><ESC>E1<CAN><ETX>\n" +
                    "<STX><ESC>F6<DEL>Etiq. de DESPACHO<ETX>\n" +
                    "<STX><ESC>F8<DEL>18/03/2021<ETX>\n" +
                    "<STX><ESC>F10<DEL>1871<ETX>\n" +
                    "<STX><ESC>F11<DEL>0351020723<ETX>\n" +
                    "<STX><ESC>F12<DEL>( INIO)<ETX>\n" +
                    "<STX><ESC>F13<DEL>AMARTINO<ETX>\n" +
                    "<STX><ESC>F15<DEL>O ROARKE 2408<ETX>\n" +
                    "<STX><ESC>F16<DEL>BARADERO<ETX>\n" +
                    "<STX><ESC>F17<DEL>*                             <ETX>\n" +
                    "<STX><ESC>F21<DEL>0001   18/03  V2 23:58   002<ETX>\n" +
                    "<STX><ESC>F25<DEL>BUSCEMI FRAN<ETX>\n" +
                    "<STX><RS>1<ETB><FF><ETX>")
        }

        //Botonera Cursores
        val botArriba: ImageButton = vistaRetorno.findViewById(R.id.DesBotArriba)
        botArriba.setOnClickListener {
            modificarX(1)
        }
        val botAbajo: ImageButton = vistaRetorno.findViewById(R.id.DesBotAbajo)
        botAbajo.setOnClickListener {
            modificarX(-1)
        }
        val botDerecha: ImageButton = vistaRetorno.findViewById(R.id.DesBotDer)
        botDerecha.setOnClickListener {
            modificarY(1)
        }
        val botIzquierda: ImageButton = vistaRetorno.findViewById(R.id.DesBotIzq)
        botIzquierda.setOnClickListener {
            modificarY(-1)
        }

        switchGire= vistaRetorno.findViewById(R.id.switchGire)

        switchGire.setPadding(50, 0, 0, 0)
        switchGire.setOnClickListener{
            if(switchGire.isChecked){
                switchGire.setPadding(0, 0, 50, 0)
                switchGire.setTextColor(Color.parseColor("#FFFFFF"))
            }else{
                switchGire.setPadding(50, 0, 0, 0)
                switchGire.setTextColor(Color.parseColor("#000000"))

            }

        }

        return vistaRetorno
    }

    private fun cargarFormato(){
        blueTooth.mandarLogo()
        lateinit var posicionesEnX:IntArray
        lateinit var posicionesEnY:IntArray
        lateinit var tamanioTexto:IntArray
        lateinit var orientacion:IntArray

        var modificadorPosicionX:Int
        var modificadorPosicionY:Int

        if(switchGire.isChecked){
            modificadorPosicionX = - ejeX * multiplicador
            modificadorPosicionY = - ejeY * multiplicador
            orientacion = intArrayOf(4,1)
        }else{
            modificadorPosicionX = + ejeX * multiplicador
            modificadorPosicionY = + ejeY * multiplicador
            orientacion = intArrayOf(2,3)
        }

        if (blueTooth.btDevice?.name?.take(3).equals("RP4")) {
            tamanioTexto = intArrayOf(18)
            if (switchGire.isChecked) {
                posicionesEnX = intArrayOf(30, 30, 10, 45, 80, 80, 15, 65, 125, 165, 230, 270, 328, 328, 328, 328, 345, 422, 460)
                posicionesEnY = intArrayOf(670, 638, 610, 610, 610, 500, 230, 203, 610, 610, 490, 760, 730, 540, 340, 115, 765, 750, 750)
            }else{
                posicionesEnX = intArrayOf(460,460,480,445,410,410,475,425,365,325,260,220,162,162,162,162,145,68,30)
                posicionesEnY = intArrayOf(130,162,185,185,185,290,600,627,185,185,310,40,60,250,450,685,35,50,50)
            }

        } else {
            tamanioTexto = intArrayOf(17)
            if (switchGire.isChecked) {
                posicionesEnX = intArrayOf(30, 30, 10, 40, 70, 70, 15, 65, 120, 160, 230, 270, 330, 330, 330, 330, 345, 422, 460)
                posicionesEnY = intArrayOf(670, 638, 610, 610, 610, 500, 215, 188, 610, 610, 490, 760, 730, 540, 340, 110, 760, 750, 750)
            } else {
                posicionesEnX = intArrayOf(460, 460, 480, 450, 420, 420, 475, 425, 370, 330, 260, 220, 160, 160, 160, 160, 145, 68, 30)
                posicionesEnY = intArrayOf(130, 162, 185, 185, 185, 290, 585, 612, 185, 185, 310, 40, 70, 260, 470, 680, 40, 50, 50)
            }
        }

        for (posicion in posicionesEnX.indices) {
            posicionesEnX[posicion] = posicionesEnX[posicion] + modificadorPosicionX
        }
        for (posicion in posicionesEnY.indices) {
            posicionesEnY[posicion] = posicionesEnY[posicion] + modificadorPosicionY
        }

        blueTooth.imprimir("<STX><ESC>C<ETX>\n" +
                "<STX><ESC>P<ETX>\n" +
                "<STX>E1;F1;<ETX>\n" +
                "<STX>B11;f" + orientacion[0] + ";o" + posicionesEnX[0] + "," + posicionesEnY[0] + ";c2,0;w2;h90;i1;d0,10;<ETX>\n" +
                "<STX>I11;f" + orientacion[0] + ";o" + posicionesEnX[1] + "," + posicionesEnY[1] + ";c20;h1;w1;b0;<ETX>\n" +
                "<STX>H6;f" + orientacion[1] + ";o" + posicionesEnX[2] + "," + posicionesEnY[2] + ";c26;b0;k11;d0,18;<ETX>\n" +
                "<STX>H8;f" + orientacion[1] + ";o" + posicionesEnX[3] + "," + posicionesEnY[3] + ";c26;k12;d0,10;<ETX>\n" +
                "<STX>H5;f" + orientacion[1] + ";o" + posicionesEnX[4] + "," + posicionesEnY[4] + ";c26;b0;k11;d3,Ped:<ETX>\n" +
                "<STX>H10;f" + orientacion[1] + ";o" + posicionesEnX[5] + "," + posicionesEnY[5] + ";c26;b0;k11;d0,8;<ETX>\n" +
                "<STX>U31;f" + orientacion[1] + ";o" + posicionesEnX[6] + "," + posicionesEnY[6] + ";c2;w1;h1;<ETX>\n" +
                "<STX>H26;f" + orientacion[1] + ";o" + posicionesEnX[7] + "," + posicionesEnY[7] + ";c26;b0;k6;d3,Monroe Americana<ETX>\n" +
                "<STX>H15;f" + orientacion[1] + ";o" + posicionesEnX[8] + "," + posicionesEnY[8] + ";c26;b0;k14;d0,26;<ETX>\n" +
                "<STX>H16;f" + orientacion[1] + ";o" + posicionesEnX[9] + "," + posicionesEnY[9] + ";c26;b0;k14;d0,26;<ETX>\n" +
                "<STX>H25;f" + orientacion[1] + ";o" + posicionesEnX[10] + "," + posicionesEnY[10] + ";c26;b0;k12;d0,15;<ETX>\n" +
                "<STX>H17;f" + orientacion[1] + ";o" + posicionesEnX[11] + "," + posicionesEnY[11] + ";c26;b0;k10;d0,50;<ETX>\n" +
                "<STX>H30;f" + orientacion[1] + ";o" + posicionesEnX[12] + "," + posicionesEnY[12] + ";c26;b0;k8;d3,Bulto<ETX>\n" +
                "<STX>H32;f" + orientacion[1] + ";o" + posicionesEnX[13] + "," + posicionesEnY[13] + ";c26;b0;k8;d3,Turno<ETX>\n" +
                "<STX>H34;f" + orientacion[1] + ";o" + posicionesEnX[14] + "," + posicionesEnY[14] + ";c26;b0;k8;d3,Radio<ETX>\n" +
                "<STX>H36;f" + orientacion[1] + ";o" + posicionesEnX[15] + "," + posicionesEnY[15] + ";c26;b0;k8;d3,Orden<ETX>\n" +
                "<STX>H21;f" + orientacion[1] + ";o" + posicionesEnX[16] + "," + posicionesEnY[16] + ";c26;b0;k" + tamanioTexto[0] + ";d0,28;<ETX>\n" +
                "<STX>H13;f" + orientacion[1] + ";o" + posicionesEnX[17] + "," + posicionesEnY[17] + ";c26;b0;k14;d0,30;<ETX>\n" +
                "<STX>H12;f" + orientacion[1] + ";o" + posicionesEnX[18] + "," + posicionesEnY[18] + ";c26;b0;k10;d0,10;<ETX>\n" +
                "<STX>R<ETX>")
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