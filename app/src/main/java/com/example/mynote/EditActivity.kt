package com.example.mynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mynote.BD.MyDBManager

class EditActivity : AppCompatActivity() {
    val myDBManager = MyDBManager(this)
    lateinit var edittext: EditText
    lateinit var edittext2: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        edittext = findViewById(R.id.editText1)
        edittext2 = findViewById(R.id.EditText2)
        findViewById<Button>(R.id.save).setOnClickListener {
            onClickSave()
            val i = Intent(this@EditActivity, MainActivity::class.java)
            startActivity(i)
        }
        getMYIntents()
    }

    override fun onResume() {
        super.onResume()
        myDBManager.open()


    }

    fun onClickSave() {
        myDBManager.open()
        myDBManager.insertDB(edittext.text.toString(), edittext2.text.toString())
        val dataList = myDBManager.readDB()


    }

    override fun onDestroy() {
        super.onDestroy()
        myDBManager.closeDB()
    }

    fun getMYIntents() {
        val i = intent
        if (i != null) {
            edittext.setText(i.getStringExtra(MyDBConstans.I_TITLE_KEY))
            edittext2.setText(i.getStringExtra(MyDBConstans.I_DESC_KEY))
        }
    }
}