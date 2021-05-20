package br.senac.flashfood.utils.ui

import android.content.Context
import android.widget.Toast

fun toastShow(context: Context?, msg: String, duration: Int) = Toast.makeText(context, msg, duration).show()