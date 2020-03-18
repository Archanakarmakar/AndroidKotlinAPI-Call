package com.example.myapplication

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.trace
import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import model.Data
import model.Reqres

class MainActivity : AppCompatActivity() {

    private val dataList:MutableList<Data> = mutableListOf()
    private  lateinit var myAdapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myAdapter = MyAdapter(dataList)

        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        my_recycler_view.adapter=myAdapter

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://reqres.in/api/users?page=2")
            .build()
            .getAsObject(Reqres::class.java, object : ParsedRequestListener<Reqres> {
                override fun onResponse(response: Reqres) {
                    dataList.addAll(response.data)
                    myAdapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {

                }


            })
    }
}



