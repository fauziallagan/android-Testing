package com.example.testing

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvWidth: EditText
    private lateinit var tvHeight:EditText
    private  lateinit var tvLength:EditText
    private lateinit var tvCalculate:Button
    private lateinit var tvResult: TextView
    companion object{
        private  const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvWidth = findViewById(R.id.width)
        tvHeight = findViewById(R.id.height)
        tvLength = findViewById(R.id.length)
        tvCalculate = findViewById(R.id.calculate)
        tvResult = findViewById(R.id.result)
        tvCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onClick(v:View){

        if (v.id == R.id.calculate){
            val inputLength = tvLength.text.toString().trim()
            val inputWidth = tvWidth.text.toString().trim()
            val inputHeight = tvHeight.text.toString().trim()

            var isEmptyFields = false
            var isInvalidDouble = false

            if (inputLength.isEmpty()){
                isEmptyFields =true
                tvLength.error = "Field is Not Empty!!"
            }
            if (inputHeight.isEmpty()){
                isEmptyFields = true
                tvHeight.error = "Field is Not Empty!!"
            }
            if (inputWidth.isEmpty()){
                isEmptyFields = true
                tvWidth.error = "Field is Not Empty!!"
            }

            val length = toDouble(inputLength)
            val height = toDouble(inputHeight)
            val width = toDouble(inputWidth)

            if (length == null){
                isInvalidDouble = true
                tvLength.error = "This Field Must be a Valid Number"
            }
            if (height == null){
                isInvalidDouble = true
                tvHeight.error = "This Field Must be a Valid Number"
            }
            if (width == null){
                isInvalidDouble = true
                tvWidth.error = "This Field Must be a Valid Number"
            }

            if (!isEmptyFields && !isInvalidDouble){
                val volume = length as Double * width as Double * height as Double
                tvResult.text = volume.toString()
            }
        }
    }

    private  fun toDouble(str: String): Double? {
        return try{
            str.toDouble()
        }catch (e: NumberFormatException){
            null
        }
    }

}



