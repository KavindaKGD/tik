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
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME(
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_TOPIC TEXT,
            $COLUMN_DETAILS TEXT,
            $COLUMN_PRIOLEVEL TEXT,
            $COLUMN_DATE TEXT,
            $COLUMN_TIME
            )
        """.trimIndent()
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

    fun updateToDo(todo: ToDoDataClass) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TOPIC, todo.toDoTopic)
            put(COLUMN_DETAILS, todo.toDoDetails)
            put(COLUMN_PRIOLEVEL, todo.toDoPLevel)
            put(COLUMN_DATE, todo.toDoDate)
            put(COLUMN_TIME, todo.toDoTime)
        }
        db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(todo.toDoId.toString()))
        db.close()
    }

    fun getToDoById(id: Int): ToDoDataClass? {
        val db = readableDatabase
        var todo: ToDoDataClass? = null
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(id.toString()))
        if (cursor.moveToFirst()) {
            val topic = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TOPIC))
            val details = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS))
            val plevel = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIOLEVEL))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
            val time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME))
            todo = ToDoDataClass(id, topic, details, plevel, date, time)
        }
        cursor.close()
        db.close()
        return todo
    }

    fun getAllToDos():List<ToDoDataClass>{
        val toDoList = mutableListOf<ToDoDataClass>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        cursor.use {
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val topic = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TOPIC))
            val details = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DETAILS))
            val plevel = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIOLEVEL))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE))
            val time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME))
            val todo = ToDoDataClass(id, topic, details, plevel, date, time)
            toDoList.add(todo)
            }
        }

        db.close()
        return toDoList
    }

    fun deleteToDoById(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }



}