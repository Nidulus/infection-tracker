package com.example.hackforcrisis.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hackforcrisis.model.InfectionData


class InfectionViewModel() : ViewModel() {

    private lateinit var infectionData: InfectionData

    fun sendData(data: InfectionData) {
        infectionData = data
        Log.d("send data", infectionData.muscle.toString())
    }

    private fun loginSuccess() {

    }

}