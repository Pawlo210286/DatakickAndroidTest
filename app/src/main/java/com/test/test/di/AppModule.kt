package com.test.test.di

import com.test.test.App
import com.test.test.domain.di.DomainModule
import com.test.test.source.remote.di.RemoteModule
import org.kodein.di.Kodein
import org.kodein.di.android.androidModule

object AppModule {
    fun get(application: App) = Kodein.Module("AppModule") {
        import(androidModule(application))
        import(RemoteModule.get())
        import(DomainModule.get())
    }
}