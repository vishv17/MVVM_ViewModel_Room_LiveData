package com.example.android.room.demo.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.android.room.demo.kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var database: ContactDatabase
    lateinit var mainViewModel: MainViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = QuoteDatabase.getDatabase(applicationContext).getQuoteDao()
        val quoteRepository = QuoteRepository(dao)
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(quoteRepository)
        ).get(MainViewModel::class.java)

        mainViewModel.getQuotes().observe(this, Observer {
            binding.quotes = it.toString()
        })

        binding.btnQuote.setOnClickListener {
            val quote = Quote(0,"This is testing","Test")
            mainViewModel.insertQuotes(quote)
        }


        /*database = ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"John","9999", Date(),1))
        }*/

        /*binding.txtTest.setOnClickListener {
            database.contactDao().getContact().observe(this, Observer {
                Log.d(TAG, "onCreate: ${it.toString()}")
            })
        }*/
    }
}