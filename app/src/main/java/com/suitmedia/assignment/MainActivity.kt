package com.suitmedia.assignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.suitmedia.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameET = binding.nameET
        val palindromeET = binding.palindromeET
        val checkBtn = binding.checkBtn
        val nextBtn = binding.nextBtn
        var dialogTV = binding.dialogTV
        isPalindrome(palindromeET.text.toString())

        checkBtn.setOnClickListener {
            dialogTV.visibility = View.VISIBLE
            if(isPalindrome(palindromeET.text.toString())){
                dialogTV.text = "isPalindrome"
            }
            else{
                dialogTV.text = "notPalindrome"
            }
        }

        nextBtn.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name",nameET.text.toString())
            startActivity(intent)
        }
    }

    private fun isPalindrome(string: String): Boolean{
        val string2 = string.reversed()
        return string == string2
    }
}