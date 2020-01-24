package com.test.test.presentation.main.item_list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.test.test.R
import com.test.test.databinding.FragmentItemListBinding
import com.test.test.presentation.base.BaseToolbarFragment
import com.test.test.presentation.main.item_list.common.ItemAdapter
import com.test.test.presentation.main.item_list.common.ItemOffsetDecoration
import org.kodein.di.generic.instance

class ItemListFragment : BaseToolbarFragment<FragmentItemListBinding>() {

    override val kodeinModule = ItemListModule.get(this)
    override val viewModel: ItemListContract.ViewModel by instance()

    private val adapter: ItemAdapter by lazy {
        ItemAdapter {
            viewModel.onItemSelected(it)
        }
    }

    override val toolbar: Toolbar? get() = binding.toolbar
    override val toolbarTitle: String? by lazy {
        getString(R.string.items)
    }

    override fun viewCreated(savedInstanceState: Bundle?) {
        initItemList()

        initViewModelObserves()

        applyAdapterDataObserver()

        initRefreshListener()
    }

    private fun initViewModelObserves() {
        viewModel.data.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initRefreshListener() {
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false

            adapter.currentList?.dataSource?.invalidate()
        }
    }

    private fun initItemList() {
        binding.itemList.adapter = adapter
        binding.itemList.addItemDecoration(ItemOffsetDecoration(requireContext(), R.dimen.space_16))
    }

    private fun applyAdapterDataObserver() {
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (positionStart == 0) {
                    binding.itemList.scrollToPosition(0)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.menu_search)
        val searchView = searchItem?.actionView as? SearchView

        searchView?.apply {
            imeOptions = EditorInfo.IME_ACTION_DONE
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.onQueryTextChange(newText.toString())
                    return false
                }
            })
        }
    }


    companion object {
        fun newInstance() = ItemListFragment()
    }
}