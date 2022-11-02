package com.android.customlintrulesandroid.data.network

import com.android.customlintrulesandroid.data.models.CommonResponse
import retrofit2.Response
import retrofit2.http.GET
import com.android.customlintrulesandroid.data.localdb.entities.Categories
import com.android.customlintrulesandroid.data.localdb.entities.CategoryImages
import com.android.customlintrulesandroid.data.models.CategoriesRemote
import com.android.customlintrulesandroid.data.models.CategoryImagesRemote

interface Api {

    //TODO: remove this class if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    @GET("categories")
    suspend fun getCategoriesRemoteLocal(): Response<CommonResponse<List<Categories>>>

    //TODO: remove this class if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    @GET("category_images")
    suspend fun getCategoryImagesRemoteLocal(): Response<CommonResponse<List<CategoryImages>>>

    //TODO: remove this class if you are USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    @GET("categories")
    suspend fun getCategoriesRemote(): Response<CommonResponse<List<CategoriesRemote>>>

    //TODO: remove this class if you are USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
    @GET("category_images")
    suspend fun getCategoryImagesRemote(): Response<CommonResponse<List<CategoryImagesRemote>>>

}
