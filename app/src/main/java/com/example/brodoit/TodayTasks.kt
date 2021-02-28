package com.example.brodoit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
        var tasksData = db?.rawQuery("SELECT * FROM TASKS", null)

        val co = db.rawQuery("SELECT COUNT(*) FROM TASKS",null)
        co.moveToNext()
        var cou = co.getString(co.getColumnIndex("COUNT(*)")).toInt()

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


        var dayHours = db.rawQuery("SELECT * FROM TIMINGS WHERE DAY = '$dayOfWeekText'", null)
        dayHours.moveToNext()
        var dayhours = dayHours.getString(dayHours.getColumnIndex("FREEHOURS")).toInt()


        if (tasksData != null) {
            while(cou != 0 && tasksData.moveToNext() && dayhours!=0){
                cou -= 1
                dayhours -=1
                var nameTemp = tasksData.getString(tasksData.getColumnIndex("NAME"))
                var hourTemp = tasksData.getString(tasksData.getColumnIndex("HOUR"))
                findViewById<TextView>(R.id.nameTaskText).text = nameTemp
                findViewById<TextView>(R.id.hoursTaskText).text = hourTemp

                Toast.makeText(this, "$nameTemp $hourTemp", Toast.LENGTH_SHORT).show()

                Thread.sleep(2000)
            }
        }
        tasksData?.close()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun doneDataFunc(view: View) {

        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var tasksData = db?.rawQuery("SELECT * FROM TASKS", null)

        val co = db.rawQuery("SELECT COUNT(*) FROM TASKS",null)
        co.moveToNext()
        var cou = co.getString(co.getColumnIndex("COUNT(*)")).toInt()

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

        var dayHours = db.rawQuery("SELECT * FROM TIMINGS WHERE DAY = '$dayOfWeekText'", null)
        dayHours.moveToNext()
        var dayhours = dayHours.getString(dayHours.getColumnIndex("FREEHOURS")).toInt()

        if (tasksData != null) {
            while(cou != 0 && tasksData.moveToNext() && dayhours!=0){
                cou -= 1
                dayhours-=1
                var nameTempValue = tasksData.getString(tasksData.getColumnIndex("NAME"))
                var hourTempValue = tasksData.getString(tasksData.getColumnIndex("HOUR")).toInt()
                db?.execSQL("UPDATE TASKS SET HOUR = ${hourTempValue-1} WHERE NAME = '$nameTempValue'")

            }
        }
        tasksData?.close()

        Toast.makeText(this, "Details Updated", Toast.LENGTH_SHORT).show()
    }
}