package com.example.basicapp.UI.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.basicapp.UI.SuperHeroesActivity
import com.example.basicapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    //private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            tryLogin()
            //launchActivity()
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
}


