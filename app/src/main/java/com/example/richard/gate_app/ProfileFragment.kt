package com.example.richard.gate_app


import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.richard.gate_app.databinding.FragmentProfileBinding

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bind = inflate<FragmentProfileBinding>(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )
        //assigning the ViewModel object to a property in the binding class to help use binding adapters in XML
        bind.user = ViewModelProviders.of(this).get(ViewModelClass::class.java)
        val user = FirebaseAuth.getInstance().currentUser
        val mainvm = ViewModelProviders.of(this).get(ViewModelClass::class.java)
        if(user != null){
            d("don't", user.toString() )
        }
        return bind.root
    }




}