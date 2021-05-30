package br.senac.flashfood.fragments.restaurant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.senac.flashfood.activities.RestaurantMenuActivity
import br.senac.flashfood.constants.ExtraConstants
import br.senac.flashfood.controller.RestaurantController
import br.senac.flashfood.databinding.CardRestaurantBinding
import br.senac.flashfood.databinding.FragmentFragRestaurantsBinding
import br.senac.flashfood.handlers.ExceptionHandler
import br.senac.flashfood.models.dto.restaurant.RestaurantResponseDTO
import br.senac.flashfood.utils.ui.toastShow


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

        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler(activity!!.baseContext))

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
            val RESTAURANT_ID = it.id
            val RESTAURANT_NAME = it.name
            val RESTAURANT_DESCRIPTION = it.description

            RESTAURANT_CARD.txtTitle.text = RESTAURANT_NAME
            RESTAURANT_CARD.txtDescription.text = RESTAURANT_DESCRIPTION

            RESTAURANT_CARD.root.setOnClickListener {
                val INTENT = Intent(activity, RestaurantMenuActivity::class.java)
                INTENT.putExtra(ExtraConstants.RESTAURANT_NAME.value, RESTAURANT_NAME)
                INTENT.putExtra(ExtraConstants.RESTAURANT_DESC.value, RESTAURANT_DESCRIPTION)
                INTENT.putExtra(ExtraConstants.RESTAURANT_ID.value, RESTAURANT_ID.toString())
                startActivity(INTENT)
            }

            binding.restaurantsContainer.addView(RESTAURANT_CARD.root)
        }
    }
}
