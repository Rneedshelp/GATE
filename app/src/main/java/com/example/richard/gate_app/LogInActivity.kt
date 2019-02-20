package com.example.richard.gate_app

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.startActivity

class LogInActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.login_activity)
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance()


        login_button.setOnClickListener {
            if (TextUtils.isEmpty(user_login_input.text)) {
                user_login_input.setError("User with this username or password is not verified or exists.")
            }
            if (TextUtils.isEmpty(login_password_input.text)) {
                login_password_input.setError("Can't be Empty")
            }
            else {

                val username = user_login_input.text.toString()
                val pass = login_password_input.text.toString()
                user.signInWithCustomToken(username).continueWith{
                    if(checkcredentials(it.result.toString()))
                    {
                        startActivity<MainActivity>()
                        finish()
                    }
                    else{
                        user_login_input.setError("Username does not exist")
                        login_password_input.setError("Password does not exist")
                    }
                }

            }


        }
    }

    fun checkcredentials(credential : String) : Boolean{


        return true
    }

}