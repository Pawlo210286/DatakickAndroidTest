package com.test.test.source.remote.data.response

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    val gtin14: String?,
    @SerializedName("brand_name")
    val brandName: String?,
    val name: String?,
    val size: String?,
    @SerializedName("serving_size")
    val servingSize: String?,
    @SerializedName("servings_per_container")
    val servingsPerContainer: String?,
    val calories: Int?,
    @SerializedName("fat_calories")
    val fatCalories: Int?,
    val fat: Float?,
    @SerializedName("saturated_fat")
    val saturatedFat: Float?,
    @SerializedName("trans_fat")
    val transFat: Float?,
    @SerializedName("polyunsaturated_fat")
    val polyunsaturatedFat: Float?,
    @SerializedName("monounsaturated_fat")
    val monounsaturatedFat: Float?,
    val cholesterol: Int?,
    val sodium: Int?,
    val carbohydrate: Int?,
    val fiber: Int?,
    val sugars: Int?,
    val protein: Int?
)