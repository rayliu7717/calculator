package com.example.calculater_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.calculater_v1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var firstOperand = ""
    private var lastOperator =""
    private var inputNum = ""
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_main)
        binding.tvOne.setOnClickListener{clickNumber(it) }
        binding.tvTwo.setOnClickListener{clickNumber(it) }
        binding.tvPlus.setOnClickListener{clickOperator(it) }
        binding.tvMinus.setOnClickListener{clickOperator(it) }
        binding.tvEqual.setOnClickListener{clickOperator(it) }
        binding.back.setOnClickListener{clickBackOperator() }
        binding.tvSign.setOnClickListener{clickSignOperator() }
    }

    private fun clickSignOperator() {
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

    private fun clickBackOperator() {
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

    private fun clickOperator(v: View) {
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

                binding.tvResult.text = r
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

    private fun calculate(x: Int, op: String, y: Int): String {
        if(op == "+")
            return (x+y).toString()
        if(op == "-")
            return (x-y).toString()
        return ""
    }

    private fun clickNumber(v: View) {
        val textView= v as TextView
        inputNum += textView.text.toString()
        if(lastOperator.isEmpty())
            firstOperand = inputNum
        displayExpression()
    }
    private fun displayExpression() {
        var str = ""
        str = firstOperand.toString()
        if(lastOperator.isNotEmpty() &&  lastOperator!="=") {
            str += " " + lastOperator + " "
            str += inputNum
        }
        binding.tvProgress.text = str
    }
}