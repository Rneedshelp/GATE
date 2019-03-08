package com.example.richard.gate_app

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity

class LogInActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.login_activity)
        super.onCreate(savedInstanceState)

        login_button.setOnClickListener {
            if (TextUtils.isEmpty(user_login_input.text)) {
                user_login_input.setError("User with this username or password is not verified or exists.")
            }
            if (TextUtils.isEmpty(login_password_input.text)) {
                login_password_input.setError("Can't be Empty")
            }
            else {
                //call credentials first? to go to listener
                //on the data change if found, go to main
                //problem is the error afterwards
                val username = user_login_input.text.toString()
                val pass = login_password_input.text.toString()
                checkcredentials(username,pass)

            }

        }

    }

    private fun checkcredentials(username : String, pass : String){
        var security = false
        val credentials = FirebaseDatabase.getInstance().reference.child("users")
        credentials.addListenerForSingleValueEvent(object : ValueEventListener {
           override fun onCancelled(p0: DatabaseError) {
               Log.d("don't","Error occured in Database!")
           }

           override fun onDataChange(p0: DataSnapshot) {
               p0.children.forEach{
                   if(it.key.toString() == username) {
                           FirebaseAuth.getInstance().signInWithEmailAndPassword(it.child("Email").value.toString(),pass).addOnCompleteListener{
                               if(it.isSuccessful)
                               {
                                   security = true
                                   keylock()
                               }
                               else
                               {
                                   login_password_input.setError("Password does not exist")
                               }
                           }


                   }
               }

           }
       })
        if(!security)
        {
            user_login_input.setError("Username does not exist")
            Log.d("don't",security.toString())
        }



    }

    private fun keylock(){
            startActivity<MainActivity>()
            finish()
    }
}


