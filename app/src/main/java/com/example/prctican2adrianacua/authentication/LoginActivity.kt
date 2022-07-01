package com.example.prctican2adrianacua.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.data.services.AppPreferences
import com.example.prctican2adrianacua.databinding.ActivityLoginBinding
import com.example.prctican2adrianacua.home.HomeActivity

private lateinit var binding: ActivityLoginBinding
val INT_KEY = "key"

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_PrácticaN2AdrianAcuña)
        val view = binding.root
        setContentView(view)

        !binding.btnLoginLogin.isEnabled
        var userTextChanged : Boolean = false
        var pwTextChanged : Boolean = false

        binding.editTextUsernameLogin.addTextChangedListener {
                fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                    userTextChanged = true
            }
        }
        binding.editTextPasswordLogin.addTextChangedListener {
            fun onTextChanged(s: CharSequence, start: Int,
                              before: Int, count: Int) {
                pwTextChanged = true
            }
        }

        if (userTextChanged && pwTextChanged)
            binding.btnLoginLogin.isEnabled


        binding.btnLoginLogin.setOnClickListener {
          //  Así llamaría al usuario...
          //  SI TUVIERA AUTENTICACION
          //
          //  val user = AppPreferences.getUser(applicationContext)
          //  user?.let {
          //      val username: String = it.username
          //      val intent = Intent(this, HomeActivity::class.java)
          //      intent.putExtra(INT_KEY, username)
          //      startActivity(intent)
          //  }
            val username = binding.editTextUsernameLogin.text.toString()
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra(INT_KEY, username)
            startActivity(intent)
        }
    }
}