package com.example.richard.gate_app

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class LogInActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.login_activity)
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance()
        val fb : FirebaseUser? = user.currentUser
        if(fb!!.isEmailVerified && user!=null)
        {
            startActivity<SignUpActivity>()
        }
        else
        {
            startActivity<MainActivity>()
        }
    }
}