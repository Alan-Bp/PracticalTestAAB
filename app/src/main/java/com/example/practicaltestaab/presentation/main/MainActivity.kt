package com.example.practicaltestaab.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.room.Room
import com.example.practicaltestaab.data.local.db.BankDB
import com.example.practicaltestaab.data.local.preferences.MyAdapter
import com.example.practicaltestaab.databinding.ActivityMainBinding
import com.example.practicaltestaab.domain.model.Quote
import com.example.practicaltestaab.presentation.mainactivity.MainPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    var resultEntity: List<Quote> = ArrayList()
    var result: ArrayList<Quote> = ArrayList()
    private lateinit var binding: ActivityMainBinding
    var mainPresenter: MainPresenter = MainPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val room: BankDB = Room.databaseBuilder(this, BankDB::class.java, "BankDB").build()
        // TODO: Validación primera vez que abre la app llamar a servicio, siguientes llamar a BD 
        principalThread(room)

    }

    /**
     * Hilo Principal el cual recibe el resultado a la llamada retrofir para consumo de archivo json
     */
    fun principalThread(room: BankDB) {
        Log.d(TAG, "principalThread() called with: room = $room")
        CoroutineScope(Dispatchers.IO).launch {
            result = mainPresenter.showList(this@MainActivity)
            resultEntity = mainPresenter.showListEntity(this@MainActivity)
            runOnUiThread {
                val adapter = MyAdapter(this@MainActivity, result)
                binding.listBanksObject.adapter = adapter
                binding.loading.isVisible = false
            }
            // TODO: realizar insercion a base dedatos con respuesta retrofit
//            room.getBankDao().insertAll(resultEntity)
        }
        consumoBD()
    }


    /**
     * Metodo para llamar lo almacenado en Base de datos
     */
    fun consumoBD() {
        Log.d(TAG, "consumoBD() called")
        val room: BankDB = Room.databaseBuilder(this, BankDB::class.java, "BankDB").build()
        CoroutineScope(Dispatchers.IO).launch {
            room.getBankDao().getAllBanks()
        }
    }
}

