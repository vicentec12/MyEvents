package br.com.vicentec12.eventtest.data.source.retrofit_api

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    private const val BASE_URL = "http://5f5a8f24d44d640016169133.mockapi.io/api/"

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(mOkHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(mOkHttpClient)
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideEventsService(mRetrofit: Retrofit): EventsService =
        mRetrofit.create(EventsService::class.java)

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient {
        val mInterceptor = HttpLoggingInterceptor()
        mInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(mInterceptor).build()
    }

}