package com.kohls.bootstrap.bootstrapapp.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kohls.bootstrap.bootstrapapp.R
import com.kohls.bootstrap.bootstrapapp.data.model.Product
import com.kohls.bootstrap.bootstrapapp.data.model.Repo
import com.kohls.bootstrap.bootstrapapp.databinding.MainFragmentBinding
import com.kohls.bootstrap.bootstrapapp.di.Injectable
import com.kohls.bootstrap.bootstrapapp.ui.app.ProductDimViewModel
import com.kohls.bootstrap.bootstrapapp.databinding.MainRepoItemBinding
import javax.inject.Inject
import com.kohls.bootstrap.bootstrapapp.util.ext.observe

class MainFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var productDimViewModel: ProductDimViewModel
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private val adapter = MainAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<MainFragmentBinding>(inflater, R.layout.main_fragment, container, false).also {
            binding = it
        }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.productList.observe(this) {
            it ?: return@observe
            adapter.run {
                items.clear()
                items.addAll(it.payload.products)
                notifyDataSetChanged()
            }
        }
        productDimViewModel.productDimensionId.observe(this) {
            viewModel.productID.value = it
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}


class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    val items = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<MainRepoItemBinding>(
                LayoutInflater.from(parent.context), R.layout.main_repo_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.binding.product = items[position]
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: MainRepoItemBinding) : RecyclerView.ViewHolder(binding.root)
}