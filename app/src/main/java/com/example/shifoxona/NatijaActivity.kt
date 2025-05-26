package com.example.shifoxona

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shifoxona.databinding.ActivityNatijaBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NatijaActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_natija)

        listView = findViewById(R.id.listView)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            startActivity(Intent(this, AddPatientActivity::class.java))
        }

        findViewById<Button>(R.id.sortByNameBtn).setOnClickListener {
            showPatientsSortedBy("name")
        }

        findViewById<Button>(R.id.sortByArrivalBtn).setOnClickListener {
            showPatientsSortedBy("time")
        }

        findViewById<Button>(R.id.sortByDoctorBtn).setOnClickListener {
            showPatientsSortedBy("doctor")
        }

        // Dastlab nom bo‘yicha ko‘rsatamiz
        showPatientsSortedBy("name")
    }

    private fun showPatientsSortedBy(criteria: String) {
        val sorted = when (criteria) {
            "name" -> PatientStorage.patients.sortedWith(compareBy({ it.lastName.lowercase() }, { it.firstName.lowercase() }))
            "time" -> PatientStorage.patients.sortedBy { it.arrivalTime }
            "doctor" -> PatientStorage.patients.sortedWith(compareBy({ it.doctorLastName.lowercase() }, { it.doctorFirstName.lowercase() }))
            else -> PatientStorage.patients
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val listItems = sorted.map {
            val time = dateFormat.format(Date(it.arrivalTime))
            "${it.fullName()} | ${it.doctorFullName()} | $time"
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter
    }
}