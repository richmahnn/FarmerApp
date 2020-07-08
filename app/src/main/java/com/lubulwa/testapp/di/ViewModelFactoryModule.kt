package com.lubulwa.testapp.di

import androidx.lifecycle.ViewModelProvider
import com.lubulwa.testapp.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}