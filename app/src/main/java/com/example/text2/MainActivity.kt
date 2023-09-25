package com.example.text2

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDoBPicker = findViewById<Button>(R.id.btnDoBPicker)
        btnDoBPicker.setOnClickListener {_ ->
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val date1 = "$day/${month+1}/$year"
        val tvMinutes = findViewById<TextView>(R.id.tvMinutes)
        val myPicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val date2 = "$selectedDay/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.text = date2
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN)
            val newDate1 = sdf.parse(date1)!!.time / 60000
            val newDate2 = sdf.parse(date2)!!.time / 60000
            val result = "%,d".format(newDate1 - newDate2)
            tvMinutes.text = result
        }, year, month, day)
        myPicker.datePicker.maxDate = myCalendar.timeInMillis - 1000

        myPicker.show()

    }
}