package com.test.test.domain.data

import android.os.Parcel
import androidx.test.runner.AndroidJUnit4
import com.google.gson.internal.LinkedTreeMap
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Test {

    @Test
    fun parcelTest() {
        val item = Item(
            id = "123",
            name = "name",
            otherFields = LinkedTreeMap<String, String>().apply {
                this["1"] = "1"
                this["2"] = "2"
            },
            images = listOf(
                "image 1",
                "image 2"
            )
        )

        val parcel = Parcel.obtain()
        item.writeToParcel(parcel, 0)
        parcel.setDataPosition(0)

        val createdFromParcel = Item.createFromParcel(parcel)
        Assert.assertThat(createdFromParcel.id, Is.`is`(item.id))
        Assert.assertThat(createdFromParcel.name, Is.`is`(item.name))
        Assert.assertThat(createdFromParcel.otherFields.size, Is.`is`(item.otherFields.size))
        Assert.assertThat(createdFromParcel.images.size, Is.`is`(item.images.size))
    }
}