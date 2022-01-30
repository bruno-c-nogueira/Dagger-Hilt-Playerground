package com.codingwithmitch.daggerhiltplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import com.codingwithmitch.daggerhiltplayground.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.setStateEvent()
        viewModel.loading.observe(this) {
            progress_bar.isVisible = true
            text.isVisible = false


        }

        viewModel.blog.observe(this) {
            progress_bar.isVisible = false
            text.isVisible = true
            text.text = it.map { it.title }.toList().toString()
        }

    }


}



















