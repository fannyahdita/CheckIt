package com.example.checkIt

import android.widget.EditText
import java.util.*

fun EditText.setErrorNotBlank(name : String) {
    this.error = "$name cannot be blank"
}

fun EditText.textString() : String {
    return this.text.toString().trim()
}

fun EditText.setErrorPhoneLength() {
    this.error = "Phone number length must be between 11 to 14"
}

fun String.getAge() : Int {
    val dateInt = this.split("-")
    val birthday = Calendar.getInstance()
    birthday.set(dateInt[0].toInt(), dateInt[1].toInt() - 1, dateInt[2].toInt())
    val today = Calendar.getInstance()

    var age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR)
    if (today.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
        if (today.get(Calendar.DAY_OF_MONTH) < birthday.get(Calendar.DAY_OF_MONTH)) {
            age--
        }
    } else if (today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
        age--
    }
    return age
}