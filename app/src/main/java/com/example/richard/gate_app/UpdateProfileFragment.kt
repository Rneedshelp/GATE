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
import com.example.richard.gate_app.databinding.FragmentProfileBinding
import com.example.richard.gate_app.databinding.FragmentUpdateBinding
import com.google.firebase.auth.*
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_update.*
import org.jetbrains.anko.support.v4.startActivity

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
            if(TextUtils.isEmpty(email_edit.text) || TextUtils.isEmpty(password_edit.text)){
                email_edit.setError("Cannot be Empty!")
            }
            else if(password_edit.text.toString() != password_confirm.text.toString()){
                password_edit.setError("Passwords do not match")
            }
            else {
                //call firebase to update passwords
                if (user != null)
                {
                    //user.updatePassword(password_edit.text.toString())
                    user.updateEmail(email_edit.text.toString()).addOnCompleteListener{
                        if(it.isSuccessful) {
                             startActivity<LogInActivity>()
                        }
                        else Log.d("ge","NOT WORKING")
                    }



                    var reference : DatabaseReference
                    val usermap = HashMap<String?, String?>()
                    usermap["Name"] =  ""
                    usermap["Phone"] = ""
                    usermap["Email"] = user.email
                    usermap["ID"] = user.uid



                }

            }
        }
    }
}