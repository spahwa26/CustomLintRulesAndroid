package com.android.customlintrulesandroid.data.respository

import com.android.customlintrulesandroid.data.localdb.entities.Categories
import com.android.customlintrulesandroid.data.localdb.entities.CategoryImages
import com.android.customlintrulesandroid.data.models.CategoriesModel
import com.android.customlintrulesandroid.data.models.CustomResult
import com.android.customlintrulesandroid.data.network.Api
import com.android.customlintrulesandroid.data.network.SafeApiRequest
import com.android.customlintrulesandroid.utils.Constants.EMPTY_STRING
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val client: Api
) : SafeApiRequest() {

    //TODO: remove this method if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    suspend fun getCategories(): CustomResult<Pair<List<Categories>, List<CategoryImages>>> {
        return when (val result = apiRequest { client.getCategoriesRemoteLocal() }) {
            is CustomResult.Success -> {
                when (val imageResult = apiRequest { client.getCategoryImagesRemoteLocal() }) {
                    is CustomResult.Success -> {
                        CustomResult.Success(Pair(result.data.data, imageResult.data.data))
                    }
                    is CustomResult.Error -> {
                        CustomResult.Error(imageResult.exception)
                    }
                }
            }
            is CustomResult.Error -> {
                CustomResult.Error(result.exception)
            }
        }
    }

    //TODO: remove this method if you are USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    suspend fun getCategoriesRemote(): CustomResult<List<CategoriesModel>> {
        return when (val result = apiRequest { client.getCategoriesRemote() }) {
            is CustomResult.Success -> {
                when (val imageResult = apiRequest { client.getCategoryImagesRemote() }) {
                    is CustomResult.Success -> {
                        val map = result.data.data.map { category ->
                            CategoriesModel(imageResult.data.data.find {
                                it.imageId == category.categoryId
                            }?.imageUrl ?: EMPTY_STRING, category.categoryDisplayName, category.isActive)
                        }
                        CustomResult.Success(map)
                    }
                    is CustomResult.Error -> {
                        CustomResult.Error(imageResult.exception)
                    }
                }
            }
            is CustomResult.Error -> {
                CustomResult.Error(result.exception)
            }
        }
    }

}
