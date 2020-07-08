package com.lubulwa.testapp.di

import android.app.Application
import com.lubulwa.testapp.FarmerApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelFactoryModule::class,
    ViewModelFactoryModule::class,
    DbModule::class,
    ViewModelModule::class,
    ActivityBuilder::class
])
interface AppComponent : AndroidInjector<FarmerApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: FarmerApp)

}