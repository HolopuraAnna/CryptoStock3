package com.example.cryptostock.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptostock3.domain.CurrencyItem

class StockItemDiffUtil: DiffUtil.ItemCallback<CurrencyItem>() {
    override fun areItemsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem) =
        oldItem.fromSymbol == newItem.fromSymbol

    override fun areContentsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem) =
        oldItem == newItem
}