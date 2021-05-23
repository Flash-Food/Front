package br.senac.flashfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senac.flashfood.fragments.FragSearch
import br.senac.flashfood.R
import br.senac.flashfood.databinding.ActivityBottomNavigationBinding
import br.senac.flashfood.fragments.FragProfile
import br.senac.flashfood.fragments.FragRestaurant
import br.senac.flashfood.utils.ui.alterFragment

class BottomNavigationActivity : AppCompatActivity() {

    lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alterFragment(this, R.id.containerBottomFrags, FragRestaurant.newInstance())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
           when(it.itemId) {
                R.id.frag_menu -> {
                    alterFragment(this, R.id.containerBottomFrags, FragRestaurant.newInstance())
                    true
                }
                R.id.frag_search -> {
                    alterFragment(this, R.id.containerBottomFrags, FragSearch.newInstance())
                    true
                }
                R.id.frag_profile -> {
                    alterFragment(this, R.id.containerBottomFrags, FragProfile.newInstance())
                    true
                }
               else -> false
           }
        }
    }
}