package com.example.richard.gate_app

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_chat.*
import java.lang.Exception

class ChatActivity : AppCompatActivity() {
    val user = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_chat)
        super.onCreate(savedInstanceState)
        val msglist = ArrayList<MessageInfo>()
        val adap = ChatAdapter(msglist)
        setAdapter(adap)
        LoadDB(msglist)
    }


    fun onNewMessage(msglist : ArrayList<MessageInfo>){
        val chatchannel = intent.getStringExtra("chatID")
        val adap = ChatAdapter(msglist)
        setAdapter(adap)

        msg_button.setOnClickListener{
            if(TextUtils.isEmpty(messageinput.text))
            {
                Log.d("hey","error")
            }
            else{
                val timestamp = System.currentTimeMillis().toString()
                val msgDatabase = MessageInfo(messageinput.text.toString(),timestamp,user!!.displayName.toString() )
                FirebaseDatabase.getInstance().reference.child("Messages").child(chatchannel.toString()).child(timestamp).setValue(msgDatabase)
                msglist.add(msgDatabase)
                adap.notifyItemInserted(adap.itemCount - 1)
            }

        }
    }

   private fun LoadDB(msglist : ArrayList<MessageInfo>) {
        val chatchannel = intent.getStringExtra("chatID")
        val msgDB = FirebaseDatabase.getInstance().reference.child("Messages").child(chatchannel)
        msgDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("don't", "Error occured in Database!")
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                        val sender = it.child("sndr").value.toString()
                        val msgtime = it.child("timestamp").value.toString()
                        val message = it.child("textmsg").value.toString()
                        msglist.add(MessageInfo(message,msgtime,sender))
                }
                onNewMessage(msglist)

            }
        })

    }

    private fun setAdapter(adapter: ChatAdapter){
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview_chat)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
    }


}