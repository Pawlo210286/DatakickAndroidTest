package com.test.test.presentation.main.item_details

import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

object DetailsModule {

    fun get(fragment: DetailsFragment) = Kodein.Module(DetailsFragment::class.java.simpleName) {
        bind<ViewModelProvider.Factory>(tag = "Details") with singleton {
            DetailsViewModelFactory(instance())
        }
        bind<DetailsContract.ViewModel>() with provider {
            fragment.vm<DetailsViewModel>(instance(tag = "Details"))
        }
    }
}