package com.test.test.presentation.base

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein

abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    private val _parentKodein by closestKodein()
    override val kodein: Kodein by retainedKodein {
        extend(_parentKodein)
        import(diModule())
    }
    override val kodeinTrigger = KodeinTrigger()

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        kodeinTrigger.trigger()
        super.onCreate(savedInstanceState)
    }

    abstract fun diModule(): Kodein.Module

    inline fun <reified VM : BaseViewModelImpl> vm(factory: ViewModelProvider.Factory): VM =
        ViewModelProvider(this, factory)[VM::class.java]
}