package br.senac.flashfood.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.flashfood.R
import br.senac.flashfood.databinding.FragmentFragEsqSenhaBinding
import br.senac.flashfood.utils.ui.alterFragment

class FragEsqSenha : Fragment() {

    lateinit var binding : FragmentFragEsqSenhaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragEsqSenhaBinding.inflate(inflater)

        binding.buttonEnviarRedefinir.setOnClickListener {
            alterView(FragRedSenha())
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FragEsqSenha()
    }

    fun alterView(fragment: Fragment) {
        alterFragment(activity, R.id.containerMain, fragment)
    }
}