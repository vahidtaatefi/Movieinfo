package com.taatefi.movieinfo.MyLib

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.HashMap


class MyLib {
    fun genre(key: Int): String {
        val hmap = HashMap<Int, String>()
        hmap[28] = "Action"
        hmap[12] = "Adventure"
        hmap[16] = "Animation"
        hmap[35] = "Comedy"
        hmap[80] = "Crime"
        hmap[99] = "Documentary"
        hmap[18] = "Drama"
        hmap[10751] = "Family"
        hmap[14] = "Fantasy"
        hmap[36] = "History"
        hmap[27] = "Horror"
        hmap[10402] = "Music"
        hmap[9648] = "Mystery"
        hmap[10749] = "Romance"
        hmap[878] = "Science Fiction"
        hmap[10770] = "TV Movie"
        hmap[53] = "Thriller"
        hmap[10752] = "War"
        hmap[37] = "Western"
        return hmap.get(key).toString()
    }
    fun BitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getEncoder().encodeToString(b);
        } else {
            android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);
        }
        return data
    }
    fun stringtobitmap(str:String): Bitmap? {
        val dt=if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Base64.getDecoder().decode(str);
        } else {
            android.util.Base64.decode(str, android.util.Base64.DEFAULT);
        }
       // val decodedString: ByteArray = Base64.decode(encodedImage, Base64.DEFAULT)
        val decodedByte =BitmapFactory.decodeByteArray(dt, 0, dt.size)
        return decodedByte
    }

}



//
//val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "moviedb")
//    .allowMainThreadQueries().build()
//val moviedao = db.movieDao()
//
//
//val tit = "vahid"
//val back = "12222"
//val poster = "222222"
//val json = "32225"
//val word = Movieinf(0, tit, back, poster, json)
//val dd=moviedao.delete(word)
//
//var qq = moviedao.getAll()
//moviedao.deleteAll()
//qq = moviedao.getAll()
//val id = moviedao.insetdata(word)