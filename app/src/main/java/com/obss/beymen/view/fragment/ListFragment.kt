package com.obss.beymen.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obss.beymen.R
import com.obss.beymen.adapter.ProductListAdapter
import com.obss.beymen.databinding.FragmentListBinding
import com.obss.beymen.helpers.Utilities
import com.obss.beymen.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var dataBinding: FragmentListBinding
    private var spanCount = 2
    private var currentPage = 1
    private var maxPage = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Utilities.getFavoriteList(requireContext())
        setHasOptionsMenu(true)

        initViewModel()
        initRecyclerView()
        initPagination()
        productObserveLiveData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        val item = menu.getItem(0)
        if (spanCount == 2){
            item.setIcon(R.drawable.ic_one_column)
        }else{
            item.setIcon(R.drawable.ic_two_column)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.span_count -> {
                setSpanCount(item)
                changeSpanRv()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setSpanCount(item: MenuItem) {
        spanCount = if (spanCount == 1) {
            item.setIcon(R.drawable.ic_one_column)
            2
        } else {
            item.setIcon(R.drawable.ic_two_column)
            1
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        currentPage = 1
        viewModel.getProductList(currentPage)
    }


    private fun initRecyclerView() {
        productListAdapter = ProductListAdapter(arrayListOf())
        changeSpanRv()
        listRv.adapter = productListAdapter
    }

    private fun changeSpanRv() {
        listRv.layoutManager = GridLayoutManager(context, spanCount)
    }

    private fun initPagination() {
        listRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && dy != 0) {
                    if (currentPage <= maxPage) {
                        viewModel.getProductList(currentPage)
                    }
                }
            }
        })
    }

    private fun productObserveLiveData() {
        viewModel.productsListModel.observe(viewLifecycleOwner, Observer { productsList ->
            productsList?.let {
                maxPage = productsList.Result.TotalPageCount
                currentPage += 1
                productListAdapter.updateProductList(productsList.Result.ProductList)
            }
        })

        viewModel.productsListError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                Toast.makeText(context, "Hata", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.productsListLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (loading) {
                    Log.e("request", "Loading")
                } else {

                    Log.e("request", "result")
                }
            }
        })
    }

}