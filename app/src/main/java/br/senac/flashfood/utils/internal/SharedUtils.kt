package br.senac.flashfood.utils.internal

import android.content.Context
import android.util.Log
import br.senac.flashfood.constants.SharedPreferencesConstants

class SharedUtils(context: Context) {

    private val SHARED_PREFERENCES =
        context.getSharedPreferences(SharedPreferencesConstants.NAME.value, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        SHARED_PREFERENCES.edit()
            .putString(SharedPreferencesConstants.TOKEN_NAME.value, token)
            .commit()
    }

    fun getToken(): String? {
       return SHARED_PREFERENCES.getString(SharedPreferencesConstants.TOKEN_NAME.value, "")
    }

    fun removeToken() {
        SHARED_PREFERENCES.edit()
            .putString(SharedPreferencesConstants.TOKEN_NAME.value, "")
            .commit()
    }
}