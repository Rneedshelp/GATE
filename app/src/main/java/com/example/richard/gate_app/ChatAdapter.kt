package com.example.richard.gate_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class ChatAdapter(private val msg : ArrayList<MessageInfo>) : RecyclerView.Adapter<ChatAdapter.MainViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.messageitem,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return msg.size
    }

    override fun onBindViewHolder(holder: ChatAdapter.MainViewHolder, position: Int) {
        val messageInfo = msg.get(position)
        holder.msgtext.text = messageInfo.textmsg
        holder.msgtime.text = messageInfo.timestamp
        Log.d("hey",messageInfo.sndr)
        if(messageInfo.sndr == FirebaseAuth.getInstance().currentUser!!.displayName.toString())
        {
            holder.msgtime.setPadding(500,0,0,0)
            holder.msgtext.setPadding(500,0,0,0)
        }
        else
        {
            holder.msgtime.setPadding(10,0,0,0)
            holder.msgtext.setPadding(10,0,0,0)
        }


    }


    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val msgtime = itemView.findViewById<TextView>(R.id.time_msg)
        val msgtext= itemView.findViewById<TextView>(R.id.text_msg)
    }
}