package com.test.test.di

import com.test.test.App
import org.kodein.di.Kodein
import org.kodein.di.android.androidModule

object AppModule {
    fun get(application: App) = Kodein.Module("AppModule") {
        import(androidModule(application))
    }
}