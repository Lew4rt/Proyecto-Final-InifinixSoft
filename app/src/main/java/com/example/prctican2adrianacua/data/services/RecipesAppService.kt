package com.example.prctican2adrianacua.data.services

import com.example.prctican2adrianacua.data.model.Plate
import com.example.prctican2adrianacua.data.model.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesAppService {

    @GET("recipes/random")
    fun getRandomRecipes(@Query("apiKey") apiKey : String, @Query("number") count : Int) : Call<Recipes>
}