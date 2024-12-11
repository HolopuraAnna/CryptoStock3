package com.example.cryptostock3.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cryptostock3.R
import com.example.cryptostock3.databinding.ActivityItemBinding
import com.example.cryptostock3.domain.StockItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockItemViewActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        parseItemIntent(intent)
    }

    private fun parseItemIntent(intent: Intent) {
        val fromSymbol = intent.getStringExtra(EXTRA_ITEM_FSYM).toString()
        if (fromSymbol != null) {
            setupFragment(StockItemFragment.newInstance(fromSymbol))
        } else {
            Log.e("StockItemViewActivity", "No fromSymbol found in intent")
        }
    }

    private fun setupFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.stock_item_container, fragment)
            .commit()
    }
}