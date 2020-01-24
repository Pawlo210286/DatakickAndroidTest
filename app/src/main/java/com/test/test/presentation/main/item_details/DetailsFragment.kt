package com.test.test.presentation.main.item_details

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.test.test.R
import com.test.test.databinding.FragmentDetailsBinding
import com.test.test.domain.data.Item
import com.test.test.presentation.base.BaseToolbarFragment
import com.test.test.presentation.main.item_details.common.ItemDetailsAdapter
import com.test.test.presentation.util.setupCarouselView
import kotlinx.android.synthetic.main.fragment_details.*
import org.kodein.di.generic.instance


class DetailsFragment : BaseToolbarFragment<FragmentDetailsBinding>() {

    override val kodeinModule = DetailsModule.get(this)
    override val viewModel: DetailsContract.ViewModel by instance()

    override val toolbar: Toolbar? get() = binding.toolbar
    override val toolbarTitle: String?
        get() = item?.name ?: getString(R.string.details)

    private val item: Item?
        get() = arguments?.getParcelable(ARG_KEY_ITEM)


    override fun viewCreated(savedInstanceState: Bundle?) {
        viewModel.setItemFromArguments(item)

        viewModel.itemLD.observe(viewLifecycleOwner, Observer {
            setupCarouselView(it.images)
            binding.rvDetails.adapter = ItemDetailsAdapter(it)
        })
    }

    override fun setupToolbar(activity: AppCompatActivity) {
        super.setupToolbar(activity)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupCarouselView(images: List<String>) {
        carousel_view?.setupCarouselView(
            images = images,
            withEmptyImageList = {
                it.visibility = View.INVISIBLE
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            viewModel.onBackClick()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val ARG_KEY_ITEM = "arg_key_item"
        fun newInstance(item: Item) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_KEY_ITEM, item)
            }
        }
    }
}
