package com.reavature.beefcake.network

import com.reavature.beefcake.auth_model.MemberDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */

object RetrofitAPIService {
    var token = MemberDatabase.currentMember?.token.toString()

    private const val BASE_URL = "https://beefcake-app-api.herokuapp.com/"



    private val okHttpClient2 = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val log = HttpLoggingInterceptor()
            log.level = HttpLoggingInterceptor.Level.BODY
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance2: Methods_API by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient2)
            .build()

        retrofit.create(Methods_API::class.java)

    }

}
