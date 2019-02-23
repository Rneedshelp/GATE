package com.example.richard.gate_app

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_activity.*
import org.jetbrains.anko.startActivity

class LogInActivity : AppCompatActivity(){
   var security = false
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
                    if(checkcredentials(username,pass)) {
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

    private fun checkcredentials(username : String, pass : String) : Boolean{
//KEY VALUE = USERNAME
        val credentials = FirebaseDatabase.getInstance().reference.child("users")
        credentials.addListenerForSingleValueEvent(object : ValueEventListener {
           override fun onCancelled(p0: DatabaseError) {

           }

           override fun onDataChange(p0: DataSnapshot) {
               p0.children.forEach{

                   if(it.key.toString() == username) {
                       Log.d("don't",username)
                       if(it.child("Password").value.toString() == pass){
                           Log.d("don't",username)
                           FirebaseAuth.getInstance().signInWithEmailAndPassword(it.child("Email").value.toString(),pass)
                           security = true
                           return
                       }
                   }

               }

           }
       })
        Log.d("don't",security.toString())
        return security

    }
}


