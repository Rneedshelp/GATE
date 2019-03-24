package com.example.richard.gate_app

import java.lang.reflect.Constructor
import java.sql.Timestamp

data class MessageInfo(val textmsg : String, val timestamp : String){
    constructor(textmsg: String, timestamp: String, rcvr : String = "") : this(textmsg,timestamp)
}