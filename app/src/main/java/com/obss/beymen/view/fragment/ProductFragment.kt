package com.obss.beymen.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.obss.beymen.R
import com.obss.beymen.databinding.FragmentProductBinding
import com.obss.beymen.db.ProductDatabase
import com.obss.beymen.helpers.Utilities
import com.obss.beymen.model.FavoriteModel
import com.obss.beymen.viewmodel.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class ProductFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var dataBinding: FragmentProductBinding
    private var productId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        Utilities.getFavoriteList(requireContext())

        arguments?.let {
            productId = ProductFragmentArgs.fromBundle(it).productId
            initViewModel()
            productObserveLiveData()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        viewModel.getProduct(productId)
    }

    private fun productObserveLiveData() {
        viewModel.productModel.observe(viewLifecycleOwner, Observer { product ->
            product?.let {
                println(product)
                dataBinding.product = product
                dataBinding.desc = product.Result.Description["Özellikler :"]
                dataBinding.image = product.Result.Images[0].Images[0]
            }
        })

        viewModel.productError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.productLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (loading) {
                    Log.e("request", "Loading")
                } else {

                    Log.e("request", "result")
                }
            }
        })
    }


    private fun addOrDeleteFavorite() {
        CoroutineScope(IO).launch {
            if (Utilities.favoriteList.contains(FavoriteModel(productId))) {
                val dao = ProductDatabase(requireContext()).productDao()
                dao.deleteById(productId)
            } else {
                val dao = ProductDatabase(requireContext()).productDao()
                dao.insertAll(FavoriteModel(productId))
            }
            Utilities.getFavoriteList(requireContext())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_menu, menu)
        val item = menu.getItem(0)
        if (Utilities.favoriteList.contains(FavoriteModel(productId))) {
            item.setIcon(R.drawable.ic_favorite)
            item.isChecked = true
        } else {
            item.setIcon(R.drawable.ic_favorite_border)
            item.isChecked = false
        }
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                addOrDeleteFavorite()
                if (item.isChecked) {
                    item.setIcon(R.drawable.ic_favorite_border)
                } else {
                    item.setIcon(R.drawable.ic_favorite)
                }
                item.isChecked = !item.isChecked
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
