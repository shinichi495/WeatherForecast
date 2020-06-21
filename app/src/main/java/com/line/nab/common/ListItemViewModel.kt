package com.line.nab.common

import androidx.databinding.BaseObservable

abstract class ListItemViewModel : BaseObservable() {
    var adapterPos: Int = -1
    var onListViewClick: BaseAdapter.OnListItemViewClickListener? = null
}