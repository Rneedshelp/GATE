package com.example.richard.gate_app


import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import androidx.lifecycle.ViewModelProviders
import com.example.richard.gate_app.databinding.FragmentProfileBinding

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bind = inflate<FragmentProfileBinding>(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        )
        //assigning the ViewModel object to a property in the binding class to help use binding adapters in XML
        bind.user = ViewModelProviders.of(this).get(ViewModelClass::class.java)
        bind.editButton.setOnClickListener{
            fragmentManager!!.beginTransaction().replace(R.id.container_profile,UpdateProfileFragment()).addToBackStack(null).commit()

        }
        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = FirebaseAuth.getInstance().currentUser
        if(user != null){
            Log.d("WORK",user.displayName.toString())
            username_textview.setText(user.displayName)
            email_textview.setText( user.email)
        }

        signout_button.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
        }

    }




}