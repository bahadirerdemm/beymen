package com.obss.beymen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obss.beymen.model.ProductModel
import com.obss.beymen.service.BeymenAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProductViewModel: ViewModel() {

    private val beymenApiService = BeymenAPIService()
    private val disposable = CompositeDisposable()

    val productModel = MutableLiveData<ProductModel>()
    val productLoading = MutableLiveData<Boolean>()
    val productError = MutableLiveData<Boolean>()

    fun getProduct(productId: Int) {
        productLoading.value = true
        disposable.add(
            beymenApiService.api.getProduct(productId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ProductModel>() {
                    override fun onSuccess(model: ProductModel) {
                        productModel.value = model
                        productLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        productError.value = true
                        productLoading.value = false
                    }
                })
        )
    }

}