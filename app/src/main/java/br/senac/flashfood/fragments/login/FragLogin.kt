package br.senac.flashfood.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.senac.flashfood.R
import br.senac.flashfood.activities.BottomNavigationActivity
import br.senac.flashfood.controller.UserController
import br.senac.flashfood.databinding.FragloginBinding
import br.senac.flashfood.fragments.password.FragForgotPassword
import br.senac.flashfood.models.dto.user.UserLoginRequestDTO
import br.senac.flashfood.utils.ui.alterFragment
import br.senac.flashfood.utils.ui.toastShow


class FragLogin : Fragment() {

    private var mLoginResult = MutableLiveData<Boolean>()

    lateinit var binding: FragloginBinding

    private val USER_CONTROLLER = UserController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragloginBinding.inflate(inflater)

        binding.btnSignupLogin.setOnClickListener {
            alterView(FragSignup())
        }

        binding.txtForgotePassword.setOnClickListener {
            alterView(FragForgotPassword())
        }

        binding.btnEnterLogin.setOnClickListener {
            val username = binding.editEmailLogin.text.toString()
            val password = binding.editPasswordLogin.text.toString()
            val user = UserLoginRequestDTO(
                username,
                password
            )
            binding.prbLogin?.visibility = View.VISIBLE
            binding.btnEnterLogin.isEnabled = false
            USER_CONTROLLER.login(user, mLoginResult)
        }

        mLoginResult.observe(this, Observer<Boolean>{
            if(it) startActivity(Intent(activity, BottomNavigationActivity::class.java))
            else toastShow(activity, "Erro ao realizar login!", Toast.LENGTH_LONG)
            binding.prbLogin?.visibility = View.INVISIBLE
            binding.btnEnterLogin.isEnabled = true
        })

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