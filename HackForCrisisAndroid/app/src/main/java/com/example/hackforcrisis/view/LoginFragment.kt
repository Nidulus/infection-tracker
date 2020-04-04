package com.example.hackforcrisis.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.hackforcrisis.R
import com.example.hackforcrisis.model.LoginRequest
import com.example.hackforcrisis.token.TokenHandler
import com.example.hackforcrisis.viewmodel.LoginViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment() : Fragment() {

    private var disposable: Disposable? = null
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
            disposable?.dispose()
            disposable = viewModel.login(
                LoginRequest(
                    inputUsername.editText?.text.toString(),
                    inputPassword.editText?.text.toString()
                )
            ).map {
                loginSucceded(it)
            }
                .subscribe()
        }
    }

    private fun loginSucceded(greatSuccess: Boolean) {
        if (greatSuccess) {

        }

        //Move this into if case when we can get token
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main, InfectionTrackingtFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
