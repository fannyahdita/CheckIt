package com.example.checkIt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {
    private lateinit var actionBar: ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        actionBar = this.supportActionBar!!
        actionBar.apply {
            title = "Form"
            setDisplayHomeAsUpEnabled(true)
            elevation = 0F
        }


        button_submit.setOnClickListener {
            when {
                edittext_name.text.isNullOrEmpty() -> {
                    edittext_name.setErrorNotBlank("Name")
                }
                edittext_phone.text.isNullOrEmpty() -> {
                    edittext_phone.setErrorNotBlank("Phone Number")
                }
                edittext_city.text.isNullOrEmpty() -> {
                    edittext_city.setErrorNotBlank("City")
                }
                edittext_deposit.text.isNullOrEmpty() -> {
                    edittext_deposit.setErrorNotBlank("First Deposit")
                }
                edittext_phone.length() !in 11..14 -> {
                    edittext_phone.setErrorPhoneLength()
                }
                else -> {
                    val intent = Intent()
                    intent.putExtra("name", edittext_name.textString())
                    intent.putExtra(
                        "date",
                        "${datepicker_birthday.year}-${datepicker_birthday.month + 1}-${datepicker_birthday.dayOfMonth}"
                    )
                    intent.putExtra("phone", edittext_phone.textString())
                    intent.putExtra("city", edittext_city.textString())
                    intent.putExtra("deposit", edittext_deposit.textString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                setResult(Activity.RESULT_CANCELED)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
