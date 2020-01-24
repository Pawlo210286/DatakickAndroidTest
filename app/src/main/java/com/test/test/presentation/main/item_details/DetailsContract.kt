package com.test.test.presentation.main.item_details

import androidx.lifecycle.LiveData
import com.test.test.domain.data.Item
import com.test.test.presentation.base.BaseViewModel

interface DetailsContract {
    interface ViewModel : BaseViewModel {
        val itemLD: LiveData<Item>

        fun setItemFromArguments(item: Item?)

        fun onBackClick()
    }

    interface Router {
        fun goBack()
    }
}