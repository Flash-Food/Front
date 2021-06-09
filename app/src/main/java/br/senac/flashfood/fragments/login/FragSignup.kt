package br.senac.flashfood.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.senac.flashfood.R
import br.senac.flashfood.controller.UserController
import br.senac.flashfood.databinding.FragmentFragSignupBinding
import br.senac.flashfood.models.dto.user.UserSignUpRequestDTO
import br.senac.flashfood.utils.ui.alterFragment
import br.senac.flashfood.utils.ui.toastShow

class FragSignup : Fragment() {

    private var mSignUpResult = MutableLiveData<Boolean>()

    private lateinit var binding: FragmentFragSignupBinding

    private val userController = UserController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragSignupBinding.inflate(inflater)

        binding.buttonBackSignup.setOnClickListener {
            alterView(FragLogin())
        }

        binding.buttonSignup.setOnClickListener {
            val name = binding.editNameSignup.text.toString()
            val email = binding.editEmailSignup.text.toString()
            val password = binding.editPasswordSignup.text.toString()
            val cpf = binding.editCPFSignup.text.toString()
            val phoneNumber = binding.editPhoneNumberSignup.text.toString()
            val userSignUpRequestDTO =
                UserSignUpRequestDTO (
                    name,
                    email,
                    password,
                    cpf,
                    phoneNumber
                )
            userController.signup(userSignUpRequestDTO, mSignUpResult)
            binding.prbSigNup?.visibility = View.VISIBLE
            binding.buttonSignup.isEnabled = false
        }

        mSignUpResult.observe(this, Observer<Boolean> {
            if(it) alterView(FragLogin())
            else toastShow(activity, "Erro ao realizar cadatro!", Toast.LENGTH_LONG)
            binding.buttonSignup.isEnabled = true
            binding.prbSigNup?.visibility = View.INVISIBLE
        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragSignup()
    }

    fun alterView(fragment: Fragment) {
        alterFragment(activity, R.id.containerMain, fragment)
    }
}