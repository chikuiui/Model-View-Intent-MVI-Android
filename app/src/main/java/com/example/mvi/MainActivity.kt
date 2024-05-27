package com.example.mvi

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.mvi.databinding.ActivityMainBinding
import com.example.mvi.ui.adapter.MainAdapter
import com.example.mvi.ui.intent.MainIntent
import com.example.mvi.ui.viewmodel.MainViewModel
import com.example.mvi.ui.viewstate.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/*
   MVI -> [ Model View Intent ] Architecture

 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()
    private val mainAdapter = MainAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initView()

        observeViewModels()


        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.GetPosts)
        }
    }

    private fun observeViewModels() {
        // avoid memory leak and destroyed when activity is destroyed.
        lifecycleScope.launch {
            // have to launch in lifecycle scope for collect
            viewModel.state.collect{
                 when(it){
                     is MainViewState.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                     }
                     is MainViewState.Success -> {
                        binding.progress.visibility = View.GONE
                         mainAdapter.addItems(it.data)
                     }
                     is MainViewState.Error -> {
                         binding.progress.visibility = View.GONE
                     }
                     else -> { Toast.makeText(this@MainActivity,"Others!",Toast.LENGTH_SHORT).show()}
                 }
            }
        }

    }

    private fun initView() {
        binding.rvPosts.adapter = mainAdapter
    }
}