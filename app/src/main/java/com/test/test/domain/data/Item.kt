package com.test.test.domain.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.internal.LinkedTreeMap

class Item(
    val id: String,
    val name: String,
    val images: List<String>,
    val otherFields: LinkedTreeMap<String, String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readString().orEmpty(),
        name = parcel.readString().orEmpty(),
        otherFields = parcel.let {
            val keys = it.readString().orEmpty().split(SEPARATOR)
            val values = it.readString().orEmpty().split(SEPARATOR)

            if (keys.isEmpty() || keys.size != values.size) {
                LinkedTreeMap<String, String>()
            } else {
                val result = LinkedTreeMap<String, String>()

                keys.forEachIndexed { index, s ->
                    result[s] = values[index]
                }

                result
            }
        },
        images = parcel.readString().orEmpty().split(SEPARATOR)
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (id != other.id) return false
        if (name != other.name) return false
        if (otherFields != other.otherFields) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + otherFields.hashCode()
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)

        val keys = otherFields.keys.joinToString(SEPARATOR)
        val values = otherFields.values.joinToString(SEPARATOR)

        parcel.writeString(keys)
        parcel.writeString(values)

        val images = images.joinToString(SEPARATOR)

        parcel.writeString(images)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }

        private const val SEPARATOR = "<|>"
    }
}