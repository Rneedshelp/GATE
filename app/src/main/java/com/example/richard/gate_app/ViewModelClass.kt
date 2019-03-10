package com.example.richard.gate_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ViewModelClass : ViewModel() {

    var holder = MutableLiveData<CharSequence>()
    var passcode : String = ""

    fun ontextchange(seq: CharSequence) {
        holder.apply { value = seq }
        passcode = holder.value.toString()
    }
}