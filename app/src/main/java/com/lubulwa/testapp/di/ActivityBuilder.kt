package com.lubulwa.testapp.di

import com.lubulwa.testapp.views.MainActivity
import com.lubulwa.testapp.views.edit.EditExpenseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindHomeActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindEditExpenseActivity(): EditExpenseActivity

}