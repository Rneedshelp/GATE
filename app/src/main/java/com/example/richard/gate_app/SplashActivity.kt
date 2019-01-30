package com.example.richard.gate_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private var delayhandler: Handler? = null
    private val delay : Long = 10000

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_splash)

        delayhandler = Handler()

        delayhandler!!.postDelayed(runnable,delay)

    }

    internal val runnable : Runnable = Runnable {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

    }

}