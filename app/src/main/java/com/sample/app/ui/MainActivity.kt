package com.sample.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.app.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree());
    }
}