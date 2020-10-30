package com.example.checkIt

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        actionBar = this.supportActionBar!!
        actionBar.apply {
            title = "Detail"
            setDisplayHomeAsUpEnabled(true)
            elevation = 0F
        }

        val user = intent.getSerializableExtra("users") as? Users

        textview_detail_name.text = user?.name
        textview_detail_age.text = getString(R.string.detail_age, user?.birthday?.getAge())
        textview_detail_phone.text = user?.phone.toString()
        textview_detail_birthday.text = user?.birthday
        textview_detail_city.text = user?.city
        textview_detail_deposit.text = user?.firstDeposit.toString()

        val bonus = user?.birthday?.let { getBonus(it) }
        textview_detail_bonus.text = getString(R.string.detail_bonus, bonus)

        val balance = calculateBalance(user?.firstDeposit) {firstDeposit ->
            firstDeposit * (bonus?.div(100.0))!!
        }
        textview_detail_balance.text = balance.toString()
    }

    private fun getBonus(birthday: String): Int {
        val month = birthday.split("-")[1].toInt()
        if (month % 2 == 0) {
            return 15
        }
        return 10
    }

    private fun calculateBalance(deposit: Double?, func: (Double) -> Double): Double {
        if (deposit != null) {
            return func.invoke(deposit) + deposit
        }
        return 0.0
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}