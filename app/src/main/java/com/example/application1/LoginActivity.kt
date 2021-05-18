package com.example.application1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.application1.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val loginCheck = sharedPreferences.getBoolean("LOGGED_IN", false)
        if (loginCheck) {
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoginBtn.setOnClickListener {
            val sharedPreferences = getSharedPreferences("SP_INFO", MODE_PRIVATE)
            when {
                binding.Username.text.trim().isBlank() -> {
                    Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                }
                binding.Password.text.trim().length <= 6 -> Toast.makeText(
                    this,
                    "Password is too short",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("LOGGED_IN", true)
                    editor.apply()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}