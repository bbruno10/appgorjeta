package com.example.appgorjeta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appgorjeta.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDone.setOnClickListener {
            val totalTableTemp = binding.tieTotal.text
            val numPeopleTemp = binding.tieNumPeople.text
            val percentageTemp = binding.tiePercentage.text

            if (totalTableTemp?.isEmpty() == true ||
                numPeopleTemp?.isEmpty() == true ||
                percentageTemp?.isEmpty() == true
            ) {

                Snackbar.make(
                    binding.tieTotal,
                    "Preencha todos os campos",
                    Snackbar.LENGTH_LONG
                ).show()

            } else {

                val totalTable: Float = binding.tieTotal.text.toString().toFloat()
                val nPeople: Int = numPeopleTemp.toString().toInt()
                val percentage: Int = percentageTemp.toString().toInt()

                val totalTemp = totalTable / nPeople
                val tips = totalTemp * percentage / 100
                val totalTips = totalTemp + tips

                val intent = Intent(this, SummaryActivity::class.java)
                intent.apply {
                    putExtra("totalTable", totalTable)
                    putExtra("numPeople", nPeople)
                    putExtra("percentage", percentage)
                    putExtra("totalAmount", totalTips)
                }
                clean()
                startActivity(intent)
            }
        }
        binding.btnClean.setOnClickListener {
            clean()
        }
    }

    private fun clean() {
        binding.tieTotal.setText("")
        binding.tieNumPeople.setText("")
        binding.tiePercentage.setText("")

    }
}