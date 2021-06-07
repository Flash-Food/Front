package br.senac.flashfood.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.senac.flashfood.R
import br.senac.flashfood.context.UserContext
import br.senac.flashfood.databinding.ActivityMainBinding
import br.senac.flashfood.fragments.login.FragLogin
import br.senac.flashfood.utils.internal.SharedUtils
import br.senac.flashfood.utils.ui.alterFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var sharedUtils : SharedUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedUtils = SharedUtils(this)

        if(sharedUtils.getToken().isNullOrBlank())
            alterFragment(this, R.id.containerMain,
                FragLogin()
            )
        else {
               UserContext.token = sharedUtils.getToken()!!
               startActivity(Intent(this, BottomNavigationActivity::class.java))
        }
    }


}