package com.example.hackforcrisis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackforcrisis.R
import com.example.hackforcrisis.module.module
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(module)
        }
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, LoginFragment(), TAG)
                .commit();
        }
    }

    companion object {
        private const val TAG = "Login"
    }
}
