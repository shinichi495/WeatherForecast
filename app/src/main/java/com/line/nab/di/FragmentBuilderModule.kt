package com.line.nab.di

import com.line.nab.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment() : MainFragment
}