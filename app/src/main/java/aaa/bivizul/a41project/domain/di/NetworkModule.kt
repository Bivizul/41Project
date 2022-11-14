package aaa.bivizul.a41project.domain.di

import aaa.bivizul.a41project.data.network.FunspoService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
//    @Singleton
    fun provideRetrofit(baseUrl: String): FunspoService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FunspoService::class.java)

    companion object {
        const val BASE_URL = "http://65.109.10.118/"
    }

}