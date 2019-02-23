package com.example.richard.gate_app

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.startActivity



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_splash)
        super.onCreate(savedInstanceState)
        splash_button.setOnClickListener {

                val user = FirebaseAuth.getInstance().currentUser
                if (user != null && user.isEmailVerified) {
                    startActivity<LogInActivity>()
                    finish()
                }
                else{
                    startActivity<LogInActivity>()
                    finish()
                }


        }

    }
}
