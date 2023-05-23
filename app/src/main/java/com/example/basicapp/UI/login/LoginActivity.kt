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
import com.example.basicapp.UI.SuperHeroesActivity
import com.example.basicapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            tryLogin()
            launchActivity()
        }

        lifecycleScope.launch {
            viewModel.uiState.collect{
                when (it){
                    is UiState.OnTokenReceived -> saveToken(it.token)
                    is UiState.Idle -> Unit
                    is UiState.Error -> showError(viewModel.uiState.value.toString())
                }
            }
        }
        //setSupportActionBar(binding.toolbar)

        /*
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/

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

    private fun saveToken(value: String) {
        val prefs = getSharedPreferences("auth", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("token", value)
        editor.apply()
    }
}


