package com.example.richard.gate_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        val newuser = FirebaseAuth.getInstance().currentUser
        if(newuser != null)
        {
            Log.d("don't",newuser.uid)
        }
        FirebaseAuth.getInstance().signOut()
        startActivity<SplashActivity>()
        finish()


        navigation.setOnNavigationItemReselectedListener {

            when(it.itemId){
                R.id.navigation_friends -> {
                    true
                }

                R.id.navigation_settings->{
                    //todo
                    true
                }

            }
        }
    }
}
