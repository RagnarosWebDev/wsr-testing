package com.khlopov.medicapp

import android.app.Application
import android.util.Log
import com.google.gson.GsonBuilder
import com.khlopov.medicapp.data.repository.AppStoreInfoRepositoryImpl
import com.khlopov.medicapp.domain.repository.AppStoreInfoRepository
import com.khlopov.medicapp.presentation.AppStoreInfoViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = "https://virtserver.swaggerhub.com/"

fun provideOkHttp(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    val interceptor = Interceptor { chain ->
        val url = chain.request().url.newBuilder().build();


        val request = chain.request().newBuilder().url(url).build();

        return@Interceptor chain.proceed(request)
    }

    return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(loggingInterceptor)
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(client)
        .build()
}

val applicationModule = module {
    single<AppStoreInfoRepository> { AppStoreInfoRepositoryImpl(androidContext()) }
    single { AppStoreInfoViewModel(get()) }
    single { provideRetrofit(get()) }
    single { provideOkHttp() }
}

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(applicationModule)
        }
    }
}