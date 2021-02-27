package com.example.brodoit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class TodayTasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_tasks)

        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var tasksData = db?.rawQuery("SELECT * FROM TASKS", null)
        tasksData?.moveToNext()

        findViewById<TextView>(R.id.nameTaskText).setText(tasksData?.getString(tasksData?.getColumnIndex("NAME")))
        findViewById<TextView>(R.id.hoursTaskText).setText(tasksData?.getString(tasksData?.getColumnIndex("HOUR")))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeDataFunc(view: View) {

        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM TIMINGS", null)
        var tasksData = db?.rawQuery("SELECT * FROM TASKS", null)

        //time
        val c: Calendar = Calendar.getInstance()
        val current = LocalDateTime.now()
        c.setTime(java.util.Date.from(current.atZone(ZoneId.systemDefault()).toInstant()))
        val dayOfWeek: Int = c.get(Calendar.DAY_OF_WEEK)
        var dayOfWeekText: String = ""
        if (dayOfWeek == 1)
            dayOfWeekText = "SUNDAY"
        else if(dayOfWeek == 2)
            dayOfWeekText = "MONDAY"
        else if(dayOfWeek == 3)
            dayOfWeekText = "TUESDAY"
        else if(dayOfWeek == 4)
            dayOfWeekText = "WEDNESDAY"
        else if(dayOfWeek == 5)
            dayOfWeekText = "THURSDAY"
        else if(dayOfWeek == 6)
            dayOfWeekText = "FRIDAY"
        else if(dayOfWeek == 7)
            dayOfWeekText = "SATURDAY"
        //time


        var dayHours = db.rawQuery("SELECT * FROM TIMINGS WHERE DAY = $dayOfWeekText", null)
        dayHours.moveToNext()
        var dayhours = dayHours.getString(dayHours.getColumnIndex("FREEHOURS")).toInt()
        if (tasksData != null) {
            while(dayhours != 0){
                dayhours = dayhours-1
                tasksData.moveToNext()
                findViewById<TextView>(R.id.nameTaskText).setText(tasksData?.getString(tasksData?.getColumnIndex("NAME")))
                findViewById<TextView>(R.id.hoursTaskText).setText(tasksData?.getString(tasksData?.getColumnIndex("HOUR")))
            }
            Thread.sleep(2000)
        }
        if (tasksData != null) {
            tasksData.close()
        }
    }

    fun doneDataFunc(view: View) {}
}