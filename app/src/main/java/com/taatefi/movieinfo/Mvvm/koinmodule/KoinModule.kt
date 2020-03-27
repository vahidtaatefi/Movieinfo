package com.taatefi.movieinfo.Mvvm.koinmodule


import com.taatefi.movieinfo.Mvvm.model.modelmain
import com.taatefi.movieinfo.Mvvm.model.moviemodel
import com.taatefi.movieinfo.Mvvm.viewmodel.viewmodelmain
import com.taatefi.movieinfo.Mvvm.viewmodel.viewmodelmovie
import org.koin.dsl.module

import org.koin.android.viewmodel.dsl.viewModel

val KoinModule = module {
    single { modelmain() }
    viewModel { viewmodelmain(get()) }
    single { moviemodel() }
    viewModel { viewmodelmovie(get()) }

}