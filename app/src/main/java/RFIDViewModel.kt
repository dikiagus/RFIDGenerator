package com.example.rfidgenerator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RFIDViewModel : ViewModel() {
    val rfidList = MutableLiveData<MutableList<String>>(mutableListOf())

    fun addRFID(rfid: String) {
        rfidList.value?.add(rfid)
        rfidList.postValue(rfidList.value)
    }

    fun removeRFID(position: Int) {
        rfidList.value?.removeAt(position)
        rfidList.postValue(rfidList.value)
    }

    fun clearRFIDs() {
        rfidList.value?.clear()
        rfidList.postValue(rfidList.value)
    }
}
