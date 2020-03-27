package com.taatefi.movieinfo.Mvvm.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taatefi.movieinfo.Mvvm.model.modelmain
import com.taatefi.movieinfo.Retrofit.themovieresponsemodel
import com.taatefi.movieinfo.RoomDb.Movieinf
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class viewmodelmain(val model: modelmain) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val movieresponse = MutableLiveData<themovieresponsemodel>()
       private val movieresponseget = MutableLiveData<List<Movieinf>>()

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

    fun getmoviedatalocal(context: Context) {

        model.getmovieobservabledata(context)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe() {
                movieresponseget.value = it
            }?.let {
                disposable.add(it) {
                    movieerror.value = it.toString()
                }
            }
    }

    fun getresponsedata(): MutableLiveData<List<Movieinf>> = movieresponseget
    fun getResponse(): LiveData<themovieresponsemodel> = movieresponse

    fun getError(): LiveData<String?> = movieerror
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}

private fun CompositeDisposable.add(it: Disposable, function: () -> Unit) {

}
