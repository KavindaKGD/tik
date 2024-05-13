package com.example.labexam04

import android.os.Parcelable
import java.sql.Date
import java.sql.Time

data class ToDoDataClass(val toDoId:Int, val toDoTopic:String, val toDoDetails:String, val toDoPLevel:String, val toDoDate:String, val toDoTime:String)
