package com.lubulwa.testapp.views

import android.os.Bundle
import com.lubulwa.testapp.base.BaseActivity
import com.lubulwa.testapp.databinding.ActivityMainBinding
import com.lubulwa.testapp.utils.viewModelOf

class MainActivity : BaseActivity<HomeViewModel, ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getViewModel(): HomeViewModel = viewModelOf(mViewModelProvider)
}