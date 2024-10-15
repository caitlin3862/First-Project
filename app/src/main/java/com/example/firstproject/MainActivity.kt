package com.example.firstproject

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout: LinearLayout = findViewById(R.id.main)
        val scoreText: TextView = findViewById(R.id.score)
        val strikeText: TextView = findViewById(R.id.strikes)
        var score = 0
        var strike = 0


    }
}