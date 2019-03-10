package com.example.richard.gate_app

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.richard.gate_app.databinding.FragmentReauthorizeBinding
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.fragment_reauthorize.*
import kotlinx.android.synthetic.main.fragment_update.*
import org.jetbrains.anko.support.v4.startActivity

class ReauthorizeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bind = DataBindingUtil.inflate<FragmentReauthorizeBinding>(
            inflater,
            R.layout.fragment_reauthorize,
            container,
            false
        )
        //assigning the ViewModel object to a property in the binding class to help use binding adapters in XML
        bind.user = ViewModelProviders.of(this).get(ViewModelClass::class.java)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = FirebaseAuth.getInstance().currentUser

        confirm_button.setOnClickListener {
            if(TextUtils.isEmpty(old_pass.text) || TextUtils.isEmpty(password_edit.text)){
                old_pass.setError("Cannot be Empty!")
            }
            else if(password_edit.text.toString() != password_confirm.text.toString()){
                password_edit.setError("Passwords do Not Match")
                password_confirm.setError("Passwods do Not Match")
            }
            else{

                val cred = EmailAuthProvider.getCredential(user!!.email.toString(),old_pass.text.toString())
                user.reauthenticate(cred).addOnCompleteListener {
                    if(it.isSuccessful){
                        user.updatePassword(password_edit.text.toString())
                        fragmentManager!!.beginTransaction().remove(ReauthorizeFragment()).commit()
                        startActivity<LogInActivity>()
                    }
                    else{
                        old_pass.setError("Password incorrect for current user")
                    }
                }

            }

        }



    }

    //Ask to update email, then go to new frag asking if you want to verify update? enter password and then reauthenticate.
    //after go to profile frag
    //For passwoord, go to update pass frag, after doing so reauthenticate, and go back to login activity

}