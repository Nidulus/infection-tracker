package com.example.hackforcrisis.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hackforcrisis.R
import com.example.hackforcrisis.model.InfectionData
import com.example.hackforcrisis.viewmodel.InfectionViewModel
import kotlinx.android.synthetic.main.infection_fragment.*


class InfectionTrackingtFragment() : Fragment() {

    private val viewModel = InfectionViewModel()

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
            viewModel.sendData(
                InfectionData(
                    checkbox_nose.isChecked,
                    checkbox_throat.isChecked,
                    checkbox_headache.isChecked,
                    checkbox_nausea.isChecked,
                    checkbox_muscle.isChecked
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
