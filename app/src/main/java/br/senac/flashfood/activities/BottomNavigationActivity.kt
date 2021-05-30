package br.senac.flashfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senac.flashfood.fragments.restaurant.FragSearch
import br.senac.flashfood.R
import br.senac.flashfood.context.UserContext
import br.senac.flashfood.databinding.ActivityBottomNavigationBinding
import br.senac.flashfood.fragments.user.FragProfile
import br.senac.flashfood.fragments.restaurant.FragRestaurant
import br.senac.flashfood.handlers.ExceptionHandler
import br.senac.flashfood.utils.internal.SharedUtils
import br.senac.flashfood.utils.ui.alterFragment

class BottomNavigationActivity : AppCompatActivity() {

    lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler(this))

        alterFragment(this, R.id.containerBottomFrag, FragRestaurant.newInstance())

        binding.menuNavigation.setOnNavigationItemSelectedListener {
           when(it.itemId) {
                R.id.frag_menu -> {
                    alterFragment(this, R.id.containerBottomFrag, FragRestaurant.newInstance())
                    true
                }
                R.id.frag_search -> {
                    alterFragment(this, R.id.containerBottomFrag, FragSearch.newInstance())
                    true
                }
                R.id.frag_profile -> {
                    alterFragment(this, R.id.containerBottomFrag, FragProfile.newInstance())
                    true
                }
               else -> false
           }
        }
    }

    override fun onPause() {
        super.onPause()
        if(UserContext.token.isNotBlank())
            SharedUtils(this).saveToken(UserContext.token)
    }

}