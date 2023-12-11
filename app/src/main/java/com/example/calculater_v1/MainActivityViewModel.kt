package com.example.calculater_v1

import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){
    private var firstOperand = ""
    private var lastOperator =""
    private var inputNum = ""
    var progress = MutableLiveData<String>()
    var result = MutableLiveData<String>()
    init{
        result.value = ""
        progress.value = ""
    }

    fun clickNumber(v: View) {
        val textView = v as TextView
        inputNum += textView.text.toString()
        if(lastOperator.isEmpty())
            firstOperand = inputNum
        displayExpression()
    }

    fun clickSignOperator() {
        if(lastOperator.isEmpty()){
            if(firstOperand.isNotEmpty())
            {
                val v = firstOperand.toInt()
                firstOperand = (-v).toString()
            }
        }
        else if (inputNum.isNotEmpty())
        {
            val v = inputNum.toInt()
            inputNum = (-v).toString()
        }
        displayExpression()
    }

    fun clickBackOperator() {
        if(inputNum.isNotEmpty()) {
            inputNum = inputNum.dropLast(1)
            if(lastOperator.isEmpty() )
                firstOperand = inputNum
        }
        else if(lastOperator.isNotEmpty())
            lastOperator =""
        else if(lastOperator.isEmpty() && firstOperand.isNotEmpty())
            firstOperand = firstOperand.dropLast(1)
        displayExpression()
    }

    fun clickOperator(v: View) {
        val textView= v as TextView
        if(lastOperator.isEmpty())
            lastOperator = textView.text.toString()
        else{
            if(inputNum.isEmpty())
                lastOperator = textView.text.toString()
            else{
                var x = 0
                if(firstOperand.isNotEmpty())
                    x = firstOperand.toInt()
                var r = calculate(x, lastOperator, inputNum.toInt())

                //binding.tvResult.text = r
                result.value = r
                firstOperand = r

                if(v.text.toString() == "=")
                    lastOperator = ""
                else
                    lastOperator = v.text.toString()
            }
        }
        inputNum = ""
        displayExpression()
    }


    private fun displayExpression() {
        var str = ""
        str = firstOperand.toString()
        if(lastOperator.isNotEmpty() &&  lastOperator!="=") {
            str += " " + lastOperator + " "
            str += inputNum
        }
        progress.value = str
    }

    private fun calculate(x: Int, op: String, y: Int): String {
        if(op == "+")
            return (x+y).toString()
        if(op == "-")
            return (x-y).toString()
        return ""
    }

}