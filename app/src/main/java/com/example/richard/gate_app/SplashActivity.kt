package com.example.richard.gate_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private var delayhandler: Handler? = null
    private val delay : Long = 5000

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        setContentView(R.layout.activity_splash)

        delayhandler = Handler()

        delayhandler?.postDelayed(runnable,delay)


        super.onCreate(savedInstanceState, persistentState)
    }

    internal val runnable : Runnable = Runnable{

        intent = Intent(this,SplashActivity::class.java)
        startActivity(intent)
        finish()
    }
}