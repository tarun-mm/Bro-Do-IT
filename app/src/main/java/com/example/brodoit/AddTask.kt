package com.example.brodoit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
    }

    fun saveTaskData(view: View) {
        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        val nameText = findViewById<EditText>(R.id.nameText)
        val hoursText = findViewById<EditText>(R.id.hoursText)

        val nameOfTask = nameText.editableText.toString()
        val numberOfHoursTask = hoursText.editableText.toString().toInt()

        db?.execSQL("INSERT INTO TASKS(NAME, HOUR) VALUES('$nameOfTask', $numberOfHoursTask)")

        Toast.makeText(this, "Details Added", Toast.LENGTH_SHORT).show()
    }
}