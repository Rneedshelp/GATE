package com.example.richard.gate_app

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.richard.gate_app.databinding.FragmentProfileBinding
import com.example.richard.gate_app.databinding.FragmentUpdateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateProfileFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = DataBindingUtil.inflate<FragmentUpdateBinding>(
            inflater,
            R.layout.fragment_update,
            container,
            false
        )
        //assigning the ViewModel object to a property in the binding class to help use binding adapters in XML
        bind.user = ViewModelProviders.of(this).get(ViewModelClass::class.java)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = FirebaseAuth.getInstance().currentUser
        update_button.setOnClickListener{
            if(TextUtils.isEmpty(username_edit.text) || TextUtils.isEmpty(password_edit.text)){
                username_edit.setError("Cannot be Empty!")
            }
            else if(!password_edit.text.equals(password_confirm.text)){
                password_edit.setError("Passwords do not match")
            }
            else {
                //call firebase to update passwords
                if (user != null)
                {
                    val pf : UserProfileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(username_edit.text.toString()).build()
                    user.updateProfile(pf)
                    user.updatePassword(password_edit.text.toString())
                }

            }
        }
    }
}