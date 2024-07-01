package com.example.randomuser.presentation.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object HelperUtil {
    @SuppressLint("SimpleDateFormat")
    fun formatDate(dateString: String): Date? {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.parse(dateString)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatBirthDay(birthday: String): String {
        val dob = Calendar.getInstance()
        dob.time = formatDate(birthday) ?: Date()
        val year = dob.get(Calendar.YEAR)
        val month = dob.get(Calendar.MONTH)
        val day = dob.get(Calendar.DAY_OF_MONTH)
        dob.set(year, month, day)

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(dob.time)
    }

    fun calcAge(birthday: String): Int {
        val date = Date()
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()

        dob.time = formatDate(birthday) ?: date
        val year = dob.get(Calendar.YEAR)
        val month = dob.get(Calendar.MONTH)
        val day = dob.get(Calendar.DAY_OF_MONTH)

        dob.set(year, month + 1, day)

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--
        }

        return age
    }
}
