package com.example.cryptostock.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptostock3.domain.StockItem

class StockItemDiffUtil: DiffUtil.ItemCallback<StockItem>() {
    override fun areItemsTheSame(oldItem: StockItem, newItem: StockItem) =
        oldItem.fromSymbol == newItem.fromSymbol

    override fun areContentsTheSame(oldItem: StockItem, newItem: StockItem) =
        oldItem == newItem
}