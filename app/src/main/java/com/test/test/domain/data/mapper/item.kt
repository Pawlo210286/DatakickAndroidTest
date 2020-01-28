package com.test.test.domain.data.mapper

import com.google.gson.internal.LinkedTreeMap
import com.test.test.domain.data.Item

fun LinkedTreeMap<Any, Any>.toItem(): Item {
    return Item(
        id = this["gtin14"].toString(),
        name = this["name"].toString(),
        size = this["size"]?.toString() ?: "",
        serverSize = this["server_size"]?.toString() ?: "0",
        images = this@toItem["images"]?.let {
            (it as ArrayList<LinkedTreeMap<Any, Any>>).map {
                it["url"].toString()
            }
        }.orEmpty(),
        otherFields = LinkedTreeMap<String, String>().apply {
            this@toItem.apply {
                remove("gtin14")
                remove("name")
                remove("images")
            }
            this@toItem.keys.forEach {
                this[it.toString()] = this@toItem[it].toString()
            }
        }
    )
}