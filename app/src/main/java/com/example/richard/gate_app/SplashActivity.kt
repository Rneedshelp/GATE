package com.example.richard.gate_app

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_splash)
        super.onCreate(savedInstanceState)


        Handler().postDelayed({startActivity<MainActivity>()},3000)

    }
}
