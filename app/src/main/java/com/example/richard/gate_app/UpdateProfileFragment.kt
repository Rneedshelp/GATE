package com.example.richard.gate_app

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
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
import java.net.PasswordAuthentication
import java.security.KeyStore

class UpdateProfileFragment : Fragment(){
    val user = FirebaseAuth.getInstance().currentUser
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
        update_button.setOnClickListener{
            if(TextUtils.isEmpty(email_edit.text)){
                email_edit.setError("Cannot be Empty!")
            }
            else {
                val dialogbox = AlertDialog.Builder(activity)
                val pass = EditText(activity)
                pass.transformationMethod = PasswordTransformationMethod.getInstance()

                val layout = LinearLayout(activity)
                layout.addView(pass)
                dialogbox.setMessage("Enter your Password").setView(layout).setPositiveButton("Confirm"){_, _ ->
                    val cred = EmailAuthProvider.getCredential(user!!.email.toString(),pass.text.toString())
                    user.reauthenticate(cred).addOnCompleteListener {
                        if(it.isSuccessful){

                            user.updateEmail(email_edit.text.toString()).addOnCompleteListener{
                                if(it.isSuccessful){
                                    FirebaseDatabase.getInstance().reference.child("users")
                                        .child(user.displayName.toString()).child("Email")
                                        .setValue(user.email).addOnCompleteListener{
                                            fragmentManager!!.beginTransaction()
                                                .replace(R.id.container_profile,ProfileFragment())
                                                .commit()
                                        }

                                }
                            }
                        }
                        else{
                            pass.setError("Password is incorrect!")
                            Log.d("he","Something went wrong")
                        }
                    }

                }.setNegativeButton("Cancel"){dialog, _ ->
                    dialog.dismiss()
                }
                dialogbox.create().show()

            }
        }
        password_button.setOnClickListener{
            fragmentManager!!.beginTransaction().replace(R.id.container_profile,ReauthorizeFragment()).addToBackStack(null).commit()
        }
    }
}
