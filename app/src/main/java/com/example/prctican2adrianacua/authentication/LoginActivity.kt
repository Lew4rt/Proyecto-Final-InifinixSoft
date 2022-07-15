package com.example.prctican2adrianacua.authentication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.data.services.AppPreferences
import com.example.prctican2adrianacua.databinding.ActivityLoginBinding
import com.example.prctican2adrianacua.home.activities.HomeActivity

private lateinit var binding: ActivityLoginBinding
val INT_KEY = "key"

class LoginActivity : AppCompatActivity() {
    var userTextChanged : Boolean = false
    var pwTextChanged : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_PrácticaN2AdrianAcuña_NoActionBar)
        val view = binding.root
        setContentView(view)
        binding.root.setBackgroundColor(Color.parseColor("#F8F5F2"));

        binding.btnLoginLogin.isEnabled = false

        binding.editTextUsernameLogin.addTextChangedListener {
            userTextChanged = it?.length != 0
            validateFields()
        }

        binding.editTextPasswordLogin.addTextChangedListener {
            pwTextChanged = it?.length != 0
            validateFields()
        }

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
            val user = AppPreferences.getUser(applicationContext)
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra(INT_KEY, user)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun validateFields() {
        binding.btnLoginLogin.isEnabled = userTextChanged && pwTextChanged
    }
}