package br.senac.flashfood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.flashfood.R
import br.senac.flashfood.databinding.FragmentFragLoginBinding
import br.senac.flashfood.utils.ui.alterFragment

class FragLogin : Fragment() {

    lateinit var binding: FragmentFragLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragLoginBinding.inflate(inflater)

        binding.btnSigUp.setOnClickListener {
            alterView(FragSignup())
        }

        binding.txtForgotePassword.setOnClickListener {
           alterView(FragForgotPassword())
        }

        binding.btnLogin.setOnClickListener {
          alterView(FragLogin())
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragLogin()
    }

    fun alterView(fragment: Fragment) {
        alterFragment(activity, R.id.containerMain, fragment)
    }
}