package br.senac.flashfood.fragments.password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.flashfood.R
import br.senac.flashfood.databinding.FragmentFragResetPasswordBinding
import br.senac.flashfood.fragments.login.FragLogin
import br.senac.flashfood.fragments.password.FragResetPassword
import br.senac.flashfood.utils.ui.alterFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragResetPassword2.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragResetPassword2 : Fragment() {

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