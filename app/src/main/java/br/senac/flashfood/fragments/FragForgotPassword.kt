package br.senac.flashfood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.flashfood.R
import br.senac.flashfood.databinding.FragmentFragForgotPasswordBinding
import br.senac.flashfood.utils.ui.alterFragment

class FragForgotPassword : Fragment() {

    lateinit var binding : FragmentFragForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragForgotPasswordBinding.inflate(inflater)

        binding.buttonSendForgot.setOnClickListener {
            alterView(FragResetPassword())
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FragForgotPassword()
    }

    fun alterView(fragment: Fragment) {
        alterFragment(activity, R.id.containerMain, fragment)
    }
}