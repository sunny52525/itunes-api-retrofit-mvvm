package org.shaun.itunessearch.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {

    fun getRetrofit():Retrofit{
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

         val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
         return  Retrofit.Builder().baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()


    }

}