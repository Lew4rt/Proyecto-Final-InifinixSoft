package com.example.prctican2adrianacua.data.services

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.prctican2adrianacua.data.model.Usuario
import com.google.gson.Gson

public class AppPreferences {

    companion object {

        private val USER_KEY = "user"
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
    }
}