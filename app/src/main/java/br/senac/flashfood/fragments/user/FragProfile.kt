package br.senac.flashfood.fragments.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.senac.flashfood.activities.MainActivity
import br.senac.flashfood.activities.PurchasesActivity
import br.senac.flashfood.context.UserContext
import br.senac.flashfood.controller.UserController
import br.senac.flashfood.databinding.FragmentFragProfileBinding
import br.senac.flashfood.models.dto.user.UserInfoResponseDTO
import br.senac.flashfood.utils.internal.SharedUtils
import br.senac.flashfood.utils.ui.toastShow

class FragProfile : Fragment() {


    private var mUserInfo = MutableLiveData<UserInfoResponseDTO>()

    private var mResult = MutableLiveData<Boolean>()

    private val USER_CONTROLLER = UserController()

    private lateinit var binding: FragmentFragProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragProfileBinding.inflate(layoutInflater)

        binding.txtPurchases?.setOnClickListener {
            startActivity(Intent(activity, PurchasesActivity::class.java))
        }

        binding.txtExit?.setOnClickListener {
            SharedUtils(activity!!).removeToken()
            UserContext.token = ""
            activity!!.finishAffinity()
            startActivity(Intent(activity, MainActivity::class.java))
        }

        USER_CONTROLLER.info(mUserInfo, mResult)

        mUserInfo.observe(this, Observer {
            setDataUserInFields(it)
        })

        mResult.observe(this, Observer {
            if(!it) toastShow(activity, "Erro ao buscar informções do usuario", Toast.LENGTH_LONG)
        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragProfile()
    }

    fun setDataUserInFields(user: UserInfoResponseDTO) {
        binding.txtNameProfile.text = user.name
        binding.txtCpfProfile.text = user.cpf
        binding.txtEmailProfile.text = user.email
    }
}