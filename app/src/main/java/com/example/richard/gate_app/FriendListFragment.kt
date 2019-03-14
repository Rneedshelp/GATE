package com.example.richard.gate_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FriendListFragment : Fragment() {

    fun FriendListFragment(){
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val  view : View = inflater.inflate(R.layout.fragment_recyclermain,container,false)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview_main)
        recyclerview.hasFixedSize()
        val examplelist = ArrayList<FriendInfo>()
        examplelist.add(FriendInfo("hello","test1"))
        examplelist.add(FriendInfo("hello","test1"))

        examplelist.add(FriendInfo("hello","test1"))

        examplelist.add(FriendInfo("hello","test1"))


        val adap = FriendListAdapter(examplelist)
        recyclerview.adapter = adap


        val linearlayout = LinearLayoutManager(this.context)
        recyclerview.layoutManager = linearlayout
        return view
    }
}