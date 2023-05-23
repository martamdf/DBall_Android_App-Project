package com.example.basicapp.UI.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basicapp.Data.LoginRepository
import com.example.basicapp.Data.Repository
import com.example.basicapp.Data.remote.GetHeroesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel(){

    private val repository = LoginRepository()


    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState : StateFlow<UiState> = _uiState
/*
    private val _uiState = MutableLiveData<String>()
    val uiState: LiveData<String> get() = _uiState*/


    fun loginTest(email: String, pass:String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getToken(email, pass) // Thread.sleep(1000)
            }
            _uiState.value = UiState.OnTokenReceived(result)
            Log.d("token", result)// suponiendo que llegue un token

        }
    }


}

sealed class UiState {
    object Idle : UiState()
    data class OnTokenReceived(val token:String) : UiState()
    data class Error(val error: String) : UiState()

    //
    //data class Success(val token:String): LoginState()
    //data class Failure(val error:String?):LoginState()
    //data class NetworkFailure(val code:Int, val message:String?): LoginState()


}