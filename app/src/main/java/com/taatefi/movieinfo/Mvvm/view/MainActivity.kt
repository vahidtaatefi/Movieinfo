package com.taatefi.movieinfo.Mvvm.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.taatefi.movieinfo.Mvvm.viewmodel.viewmodelmain
import com.taatefi.movieinfo.MyLib.MyLib
import com.taatefi.movieinfo.MyLib.recycleradapter
import com.taatefi.movieinfo.MyLib.saveitemadapter
import com.taatefi.movieinfo.R
import com.taatefi.movieinfo.RoomDb.AppDatabase
import com.taatefi.movieinfo.RoomDb.Movieinf
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.net.URL

class MainActivity : AppCompatActivity() {
    private var appDatabase: AppDatabase? = null
    private var responseList: List<Movieinf>? = null

    //  private val appDatabase = AppDatabase.getInstance(this)
    val vm: viewmodelmain by viewModel()
    private val titlelist = ArrayList<String>()
    private val backdroplist = ArrayList<String>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
      StrictMode.setThreadPolicy(policy)
     //   appDatabase = AppDatabase.getInstance(this)

        //    val moviedao = appDatabase?.movieDao()
        //   moviedao?.deleteAll()
        //   val qq=moviedao?.getAll()

        titlelist.clear()
        backdroplist.clear()
        vm.getmoviedatalocal(this)
        vm.getresponsedata().observe(this, androidx.lifecycle.Observer {
            val aa = it
            for (x in 0..aa.size - 1) {

                titlelist.add(aa[x].titlename)
                backdroplist.add(aa[x].backdroppic)
            }
            val saveadapter = saveitemadapter(titlelist, backdroplist)
            recyclersaveitem.adapter = saveadapter
            //  val b= aa.subList(0,aa.size)
        })

        btnsearch.setOnClickListener() {
            hideKeyboard(this)
            titlelist.clear()
            backdroplist.clear()
            vm.getmoviedata(edtsearchtittle.text.toString())
        }
        //   vm.getmoviedata("terminator")

        vm.getResponse().observe(this, androidx.lifecycle.Observer {
            for (element in it.results) {
                titlelist.add(element.title)
                if (element.backdrop_path != null) {
                    backdroplist.add(element.backdrop_path)
                } else
                    backdroplist.add("1")
            }
            val adapter =
                recycleradapter(
                    titlelist,
                    backdroplist
                ) {
                    var intent = Intent(this, MoviePage::class.java)
                    intent.putExtra("moviename", it)
                    startActivity(intent)
                    titlelist.clear()
                    backdroplist.clear()
                }
            recycler.adapter = adapter


            // showPrayerTime(it.data.timings)
        })
        navbar.setOnClickListener() {
            draw.openDrawer(GravityCompat.START)
        }

    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

}
