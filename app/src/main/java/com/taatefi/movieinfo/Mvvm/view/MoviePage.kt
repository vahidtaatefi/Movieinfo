package com.taatefi.movieinfo.Mvvm.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.taatefi.movieinfo.Mvvm.viewmodel.viewmodelmovie
import com.taatefi.movieinfo.MyLib.MyLib
import com.taatefi.movieinfo.R
import com.taatefi.movieinfo.MyLib.movierecycleradapter
import com.taatefi.movieinfo.Retrofit.themovieresponsemodel
import com.taatefi.movieinfo.RoomDb.AppDatabase
import com.taatefi.movieinfo.RoomDb.Movieinf
import kotlinx.android.synthetic.main.activity_movie_page.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.net.URL
import kotlin.math.pow

class MoviePage : AppCompatActivity() {
    lateinit var json: themovieresponsemodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_page)
        val my=MyLib()
        val vm: viewmodelmovie by viewModel()
        val intent = intent.extras
        if (intent != null) {
            var moviename = intent.get("moviename")
            vm.getmoviedata(moviename as String)
        }
        toolbar.setOnClickListener() {
            finish()
        }
        btnsave.setOnClickListener() {
            AppDatabase.getInstance(this)
            var url = URL("http://image.tmdb.org/t/p/w300/" + json.results[0].backdrop_path)
            var bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            val btosbackpro=my.BitMapToString(bmp)
            val movieinf = Movieinf(0,json.results[0].title,btosbackpro)
            vm.savemoviedata(this, movieinf)
        }
        val mylib = MyLib()
        var url: URL
        val genrelist = ArrayList<String>()
        var bmp: Bitmap?
        vm.getResponse().observe(this, androidx.lifecycle.Observer { it ->
            json=it
            toolbar.setTitle(it.results[0].title)
            if (it.results[0].backdrop_path != null) {
                url = URL("http://image.tmdb.org/t/p/w300/" + it.results[0].backdrop_path)
                bmp = decodeStream(url.openConnection().getInputStream())
                imgbackdrop.setImageBitmap(bmp)
            } else imgbackdrop.setImageBitmap(R.drawable.movie)
            if (it.results[0].poster_path != null) {
                url = URL("http://image.tmdb.org/t/p/w300/" + it.results[0].poster_path)
                bmp = decodeStream(url.openConnection().getInputStream())
                imgposter.setImageBitmap(bmp)
            } else imgposter.setImageBitmap(R.drawable.movie)
            txtreleasedate.text = "Release Date: " + it.results[0].release_date
            ratingBar1.rating = (it.results[0].vote_average / 2).pow(1).toFloat()
            txtoverview.text = it.results[0].overview

            for (x in 0..it.results[0].genre_ids.size - 1) {
                genrelist.add(mylib.genre(it.results[0].genre_ids[x]))
            }
            val adapter = movierecycleradapter(genrelist)
            recycler.adapter = adapter

        })


        // val adapter = movierecycleradapter(genrelist)
        // recycler.adapter=adapter
    }
}

private fun ImageView.setImageBitmap(movie: Int) {

}

