package com.example.richard.gate_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class FriendListAdapter(private var friendslist : ArrayList<FriendInfo>) : RecyclerView.Adapter<FriendListAdapter.MainViewHolder>() {


    fun FriendListAdapter(friendlist : ArrayList<FriendInfo>){
        friendslist = friendlist

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendListAdapter.MainViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.frienditem,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return friendslist.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val friend : FriendInfo = friendslist.get(position)
        holder.username.text = friend.username
        holder.email.text = friend.email
    }


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val username = itemView.findViewById<TextView>(R.id.friend_username)
            val email = itemView.findViewById<TextView>(R.id.friend_email)






    }
}