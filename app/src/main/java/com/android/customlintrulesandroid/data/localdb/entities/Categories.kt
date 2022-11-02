package com.android.customlintrulesandroid.data.localdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Category")
data class Categories(
    @PrimaryKey @SerializedName("category_id") @ColumnInfo(name = "category_id") val categoryId: String,
    @SerializedName("category_display_name") @ColumnInfo(name = "category_display_name") val categoryDisplayName: String,
    @SerializedName("isactive") @ColumnInfo(name = "isactive") val isActive: String
)
