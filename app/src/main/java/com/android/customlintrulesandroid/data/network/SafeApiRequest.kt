package com.android.customlintrulesandroid.data.network

import org.json.JSONObject
import retrofit2.Response
import com.android.customlintrulesandroid.data.models.CustomResult
import com.android.customlintrulesandroid.utils.ApiException
import com.android.customlintrulesandroid.utils.UnAuthorizedException
import com.android.customlintrulesandroid.utils.localizedException

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): CustomResult<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful)
                CustomResult.Success(response.body()!!)
            else
                CustomResult.Error(response.getApiException().localizedException)
        } catch (e: Exception) {
            CustomResult.Error(e.localizedException)
        }
    }

    //TODO: delete if not in use
    @Throws(Exception::class)
    suspend fun <T : Any> apiRequestWithException(call: suspend () -> Response<T>): T {
        return try {
            val response = call.invoke()
            if (response.isSuccessful)
                response.body()!!
            else
                throw response.getApiException()
        } catch (e: Exception) {
            throw e.localizedException
        }
    }

    @Throws(Exception::class)
    fun <T : Any> Response<T>.getApiException(): Exception {
        val error = errorBody()?.string()
        val errorObj = JSONObject(error ?: EMPTY_OBJECT)
        if (errorObj.getInt(CODE) == 401) {
            return UnAuthorizedException()
        }
        return ApiException(errorObj.getJSONObject(ERROR).getJSONArray(MESSAGE).getString(0))
    }

    companion object {
        private const val EMPTY_OBJECT = "{}"
        private const val CODE = "{}"
        private const val ERROR = "error"
        private const val MESSAGE = "message"
    }
}
