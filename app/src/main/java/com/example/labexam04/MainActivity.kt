package com.example.labexam04

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextImg : ImageView = findViewById(R.id.imgViewLogo)
        nextImg.setOnClickListener{
            val intent = Intent(this, BottomNavBar::class.java)
            startActivity(intent)
            finish()
        }

    }
}