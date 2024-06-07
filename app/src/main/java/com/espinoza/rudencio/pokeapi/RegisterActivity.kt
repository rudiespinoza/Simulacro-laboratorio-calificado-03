package com.espinoza.rudencio.pokeapi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.espinoza.rudencio.pokeapi.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = RegisterViewModel(this)
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        registerViewModel.apply {
            inputsError.observe(this@RegisterActivity) {
            }
            passwordMismatchError.observe(this@RegisterActivity) {
            }
            registrationSuccess.observe(this@RegisterActivity) {
                navigateToLogin()
            }
        }
    }

    private fun setupListeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                registerViewModel.registerUser(
                    email = edtEmail.text.toString(),
                    password = edtPassword.text.toString(),
                    confirmPassword = edtPassword2.text.toString()
                )
            }
            btnLoginUser.setOnClickListener {
                showToast("Ok, regresamos porque ya tienes cuenta")
                navigateToLogin()
            }
            btnBackClose.setOnClickListener {
                showToast("Ok, regresamos...")
                navigateToLogin()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}