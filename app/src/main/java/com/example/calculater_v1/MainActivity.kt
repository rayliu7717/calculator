package com.example.calculater_v1

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.calculater_v1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sf : SharedPreferences
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.result.observe(this,{
            binding.tvResult.text = it
        })
        viewModel.progress.observe(this,{
            binding.tvProgress.text = it
        })

        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_main)
        viewModel.apply {
            binding.tvOne.setOnClickListener{ clickNumber(it)}
            binding.tvTwo.setOnClickListener{clickNumber(it) }
            binding.tvPlus.setOnClickListener{clickOperator(it) }
            binding.tvMinus.setOnClickListener{clickOperator(it) }
            binding.tvEqual.setOnClickListener{clickOperator(it) }
            binding.back.setOnClickListener{clickBackOperator() }
            binding.tvSign.setOnClickListener{clickSignOperator() }
        }
    }

    override fun onPause() {
        super.onPause()
        /*
        val result = binding.tvResult.text.toString()
        val progress = binding.tvProgress.text.toString()
        sf.edit().apply{
            putString("sf_result", result)
            putString("sf_progress", progress)
            commit()
        }

         */
    }

    override fun onResume() {
        super.onResume()
        /*
        val result = sf.getString("sf_result", null)
        val progress = sf.getString("sf_progress", null)
        if(result != null ){
            binding.tvResult.text = result
            binding.tvProgress.text = progress
        }
         */
    }
}