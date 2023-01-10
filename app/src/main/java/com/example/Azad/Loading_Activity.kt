package com.example.Azad

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.Azad.MainActivity
import com.example.Azad.R
import java.util.*


class Loading_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        val animationView: LottieAnimationView = findViewById(R.id.lotte_loading)
        animationView.setAnimation(R.raw.avantio_multilenguaje)
        animationView.playAnimation()
        Handler().postDelayed({
            val myIntent = Intent(this, MainActivity::class.java)
            myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.startActivity(myIntent)
        }, 1500)
    }

    override fun onRestart() {
        super.onRestart()
        val myIntent = Intent(this, MainActivity::class.java)
        myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        this.startActivity(myIntent)
    }
}