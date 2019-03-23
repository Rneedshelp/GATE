package com.example.richard.gate_app

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_chat)
        super.onCreate(savedInstanceState)
        val msglist = ArrayList<MessageInfo>()
        initadapter(msglist)
        databaselistener()
        msg_button.setOnClickListener{
            if(TextUtils.isEmpty(messageinput.text))
            {
                Log.d("hey","error")
            }
            else{
                val timestamp: Long = System.currentTimeMillis()
                val toy = MessageInfo(messageinput.text.toString() )
                FirebaseDatabase.getInstance().reference.child("Messages").child(timestamp.toString()).setValue(toy)
                msglist.add(toy)
                //initadapter(msglist)

            }

        }




    }


    //seperate button clicker so everytime you call this listener function and then init adapter
    private fun databaselistener(){
        val currentDB = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {


            }

            override fun onDataChange(p0: DataSnapshot) {
                val messagesDB = ArrayList<MessageInfo>()
                for (data in p0.children){
                    val dat = data.value.toString()
                    messagesDB.add(MessageInfo(dat))
                }
                initadapter(messagesDB)
            }


        }
       FirebaseDatabase.getInstance().getReference().child("Messages").addValueEventListener(currentDB)
    }

    private fun initadapter(msglist : ArrayList<MessageInfo>){

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview_chat)

        recyclerview.setHasFixedSize(true)
        val adap = ChatAdapter(msglist)
        recyclerview.adapter = adap
        recyclerview.layoutManager = LinearLayoutManager(this)

        msg_button.setOnClickListener{
            if(TextUtils.isEmpty(messageinput.text))
            {
                Log.d("hey","error")
            }
            else{
                val timestamp: Long = System.currentTimeMillis()
                val toy = MessageInfo(messageinput.text.toString() )
                FirebaseDatabase.getInstance().reference.child("Messages").child(timestamp.toString()).setValue(toy)
                msglist.add(toy)
                adap.notifyDataSetChanged()
            }

        }

    }


}