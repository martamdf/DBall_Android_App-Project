package com.example.basicapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapp.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState : StateFlow<UiState> = _uiState

    fun loginTest(email: String, pass:String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.login(email, pass)
            }
            if(result.isSuccess){
                _uiState.value = UiState.OnTokenReceived
            }
            else {
                val unAuth = result.toString().contains("401")
                if(unAuth){
                    _uiState.value = UiState.AuthError("Error de autenticación.")
                    }
                else{
                    _uiState.value = UiState.Error(result.toString())
                }
                _uiState.value = UiState.Idle
            }
        }
    }
}

sealed class UiState {
    object Idle : UiState()
    object OnTokenReceived : UiState()
    data class Error(val message: String) : UiState()
    data class AuthError(val message: String) : UiState()
}