package com.example.masapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class Predespacho(btHandler: BTHandler): Fragment() {

    val blueTooth: BTHandler = btHandler
    val multiplicador = 5
    var ejeX: Int = 0
    var ejeY: Int = 0

    lateinit var textoArriba : TextView
    lateinit var textoAbajo : TextView
    lateinit var textoDerecha : TextView
    lateinit var textoIzquierda : TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var vistaRetorno = inflater.inflate(R.layout.predespacho, container, false)
        textoArriba = vistaRetorno.findViewById(R.id.textoArriba)
        textoAbajo = vistaRetorno.findViewById(R.id.textoAbajo)
        textoDerecha = vistaRetorno.findViewById(R.id.textoDer)
        textoIzquierda = vistaRetorno.findViewById(R.id.textoIzq)
        textoArriba.text = ejeX.toString()
        textoAbajo.text = (-ejeX).toString()
        textoDerecha.text = ejeY.toString()
        textoIzquierda.text = (-ejeY).toString()

        val botCargaE11: Button = vistaRetorno.findViewById(R.id.botCargaE11)
        botCargaE11.setOnClickListener {
            if(!blueTooth.mandoLogo){
                blueTooth.mandarLogo()
            }
            blueTooth.imprimir("<STX><ESC>C<ETX>\n" +
                    "<STX><ESC>P<ETX>\n" +
                    "<STX>E11;F11;<ETX>\n" +
                    "<STX>U31;f3;o" + (475+ejeX*multiplicador) + "," +(35+ejeY*multiplicador)+ ";c2;w1;h1;<ETX>\n" +
                    "<STX>H26;f3;o" + (425+ejeX*multiplicador) + "," +(62+ejeY*multiplicador)+ ";c26;b0;k6;d3,Monroe Americana<ETX>\n" +
                    "<STX>H4;f3;o" + (465+ejeX*multiplicador) + "," +(250+ejeY*multiplicador)+ ";c26;b0;k15;d3,Ped:<ETX>\n" +
                    "<STX>H10;f3;o" + (465+ejeX*multiplicador) + "," +(345+ejeY*multiplicador)+ ";c26;b0;k15;d0,10;<ETX>\n" +
                    "<STX>H8;f3;o" + (465+ejeX*multiplicador) + "," +(570+ejeY*multiplicador)+ ";c26;b0;k12;d0,10;<ETX>n" +
                    "<STX>H17;f3;o" + (370+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k10;d0,50;<ETX>n" +
                    "<STX>H27;f3;o" + (290+ejeX*multiplicador) + "," +(55+ejeY*multiplicador)+ ";c26;b0;k7;d3,Entrega<ETX>n" +
                    "<STX>H28;f3;o" + (290+ejeX*multiplicador) + "," +(235+ejeY*multiplicador)+ ";c26;b0;k7;d3,Radio<ETX>n" +
                    "<STX>H29;f3;o" + (290+ejeX*multiplicador) + "," +(440+ejeY*multiplicador)+ ";c26;b0;k7;d3,Salida<ETX>n" +
                    "<STX>H30;f3;o" + (290+ejeX*multiplicador) + "," +(680+ejeY*multiplicador)+ ";c26;b0;k7;d3,Orden<ETX>n" +
                    "<STX>H21;f3;o" + (285+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k29;d0,30;<ETX>n" +
                    "<STX>H13;f3;o" + (160+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k12;d0,26;<ETX>n" +
                    "<STX>H15;f3;o" + (120+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k9;d0,26;<ETX>n" +
                    "<STX>H16;f3;o" + (90+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k9;d0,26;<ETX>n" +
                    "<STX>H12;f3;o" + (100+ejeX*multiplicador) + "," +(420+ejeY*multiplicador)+ ";c26;b0;k12;d0,10;<ETX>n" +
                    "<STX>H6;f3;o" + (55+ejeX*multiplicador) + "," +(40+ejeY*multiplicador)+ ";c26;b0;k18;d0,16;<ETX>n" +
                    "<STX>B11;f3;o" + (170+ejeX*multiplicador) + "," +(620+ejeY*multiplicador)+ ";c17,200,0;w11;h11;i1;d0,10;<ETX>n" +
                    "<STX>I11;f3;o" + (30+ejeX*multiplicador) + "," +(620+ejeY*multiplicador)+ ";c20;h1;w1;b0<ETX>n" +
                    "<STX>H25;f3;o" + (40+ejeX*multiplicador) + "," +(640+ejeY*multiplicador)+ ";c26;b0;k7;d0,30;<ETX>n" +
                    "<STX>R<ETX>")
        }

        //Botón Imprimir
        val botImprimeE11: Button = vistaRetorno.findViewById(R.id.botImprimeE11)
        botImprimeE11.setOnClickListener {
            if(!blueTooth.mandoLogo){
                blueTooth.mandarLogo()
            }
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

        val botArriba: Button = vistaRetorno.findViewById(R.id.botArriba)
        botArriba.setOnClickListener {
            modificarX(1)
        }
        val botAbajo: Button = vistaRetorno.findViewById(R.id.botAbajo)
        botAbajo.setOnClickListener {
            modificarX(-1)
        }
        val botDerecha: Button = vistaRetorno.findViewById(R.id.botDer)
        botDerecha.setOnClickListener {
            modificarY(1)
        }
        val botIzquierda: Button = vistaRetorno.findViewById(R.id.botIzq)
        botIzquierda.setOnClickListener {
            modificarY(-1)
        }

        return vistaRetorno
    }

    fun modificarX(cantidad: Int){
        ejeX += cantidad
        textoArriba.text = ejeX.toString()
        textoAbajo.text = (-ejeX).toString()
    }

    fun modificarY(cantidad: Int){
        ejeY += cantidad
        textoDerecha.text = ejeY.toString()
        textoIzquierda.text = (-ejeY).toString()
    }
}