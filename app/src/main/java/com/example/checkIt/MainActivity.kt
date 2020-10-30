package com.example.checkIt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dummyData = mutableListOf(
        Users("Surya Saputra", "1998-11-30", "+621234567891", "Bandung", 1000000.0),
        Users("Raisa Andriani", "1998-03-07", "+621234567853", "Jakarta", 2000000.0),
        Users("Isyana Sarasvati", "1990-12-1", "+621838567692", "Sukabumi", 3000000.0),
        Users("Rizky Febian", "1993-01-4", "+621234567432", "Bandung", 4000000.0),
        Users("Ayu Ting Ting", "1997-09-28", "+621234567432", "Depok",5000000.0),
        Users("Nick Young", "1992-07-09", "+621234567432", "Tangerang Selatan", 6000000.0)
    )
    private lateinit var actionBar: ActionBar
    private var usersAdapter = UsersAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar = this.supportActionBar!!
        actionBar.apply {
            title = "Home"
            setDisplayHomeAsUpEnabled(false)
            elevation = 0F
        }

        usersAdapter.setUsers(dummyData)
        recyclerview.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        button_add.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val name = data.getStringExtra("name")
                val birthday = data.getStringExtra("date")
                val phone = data.getStringExtra("phone")
                val city = data.getStringExtra("city")
                val deposit = data.getStringExtra("deposit")?.toDouble()

                dummyData.add(Users(name, birthday, phone, city, deposit))
                usersAdapter.setUsers(dummyData)
                usersAdapter.notifyDataSetChanged()
            }
        }
    }
}