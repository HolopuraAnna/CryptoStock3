package com.example.cryptostock3.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cryptostock3.domain.usecases.GetItemUseCase
import com.example.cryptostock3.domain.usecases.GetItemsUseCase
import com.example.cryptostock3.domain.usecases.LoadItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val loadItemUseCase: LoadItemUseCase,
    ) : ViewModel() {

    val itemsLiveData
        get() = getItemsUseCase()


    fun loadData() {
        Log.d("XXXX", "start")
        CoroutineScope(Dispatchers.Default).launch {
            loadItemUseCase()
        }
    }
}