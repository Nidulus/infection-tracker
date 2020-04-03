package com.example.hackforcrisis.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hackforcrisis.R
import com.example.hackforcrisis.token.TokenHandler
import com.example.hackforcrisis.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment() : Fragment() {

    private val viewModel = LoginViewModel(TokenHandler())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_signin.setOnClickListener {
            viewModel.login(
                inputUsername.editText?.text.toString(),
                inputPassword.editText?.text.toString()
            )
        }
    }
}
