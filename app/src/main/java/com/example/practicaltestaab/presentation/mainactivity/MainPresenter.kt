package com.example.practicaltestaab.presentation.mainactivity

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.practicaltestaab.data.external.web.retrofit.APIService
import com.example.practicaltestaab.data.external.web.retrofit.RetrofitClient
import com.example.practicaltestaab.data.local.db.entities.QuoteEntity
import com.example.practicaltestaab.domain.model.Quote


class MainPresenter {
    var bankInfoList: ArrayList<Quote> = ArrayList()
    var bankInfoListEnt: ArrayList<QuoteEntity> = ArrayList()
    var results: ArrayList<Quote> = ArrayList()
    var resultsEnt: ArrayList<QuoteEntity> = ArrayList()
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
        return results


    }

    suspend fun showListEntity(context: Context): List<QuoteEntity> {
        Log.d(TAG, "showList() called")
        val retro = RetrofitClient()
        val call = retro.getRetrofit().create(APIService::class.java).getCharacters()
        if (call.isSuccessful) {
            Log.d(TAG, "showList() called")
            val ofBanks = call.body()
            Log.d(TAG, call.body().toString())
            //show recycler (TODO)
            ofBanks?.filter {
                bankInfoListEnt.add(
                    QuoteEntity(
                        bankName = it.bankName, description = it.description, age = it.age,
                        url = it.url
                    )
                )
            }
            resultsEnt = bankInfoListEnt
        } else {
            showError(context)
        }
        return resultsEnt
    }

    private fun showError(context: Context) {
        Toast.makeText(context, "Error en petición", Toast.LENGTH_SHORT).show()
    }
}


