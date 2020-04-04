package com.example.hackforcrisis.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hackforcrisis.R
import com.example.hackforcrisis.model.InfectionData
import com.example.hackforcrisis.query.sendData.InfectionHandler
import com.example.hackforcrisis.viewmodel.InfectionViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.infection_fragment.*


class InfectionTrackingtFragment() : Fragment() {

    private var disposable: Disposable? = null
    private val viewModel = InfectionViewModel(InfectionHandler())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.infection_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_sendData.setOnClickListener {
            disposable?.dispose()
            viewModel.sendData(
                InfectionData(
                    checkbox_nose.isChecked,
                    checkbox_throat.isChecked,
                    checkbox_headache.isChecked,
                    checkbox_nausea.isChecked,
                    checkbox_muscle.isChecked
                )
            ).map {
                sendDataResult(it)
            }
                .subscribe()
        }
    }

    private fun sendDataResult(wow: Boolean){
        if (wow){
            Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
