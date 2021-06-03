package br.senac.flashfood.managers

import android.content.Context
import android.content.Intent
import android.util.Log
import br.senac.flashfood.activities.MainActivity
import br.senac.flashfood.utils.internal.SharedUtils

class ManagerViews(context: Context) {

    private val CONTEXT = context

    private val SHARED_UTILS = SharedUtils(CONTEXT)

    fun alterViewIfForbidden() {
        SHARED_UTILS.removeToken()
        CONTEXT.startActivity(Intent(CONTEXT, MainActivity::class.java))
    }


}