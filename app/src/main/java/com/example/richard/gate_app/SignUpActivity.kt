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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.email
import org.jetbrains.anko.startActivity
import java.sql.Ref
import kotlin.math.sign
import android.R.attr.name




class SignUpActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        val newuser = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_signup)
        super.onCreate(savedInstanceState)

        reg_button.setOnClickListener(){
            if(TextUtils.isEmpty(email_input.text)){
            email_input.setError("Can't be empty")
            }
            else if(TextUtils.isEmpty(signup_pass.text) || signup_pass.text.length < 6) {
                signup_pass.setError("Can't be empty")
            }
            else{
                newuser.createUserWithEmailAndPassword(email_input.text.toString(),signup_pass.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful) {
                            if(it.result!!.user.sendEmailVerification().isSuccessful) {
                                createuser(it.result!!.user)
                            }

                        }
                        else {
                            startActivity<LogInActivity>()
                            finish()
                        }
                    }
            }

        }
    }

    fun createuser(user: FirebaseUser){
        val username = userid_input.text.toString()
       val pf : UserProfileChangeRequest = UserProfileChangeRequest.Builder()
           .setDisplayName(username).build()


        var reference : DatabaseReference
        val usermap = HashMap<String?, String?>()

        usermap["name"] =  user.displayName
        usermap["Email"] = user.email
        usermap["Password"] = signup_pass.text.toString()


        reference = FirebaseDatabase.getInstance().reference.child("users").child(username)
        reference.setValue(usermap)
    }


}

