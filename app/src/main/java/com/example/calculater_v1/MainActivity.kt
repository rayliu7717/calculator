package com.example.calculater_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var inputNum = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvOne = findViewById<TextView>(R.id.tvOne)
        val prgressText = findViewById<TextView>(R.id.tvProgress)
        tvOne.setOnClickListener{
            inputNum += tvOne.text.toString()
            prgressText.text = inputNum
        }
    }
}