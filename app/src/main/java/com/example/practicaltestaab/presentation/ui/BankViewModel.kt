package com.example.practicaltestaab.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaltestaab.domain.model.Quote
import com.example.practicaltestaab.domain.useCases.room.GetBankUseCase
import com.example.practicaltestaab.domain.useCases.room.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankViewModel @Inject constructor(
    val getQuotesUseCase: GetBankUseCase,
    val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val quoteModel = MutableLiveData<Quote>()


    fun onCreate() {
        viewModelScope.launch {
//        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
//        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(true)
            val quote = getRandomQuoteUseCase()
            quote?.let {
                quoteModel.value = it
            }
            isLoading.postValue(false)
        }
    }
}

