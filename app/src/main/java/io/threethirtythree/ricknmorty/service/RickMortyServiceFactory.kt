package io.threethirtythree.ricknmorty.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** Cinfigures a retrofit service utilizing a GsonConverter and HttpLoggingInteceptor for debug. */
object RickMortyServiceFactory {
    private const val BASE_URL = "https://rickandmortyapi.com/"

    private var rickMortyService: RickMortyService? = null

    fun getService() : RickMortyService {
        if (rickMortyService == null) {
            rickMortyService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }
                        )
                        .build()
                )
                .build()
                .create(RickMortyService::class.java)
        }
        return rickMortyService!!
    }
}