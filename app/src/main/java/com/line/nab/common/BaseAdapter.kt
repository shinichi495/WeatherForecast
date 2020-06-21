package com.line.nab.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T : ListItemViewModel>(@LayoutRes val layoutId: Int?) :
    RecyclerView.Adapter<AppViewHolder<T>>() {
    val items = mutableListOf<T>()
    private var inflater: LayoutInflater? = null
    private var onListListItemViewClickListener: OnListItemViewClickListener? = null

    @LayoutRes
    var layoutIdCustom: Int? = null

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder<T> {
        val idResLayout = if (layoutId != null) layoutId else layoutIdCustom
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        var binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, idResLayout!!, parent, false)
        return createViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: AppViewHolder<T>, position: Int) {
        val itemViewModel = items[position]
        itemViewModel.adapterPos = position
        onListListItemViewClickListener?.let { itemViewModel.onListViewClick = it }
        bindData(holder, itemViewModel)
    }

    open fun addItems(items: List<T>?) {
        this.items.clear()
        this.items.addAll(items!!)
        notifyDataSetChanged()
    }

    fun setOnListItemViewClickListener(onListItemViewClickListener: OnListItemViewClickListener?) {
        this.onListListItemViewClickListener = onListItemViewClickListener
    }

    interface OnListItemViewClickListener {
        fun onClick(view: View, pos: Int)
    }

    open fun createViewHolder(binding: ViewDataBinding) : AppViewHolder<T>{
        return AppViewHolder(binding)
    }

    open fun bindData(holder: AppViewHolder<T>, item: T){
        holder.bind(item)
    }

    fun getItem(pos: Int): T{
        return items[pos]
    }
}

open class AppViewHolder<T : ListItemViewModel>(val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var item: T? = null

    open fun bind(item: T) {
        this.item = item
        binding.setVariable(BR.data, item)
        binding.executePendingBindings()
        binding.root.setOnClickListener{ v ->
            item!!.onListViewClick?.onClick(v, item.adapterPos)
        }
    }
}