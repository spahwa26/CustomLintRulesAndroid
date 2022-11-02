package com.android.customlintrulesandroid.data.models

import com.google.gson.annotations.SerializedName

data class CommonResponse<T : Any>(@SerializedName("data") val data: T)
