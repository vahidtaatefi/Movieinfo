package com.taatefi.movieinfo.Mvvm.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taatefi.movieinfo.Mvvm.model.moviemodel
import com.taatefi.movieinfo.Retrofit.themovieresponsemodel
import com.taatefi.movieinfo.RoomDb.AppDatabase
import com.taatefi.movieinfo.RoomDb.Movieinf
import com.taatefi.movieinfo.RoomDb.moviedao
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class viewmodelmovie(val model: moviemodel) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val movieresponse = MutableLiveData<themovieresponsemodel>()


    private val movieerror = MutableLiveData<String?>()
    fun getmoviedata(query: String) {
        disposable.add(
            model.getmovieobservable(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieresponse.value = it

                }, {
                    Log.d("myTest", it.message)
                    movieerror.value = "Error in Retrieving moviedata data"
                })
        )

    }

    fun savemoviedata(context: Context, movieinf: Movieinf) {
        model.savemoviedata(context, movieinf)
    }


    fun getResponse(): LiveData<themovieresponsemodel> = movieresponse


    fun getError(): LiveData<String?> = movieerror
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}

private fun CompositeDisposable.add(it: Disposable, function: () -> Unit) {

}


