package com.obss.beymen.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.obss.beymen.R
import com.obss.beymen.databinding.ItemProductBinding
import com.obss.beymen.helpers.Utilities
import com.obss.beymen.model.FavoriteModel
import com.obss.beymen.model.Product
import com.obss.beymen.view.fragment.ListFragmentDirections

class ProductListAdapter(val productModel: ArrayList<Product>) : RecyclerView.Adapter<ProductListAdapter.SearchViewHolder>(), ProductClickListener {


    class SearchViewHolder(var view: ItemProductBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemProductBinding>(inflater, R.layout.item_product, parent, false)
        return SearchViewHolder(view)

    }

    override fun getItemCount(): Int {
        return productModel.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.view.listItem = productModel[position]
        holder.view.clickListener = this
        holder.view.isFav = Utilities.favoriteList.contains(FavoriteModel(productModel[position].ProductId))
    }

    override fun onProductClicked(v: View, model: Product) {
        val action = ListFragmentDirections.actionListFragmentToProductFragment(model.ProductId)
        Navigation.findNavController(v).navigate(action)
    }

    fun updateProductList(newProductList: List<Product>) {
        productModel.addAll(newProductList)
        notifyItemRangeInserted(productModel.size, newProductList.size)
    }


}