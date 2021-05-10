package br.senac.flashfood.utils.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import br.senac.flashfood.R
import br.senac.flashfood.fragments.FragLogin

// Responsibles function for alter of the fragments
fun alterFragment(activity : FragmentActivity?, containerId : Int, fragment: Fragment) {
    activity?.supportFragmentManager?.beginTransaction()
        ?.replace(containerId, fragment)
        ?.commit()
}