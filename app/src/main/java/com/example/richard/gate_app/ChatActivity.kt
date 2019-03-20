package com.example.richard.gate_app

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_chat)
        super.onCreate(savedInstanceState)


        val messagelist = ArrayList<MessageInfo>()
        val ly : RecyclerView.LayoutManager = LinearLayoutManager(this)
        val ad : RecyclerView.Adapter<ChatAdapter.MainViewHolder> = ChatAdapter(messagelist)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview_chat)
        //adapter
        recyclerview.setHasFixedSize(true)
        val adap = ChatAdapter(messagelist)
        recyclerview.adapter = adap
        val linearlayout = LinearLayoutManager(this)
        recyclerview.layoutManager = linearlayout


        msg_button.setOnClickListener{
            if(TextUtils.isEmpty(messageinput.text))
            {
                Log.d("hey","error")
            }
            else{
                var timestamp: Long = System.currentTimeMillis()
                val user : FriendInfo
                val toy = MessageInfo(messageinput.text.toString(),timestamp )
                FirebaseDatabase.getInstance().reference.child("Messages").child(timestamp.toString()).setValue(toy)
            }

        }




    }


    private fun initadapter(){


    }


}