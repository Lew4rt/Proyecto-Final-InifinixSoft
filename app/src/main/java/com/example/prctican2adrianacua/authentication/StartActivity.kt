package com.example.prctican2adrianacua.authentication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.databinding.ActivityStartBinding

private lateinit var binding: ActivityStartBinding

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PrácticaN2AdrianAcuña_NoActionBar)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.root.setBackgroundColor(Color.parseColor("#F8F5F2"));
        binding.btnLoginStart.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignupStart.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}