package com.example.doner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import java.lang.Thread.sleep

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val thread: Thread = Thread()
        {
            try {
                sleep(2000)
            }
            catch (e : Exception){
                e.printStackTrace()
            }
            finally{
                val intent  : Intent = Intent(this, MainActivity :: class.java)
                startActivity(intent)
            }
        }
        thread.start()
    }
}