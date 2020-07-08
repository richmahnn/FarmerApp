package com.lubulwa.testapp.di

import com.lubulwa.testapp.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): MainActivity

}