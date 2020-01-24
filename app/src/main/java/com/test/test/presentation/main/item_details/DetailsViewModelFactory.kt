package com.test.test.presentation.main.item_details

import com.test.test.presentation.base.BaseFactory

class DetailsViewModelFactory(
    private val router: DetailsContract.Router
) : BaseFactory<DetailsViewModel>() {
    override fun createViewModel(): DetailsViewModel {
        return DetailsViewModel(router)
    }
}