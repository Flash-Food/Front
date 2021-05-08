package br.senac.flashfood.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.senac.flashfood.R
import br.senac.flashfood.databinding.ActivityMainBinding
import br.senac.flashfood.fragments.FragLogin

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().
                                replace(R.id.containerMain, FragLogin()).commit()


    }


}