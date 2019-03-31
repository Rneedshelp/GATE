package com.example.richard.gate_app

import android.graphics.Color
import android.graphics.Color.*
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.backgroundColor
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
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val messageInfo = msg.get(position)
        holder.msgtext.text = messageInfo.textmsg
        holder.msgtime.text = messageInfo.timestamp
        if(messageInfo.sndr == FirebaseAuth.getInstance().currentUser!!.displayName.toString())
        {
            params.marginStart = 500
            params.bottomMargin = 20
            holder.itemView.layoutParams = params
        }
        else {
            params.marginStart = 20
            params.bottomMargin = 20
            holder.itemView.layoutParams = params
            holder.itemView.backgroundColor = LTGRAY
        }

    }


    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val msgtime = itemView.findViewById<TextView>(R.id.time_msg)
        val msgtext= itemView.findViewById<TextView>(R.id.text_msg)
    }
}