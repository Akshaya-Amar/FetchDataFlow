package com.amar.fetchdataflow.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.amar.fetchdataflow.R
import com.amar.fetchdataflow.common.Result
import com.amar.fetchdataflow.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

     private val viewModel: UserViewModel by viewModels()

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)

          viewModel.getUsers()

          lifecycleScope.launch {
               repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.users.collectLatest { result ->
                         when (result) {
                              is Result.Success -> {
                                   Log.d("check...", "onCreate: Success -> ${result.data}")
                              }

                              is Result.Failure -> {
                                   Log.d("check...", "onCreate: Failure -> ${result.message}")
                              }

                              is Result.Loading -> {
                                   Log.d("check...", "onCreate: Loading")
                              }
                         }
                    }
               }
          }
     }
}