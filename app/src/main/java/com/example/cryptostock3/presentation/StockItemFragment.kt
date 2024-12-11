package com.example.cryptostock3.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cryptostock3.R
import com.example.cryptostock3.databinding.FragmentStockItemBinding
import com.example.cryptostock3.domain.StockItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockItemFragment : Fragment() {

    private var _binding: FragmentStockItemBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: StockItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[StockItemViewModel::class.java]
        val fromSymbol = arguments?.getString(ARG_FROM_SYMBOL)
        if (!fromSymbol.isNullOrEmpty()) {
            viewModel.getItem(fromSymbol)
            setupLiveData()
        } else {
            Log.e("StockItemFragment", "No fromSymbol provided in arguments")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupLiveData() {
        viewModel.itemLiveData.observe(viewLifecycleOwner) { stockItem ->
            with(binding) {
                currency.text = "${stockItem.fromSymbol} / ${stockItem.toSymbol}"
                price.text = stockItem.price
                min.text = stockItem.lowDay
                max.text = stockItem.highDay
                lastDeal.text = stockItem.lastMarket
                update.text = convertTime(stockItem.lastUpdate)
                Glide.with(this@StockItemFragment)
                    .load(stockItem.imageUrl)
                    .into(pic)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_FROM_SYMBOL = "arg_from_symbol"

        fun newInstance(fromSymbol: String): StockItemFragment {
            val fragment = StockItemFragment()
            val args = Bundle().apply {
                putString(ARG_FROM_SYMBOL, fromSymbol)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
