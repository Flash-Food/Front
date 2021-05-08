package br.senac.flashfood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.flashfood.R
import br.senac.flashfood.databinding.FragmentFragSignupBinding
import br.senac.flashfood.utils.ui.alterFragment

class FragSignup : Fragment() {

    lateinit var binding: FragmentFragSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragSignupBinding.inflate(inflater)


        binding.buttonBackSignup.setOnClickListener {
            alterView(FragLogin())
        }

        binding.buttonSignup.setOnClickListener {
            alterView(FragLogin())
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragSignup()
    }

    fun alterView(fragment: Fragment) {
        alterFragment(activity, R.id.containerMain, fragment)
    }
}