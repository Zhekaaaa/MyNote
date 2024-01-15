package com.example.mynote.BD

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDBManager(context: Context) {
    val feedReaderDbHelper = FeedReaderDbHelper(context)
    var db: SQLiteDatabase? = null

    fun open() {
        db = feedReaderDbHelper.writableDatabase
    }

    fun insertDB(title: String, content: String) {
        val values = ContentValues().apply {
            put(FeedReaderContract.COLUMN_NAME_TITLE, title)
            put(FeedReaderContract.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(FeedReaderContract.TABLE_NAME, null, values)
    }

    fun readDB(): ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()
        val cursor = db?.query(FeedReaderContract.TABLE_NAME, null, null, null, null, null, null)
        with(cursor) {
            while (this?.moveToNext()!!) {
                val dataText =
                    cursor?.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.COLUMN_NAME_TITLE))
                val dataTextContent =
                    cursor?.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.COLUMN_NAME_CONTENT))
                var item = ListItem()
                item.title = dataText!!
                item.desc = dataTextContent!!
                dataList.add(item)
            }
        }
        cursor?.close()
        return dataList
    }

    fun closeDB() {
        feedReaderDbHelper.close()
    }
}