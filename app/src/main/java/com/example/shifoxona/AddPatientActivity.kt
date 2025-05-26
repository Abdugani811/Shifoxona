package com.example.shifoxona

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shifoxona.databinding.ActivityAddPatientBinding

class AddPatientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)

        val first_name = findViewById<EditText>(R.id.first_name)
        val last_name = findViewById<EditText>(R.id.last_name)
        val doctor_first_name = findViewById<EditText>(R.id.doctor_first_name)
        val doctor_last_name = findViewById<EditText>(R.id.doctor_last_name)

        val btnSaqlash = findViewById<Button>(R.id.add_patient_button)

        btnSaqlash.setOnClickListener {
            val patient = Patient(
                firstName = first_name.text.toString(),
                lastName = last_name.text.toString(),
                doctorFirstName = doctor_first_name.text.toString(),
                doctorLastName = doctor_last_name.text.toString()
            )

            PatientStorage.patients.add(patient)

            startActivity(Intent(this, NatijaActivity::class.java))
            finish()
        }
    }
}