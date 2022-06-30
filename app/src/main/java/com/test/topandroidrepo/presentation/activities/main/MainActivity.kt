package com.test.topandroidrepo.presentation.activities.main


import android.os.Bundle
import com.test.topandroidrepo.databinding.ActivityMainBinding
import com.test.topandroidrepo.presentation.activities.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initializeViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}