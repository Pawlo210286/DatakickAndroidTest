package com.test.test.presentation.main.item_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.test.domain.data.Item
import com.test.test.presentation.base.BaseViewModelImpl

class DetailsViewModel(
    private val router: DetailsContract.Router
) : BaseViewModelImpl(), DetailsContract.ViewModel {

    private val _itemLD = MutableLiveData<Item>()
    override val itemLD: LiveData<Item>
        get() = _itemLD

    override fun setItemFromArguments(item: Item?) {
        if (item != null) {
            _itemLD.value = item
        } else {
            router.goBack()
        }
    }

    override fun onBackClick() {
        router.goBack()
    }
}