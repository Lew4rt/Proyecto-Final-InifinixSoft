package com.example.prctican2adrianacua.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ingredient(
    @SerializedName("original") var original : String)
    : Serializable
