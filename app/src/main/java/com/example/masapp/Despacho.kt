package com.example.masapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class Despacho(btHandler: BTHandler):Fragment() {

    private val blueTooth: BTHandler = btHandler
    private val multiplicador = 3
    private var ejeX: Int = 0
    private var ejeY: Int = 0

    private lateinit var textoX : TextView
    private lateinit var textoY : TextView

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

        //Botón cargar etiqueta1
        val botCargaE1: Button = vistaRetorno.findViewById(R.id.botCargaE1)
        botCargaE1.setOnClickListener {
            if(!blueTooth.logoCargado){
                blueTooth.mandarLogo()
            }
            if (blueTooth.btDevice?.name?.take(3).equals("RP4")) {
                blueTooth.imprimir("<STX><ESC>C<ETX>\n" +
                        "<STX><ESC>P<ETX>\n" +
                        "<STX>E1;F1;<ETX>\n" +
                        "<STX>B11;f2;o" + (460+ejeX*multiplicador) + "," +(130+ejeY*multiplicador)+ ";c2,0;w2;h90;i1;d0,10;<ETX>\n" +
                        "<STX>I11;f2;o" + (460+ejeX*multiplicador) + "," +(162+ejeY*multiplicador)+ ";c20;h1;w1;b0;<ETX>\n" +
                        "<STX>H6;f3;o" + (480+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;b0;k11;d0,18;<ETX>\n" +
                        "<STX>H8;f3;o" + (450+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;k12;d0,10;<ETX>\n" +
                        "<STX>H5;f3;o" + (420+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;b0;k11;d3,Ped:<ETX>\n" +
                        "<STX>H10;f3;o" + (420+ejeX*multiplicador) + "," +(290+ejeY*multiplicador)+ ";c26;b0;k11;d0,8;<ETX>\n" +
                        "<STX>U31;f3;o" + (475+ejeX*multiplicador) + "," +(580+ejeY*multiplicador)+ ";c2;w1;h1;<ETX>\n" +
                        "<STX>H26;f3;o" + (425+ejeX*multiplicador) + "," +(607+ejeY*multiplicador)+ ";c26;b0;k6;d3,Monroe Americana<ETX>\n" +
                        "<STX>H15;f3;o" + (370+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;b0;k14;d0,26;<ETX>\n" +
                        "<STX>H16;f3;o" + (330+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;b0;k14;d0,26;<ETX>\n" +
                        "<STX>H25;f3;o" + (260+ejeX*multiplicador) + "," +(310+ejeY*multiplicador)+ ";c26;b0;k12;d0,15;<ETX>\n" +
                        "<STX>H17;f3;o" + (220+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k10;d0,50;<ETX>\n" +
                        "<STX>H30;f3;o" + (160+ejeX*multiplicador) + "," +(70+ejeY*multiplicador)+ ";c26;b0;k8;d3,Bulto<ETX>\n" +
                        "<STX>H32;f3;o" + (160+ejeX*multiplicador) + "," +(260+ejeY*multiplicador)+ ";c26;b0;k8;d3,Turno<ETX>\n" +
                        "<STX>H34;f3;o" + (160+ejeX*multiplicador) + "," +(470+ejeY*multiplicador)+ ";c26;b0;k8;d3,Radio<ETX>\n" +
                        "<STX>H36;f3;o" + (160+ejeX*multiplicador) + "," +(670+ejeY*multiplicador)+ ";c26;b0;k8;d3,Orden<ETX>\n" +
                        "<STX>H21;f3;o" + (145+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k18;d0,28;<ETX>\n" +
                        "<STX>H13;f3;o" + (68+ejeX*multiplicador) + "," +(50+ejeY*multiplicador)+ ";c26;b0;k14;d0,30;<ETX>\n" +
                        "<STX>H12;f3;o" + (30+ejeX*multiplicador) + "," +(50+ejeY*multiplicador)+ ";c26;b0;k10;d0,10;<ETX>\n" +
                        "<STX>R<ETX>")
            } else {
                blueTooth.imprimir("<STX><ESC>C<ETX>\n" +
                        "<STX><ESC>P<ETX>\n" +
                        "<STX>E1;F1;<ETX>\n" +
                        "<STX>B11;f2;o" + (460+ejeX*multiplicador) + "," +(130+ejeY*multiplicador)+ ";c2,0;w2;h90;i1;d0,10;<ETX>\n" +
                        "<STX>I11;f2;o" + (460+ejeX*multiplicador) + "," +(162+ejeY*multiplicador)+ ";c20;h1;w1;b0;<ETX>\n" +
                        "<STX>H6;f3;o" + (480+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;b0;k11;d0,18;<ETX>\n" +
                        "<STX>H8;f3;o" + (450+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;k12;d0,10;<ETX>\n" +
                        "<STX>H5;f3;o" + (420+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;b0;k11;d3,Ped:<ETX>\n" +
                        "<STX>H10;f3;o" + (420+ejeX*multiplicador) + "," +(290+ejeY*multiplicador)+ ";c26;b0;k11;d0,8;<ETX>\n" +
                        "<STX>U31;f3;o" + (475+ejeX*multiplicador) + "," +(580+ejeY*multiplicador)+ ";c2;w1;h1;<ETX>\n" +
                        "<STX>H26;f3;o" + (425+ejeX*multiplicador) + "," +(607+ejeY*multiplicador)+ ";c26;b0;k6;d3,Monroe Americana<ETX>\n" +
                        "<STX>H15;f3;o" + (370+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;b0;k14;d0,26;<ETX>\n" +
                        "<STX>H16;f3;o" + (330+ejeX*multiplicador) + "," +(185+ejeY*multiplicador)+ ";c26;b0;k14;d0,26;<ETX>\n" +
                        "<STX>H25;f3;o" + (260+ejeX*multiplicador) + "," +(310+ejeY*multiplicador)+ ";c26;b0;k12;d0,15;<ETX>\n" +
                        "<STX>H17;f3;o" + (220+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k10;d0,50;<ETX>\n" +
                        "<STX>H30;f3;o" + (160+ejeX*multiplicador) + "," +(70+ejeY*multiplicador)+ ";c26;b0;k8;d3,Bulto<ETX>\n" +
                        "<STX>H32;f3;o" + (160+ejeX*multiplicador) + "," +(260+ejeY*multiplicador)+ ";c26;b0;k8;d3,Turno<ETX>\n" +
                        "<STX>H34;f3;o" + (160+ejeX*multiplicador) + "," +(470+ejeY*multiplicador)+ ";c26;b0;k8;d3,Radio<ETX>\n" +
                        "<STX>H36;f3;o" + (160+ejeX*multiplicador) + "," +(680+ejeY*multiplicador)+ ";c26;b0;k8;d3,Orden<ETX>\n" +
                        "<STX>H21;f3;o" + (145+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k17;d0,28;<ETX>\n" +
                        "<STX>H13;f3;o" + (68+ejeX*multiplicador) + "," +(50+ejeY*multiplicador)+ ";c26;b0;k14;d0,30;<ETX>\n" +
                        "<STX>H12;f3;o" + (30+ejeX*multiplicador) + "," +(50+ejeY*multiplicador)+ ";c26;b0;k10;d0,10;<ETX>\n" +
                        "<STX>R<ETX>")
            }
        }

        //Botón Imprimir
        val botImprimePack: Button = vistaRetorno.findViewById(R.id.botImprimePack)
        botImprimePack.setOnClickListener {
            if(!blueTooth.logoCargado){
                blueTooth.mandarLogo()
            }
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
            if(!blueTooth.logoCargado){
                blueTooth.mandarLogo()
            }
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
            if(!blueTooth.logoCargado){
                blueTooth.mandarLogo()
            }
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

        return vistaRetorno
    }

    fun modificarX(cantidad: Int){
        ejeX += cantidad
        textoX.text = ejeX.toString()
    }

    fun modificarY(cantidad: Int){
        ejeY += cantidad
        textoY.text = ejeY.toString()
    }
}