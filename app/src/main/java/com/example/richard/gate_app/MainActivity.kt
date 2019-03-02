package com.example.richard.gate_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        //val binding : ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding.setLifecycleOwner(this) //adding lifecycle dependencies owner to main activity
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

        val newuser = FirebaseAuth.getInstance().currentUser
        FirebaseAuth.getInstance().signOut()

        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_friends -> {
                    Log.d("don't","ON CLICKED friends")
                    supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.container_profile)!!).commit()
                    true
                }

                R.id.navigation_settings-> {
                   // supportFragmentManager.beginTransaction().replace(R.id.container_profile, ProfileFragment()).commit()
                    Log.d("don't","ON CLICKED SETTINGS")
                    supportFragmentManager.transaction { replace(R.id.container_profile, ProfileFragment()) }
                    true
                }

                else -> false
            }
        }
    }

    inline fun FragmentManager.transaction(now: Boolean = false, allowStateLoss: Boolean = false, body: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().body().commit()
    }
}
