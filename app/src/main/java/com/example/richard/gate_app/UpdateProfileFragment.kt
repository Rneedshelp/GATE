package com.example.richard.gate_app

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
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
            if(TextUtils.isEmpty(email_edit.text)){
                email_edit.setError("Cannot be Empty!")
            }
            else {
                val dialogbox = AlertDialog.Builder(activity)
                val pass = EditText(activity)
                val confirmpass = Button(activity).setText("Confirm")
                val layout = LinearLayout(activity)
                layout.addView(pass)
                dialogbox.setMessage("Enter your Password").setView(layout).setPositiveButton("Confirm"){dialog, which ->

                    val cred = EmailAuthProvider.getCredential(user!!.email.toString(),pass.text.toString())
                    user.reauthenticate(cred).addOnCompleteListener {
                        if(it.isSuccessful){
                            Log.d("GRRGE",email_edit.text.toString())
                            user.updateEmail(email_edit.text.toString()).addOnCompleteListener{
                                if(it.isSuccessful)fragmentManager!!.beginTransaction()
                                    .replace(R.id.container_profile,ProfileFragment()).addToBackStack(null).commit()
                            }
                        }
                        else{
                            Log.d("he","Something went wrong")
                        }
                    }

                }.setNegativeButton("Cancel"){dialog, which ->

                    dialog.dismiss()

                }

                dialogbox.create().show()
                //fragmentManager!!.beginTransaction().replace(R.id.container_profile,ConfirmFragment()).addToBackStack(null).commit()

            }
        }
        password_button.setOnClickListener{
            fragmentManager!!.beginTransaction().replace(R.id.container_profile,ReauthorizeFragment()).addToBackStack(null).commit()
        }
    }
}