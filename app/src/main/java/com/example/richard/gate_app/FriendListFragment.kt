package com.example.richard.gate_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recyclermain.*
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.startActivity
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class FriendListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_recyclermain, container, false)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview_main)
        recyclerview.hasFixedSize()
        val friendlist = ArrayList<FriendInfo>()

        val user = FirebaseAuth.getInstance().currentUser

        val credentials = FirebaseDatabase.getInstance().reference.child("users")
        val adap = FriendListAdapter(friendlist)
        credentials.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.d("don't", "Error occured in Database!")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    adap.notifyDataSetChanged()
                    p0.children.forEach {
                        if (it.key.toString() != user!!.displayName.toString()) {
                            friendlist.add(FriendInfo(it.key.toString(),
                                it.child("Email").value.toString(),
                                it.child("ID").value.toString()))
                        }
                    }


                }
            })

        recyclerview.adapter = adap
        val linearlayout = LinearLayoutManager(context)
        recyclerview.layoutManager = linearlayout

        //Pass user clicked on to chat frag, to ensure who you are sending to.
        //Pass user from this frag to chat frag.

        adap.onItemClick = {
            startActivity<ChatActivity>()
            MainActivity().finish()
        }
        return view
    }



}
