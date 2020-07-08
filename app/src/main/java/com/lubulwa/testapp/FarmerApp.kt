package com.lubulwa.testapp

import android.app.Application
import com.lubulwa.testapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class FarmerApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .create(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = injector

}