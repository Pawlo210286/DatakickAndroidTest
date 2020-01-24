package com.test.test.presentation.main

import android.os.Bundle
import com.test.test.R
import com.test.test.presentation.base.BaseActivity
import org.kodein.di.generic.instance

class MainActivity : BaseActivity() {

    override fun diModule() = MainModule.get(this)

    private val router: MainRouter by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.navigateToTestFragment()
    }
}