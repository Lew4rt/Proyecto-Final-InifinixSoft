package com.example.prctican2adrianacua.data.services

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull
import androidx.core.content.edit
import com.example.prctican2adrianacua.data.model.Plate
import com.example.prctican2adrianacua.data.model.Usuario
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

public class AppPreferences {

    companion object {

        private val USER_KEY = "user"
        private val FAV_KEY = "favorites"
        private val gson: Gson = Gson()
        private var preferences: SharedPreferences? = null

        private fun getPreferences(context: Context): SharedPreferences {
            if (preferences == null) {
                preferences = context.applicationContext.getSharedPreferences(
                    "practica_n2_preferences",
                    Context.MODE_PRIVATE
                )
            }
            return preferences!!
        }

        public fun getUser(context: Context): Usuario? {
            val userJson: String? = getPreferences(context).getString(USER_KEY, null)
            userJson?.let {
                return gson.fromJson(userJson, Usuario::class.java)
            }
            return null
        }

        public fun saveUser(context: Context, user : Usuario) {
            getPreferences(context).edit().putString(USER_KEY, gson.toJson(user)).apply()
        }

        public fun deleteUser(context: Context, user: Usuario){
            getPreferences(context).edit().remove(gson.toJson(user)).apply()
        }

        fun saveFavorite(context : Context, plate : Plate) : Boolean{
            val lista = getFavorites(context)
            lista.forEach {
                if(it.id == plate.id) {
                    return false
                }
            }
            lista.add(plate)
            getPreferences(context).edit().putString(FAV_KEY, gson.toJson(lista)).apply()
            return true
        }

        fun getFavorites(context : Context) : MutableList<Plate> {
            val json : String? = getPreferences(context).getString(FAV_KEY, null)
            return if(json == null) {
                emptyList<Plate>().toMutableList()
            } else {
                val itemType = object : TypeToken<MutableList<Plate>>() {}.type
                gson.fromJson(json, itemType)
            }
        }

        fun removeFavorite(context : Context, plate : Plate) {
            val lista = getFavorites(context)
            run breaking@ {
                lista.forEach {
                    if(plate.id == it.id) {
                        lista.remove(it)
                        return@breaking
                    }
                }
            }

            getPreferences(context).edit().putString(FAV_KEY, gson.toJson(lista)).apply()
        }
    }
}
