package com.suitmedia.assignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.suitmedia.assignment.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var getResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nameTV = binding.nameTV
        val usernameTV = binding.usernameTV
        val chooseBtn = binding.chooseBtn
        val backBtn = binding.backButton
        nameTV.text = intent.getStringExtra("name")

        getResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle the Intent returned from the ThirdActivity
                val data: Intent? = result.data
                val usernameResult = data?.getStringExtra("username")
                // Use the received data
                usernameTV.text = usernameResult ?: "No item selected"
            }
        }

        chooseBtn.setOnClickListener{
            val intent = Intent(this,ThirdActivity::class.java)
            getResultLauncher.launch(intent)
        }

        backBtn.setOnClickListener{
            finish()
        }
    }

}