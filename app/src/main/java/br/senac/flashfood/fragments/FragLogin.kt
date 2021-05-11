package br.senac.flashfood.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.flashfood.R
import br.senac.flashfood.activities.BottomNavigationActivity
import br.senac.flashfood.databinding.FragmentFragLoginBinding
import br.senac.flashfood.utils.ui.alterFragment

class FragLogin : Fragment() {

    lateinit var binding: FragmentFragLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragLoginBinding.inflate(inflater)

        binding.btnSignupLogin.setOnClickListener {
            alterView(FragSignup())
        }

        binding.txtForgotePassword.setOnClickListener {
           alterView(FragForgotPassword())
        }

        binding.btnEnterLogin.setOnClickListener {
          startActivity(Intent(activity, BottomNavigationActivity::class.java))
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