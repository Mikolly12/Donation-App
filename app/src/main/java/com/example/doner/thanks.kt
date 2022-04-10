package com.example.doner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class thanks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanks)

    }

    fun contin(view: android.view.View) {
        val intent = Intent(this,donatePage::class.java)
        startActivity(intent)
        finish()
    }
}