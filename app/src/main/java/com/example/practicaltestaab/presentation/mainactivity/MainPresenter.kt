package com.example.practicaltestaab.presentation.mainactivity

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.practicaltestaab.data.external.web.retrofit.APIService
import com.example.practicaltestaab.data.external.web.retrofit.RetrofitClient
import com.example.practicaltestaab.databinding.ActivityMainBinding
import com.example.practicaltestaab.domain.model.Quote


class MainPresenter {
    var bankInfoList: ArrayList<Quote> = ArrayList()
    var bankInfoListEntity: ArrayList<Quote> = ArrayList()
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

    suspend fun showListEntity(context: Context): List<Quote> {
        Log.d(TAG, "showList() called")
        val retro = RetrofitClient()
        val call = retro.getRetrofit().create(APIService::class.java).getCharacters()
        if (call.isSuccessful) {
            Log.d(TAG, "showList() called")
            val ofBanks = call.body()
            Log.d(TAG, call.body().toString())
            //show recycler (TODO)
            ofBanks?.filter {
                bankInfoListEntity.add(
                    Quote(
                        bankName = it.bankName, description = it.description, url = it.url,
                        age = it.age
                    )
                )
            }
            results = bankInfoListEntity
        } else {
            showError(context)
        }
//        viewModel.onCreate()
        return results
    }

    private fun showError(context: Context) {
        Toast.makeText(context, "Error en petición", Toast.LENGTH_SHORT).show()
    }
}


