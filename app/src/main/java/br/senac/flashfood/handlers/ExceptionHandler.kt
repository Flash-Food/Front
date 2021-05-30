package br.senac.flashfood.handlers

import android.content.Context
import android.util.Log
import br.senac.flashfood.managers.ManagerViews
import io.reactivex.rxjava3.exceptions.CompositeException

class ExceptionHandler(context: Context) : Thread.UncaughtExceptionHandler {

    private val MANAGER_VIEWS = ManagerViews(context)

    override fun uncaughtException(t: Thread, e: Throwable) {
        (e as CompositeException).exceptions.forEach{
            if(it.message.equals("403")) {
                Log.e("ACHOI", "AAAA")
                MANAGER_VIEWS.alterViewIfForbidden()
            }

        }
    }
}