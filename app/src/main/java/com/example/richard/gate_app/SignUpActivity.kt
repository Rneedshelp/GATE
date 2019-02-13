package com.example.richard.gate_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.startActivity
import kotlin.math.sign


class SignUpActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        val newuser = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_signup)
        super.onCreate(savedInstanceState)

        reg_button.setOnClickListener(){
            if(TextUtils.isEmpty(email_input.text)){
            email_input.setError("Can't be empty")
            }
            else if(TextUtils.isEmpty(signup_pass.text)) {
                signup_pass.setError("Can't be empty")
            }
            else{
                newuser.createUserWithEmailAndPassword(email_input.text.toString(),signup_pass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            createuser(it.result!!.user)
                            if (newuser.currentUser!!.sendEmailVerification().isSuccessful) {
                                startActivity<MainActivity>()
                                finish()
                            }
                        } else {

                        }

                    }
            }

        }
    }

    fun createuser(user: FirebaseUser){
        val username = userid_input.text.toString()
       val pf : UserProfileChangeRequest = UserProfileChangeRequest.Builder()
           .setDisplayName(username).build()
        user.updateProfile(pf).addOnCompleteListener{
            if(it.isSuccessful)
            {

            }
        }


    }


}