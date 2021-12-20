package com.example.kltnetworkchecker

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val tv: TextView by lazy {
        findViewById(R.id.status)
    }

    private val viewModel: NetworkStatusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                when (it) {
                    MyState.Fetched -> showMessage(true)
                    MyState.Error -> showMessage(false)
                }
            }
        }
    }

    private fun showMessage(isConnected: Boolean) {
        if (isConnected) {
            tv.apply {
                text = "Connected to INTERNET"
                this.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.connected))
            }
            displaySnackTop("Connected to INTERNET", Snackbar.LENGTH_LONG)
        } else {
            tv.apply {
                text = "NO INTERNET ACCESS"
                this.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.disconnect))
            }
            displaySnackTop("NO INTERNET ACCESS", Snackbar.LENGTH_INDEFINITE)
        }

    }

}