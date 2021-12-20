package com.example.kltnetworkchecker

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val tv : TextView by lazy {
        findViewById(R.id.status)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*NetworkStatusHelper(this).observe(this){
            when(it){
                NetworkStatus.Available ->{
                    showMessage(true)
                }
                NetworkStatus.Unavailable ->{
                    showMessage(false)
                }
            }
        }*/

        ConnectionLiveData(this).observe(this){
           showMessage(it)
        }

    }

    private fun showMessage(isConnected : Boolean){
        if (isConnected){
            tv.apply {
                text = "Connected to INTERNET"
                this.setTextColor(ContextCompat.getColor(this@MainActivity,R.color.connected))
            }
            displaySnackTop("Connected to INTERNET",Snackbar.LENGTH_LONG)
        }else{
            tv.apply {
                text = "NO INTERNET ACCESS"
                this.setTextColor(ContextCompat.getColor(this@MainActivity,R.color.disconnect))
            }
            displaySnackTop("NO INTERNET ACCESS",Snackbar.LENGTH_INDEFINITE)
        }

    }

}