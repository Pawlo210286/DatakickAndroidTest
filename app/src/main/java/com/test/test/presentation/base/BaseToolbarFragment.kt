package com.test.test.presentation.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding

abstract class BaseToolbarFragment<B : ViewDataBinding> : BaseFragment<B>() {

    abstract val toolbarTitle: String?
    abstract val toolbar: Toolbar?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar((activity as AppCompatActivity))
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    open fun setupToolbar(activity: AppCompatActivity) {
        activity.apply {
            setSupportActionBar(this@BaseToolbarFragment.toolbar)
            supportActionBar?.apply {
                title = toolbarTitle ?: ""
            }
        }
    }

}