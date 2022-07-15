package com.example.prctican2adrianacua.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Plate(
    @SerializedName("title") var title : String,
    @SerializedName("summary") var summary : String,
    @SerializedName("pricePerServing") var pricePerServing : String,
    @SerializedName("glutenFree") var glutenFree : Boolean,
    @SerializedName("image") var image : String,
    @SerializedName("id") var id : String,
    @SerializedName("extendedIngredients") var ingredients : List<Ingredient>
    )
    : Serializable