package com.example.richard.gate_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ChatAdapter(private val msg : ArrayList<MessageInfo>) : RecyclerView.Adapter<ChatAdapter.MainViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_chat,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return msg.size
    }

    override fun onBindViewHolder(holder: ChatAdapter.MainViewHolder, position: Int) {
        val messageInfo = msg.get(position)
        holder.msgtext.text = messageInfo.textmsg

    }


    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //val msgtime = itemView.findViewById<TextView>(R.id.idtext)
        val msgtext= itemView.findViewById<TextView>(R.id.text_msg)


    }
}