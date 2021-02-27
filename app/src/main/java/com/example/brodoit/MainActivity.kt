package com.example.brodoit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun yourWeekIntent(view: View) {
        val week_Intent = Intent(this, yourWeekActivity::class.java)
        startActivity(week_Intent)
    }

    fun tasksIntent(view: View) {
        val tasks_Intent = Intent(this, tasksActivity::class.java)
        startActivity(tasks_Intent)
    }
}