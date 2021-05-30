package br.senac.flashfood.fragments.password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.flashfood.R
import br.senac.flashfood.databinding.FragmentFragResetPasswordBinding
import br.senac.flashfood.fragments.login.FragLogin
import br.senac.flashfood.utils.ui.alterFragment

class FragResetPassword : Fragment() {

    lateinit var binding: FragmentFragResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragResetPasswordBinding.inflate(inflater)

        binding.buttonAlterarRedefinir.setOnClickListener {
            alterView(FragLogin())
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragResetPassword()
    }

    fun alterView(fragment: Fragment) {
        alterFragment(activity, R.id.containerMain, fragment)
    }
}