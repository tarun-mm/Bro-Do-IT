package com.example.brodoit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast


class MyTasks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tasks)

        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var tasksData = db?.rawQuery("SELECT * FROM TASKS", null)
        tasksData?.moveToNext()

        findViewById<TextView>(R.id.nameTaskText).setText(tasksData?.getString(tasksData.getColumnIndex("NAME")))
        findViewById<TextView>(R.id.hoursTaskText).setText(tasksData?.getString(tasksData.getColumnIndex("HOUR")))
    }

    fun changeDataFunc(view: View) {

        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase

        val co = db.rawQuery("SELECT COUNT(*) FROM TASKS",null)
        co.moveToNext()
        var cou = co.getString(co.getColumnIndex("COUNT(*)")).toInt()
        var tasksData = db?.rawQuery("SELECT * FROM TASKS", null)

        if (tasksData != null) {
            while(cou != 0 && tasksData.moveToNext()){
                cou -= 1
                var nameTemp = tasksData.getString(tasksData.getColumnIndex("NAME"))
                var hourTemp = tasksData.getString(tasksData.getColumnIndex("HOUR"))
                findViewById<TextView>(R.id.nameTaskText).text = nameTemp
                findViewById<TextView>(R.id.hoursTaskText).text = hourTemp

                Toast.makeText(this, "$nameTemp $hourTemp", Toast.LENGTH_SHORT).show()

                Thread.sleep(2000)
            }
        }
    }

}