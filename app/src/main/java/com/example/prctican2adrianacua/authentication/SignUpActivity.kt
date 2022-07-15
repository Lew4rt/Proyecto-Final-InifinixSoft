package com.example.prctican2adrianacua.authentication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.prctican2adrianacua.R
import com.example.prctican2adrianacua.data.model.Usuario
import com.example.prctican2adrianacua.data.services.AppPreferences
import com.example.prctican2adrianacua.databinding.ActivityLoginBinding
import com.example.prctican2adrianacua.databinding.ActivitySignUpBinding

private lateinit var binding: ActivitySignUpBinding
class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_PrácticaN2AdrianAcuña_NoActionBar)
        val view = binding.root
        setContentView(view)
        binding.root.setBackgroundColor(Color.parseColor("#F8F5F2"));
        binding.btnSignupSignUp.isEnabled = false
        binding.checkBoxSignUp.setOnClickListener{binding.btnSignupSignUp.isEnabled = binding.checkBoxSignUp.isChecked}


        binding.btnSignupSignUp.setOnClickListener {
            val user = Usuario()
            //Validar que todos los campos estén ingresados
            //Si hay alguno que no: retorno
            if (binding.editTextNameSignUp.text.isBlank()) {
                Toast.makeText(
                    applicationContext,
                    "Todos los campos son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (binding.editTextSurnameSignUp.text.isBlank()) {
                Toast.makeText(
                    applicationContext,
                    "Todos los campos son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (binding.editTextUserameSignUp.text.isBlank()) {
                Toast.makeText(
                    applicationContext,
                    "Todos los campos son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (binding.editTextEmailSignUp.text.isBlank()) {
                Toast.makeText(
                    applicationContext,
                    "Todos los campos son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (binding.editTextPasswordSignUp.text.isBlank()) {
                Toast.makeText(
                    applicationContext,
                    "Todos los campos son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (binding.editTextConfirmPasswordSignUp.text.isBlank()) {
                Toast.makeText(
                    applicationContext,
                    "Todos los campos son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!binding.editTextPasswordSignUp.text.toString().equals(binding.editTextConfirmPasswordSignUp.text.toString())) {
                Toast.makeText(
                    applicationContext,
                    "Las contraseñas deben coincidir",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            user.nombre = binding.editTextNameSignUp.text.toString()
            user.apellido = binding.editTextSurnameSignUp.text.toString()
            user.username = binding.editTextUserameSignUp.text.toString()
            user.email = binding.editTextEmailSignUp.text.toString()
            user.password = binding.editTextPasswordSignUp.text.toString()

            AppPreferences.saveUser(applicationContext, user)
            Toast.makeText(applicationContext, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()
            intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }
    }
}