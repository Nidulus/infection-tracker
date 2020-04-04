package com.example.hackforcrisis.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import api.RetrofitProvider
import com.example.hackforcrisis.model.InfectionData
import com.example.hackforcrisis.query.SYMPTOMS_URL
import com.example.hackforcrisis.query.sendData.InfectionHandler
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


class InfectionViewModel(private val infectionHandler: InfectionHandler) : ViewModel() {

    private lateinit var infectionData: InfectionData

    fun sendData(data: InfectionData): Flowable<Boolean> {
        infectionData = data
        Log.d("send data", infectionData.muscle.toString())

        return Flowable.create({ emitter ->
            RetrofitProvider().createRetrofit(SYMPTOMS_URL)
                .flatMap { infectionHandler.sendInfectionData(it, data) }
                .doOnSuccess {
                    Log.d("Token", " received: $it")
                    emitter.onNext(true)
                }
                .doOnError {
                    Log.d("Token", "not received")
                    emitter.onNext(false)
                }
                .onErrorReturnItem("")
                .subscribe()
        }, BackpressureStrategy.LATEST)
    }
}