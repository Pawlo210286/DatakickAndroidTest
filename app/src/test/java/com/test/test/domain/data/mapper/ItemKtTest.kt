package com.test.test.domain.data.mapper

import com.google.gson.internal.LinkedTreeMap
import com.test.test.domain.data.Item
import org.junit.Assert.assertEquals
import org.junit.Test

class ItemKtTest {

    @Test
    fun remoteToEntityTest() {
        val remote = LinkedTreeMap<Any, Any>().apply {
            this["gtin14"] = "id"
            this["name"] = "name"
            this["images"] = ArrayList<LinkedTreeMap<String, String>>().apply {
                add(LinkedTreeMap<String, String>().apply { this["url"] = "image 1" })
                add(LinkedTreeMap<String, String>().apply { this["url"] = "image 2" })
            }
            this["some field"] = 14
            this["some field 2"] = 89
            this["some field 3"] = "some value"
        }

        val entity = remote.toItem()

        val expected = Item(
            id = "id",
            name = "name",
            images = listOf(
                "image 1",
                "image 2"
            ),
            otherFields = LinkedTreeMap<String, String>().apply {
                this["some field"] = "14"
                this["some field 2"] = "89"
                this["some field 3"] = "some value"
            }
        )

        assertEquals(expected, entity)
    }
}