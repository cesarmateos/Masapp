package com.example.masapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textoEstadoConexion: TextView? = findViewById(R.id.estadoConexion) as TextView
        var btHandler: BTHandler = BTHandler(textoEstadoConexion, this)
        var posicionListaImpresora: Int = 0;

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
        val button: Button = findViewById(R.id.botConectar)
        button.setOnClickListener {
            btHandler.conectar(posicionListaImpresora)
        }

        //Botón cargar etiqueta1
        val botCargaE1: Button = findViewById(R.id.botCargaE1)
        botCargaE1.setOnClickListener {
            btHandler.imprimir("<STX><ESC>C<ETX>\n" +
                    "<STX><ESC>P<ETX>\n" +
                    "<STX>E1;F1;<ETX>\n" +
                    "<STX>B11;f2;o460,130;c2,0;w2;h90;i1;d0,10;<ETX>\n" +
                    "<STX>I11;f2;o460,162;c20;h1;w1;b0;<ETX>\n" +
                    "<STX>H6;f3;o480,185;c26;b0;k11;d0,18;<ETX>\n" +
                    "<STX>H8;f3;o450,185;c26;k12;d0,10;<ETX>\n" +
                    "<STX>H5;f3;o420,185;c26;b0;k11;d3,Ped:<ETX>\n" +
                    "<STX>H10;f3;o420,290;c26;b0;k11;d0,8;<ETX>\n" +
                    "<STX>U31;f3;o475,580;c2;w1;h1;<ETX>\n" +
                    "<STX>H26;f3;o425,607;c26;b0;k6;d3,Monroe Americana<ETX>\n" +
                    "<STX>H15;f3;o370,185;c26;b0;k14;d0,26;<ETX>\n" +
                    "<STX>H16;f3;o330,185;c26;b0;k14;d0,26;<ETX>\n" +
                    "<STX>H25;f3;o260,310;c26;b0;k12;d0,15;<ETX>\n" +
                    "<STX>H17;f3;o220,40;c26;b0;k10;d0,50;<ETX>\n" +
                    "<STX>H30;f3;o160,70;c26;b0;k8;d3,Bulto<ETX>\n" +
                    "<STX>H32;f3;o160,260;c26;b0;k8;d3,Turno<ETX>\n" +
                    "<STX>H34;f3;o160,470;c26;b0;k8;d3,Radio<ETX>\n" +
                    "<STX>H36;f3;o160,670;c26;b0;k8;d3,Orden<ETX>\n" +
                    "<STX>H21;f3;o145,40;c26;b0;k17;d0,28;<ETX>\n" +
                    "<STX>H13;f3;o68,50;c26;b0;k14;d0,30;<ETX>\n" +
                    "<STX>H12;f3;o30,50;c26;b0;k10;d0,10;<ETX>\n" +
                    "<STX>R<ETX>")
        }

        //Botón Imprimir
        val botImprimeE1: Button = findViewById(R.id.botImprimeE1)
        botImprimeE1.setOnClickListener {
            btHandler.imprimir("<STX>R<ETX> \n" +
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

        //Botón Cargar Logo
        val botCargarLogo: Button = findViewById(R.id.botCargaLogo)
        botCargarLogo.setOnClickListener {
            btHandler.imprimir("<STX><ESC>C<ETX>\n" +
                    "<STX><ESC>P<ETX>\n" +
                    "<STX>G2,Masa;x174;y50;<ETX>\n" +
                    "<STX>u0,@@@@`\u007F@@@;<ETX>\n" +
                    "<STX>u1,@@@@|\u007FG@@;<ETX>\n" +
                    "<STX>u2,@@@@\u007F\u007FO@@;<ETX>\n" +
                    "<STX>u3,@@@`\u007F\u007F\u007F@@;<ETX>\n" +
                    "<STX>u4,@@@p\u007F\u007F\u007FA@;<ETX>\n" +
                    "<STX>u5,@@@x\u007F\u007F\u007FC@;<ETX>\n" +
                    "<STX>u6,@@@|\u007FGxG@;<ETX>\n" +
                    "<STX>u7,@@@~\u007F@@O@;<ETX>\n" +
                    "<STX>u8,@@@\u007FO@@\\@;<ETX>\n" +
                    "<STX>u9,@@`\u007FG@@X@;<ETX>\n" +
                    "<STX>u10,@@p\u007FC@@p@;<ETX>\n" +
                    "<STX>u11,@@p\u007FA@@`A;<ETX>\n" +
                    "<STX>u12,@@x\u007F@@@@C;<ETX>\n" +
                    "<STX>u13,@@|_@@@@B;<ETX>\n" +
                    "<STX>u14,@@|O@@@@@;<ETX>\n" +
                    "<STX>u15,@@~G@@@@@;<ETX>\n" +
                    "<STX>u16,@@~G@@@@@;<ETX>\n" +
                    "<STX>u17,@@\u007FC@@@@@;<ETX>\n" +
                    "<STX>u18,@@\u007FC@@@@@;<ETX>\n" +
                    "<STX>u19,@`\u007FA@@@@@;<ETX>\n" +
                    "<STX>u20,@`\u007F@@@@@@;<ETX>\n" +
                    "<STX>u21,@`\u007F@@@@@@;<ETX>\n" +
                    "<STX>u22,@p\u007F@@@@@@;<ETX>\n" +
                    "<STX>u23,@p_@@@@@@;<ETX>\n" +
                    "<STX>u24,@x_@@@@@@;<ETX>\n" +
                    "<STX>u25,@xO@@@@\u007FA;<ETX>\n" +
                    "<STX>u26,@xO@@@\u007F\u007FA;<ETX>\n" +
                    "<STX>u27,@|O@@\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u28,@|G@\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u29,@|G~\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u30,@~g\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u31,@~c\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u32,@~c\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u33,@~c\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u34,@\u007Fa\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u35,@\u007Fa\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u36,@\u007Fa\u007F\u007F\u007F\u007FG@;<ETX>\n" +
                    "<STX>u37,@\u007Fa\u007F\u007F\u007FG@@;<ETX>\n" +
                    "<STX>u38,`\u007F`\u007F\u007FG@@@;<ETX>\n" +
                    "<STX>u39,`\u007F`\u007F_@@@@;<ETX>\n" +
                    "<STX>u40,`\u007F@xG@@@@;<ETX>\n" +
                    "<STX>u41,`\u007F@|C@@@@;<ETX>\n" +
                    "<STX>u42,p_@~C@@\u007FA;<ETX>\n" +
                    "<STX>u43,p_@~C@\u007F\u007FA;<ETX>\n" +
                    "<STX>u44,p_@\u007FG\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u45,p_@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u46,p_@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u47,pO`\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u48,xO`\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u49,xO`\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u50,xO@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u51,xO@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u52,xO@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u53,xO@~\u007F\u007F\u007FO@;<ETX>\n" +
                    "<STX>u54,xG@|\u007F\u007FO@@;<ETX>\n" +
                    "<STX>u55,|G@x\u007FO@@@;<ETX>\n" +
                    "<STX>u56,|G@p_@@@@;<ETX>\n" +
                    "<STX>u57,|G@xG@@@@;<ETX>\n" +
                    "<STX>u58,|G@|G@@@@;<ETX>\n" +
                    "<STX>u59,|G@~C@@~A;<ETX>\n" +
                    "<STX>u60,|G@\u007FC@~\u007FA;<ETX>\n" +
                    "<STX>u61,|C@\u007FG~\u007F\u007FA;<ETX>\n" +
                    "<STX>u62,|C@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u63,~C`\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u64,~C`\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u65,~C`\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u66,~C@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u67,~C@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u68,~C@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u69,~C@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u70,~C@~\u007F\u007F\u007F\u007F@;<ETX>\n" +
                    "<STX>u71,~C@|\u007F\u007F\u007FA@;<ETX>\n" +
                    "<STX>u72,~A@x\u007F\u007FA@@;<ETX>\n" +
                    "<STX>u73,~A@`\u007FAP@@;<ETX>\n" +
                    "<STX>u74,~A@@@@\u007FG@;<ETX>\n" +
                    "<STX>u75,~A@@@`\u007FO@;<ETX>\n" +
                    "<STX>u76,\u007FA@@@p\u007F_@;<ETX>\n" +
                    "<STX>u77,\u007FA@@Bx\u007F\u007F@;<ETX>\n" +
                    "<STX>u78,\u007FA@@G|\u007F\u007FA;<ETX>\n" +
                    "<STX>u79,\u007FA@pG~\u007F\u007FA;<ETX>\n" +
                    "<STX>u80,\u007FA@xG~\u007F\u007FA;<ETX>\n" +
                    "<STX>u81,\u007FA@xG~\u007F\u007FA;<ETX>\n" +
                    "<STX>u82,\u007FA@|G\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u83,\u007FA@~O\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u84,\u007FA@~O\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u85,\u007FA@~O\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u86,\u007FA@\u007FO\u007Fw\u007FA;<ETX>\n" +
                    "<STX>u87,\u007FA@\u007Fo\u007FA\u007FA;<ETX>\n" +
                    "<STX>u88,\u007FA@\u007Fg\u007F@\u007F@;<ETX>\n" +
                    "<STX>u89,\u007FA@\u007Fc\u007F@\u007F@;<ETX>\n" +
                    "<STX>u90,\u007FA@\u007Fa_`_@;<ETX>\n" +
                    "<STX>u91,\u007F@@\u007Fa_`O@;<ETX>\n" +
                    "<STX>u92,\u007F@`\u007Fa_pG@;<ETX>\n" +
                    "<STX>u93,\u007F@`\u007Fa_~\u007FA;<ETX>\n" +
                    "<STX>u94,\u007F@`\u007Fa\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u95,\u007F@@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u96,\u007F@@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u97,\u007F@@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u98,\u007F@@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u99,\u007F@@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u100,\u007F@@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u101,\u007F@@~\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u102,\u007F@@~\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u103,\u007F@@~\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u104,\u007F@@|\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u105,\u007F@@x\u007F\u007F_@@;<ETX>\n" +
                    "<STX>u106,\u007F@@p\u007F_@@@;<ETX>\n" +
                    "<STX>u107,\u007F@@@_@P@@;<ETX>\n" +
                    "<STX>u108,\u007F@@@@@pA@;<ETX>\n" +
                    "<STX>u109,\u007F@@@@@pG@;<ETX>\n" +
                    "<STX>u110,\u007F@@@|ApO@;<ETX>\n" +
                    "<STX>u111,\u007F@@`\u007FGxO@;<ETX>\n" +
                    "<STX>u112,\u007F@@p\u007FOx_@;<ETX>\n" +
                    "<STX>u113,\u007F@@x\u007F_x\u007F@;<ETX>\n" +
                    "<STX>u114,~@@|\u007F\u007Fx\u007F@;<ETX>\n" +
                    "<STX>u115,~@@|\u007F\u007Fx\u007F@;<ETX>\n" +
                    "<STX>u116,~@@~\u007F\u007Fy\u007FA;<ETX>\n" +
                    "<STX>u117,~@@~\u007F\u007Fy\u007FA;<ETX>\n" +
                    "<STX>u118,~@@\u007F\u007F\u007Fa\u007FA;<ETX>\n" +
                    "<STX>u119,~A@\u007F\u007F\u007Fc\u007FA;<ETX>\n" +
                    "<STX>u120,~A@\u007F\u007F\u007FC\u007FA;<ETX>\n" +
                    "<STX>u121,~A@\u007F\u007F\u007FC\u007FA;<ETX>\n" +
                    "<STX>u122,~A@\u007Fq\u007FC\u007FA;<ETX>\n" +
                    "<STX>u123,~A`\u007Fq\u007FG\u007FA;<ETX>\n" +
                    "<STX>u124,~A`\u007Fq\u007FG\u007FA;<ETX>\n" +
                    "<STX>u125,~A`\u007Fq\u007FG\u007FA;<ETX>\n" +
                    "<STX>u126,|A@\u007Fa\u007Fg\u007FA;<ETX>\n" +
                    "<STX>u127,|A@\u007Fa\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u128,|A@\u007Fc\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u129,|A@\u007Fg\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u130,|A@\u007FO\u007F\u007F\u007F@;<ETX>\n" +
                    "<STX>u131,|A@~G\u007F\u007F\u007F@;<ETX>\n" +
                    "<STX>u132,|A@~G\u007F\u007F\u007F@;<ETX>\n" +
                    "<STX>u133,|A@|G~\u007F_@;<ETX>\n" +
                    "<STX>u134,xA@|G~\u007FO@;<ETX>\n" +
                    "<STX>u135,xC@xG|\u007FG@;<ETX>\n" +
                    "<STX>u136,xC@pCx\u007FC@;<ETX>\n" +
                    "<STX>u137,xC@`C`\u007FA@;<ETX>\n" +
                    "<STX>u138,xC@@C@@@@;<ETX>\n" +
                    "<STX>u139,xC@@@@x@@;<ETX>\n" +
                    "<STX>u140,pC@@@@\u007FG@;<ETX>\n" +
                    "<STX>u141,pC@@@`\u007FO@;<ETX>\n" +
                    "<STX>u142,pC@@@x\u007F_@;<ETX>\n" +
                    "<STX>u143,pC@@Bx\u007F\u007F@;<ETX>\n" +
                    "<STX>u144,pG@@G|\u007F\u007FA;<ETX>\n" +
                    "<STX>u145,`G@pG~\u007F\u007FA;<ETX>\n" +
                    "<STX>u146,`G@xG~\u007F\u007FA;<ETX>\n" +
                    "<STX>u147,`G@xG~\u007F\u007FA;<ETX>\n" +
                    "<STX>u148,`G@|G\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u149,@G@~O\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u150,@O@~O\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u151,@O@~O\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u152,@O@\u007FO\u007Fc\u007FA;<ETX>\n" +
                    "<STX>u153,@N@\u007Fo\u007FA\u007FA;<ETX>\n" +
                    "<STX>u154,@N@\u007Fc\u007F@\u007F@;<ETX>\n" +
                    "<STX>u155,@N@\u007Fc_@\u007F@;<ETX>\n" +
                    "<STX>u156,@\\@\u007Fa_`_@;<ETX>\n" +
                    "<STX>u157,@\\@\u007Fa_`O@;<ETX>\n" +
                    "<STX>u158,@\\`\u007Fa_pG@;<ETX>\n" +
                    "<STX>u159,@\\`\u007Fa_~\u007FA;<ETX>\n" +
                    "<STX>u160,@x`\u007Fc\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u161,@x@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u162,@p@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u163,@p@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u164,@p@\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u165,@`A\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u166,@`A\u007F\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u167,@`A~\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u168,@@A~\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u169,@@C|\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u170,@@B|\u007F\u007F\u007F\u007FA;<ETX>\n" +
                    "<STX>u171,@@Fx\u007F\u007F_@@;<ETX>\n" +
                    "<STX>u172,@@Dp\u007F_@@@;<ETX>\n" +
                    "<STX>u173,@@H@^@@@@;<ETX>\n" +
                    "<STX>R<ETX>")
        }
    }
}