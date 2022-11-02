package com.android.customlintrulesandroid.data.models

import com.google.gson.annotations.SerializedName

//TODO: remove this class if you are USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
data class CategoriesRemote(
    @SerializedName("category_id") val categoryId: String,
    @SerializedName("category_display_name") val categoryDisplayName: String,
    @SerializedName("isactive") val isActive: String
)
