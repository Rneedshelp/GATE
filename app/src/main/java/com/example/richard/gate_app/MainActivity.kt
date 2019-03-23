package com.example.richard.gate_app

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.navigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        supportFragmentManager.transaction { add(R.id.container_profile, FriendListFragment()) }

        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_settings->{
                    if(!it.isChecked)
                    {
                        Log.d("don't","ON CLICKED SETTINGS")
                        supportFragmentManager.transaction { replace(R.id.container_profile, ProfileFragment()) }
                        navigation.menu.getItem(0).isChecked= false
                    }

                    true
                }
                R.id.navigation_friends->{
                    if(!it.isChecked)
                    {
                        Log.d("don't","ON CLICKED friends")
                        supportFragmentManager.transaction { replace(R.id.container_profile, FriendListFragment()) }
                        it.isChecked = true

                    }
                    true
                }
                else->false
            }
            }
        }


    inline fun FragmentManager.transaction(now: Boolean = false, allowStateLoss: Boolean = false, body: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().body().commit()
    }
}
