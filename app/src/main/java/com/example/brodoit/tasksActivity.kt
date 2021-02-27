package com.example.brodoit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class tasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
    }

    fun addNewTaskFunc(view: View) {
        val addNewTaskIntent = Intent(this, AddTask::class.java)
        startActivity(addNewTaskIntent)
    }

    fun myTasksFunc(view: View) {
        val myTasksIntent = Intent(this, MyTasks::class.java)
        startActivity(myTasksIntent)
    }

    fun todayTasksFunc(view: View) {
        val todayTasksIntent = Intent(this, TodayTasks::class.java)
        startActivity(todayTasksIntent)
    }
}