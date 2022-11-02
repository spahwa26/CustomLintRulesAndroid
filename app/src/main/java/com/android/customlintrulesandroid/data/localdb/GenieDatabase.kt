package com.android.customlintrulesandroid.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.customlintrulesandroid.data.localdb.dao.CategoryDao
import com.android.customlintrulesandroid.data.localdb.entities.Categories
import com.android.customlintrulesandroid.data.localdb.entities.CategoryImages

@Database(entities = [Categories::class, CategoryImages::class], version = 1, exportSchema = false)
abstract class GenieDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
