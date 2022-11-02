package com.android.customlintrulesandroid.data.models

import androidx.room.ColumnInfo

//TODO: remove @ColumnInfo if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
data class CategoriesModel(
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "category_display_name") val name: String,
    @ColumnInfo(name = "isactive") val isActive: String?,
)
