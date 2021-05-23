package br.senac.flashfood.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.senac.flashfood.controller.RestaurantController
import br.senac.flashfood.databinding.CardRestaurantBinding
import br.senac.flashfood.databinding.FragmentFragRestaurantsBinding
import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import br.senac.flashfood.models.dto.restaurant.RestaurantResponseDTO
import br.senac.flashfood.utils.ui.toastShow
import kotlin.math.log


class FragRestaurant : Fragment() {


    private var mGetAllResult = MutableLiveData<Boolean>()

    private var mGetAllList = MutableLiveData<List<RestaurantResponseDTO?>>()

    private val RESTAURANT_CONTROLLER = RestaurantController()

    private lateinit var binding: FragmentFragRestaurantsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragRestaurantsBinding.inflate(inflater)

        RESTAURANT_CONTROLLER.getAll(mGetAllList, mGetAllResult)

        mGetAllResult.observe(this, Observer<Boolean> {
            if(!it) toastShow(activity, "Erro para listar os restaurantes!", Toast.LENGTH_LONG)
        })

        mGetAllList.observe(this, Observer {
            it?.let { showCardRestaurants(it as List<RestaurantResponseDTO>) }
        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragRestaurant()
            }

    fun showCardRestaurants(restaurants: List<RestaurantResponseDTO>) {
        restaurants.forEach {
            val RESTAURANT_CARD = CardRestaurantBinding.inflate(layoutInflater)

            RESTAURANT_CARD.txtTitle.text = it.name
            RESTAURANT_CARD.txtDescription.text = it.description

            binding.restaurantsContainer.addView(RESTAURANT_CARD.root)
        }
    }
}
