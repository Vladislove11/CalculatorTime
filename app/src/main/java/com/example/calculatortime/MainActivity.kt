package com.example.calculatortime

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firstOperandET: EditText
    private lateinit var secondOperandET: EditText

    private lateinit var buttonSumBTN: Button
    private lateinit var buttonDifBTN: Button

    private lateinit var TextViewResultTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        firstOperandET = findViewById(R.id.FirstOperantET)
        secondOperandET =  findViewById(R.id.SecondOperantET)

        buttonDifBTN = findViewById(R.id.ButtonDifBTN)
        buttonSumBTN = findViewById(R.id.ButtonSumBTN)

        TextViewResultTV = findViewById(R.id.ResultTV)

        buttonDifBTN.setOnClickListener(this)
        buttonDifBTN.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(firstOperandET.text.isEmpty() || secondOperandET.text.isEmpty()){
            return
        }

        var first = firstOperandET.text.toString()
        var second = secondOperandET.text.toString()

        val firstSecond = parsStringTimeToSecond(first)
        val secondsSecond = parsStringTimeToSecond(second)

        var result = when(view.id) {
            R.id.ButtonSumBTN -> Operation(firstSecond, secondsSecond).sum()
            R.id.ButtonDifBTN -> Operation(firstSecond, secondsSecond).dif()
            else -> 0
        }

        TextViewResultTV.text = parsStringSecondToTime(result)
    }

    private fun parsStringTimeToSecond(str: String): Int{
        var hour = 0
        var minute = 0
        var second = 0

        if(str.indexOf('h') > 0){
            hour = str.substring(0 until str.indexOf('h')).toInt() * 60 * 60
        }

        if(str.indexOf('m') > 0){
            if (hour > 0) {
                minute = str.substring(str.indexOf('h')+1 until str.indexOf('m')).toInt() * 60
            } else {
                minute = str.substring(0 until str.indexOf('m')).toInt() * 60
            }
        }

        if(str.indexOf('s') > 0){
            if(hour == 0 && minute == 0) {
                second = str.substring(0 until str.indexOf('s')).toInt()
            } else if (hour > 0 && minute == 0 ){
                second = str.substring(str.indexOf('h')+1 until str.indexOf('s')).toInt()
            } else if (minute > 0){
                second = str.substring(str.indexOf('m')+1 until str.indexOf('s')).toInt()
            }
        }

        return hour + minute + second

    }

    private fun parsStringSecondToTime(timeSec: Int): String{
        var hour: Int = 0
        var minute:Int = 0
        var second: Int = 0
        var result: String = ""

        hour = timeSec / 3600

        if (timeSec % 60 > 0 ){
            minute = (timeSec - 3600 * hour)/60
        }

        if ((timeSec - 3600 * hour) % 60 > 0){
            second = (timeSec - 3600 * hour) - minute * 60
        }

        if (hour > 0) result = hour.toString() + "h"
        if (minute > 0) result += minute.toString() + "m"
        if (second > 0) result += second.toString() + "s"

        return result
    }
}