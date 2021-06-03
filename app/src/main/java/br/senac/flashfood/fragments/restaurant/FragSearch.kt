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
import br.senac.flashfood.R
import br.senac.flashfood.activities.RestaurantMenuActivity
import br.senac.flashfood.constants.ExtraConstants
import br.senac.flashfood.controller.ProductController
import br.senac.flashfood.controller.RestaurantController
import br.senac.flashfood.databinding.CardProductBinding
import br.senac.flashfood.databinding.FragmentFragSearchBinding
import br.senac.flashfood.models.dto.restaurant.ProductResponseDTO
import br.senac.flashfood.models.dto.restaurant.RestaurantResponseDTO
import br.senac.flashfood.utils.ui.alterFragment
import br.senac.flashfood.utils.ui.toastShow

class FragSearch : Fragment() {


    private lateinit var binding : FragmentFragSearchBinding

    private var mListProducts = MutableLiveData<List<ProductResponseDTO>>()

    private var mResult = MutableLiveData<Boolean>()

    private val PRODUCT_CONTROLLER = ProductController()

    private val RESTAURANT_CONTROLLER = RestaurantController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragSearchBinding.inflate(layoutInflater)


        binding.imageView.setOnClickListener {
           binding.editFindProduct?.text.toString().let {
               if (it.isNullOrBlank()) toastShow(
                   activity,
                   "Digite algo para a busca",
                   Toast.LENGTH_LONG
               )
               else PRODUCT_CONTROLLER.findByName(mListProducts, mResult, it)
           }
        }

        mResult.observe(this, Observer<Boolean> {
            if (!it) toastShow(activity, "Erro para listar os produtos!", Toast.LENGTH_LONG)
        })

        mListProducts.observe(this, Observer {
            it?.let { showCardProducts(it) }
        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragSearch()
    }



    fun showCardProducts(products: List<ProductResponseDTO>) {
        products.forEach {
            val PRODUCT_CARD = CardProductBinding.inflate(layoutInflater)

            PRODUCT_CARD.txtNameProduct.text = it.name
            PRODUCT_CARD.txtPrice.text = it.price.toString()

            val PRODUCT_ID = it.id

            PRODUCT_CARD.root.setOnClickListener {
                        var mRest = MutableLiveData<RestaurantResponseDTO?>()
                        var mResult = MutableLiveData<Boolean>()

                        mRest.observe(this, Observer {
                            it?.let {
                                val INTENT = Intent(activity, RestaurantMenuActivity::class.java)
                                    .putExtra(ExtraConstants.RESTAURANT_NAME.value, it.name)
                                    .putExtra(ExtraConstants.RESTAURANT_DESC.value, it.description)
                                    .putExtra(ExtraConstants.RESTAURANT_ID.value, it.id.toString())
                                startActivity(INTENT)
                            }
                        })

                        mResult.observe(this, Observer {
                            if(!it) toastShow(activity, "Erro para buscar restaurante", Toast.LENGTH_LONG)
                        })

                    RESTAURANT_CONTROLLER.getRestaurantByProductId(PRODUCT_ID, mRest, mResult)
            }

            binding.containerSearchProducts?.addView(PRODUCT_CARD.root)
        }
    }
}