package br.senac.flashfood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.flashfood.R
import br.senac.flashfood.databinding.FragmentFragRedSenhaBinding
import br.senac.flashfood.utils.ui.alterFragment

class FragRedSenha : Fragment() {

    lateinit var binding: FragmentFragRedSenhaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragRedSenhaBinding.inflate(inflater)

        binding.buttonAlterarRedefinir.setOnClickListener {
            alterView(FragLogin())
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragRedSenha()
    }

    fun alterView(fragment: Fragment) {
        alterFragment(activity, R.id.containerMain, fragment)
    }
}