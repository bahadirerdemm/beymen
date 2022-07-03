package com.obss.beymen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obss.beymen.helpers.Constant
import com.obss.beymen.model.ListModel
import com.obss.beymen.service.BeymenAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val beymenApiService = BeymenAPIService()
    private val disposable = CompositeDisposable()

    val productsListModel = MutableLiveData<ListModel>()
    val productsListLoading = MutableLiveData<Boolean>()
    val productsListError = MutableLiveData<Boolean>()


    fun getProductList(page: Int) {
        productsListLoading.value = true
        disposable.add(
            beymenApiService.api.getProductList(Constant.requestSorted, page, Constant.categoryId, Constant.includeDocuments)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ListModel>() {
                    override fun onSuccess(listModel: ListModel) {
                        productsListModel.value = listModel
                        productsListLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        productsListError.value = true
                        productsListLoading.value = false
                    }
                })
        )
    }
}