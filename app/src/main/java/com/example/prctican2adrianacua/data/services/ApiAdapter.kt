package com.example.prctican2adrianacua.data.services

import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime


class ApiAdapter {

    companion object {
        private var instance : Retrofit? = null
        val BASE_URL = "https://api.spoonacular.com/"

        fun get() : Retrofit {
            if (instance == null) {
                //Instanciacion

                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return instance!!
            // Agrego un non-null asserted al estar seguro de que jamás llegará un null debido al condicional
        }
    }


}