package com.example.cryptostock3.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptostock3.domain.StockItem
import com.example.cryptostock3.domain.usecases.GetItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StockItemViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase,
) : ViewModel() {

}