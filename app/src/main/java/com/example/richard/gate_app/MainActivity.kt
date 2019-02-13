package com.example.richard.gate_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

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
