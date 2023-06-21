package com.example.basicapp.UI.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapp.Data.Repository
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