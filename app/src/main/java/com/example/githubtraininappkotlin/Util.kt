package com.example.githubtraininappkotlin
object Util {

    fun displayDate(date: String): String{
        return date.substring(0, 10)+ " h: " + date.substring(11, 16)
    }
}