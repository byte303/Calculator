package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRes.setOnClickListener {
            if(txtResult.length() > 0)
                checkResult()
        }
        btnToch.setOnClickListener {
            if(txtResult.length() > 0){
                if(txtResult.text[txtResult.length() - 1] != '+' &&
                    txtResult.text[txtResult.length() - 1] != '/' &&
                    txtResult.text[txtResult.length() - 1] != '*' &&
                    txtResult.text[txtResult.length() - 1] != '-'){
                    if(checkFloat())
                        txtResult.text = String.format("%s.", txtResult.text)
                }
            }
        }
        btnDelete.setOnClickListener {
            if(txtResult.length() > 0)
                txtResult.text = txtResult.text.substring(0, txtResult.length() - 1)
        }
    }

    private fun checkOperator(i : Int) : Boolean{
        if(txtResult.text[i] == '+' ||
            txtResult.text[i] == '/' ||
            txtResult.text[i] == '*' ||
            txtResult.text[i] == '-')
            return true

        return false
    }

    private fun checkSymbol(){
        if(checkOperator(txtResult.length() - 1)){
            txtResult.text = txtResult.text.substring(0, txtResult.length() - 1)
        }
    }

    private fun checkFloat() : Boolean{
        for(i in txtResult.length()-1 downTo 1) {
            if (checkOperator(i)) return true
            if (txtResult.text[i] == '.')
                return false
        }
        return true
    }

    private fun checkResult(){
        for(i in 0 until txtResult.length()) {
            if (i != txtResult.length() - 1 && i != 0) {

                when (txtResult.text[i]) {
                    '+' -> {
                        val num1 = txtResult.text.substring(0, i).toFloat()
                        val num2 = txtResult.text.substring(i+1,txtResult.length()).toFloat()

                        val result = num1 + num2
                        txtResult.text = result.toString()
                        return
                    }
                    '-' -> {
                        val num1 = txtResult.text.substring(0, i).toFloat()
                        val num2 = txtResult.text.substring(i+1,txtResult.length()).toFloat()

                        val result = num1 - num2
                        txtResult.text = result.toString()
                        return
                    }
                    '*' -> {
                        val num1 = txtResult.text.substring(0, i).toFloat()
                        val num2 = txtResult.text.substring(i+1,txtResult.length()).toFloat()

                        val result = num1 * num2
                        txtResult.text = result.toString()
                        return
                    }
                    '/' -> {
                        val num1 = txtResult.text.substring(0, i).toFloat()
                        val num2 = txtResult.text.substring(i+1,txtResult.length()).toFloat()

                        val result = num1 / num2
                        txtResult.text = result.toString()
                        return
                    }
                }
            }
        }
    }

    fun onClick(view: View) {

        val button : Button = findViewById(view.id)

        if(txtResult.length() <= 30)
            txtResult.text =  String.format("%s%s",txtResult.text,button.text)
        else
            Toast.makeText(applicationContext,R.string.text_error,Toast.LENGTH_SHORT).show()

    }

    fun onClickOperator(view: View) {
        val button : Button = findViewById(view.id)

        val tag = button.tag.toString()

        if(txtResult.length() > 0) {
            checkResult()
            checkSymbol()
            txtResult.text = String.format("%s%s", txtResult.text, tag)
        }
    }
}
