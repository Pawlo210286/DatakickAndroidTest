package com.test.test.presentation.main

import com.test.test.R
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

object MainModule {

    fun get(activity: MainActivity) = Kodein.Module("MainModule") {
        bind<MainRouter>() with provider {
            MainRouter(
                manager = activity.supportFragmentManager,
                containerId = R.id.fragmentContainer
            )
        }
    }
}