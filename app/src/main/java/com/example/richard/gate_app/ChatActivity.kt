package com.example.richard.gate_app

import android.os.Bundle
import android.provider.CalendarContract
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat.*
import java.lang.Exception
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ChildEventListener
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ChatActivity : AppCompatActivity() {
    val user = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_chat)
        super.onCreate(savedInstanceState)
        val form = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.US)
        var timemsg : String
        val msglist = ArrayList<MessageInfo>()
        val adap = ChatAdapter(msglist)
        LoadDB(msglist)
        setAdapter(adap)

        val chatchannel = intent.getStringExtra("chatID")
        title = intent.getStringExtra("friend").toUpperCase()

        msg_button.setOnClickListener{
            if(TextUtils.isEmpty(messageinput.text))
            {
                Log.d("hey","error")
            }
            else{
                val timestamp = System.currentTimeMillis()
                Calendar.getInstance().timeInMillis = timestamp
                timemsg = form.format(Calendar.getInstance().time).toString()
                Log.d("hey",timemsg)
                val msgDatabase = MessageInfo(messageinput.text.toString(),timemsg,user!!.displayName.toString() )
                val DBref = FirebaseDatabase.getInstance().reference.child("Messages").child(chatchannel.toString()).child(timestamp.toString())
                DBref.setValue(msgDatabase)
            }

        }
    }


    fun onNewMessage(msglist : ArrayList<MessageInfo>){
        val adap = ChatAdapter(msglist)
        setAdapter(adap)
    }

   private fun LoadDB(msglist : ArrayList<MessageInfo>) {
        val chatchannel = intent.getStringExtra("chatID")
        val msgDB = FirebaseDatabase.getInstance().reference.child("Messages").child(chatchannel)


       msgDB.addChildEventListener(object : ChildEventListener {
           override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
               val sender = dataSnapshot.child("sndr").value.toString()
               val msgtime = dataSnapshot.child("timestamp").value.toString()
               val message = dataSnapshot.child("textmsg").value.toString()
               msglist.add(MessageInfo(message, msgtime,sender))
               onNewMessage(msglist)

           }
           override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
           override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
           override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
           override fun onCancelled(databaseError: DatabaseError) {}
       })
    }

    private fun setAdapter(adapter: ChatAdapter){
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview_chat)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
    }


}