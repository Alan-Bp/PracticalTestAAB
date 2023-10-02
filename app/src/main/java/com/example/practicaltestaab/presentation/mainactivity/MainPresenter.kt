package com.example.practicaltestaab.presentation.mainactivity

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.practicaltestaab.data.external.web.retrofit.APIService
import com.example.practicaltestaab.data.external.web.retrofit.RetrofitClient
import com.example.practicaltestaab.domain.model.DcBankResponseItem
import com.example.practicaltestaab.domain.model.Quote


class MainPresenter {
    var bankInfoList: ArrayList<Quote> = ArrayList()
    var results: ArrayList<Quote> = ArrayList()
    private val TAG = "MainPresenter"


    suspend fun showList(context: Context): ArrayList<Quote> {
        Log.d(TAG, "showList() called")
        val retro = RetrofitClient()
        val call = retro.getRetrofit().create(APIService::class.java).getCharacters()
        if (call.isSuccessful) {
            Log.d(TAG, "showList() called")
            val ofBanks = call.body()
            Log.d(TAG, call.body().toString())
            //show recycler (TODO)
            ofBanks?.filter {
                bankInfoList.add(
                    Quote(
                        it.bankName,
                        it.age,
                        it.description,
                        it.url
                    )
                )
            }
            results = bankInfoList
        } else {
            showError(context)
        }
//        viewModel.onCreate()
        return results
    }
}

private fun showError(context: Context) {
    Toast.makeText(context, "Error en petición", Toast.LENGTH_SHORT).show()
}


