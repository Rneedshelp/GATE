package com.example.richard.gate_app

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_chat, container, false)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview_chat)
        recyclerview.hasFixedSize()
        //adapter

       val linearlayout = LinearLayoutManager(context)
       recyclerview.layoutManager = linearlayout
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button.setOnClickListener {

            if(TextUtils.isEmpty(textinput.text))
            {
                Log.d("hey","error")
            }
            else{
                var timestamp: Long = System.currentTimeMillis()
                val user : FriendInfo
               val toy = MessageInfo(textinput.text.toString(),timestamp )
                FirebaseDatabase.getInstance().reference.child("Messages").child(timestamp.toString()).setValue(toy)
            }

        }

    }



}