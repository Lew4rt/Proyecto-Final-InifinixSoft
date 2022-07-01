package com.example.prctican2adrianacua.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.databinding.ActivityHomeBinding
import com.example.prctican2adrianacua.databinding.ActivityLoginBinding

private lateinit var binding: ActivityHomeBinding

val INT_KEY = "key"

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_PrácticaN2AdrianAcuña)
        val view = binding.root
        setContentView(view)

        val extras = intent.extras
        extras?.let {
            val username = it.getString(INT_KEY)
            binding.textViewHome.text = "Welcome, \n" + username + "!"
        }
    }
}