package com.example.richard.gate_app

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.richard.gate_app.databinding.FragmentConfirmBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_confirm.*
import kotlinx.android.synthetic.main.fragment_update.*

class ConfirmFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bind = DataBindingUtil.inflate<FragmentConfirmBinding>(
            inflater,
            R.layout.fragment_confirm,
            container,
            false
        )
        //assigning the ViewModel object to a property in the binding class to help use binding adapters in XML
        bind.user = ViewModelProviders.of(this).get(ViewModelClass::class.java)

        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = FirebaseAuth.getInstance().currentUser
        val mainvm = ViewModelProviders.of(this).get(ViewModelClass::class.java)

        confirm_update_button.setOnClickListener {
            if(TextUtils.isEmpty(confirm_passtext.text) ){
                confirm_passtext.setError("Cannot be Empty!")
            }
            else{


            }

        }



    }

}