package com.example.minutesage

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
//    This private variable used many time that why i used her in top
   private var date:TextView?=null
    private var minute:TextView?=null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button2: Button = findViewById(R.id.button2)
        date=findViewById(R.id.tvdate)
        minute=findViewById(R.id.min)
           button2.setOnClickListener {
               btn()
           }
        }
    @RequiresApi(Build.VERSION_CODES.N)
//    all the date is setup, they were imported from java util
    fun btn(){
//        for setting up the date using Calender.getInstance
        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

//        Datepicker in one variable to write code easily in next step for cover all the things in
//        date picker dialog
        val dpd=DatePickerDialog(this,
           { view , year, month ,dayOfMonth ->
               Toast.makeText(this,"Your Age In Minute ",Toast.LENGTH_LONG).show()
               val mainDate="$day/${month+1}/$year"
               date?.text = mainDate
               val sdf= SimpleDateFormat("DD/MM/yyyy", Locale.ENGLISH)
               var theDate=sdf.parse(mainDate)
               val selectedminute=theDate.time/60000
               val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
               val currDateinmin=currentDate.time/60000
               val diffInMinutes=currDateinmin - selectedminute
               minute?.text=diffInMinutes.toString()
           },
           year, month, day
       )
//        TO set the max date to chose till date no one can born in future
        dpd.datePicker.maxDate=System.currentTimeMillis()-8640000

        dpd.show()

    }

}

