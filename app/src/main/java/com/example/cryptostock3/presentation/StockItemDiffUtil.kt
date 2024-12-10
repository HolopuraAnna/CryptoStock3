package com.example.cryptostock.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptostock.domain.CurrencyItem

class StockItemDiffUtil: DiffUtil.ItemCallback<CurrencyItem>() {
    override fun areItemsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem) =
        oldItem == newItem
}