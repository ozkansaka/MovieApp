package com.movieapp.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.movieapp.R
import com.movieapp.databinding.ActivitySplashBinding
import com.movieapp.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val main = Intent(this, MainActivity::class.java)


        var i = 0
        val timer = object : CountDownTimer(2000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.splashProgress.progress = i * 100 / (2000 / 1000)
                i++
            }

            override fun onFinish() {
                i++
                binding.splashProgress.progress = 100
                overridePendingTransition(0, 0)
                main.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(main)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }
        }
        timer.start()
    }
}