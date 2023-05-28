package com.example.basicapp.UI.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapp.Data.LoginRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel(){

    private val repository = LoginRepositoryImpl()

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState : StateFlow<UiState> = _uiState

    fun loginTest(email: String, pass:String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getToken(email, pass) // Thread.sleep(1000)
            }

            if(result != "Error"){
                _uiState.value = UiState.OnTokenReceived(result)
            }
            else {
                _uiState.value = UiState.Error(result)
                _uiState.value = UiState.Idle
            }
        }
    }
}

sealed class UiState {
    object Idle : UiState()
    data class OnTokenReceived(val token:String) : UiState()
    data class Error(val message: String) : UiState()
}