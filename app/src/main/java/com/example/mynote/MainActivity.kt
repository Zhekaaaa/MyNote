package com.example.mynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.BD.MyAdapter
import com.example.mynote.BD.MyDBManager
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    val myAdapter = MyAdapter(ArrayList(), this)
    val myDBManager = MyDBManager(this)
    lateinit var textView: TextView
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rcView)
        textView = findViewById(R.id.textView1)
        myDBManager.open()
        init()
    }

    override fun onResume() {
        super.onResume()
        fillAdapter()
    }

    fun onClickNew(view: View) {
        val i = Intent(this@MainActivity, EditActivity::class.java)
        startActivity(i)
    }

    fun init() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
    }

    fun fillAdapter() {
        val list = myDBManager.readDB()
        myAdapter.updateAdapter(list)
        if (list.size > 0) {
            textView.visibility = View.GONE
        }else{
            textView.visibility = View.VISIBLE
        }
    }
}