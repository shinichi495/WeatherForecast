package com.line.nab.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.line.nab.ui.main.MainViewModel
import com.line.nab.utilities.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewmodel(mainVm : MainViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}