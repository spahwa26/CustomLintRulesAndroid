package com.android.customlintrulesandroid.data.localdb

import javax.inject.Inject


//TODO: remove this whole package if you are NOT USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
@Suppress("unused")
class LocalRepo @Inject constructor(private val database: GenieDatabase)
