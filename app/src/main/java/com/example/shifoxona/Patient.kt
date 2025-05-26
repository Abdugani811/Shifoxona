package com.example.shifoxona

data class Patient(
    val firstName: String,
    val lastName: String,
    val doctorFirstName: String,
    val doctorLastName: String,
    val arrivalTime: Long = System.currentTimeMillis() // Kelgan vaqt (milisaniyalar)
) {
    fun fullName(): String = "$firstName $lastName"
    fun doctorFullName(): String = "$doctorFirstName $doctorLastName"
}