package com.example.basicapp.UI.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.basicapp.UI.heroes.SuperHeroesActivity
import com.example.basicapp.databinding.ActivityLoginBinding
import com.example.basicapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        Constants.instance(applicationContext);

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            tryLogin()
        }

        lifecycleScope.launch {
            viewModel.uiState.collect{
                when (it){
                    is UiState.OnTokenReceived -> launchActivity()
                    is UiState.Idle -> Log.d("idle", viewModel.uiState.value.toString())
                    is UiState.Error -> showError(it.message)
                }
            }
        }
    }

    private fun tryLogin(){
        val email = binding.etEmailAddress.text.trim().toString()
        val password = binding.editTextTextPassword.text.trim().toString()
        viewModel.loginTest(email, password)
    }

    private fun launchActivity(){
        val intent = Intent(this, SuperHeroesActivity::class.java)
        startActivity(intent)
    }

    private fun showError(error: String) {
        Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
    }
}


