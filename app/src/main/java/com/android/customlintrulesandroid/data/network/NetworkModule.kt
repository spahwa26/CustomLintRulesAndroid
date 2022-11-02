package com.android.customlintrulesandroid.data.network

import android.annotation.SuppressLint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.android.customlintrulesandroid.BuildConfig.BASE_URL
import com.android.customlintrulesandroid.BuildConfig.DEBUG
import com.android.customlintrulesandroid.data.preferences.UserPreferences
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //TODO: hardcoded string required here
    @SuppressLint("HardcodedStrings")
    @Provides
    @Singleton
    fun provideTokenInterceptor(preferences: UserPreferences) = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${preferences.authToken}")
            .build()
        chain.proceed(newRequest)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenInterceptor: Interceptor,
        fakeInterceptor: FakeInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(tokenInterceptor)

        //TODO: remove this line when you do not require fake data
        client.addInterceptor(fakeInterceptor)

        if (DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(logging)
        }

        return client.build()
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): Api {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        builder.client(client)
        return builder.build().create(Api::class.java)
    }

}
