package com.example.annotation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        supportActionBar?.hide()
        findViewById<Button>(R.id.button_continue).setOnClickListener {
            //Add nome do usuário no localStorage
            //navegar para main activiti e matar essa tela pra sempre
            addNameUserStorage()
        }
    }

    private fun addNameUserStorage(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}