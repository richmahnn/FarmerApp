package com.lubulwa.testapp.di

import android.app.Application
import com.lubulwa.testapp.data.DbFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun providesFarmerDb(context: Application) = DbFactory.getInstance(context)

    @Provides
    @Singleton
    fun provideExpensesDao(dbFactory: DbFactory) = dbFactory.getExpensesDao()

}