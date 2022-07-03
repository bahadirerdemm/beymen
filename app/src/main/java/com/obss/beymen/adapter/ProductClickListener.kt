package com.obss.beymen.adapter

import android.view.View
import com.obss.beymen.model.Product
import com.obss.beymen.viewmodel.ListViewModel

interface ProductClickListener {
    fun onProductClicked(v: View, model: Product)
}