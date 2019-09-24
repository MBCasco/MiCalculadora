package com.example.micalculadora

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var RESULTADOG = "resultadog"
    var MEMORIAG = "memoriag"
    var queHacer=0  //Variable para saber que hacer
    var valor1=0                    //valores para las operaciones
    var valor2=0
    var resultado=0                 //resultado de una operacion
    var memoria=0                   //Memoria de resultado
    var cambiarBinario=false        //controlador para el binario
    /*1=suma, 2=resta, 3=multiplica*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            resultado=savedInstanceState.getInt("resultadog")
            memoria=savedInstanceState.getInt("memoriag")
            textResultado.text=resultado.toString()
        }//Devuelve el estado de variables en caso de cambio de actividad

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        // Guarda el estado de la vista en variables (principio de clase)
        if (outState != null) {
            outState.putInt(RESULTADOG,resultado)
            outState.putInt(MEMORIAG, memoria)
        }
        //Llama al metodo de la clase super para guardar el estado de la View
        super.onSaveInstanceState(outState)
    }//Guarda el estado de las variables

    fun numero(v: View) {
        val valorNumerico = findViewById<Button>(v.id)
        val textNumerico = valorNumerico.text.toString()
        //muestra los numeros que coge concadenadamente
        textResultado.text="${textResultado.text.toString()}${textNumerico}"
    }//Funcion cuando pulsas boton

    /*Funciones aparte*/
    fun borrarText(v: View) {
        textResultado.text=null
        resultado=0                 //Pone el resultado a 0
        queHacer=0                  //No hace nada resetea
    }//Borra toda la caja de texto


    fun operar(v: View) {
        if (textResultado.length()>0) {
            val valorDeOperacion = findViewById<Button>(v.id)
            if(valorDeOperacion.text.toString()=="+") {
                queHacer=1
            } else if(valorDeOperacion.text.toString()=="-") {
                queHacer=2
            } else if (valorDeOperacion.text.toString()=="*") {
                queHacer=3
            } else if (valorDeOperacion.text.toString()=="/") {
                queHacer=4
            } else {
                queHacer = 0  //No hace nada
            }
            valor1=textResultado.text.toString().toInt()    //obtiene el valor del text
            textResultado.text=null                         //limpia el valor
        }//comprueba que no este a nulo
    }//hace la suma o la resta con el siguiente numero introducido

    fun relizarOperacion(v: View) {
        if(queHacer!=0) {
            valor2 = textResultado.text.toString().toInt()    //obtiene el valor del text
            if(queHacer==1) {
                resultado = valor1+valor2
                textResultado.text="${resultado}"
            }else if(queHacer==2) {
                resultado = valor1-valor2
                textResultado.text="${resultado}"
            }else if(queHacer==3) {
                resultado = valor1*valor2
                textResultado.text="${resultado}"
            }else if(queHacer==4) {
                resultado = valor1/valor2
                textResultado.text="${resultado}"
            }else if (queHacer==0) {
                textResultado.text=null                     //limpia el valor
            }//comprueba que operacion hacer
        }//Comprueba que haya una operacion
        else {
            textResultado.text="0"
        }
    }//realiza la operacion final

}
