package com.example.checkIt

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_biodata.view.*
import kotlin.collections.ArrayList

class UsersAdapter(private val context: Context) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private var user: List<Users?> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_biodata, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(user[position], context)
    }

    override fun getItemCount(): Int = user.size

    fun setUsers(user: List<Users>) {
        this.user = user
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(users: Users?, context: Context) {
            with(itemView) {
                textview_name.text = users?.name
                textview_age.text = users?.birthday?.getAge().toString()
                textview_phone.text = users?.phone
                textview_city.text = users?.city

                this.setOnClickListener {
                    val intent = Intent(context, UserDetailActivity::class.java)
                    intent.putExtra("users", users)
                    context.startActivity(intent)
                }
            }
        }
    }
}