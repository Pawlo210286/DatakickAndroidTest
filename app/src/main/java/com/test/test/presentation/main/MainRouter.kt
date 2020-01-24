package com.test.test.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.test.R
import com.test.test.domain.data.Item
import com.test.test.presentation.main.item_details.DetailsContract
import com.test.test.presentation.main.item_details.DetailsFragment
import com.test.test.presentation.main.item_list.ItemListContract
import com.test.test.presentation.main.item_list.ItemListFragment


class MainRouter(
    private val manager: FragmentManager,
    private val containerId: Int
) : ItemListContract.Router,
    DetailsContract.Router {

    fun navigateToTestFragment() {
        ItemListFragment.newInstance()
            .replace()
    }

    override fun navigateToDetailsFragment(item: Item) {
        DetailsFragment.newInstance(item)
            .addWithBackStackAndAnim()
    }

    override fun goBack() {
        manager.popBackStack()
    }

    private fun Fragment.replace() {
        manager.beginTransaction()
            .replace(containerId, this, this::class.java.simpleName)
            .commit()
    }

    private fun Fragment.addWithBackStackAndAnim() {
        manager.beginTransaction()
            .setCustomAnimations(
                R.anim.anim_slide_from_right,
                R.anim.anim_fade_out,
                R.anim.anim_fade_in,
                R.anim.anim_slide_to_right
            )
            .add(containerId, this, this::class.java.simpleName)
            .addToBackStack(this::class.java.simpleName)
            .commit()
    }

}