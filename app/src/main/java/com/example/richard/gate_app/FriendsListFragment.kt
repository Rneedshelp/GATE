package com.example.richard.gate_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.richard.gate_app.databinding.FragmentFriendslistBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_friendslist.*

class FriendsListFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = inflate<FragmentFriendslistBinding>(
            inflater,
            R.layout.fragment_friendslist,
            container,
            false
        )
        //assigning the ViewModel object to a property in the binding class to help use binding adapters in XML
        bind.user = ViewModelProviders.of(this).get(ViewModelClass::class.java)
        return bind.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = FirebaseAuth.getInstance().currentUser
        friend_username.text = user!!.displayName
        friend_email.text = user.email
    }
}