package com.example.rfidgenerator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RFIDAdapter
    private val rfidViewModel: RFIDViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generateButton: Button = findViewById(R.id.generateButton)
        val deleteAllButton: Button = findViewById(R.id.deleteAllButton)
        val printButton: Button = findViewById(R.id.printButton)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = RFIDAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        rfidViewModel.rfidList.observe(this, Observer { rfids ->
            adapter.updateRFIDs(rfids)
        })

        generateButton.setOnClickListener {
            val newRFID = generateRandomRFID()
            rfidViewModel.addRFID(newRFID)
        }

        deleteAllButton.setOnClickListener {
            rfidViewModel.clearRFIDs()
        }

        printButton.setOnClickListener {
            printRFIDs()
        }
    }

    private fun generateRandomRFID(): String {
        val chars = "0123456789ABCDEF"
        return (1..8)
            .map { chars.random() }
            .joinToString("")
    }

    private fun printRFIDs() {
        rfidViewModel.rfidList.value?.forEach { rfid ->
            Log.d("RFID List", rfid)
        }
    }
}
