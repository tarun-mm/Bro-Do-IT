package com.example.brodoit

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context): SQLiteOpenHelper(context, "TASKSDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE TIMINGS(DAY TEXT, FREEHOURS INTEGER)")
        db?.execSQL("CREATE TABLE TASKS(NAME TEXT, HOUR INTEGER)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}