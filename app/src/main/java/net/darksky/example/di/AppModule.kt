package net.darksky.example.di

import com.google.gson.Gson
import net.darksky.example.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggerInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.d(it)
        })
        loggerInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return loggerInterceptor
    }

    @Singleton
    @Provides
    fun provideEcbService(logInterceptor: HttpLoggingInterceptor): ApiService {
        val httpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(logInterceptor)
        val client = httpClientBuilder.build()

        return Retrofit.Builder()
                .baseUrl("https://api.darksky.net/forecast/71f7913db8f04fddc4f7419ed4ebe3a3/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }
}
