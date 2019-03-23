package com.example.richard.gate_app

import java.lang.reflect.Constructor
import java.sql.Timestamp

data class MessageInfo(val textmsg : String ){
    constructor(textmsg: String, timestamp: Long) : this(textmsg)
}