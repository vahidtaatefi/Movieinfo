package com.taatefi.movieinfo.baseapplication

import android.app.Application
import com.taatefi.movieinfo.Mvvm.koinmodule.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class baseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@baseApplication)
            modules(KoinModule)

          //  modules(koinmodelaladhan, koinviewmodelaladhan, KoinModule)
            //  modules(koinviewmodelaladhan,KoinModule)
        }
    }
}
