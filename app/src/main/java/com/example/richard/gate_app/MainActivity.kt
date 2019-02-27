package com.example.richard.gate_app

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        //val binding : ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding.setLifecycleOwner(this) //adding lifecycle dependencies owner to main activity
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
