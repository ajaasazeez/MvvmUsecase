package com.example.cnews.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object Utils {

    @SuppressLint("NewApi", "SimpleDateFormat")
    fun getFormattedDate(date: String): String {
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ")
            val formatter = SimpleDateFormat("MMM dd, yyyy")
            formatter.format(parser.parse(date))
        }catch (e:Exception){
            ""
        }
    }
}