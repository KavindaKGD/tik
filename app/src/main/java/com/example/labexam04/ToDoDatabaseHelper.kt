package com.example.labexam04

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ToDoDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "todoapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "alltodos"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TOPIC = "topic"
        private const val COLUMN_DETAILS = "details"
        private const val COLUMN_PRIOLEVEL = "plevel"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_TIME = "time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER, $COLUMN_TOPIC TEXT, $COLUMN_DETAILS TEXT, $COLUMN_PRIOLEVEL TEXT, $COLUMN_DATE TEXT, $COLUMN_TIME)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertToDo(todo:ToDoDataClass){
        val db = writableDatabase
        val values = ContentValues().apply{
            put(COLUMN_TOPIC, todo.toDoTopic)
            put(COLUMN_DETAILS, todo.toDoDetails)
            put(COLUMN_PRIOLEVEL, todo.toDoPLevel)
            put(COLUMN_DATE, todo.toDoDate)
            put(COLUMN_TIME, todo.toDoTime)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllToDos():List<ToDoDataClass>{
        val toDoList = mutableListOf<ToDoDataClass>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val topic = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TOPIC))
            val details = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS))
            val plevel = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIOLEVEL))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
            val time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME))

            val todo = ToDoDataClass(id, topic, details, plevel, date, time)
            toDoList.add(todo)

        }
        cursor.close()
        db.close()
        return toDoList
    }
}