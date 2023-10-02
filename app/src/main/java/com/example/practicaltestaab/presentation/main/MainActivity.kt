package com.example.practicaltestaab.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaltestaab.data.local.preferences.MyAdapter
import com.example.practicaltestaab.databinding.ActivityMainBinding
import com.example.practicaltestaab.domain.model.Quote
import com.example.practicaltestaab.presentation.mainactivity.MainPresenter
import com.example.practicaltestaab.presentation.ui.BankViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    var result: ArrayList<Quote> = ArrayList()
    private lateinit var binding: ActivityMainBinding
    var mainPresenter: MainPresenter = MainPresenter()

    //    private  val viewModel = ViewModelProvider(this)[BankViewModel::class.java]
    private val quoteViewModel: BankViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        principalThread()
//        val viewModel = ViewModelProvider(this)[BankViewModel::class.java]

    }

    fun principalThread() {
        CoroutineScope(Dispatchers.IO).launch {
            result = mainPresenter.showList(this@MainActivity)
            runOnUiThread {
                val adapter = MyAdapter(this@MainActivity, result)
                binding.listBanksObject.adapter = adapter

            }

        }

//        quoteViewModel.onCreate()

    }


    fun consumoBD(bankViewModel: BankViewModel) {
        bankViewModel.onCreate()
//        bankViewModel.quoteModel
    }
}

