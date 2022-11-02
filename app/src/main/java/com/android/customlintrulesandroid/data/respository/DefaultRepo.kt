package com.android.customlintrulesandroid.data.respository

import com.android.customlintrulesandroid.data.localdb.GenieDatabase
import com.android.customlintrulesandroid.data.models.CustomResult
import com.android.customlintrulesandroid.utils.Constants.SUCCESS
import javax.inject.Inject

//TODO: remove this class if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
class DefaultRepo @Inject constructor(
    private val remoteRepo: RemoteRepository,
    private val database: GenieDatabase
) {

    fun observeCategories() = database.categoryDao().getCategoriesWithImages()

    suspend fun getCategories(): CustomResult<String> {
        return when (val result = remoteRepo.getCategories()) {
            is CustomResult.Success -> {
                database.categoryDao().insertUpdateCategories(result.data.first)
                database.categoryDao().insertUpdateCategoryImages(result.data.second)
                CustomResult.Success(SUCCESS)
            }
            is CustomResult.Error -> {
                CustomResult.Error(result.exception)
            }
        }
    }

}
