package com.example.myapplication

import android.os.Parcelable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import model.Data
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class MyAdapter(private val dataList: MutableList<Data>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, pl: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent,false))
   }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = dataList[position]


        val userFullNameTextView = holder.itemView.user_full_name
        val userAvatarImageView = holder.itemView.user_avatar

        val fullName = "${data.firstName} ${data.lastName}"

        userFullNameTextView.text = fullName

        Picasso.get()
            .load(data.avatar)
            .into(userAvatarImageView)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, fullName, Toast.LENGTH_SHORT).show()
        }

    }


}

