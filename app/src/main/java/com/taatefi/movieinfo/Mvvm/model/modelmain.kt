package com.taatefi.movieinfo.Mvvm.model

import android.content.Context
import com.taatefi.movieinfo.Retrofit.RetrofitInterfacethmd
import com.taatefi.movieinfo.Retrofit.themovieresponsemodel
import com.taatefi.movieinfo.RoomDb.AppDatabase
import com.taatefi.movieinfo.RoomDb.Movieinf
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class modelmain {
    private fun getmoviedata(): RetrofitInterfacethmd {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(RetrofitInterfacethmd::class.java)


    }
    fun getmovieobservable(qr:String): Observable<themovieresponsemodel> {

        return getmoviedata().getmoviinfo("29c980d915e3290456b6d3c897ff2ba4","en-US",qr)
    }
    fun getmovieobservabledata(context: Context): Flowable<List<Movieinf>>? {

        return AppDatabase.getInstance(context)?.movieDao()?.getAll2()
    }
}