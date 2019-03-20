package com.example.richard.gate_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        supportFragmentManager.transaction { replace(R.id.container_profile, FriendListFragment()) }



        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_friends -> {
                    Log.d("don't","ON CLICKED friends")
                    supportFragmentManager.transaction { replace(R.id.container_profile, FriendListFragment()) }
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
