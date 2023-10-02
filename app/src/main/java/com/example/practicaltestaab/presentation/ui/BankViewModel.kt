package com.example.practicaltestaab.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.practicaltestaab.domain.model.Quote
import com.example.practicaltestaab.domain.useCases.room.GetBankUseCase
import com.example.practicaltestaab.domain.useCases.room.GetRandomQuoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class BankViewModel @Inject constructor(
    val getQuotesUseCase: GetBankUseCase,
    val getRandomQuoteUseCase: GetRandomQuoteUseCase
//) : ViewModelProvider.Factory {
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val quoteModel = MutableLiveData<Quote>()


    fun onCreate() {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
//        viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val quote = getRandomQuoteUseCase()
            quote?.let {
                quoteModel.value = it
            }
            isLoading.postValue(false)
        }
    }
}

