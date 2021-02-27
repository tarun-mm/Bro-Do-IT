package com.example.brodoit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class yourWeekActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_week)

        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM TIMINGS", null)

        val cou = db.rawQuery("SELECT COUNT(*) FROM TIMINGS", null)
        cou.moveToNext()

        if (cou.getString(cou.getColumnIndex("COUNT(*)")).toInt() == 0) {
            db?.execSQL("INSERT INTO TIMINGS(DAY, FREEHOURS) VALUES('MONDAY', 0)")
            db?.execSQL("INSERT INTO TIMINGS(DAY, FREEHOURS) VALUES('TUESDAY', 0)")
            db?.execSQL("INSERT INTO TIMINGS(DAY, FREEHOURS) VALUES('WEDNESDAY', 0)")
            db?.execSQL("INSERT INTO TIMINGS(DAY, FREEHOURS) VALUES('THURSDAY', 0)")
            db?.execSQL("INSERT INTO TIMINGS(DAY, FREEHOURS) VALUES('FRIDAY', 0)")
            db?.execSQL("INSERT INTO TIMINGS(DAY, FREEHOURS) VALUES('SATURDAY', 0)")
            db?.execSQL("INSERT INTO TIMINGS(DAY, FREEHOURS) VALUES('SUNDAY', 0)")
        }
        cou.close()

        val monText = findViewById<EditText>(R.id.monText)
        val tueText = findViewById<EditText>(R.id.tueText)
        val wedText = findViewById<EditText>(R.id.wedText)
        val thuText = findViewById<EditText>(R.id.thuText)
        val friText = findViewById<EditText>(R.id.friText)
        val satText = findViewById<EditText>(R.id.satText)
        val sunText = findViewById<EditText>(R.id.sunText)

        rs.moveToNext()
        monText.setText(rs.getString(rs.getColumnIndex("FREEHOURS")))
        rs.moveToNext()
        tueText.setText(rs.getString(rs.getColumnIndex("FREEHOURS")))
        rs.moveToNext()
        wedText.setText(rs.getString(rs.getColumnIndex("FREEHOURS")))
        rs.moveToNext()
        thuText.setText(rs.getString(rs.getColumnIndex("FREEHOURS")))
        rs.moveToNext()
        friText.setText(rs.getString(rs.getColumnIndex("FREEHOURS")))
        rs.moveToNext()
        satText.setText(rs.getString(rs.getColumnIndex("FREEHOURS")))
        rs.moveToNext()
        sunText.setText(rs.getString(rs.getColumnIndex("FREEHOURS")))

        rs.close()
    }

    fun saveData(view: View) {
        var helper = MyDBHelper(applicationContext)
        var db = helper.readableDatabase

        val monText = findViewById<EditText>(R.id.monText)
        val tueText = findViewById<EditText>(R.id.tueText)
        val wedText = findViewById<EditText>(R.id.wedText)
        val thuText = findViewById<EditText>(R.id.thuText)
        val friText = findViewById<EditText>(R.id.friText)
        val satText = findViewById<EditText>(R.id.satText)
        val sunText = findViewById<EditText>(R.id.sunText)

        val monhours = monText.editableText.toString()
        val tuehours = tueText.editableText.toString()
        val wedhours = wedText.editableText.toString()
        val thuhours = thuText.editableText.toString()
        val frihours = friText.editableText.toString()
        val sathours = satText.editableText.toString()
        val sunhours = sunText.editableText.toString()

        db?.execSQL("UPDATE TIMINGS SET FREEHOURS = $monhours WHERE DAY = 'MONDAY'")
        db?.execSQL("UPDATE TIMINGS SET FREEHOURS = $tuehours WHERE DAY = 'TUESDAY'")
        db?.execSQL("UPDATE TIMINGS SET FREEHOURS = $wedhours WHERE DAY = 'WEDNESDAY'")
        db?.execSQL("UPDATE TIMINGS SET FREEHOURS = $thuhours WHERE DAY = 'THURSDAY'")
        db?.execSQL("UPDATE TIMINGS SET FREEHOURS = $frihours WHERE DAY = 'FRIDAY'")
        db?.execSQL("UPDATE TIMINGS SET FREEHOURS = $sathours WHERE DAY = 'SATURDAY'")
        db?.execSQL("UPDATE TIMINGS SET FREEHOURS = $sunhours WHERE DAY = 'SUNDAY'")

        Toast.makeText(applicationContext, "Details Saved", Toast.LENGTH_SHORT).show()
    }
}