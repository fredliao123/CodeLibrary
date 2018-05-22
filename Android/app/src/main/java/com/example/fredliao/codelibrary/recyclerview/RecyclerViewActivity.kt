package com.example.fredliao.codelibrary.recyclerview

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.fredliao.codelibrary.BR
import com.example.fredliao.codelibrary.R
import com.example.fredliao.codelibrary.common.BaseActivity
import com.example.fredliao.codelibrary.common.OnItemClickListener
import com.example.fredliao.codelibrary.databinding.ActivityRecyclerViewBinding
import com.example.fredliao.codelibrary.router.ViewModelFactory
import me.tatarka.bindingcollectionadapter2.ItemBinding

class RecyclerViewActivity : BaseActivity<RecyclerViewModel>() {

    val binding: ActivityRecyclerViewBinding by lazy {
        DataBindingUtil.setContentView<ActivityRecyclerViewBinding>(this, R.layout.activity_recycler_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = initViewModel()
        initRecyclerView()
    }

    private fun initViewModel(): RecyclerViewModel {
        val factory = ViewModelFactory(application, intent)
        viewModel = ViewModelProviders.of(this, factory).get(RecyclerViewModel::class.java)
        return viewModel
    }

    private fun initRecyclerView() {
        val itemBinding: ItemBinding<RecyclerItem> = ItemBinding.of(BR.itemViewModel, R.layout.item_recycler)
        itemBinding.bindExtra(BR.clickListener, object : OnItemClickListener<RecyclerItemViewModel> {
            override fun onItemClick(item: RecyclerItemViewModel) {
                itemClicked(item)
            }
        })
        binding.itemBinding = itemBinding
        binding.executePendingBindings()
    }

    private fun itemClicked(item: RecyclerItemViewModel) {

    }

}