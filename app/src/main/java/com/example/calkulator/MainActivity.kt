package com.example.calkulator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.operator.Operator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_0.setOnClickListener{setTextFields("0")}
        btn_1.setOnClickListener{setTextFields("1")}
        btn_2.setOnClickListener{setTextFields("2")}
        btn_3.setOnClickListener{setTextFields("3")}
        btn_4.setOnClickListener{setTextFields("4")}
        btn_5.setOnClickListener{setTextFields("5")}
        btn_6.setOnClickListener{setTextFields("6")}
        btn_7.setOnClickListener{setTextFields("7")}
        btn_8.setOnClickListener{setTextFields("8")}
        btn_9.setOnClickListener{setTextFields("9")}
        minus_btn.setOnClickListener{setTextFields("-")}
        plus_btn.setOnClickListener{setTextFields("+")}
        mult_btn.setOnClickListener{setTextFields("*")}
        desivion_btn.setOnClickListener{setTextFields("/")}
        bracket_open_btn.setOnClickListener{setTextFields("(")}
        bracket_close_btn.setOnClickListener{setTextFields(")")}
        clear_btn.setOnClickListener{
            math_operation.text = ""
            result_text.text = ""
        }
        back_btn.setOnClickListener{
            val str = math_operation.text.toString()
            if(str.isNotEmpty()) math_operation.text = str.substring(0,str.length -1)
            result_text.text = "" }


        equal_btn.setOnClickListener{
            val e: math_operation = ExpressionBuilder("3 * sin(y) - 2 / (x - 2)")
            .build()
            val result: Double = e.result_text()
        }






    }
    fun setTextFields(str:String){
        math_operation.append(str)
    }



}
