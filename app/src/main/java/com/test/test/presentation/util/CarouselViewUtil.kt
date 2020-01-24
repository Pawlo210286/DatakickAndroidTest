package com.test.test.presentation.util

import android.view.View
import com.bumptech.glide.Glide
import com.synnapps.carouselview.CarouselView

fun CarouselView.setupCarouselView(
    images: List<String> = emptyList(),
    onImageClick: (String) -> Unit = {},
    withEmptyImageList: (CarouselView) -> Unit = {}
) {
    if (images.isNotEmpty()) {
        visibility = View.VISIBLE
        setImageListener { position, imageView ->
            Glide.with(this)
                .load(images[position])
                .into(imageView)
            imageView?.setOnClickListener {
                onImageClick.invoke(images[position])
            }
        }
        pageCount = images.size
    } else {
        setImageListener { _, _ -> }
        withEmptyImageList.invoke(this)
    }
}
