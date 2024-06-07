package com.espinoza.rudencio.pokeapi

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(
    private val context: Context
) : ViewModel() {

    val inputsError = MutableLiveData<Boolean>()
    val passwordMismatchError = MutableLiveData<Boolean>()
    val registrationSuccess = MutableLiveData<Boolean>()

    private val sharedPreferencesRepository: SharedPreferencesRepository = SharedPreferencesRepository().apply {
        setSharedPreferences(context)
    }

    fun registerUser(email: String, password: String, confirmPassword: String) {
        if (validateInputs(email, password, confirmPassword)) {
            sharedPreferencesRepository.apply {
                saveUserEmail(email)
                saveUserPassword(password)
            }
            registrationSuccess.postValue(true)
        }
    }

    private fun validateInputs(email: String, password: String, confirmPassword: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            inputsError.postValue(true)
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputsError.postValue(true)
            return false
        }

        if (password != confirmPassword) {
            passwordMismatchError.postValue(true)
            return false
        }

        return true
    }
}