package br.senac.flashfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senac.flashfood.R
import br.senac.flashfood.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}