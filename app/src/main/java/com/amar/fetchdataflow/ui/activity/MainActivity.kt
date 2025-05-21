package com.amar.fetchdataflow.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.amar.fetchdataflow.common.Result
import com.amar.fetchdataflow.data.model.User
import com.amar.fetchdataflow.data.model.UserData
import com.amar.fetchdataflow.databinding.ActivityMainBinding
import com.amar.fetchdataflow.ui.adapter.UserAdapter
import com.amar.fetchdataflow.ui.fragment.BottomSheetFragment
import com.amar.fetchdataflow.ui.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
     private val viewModel: UserViewModel by viewModels()
     private lateinit var binding: ActivityMainBinding
     private val userAdapter by lazy {
          UserAdapter { user ->
               showBottomSheet(user)
          }
     }

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)
          setUpRecyclerView()
          observeUserData()
     }

     private fun observeUserData() {
          lifecycleScope.launch {
               repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.users.collectLatest { result ->
                         handleResult(result)
                    }
               }
          }
     }

     private fun handleResult(result: Result<UserData>) {
          when (result) {
               is Result.Success -> {
                    displayData(result.data.users)
                    Log.d("check...", "onCreate: Success -> ${result.data}")
               }

               is Result.Failure -> {
                    showError(result.message)
                    Log.d("check...", "onCreate: Failure -> ${result.message}")
               }

               is Result.Loading -> {
                    showLoadingIndicator()
                    Log.d("check...", "onCreate: Loading")
               }
          }
     }

     private fun showLoadingIndicator() {
          binding.progressBar.isVisible = true
          binding.recyclerView.isVisible = false
     }

     private fun showError(message: String) {
          binding.progressBar.isVisible = false
          binding.recyclerView.isVisible = false
          Snackbar.make(
               binding.root,
               message,
               Snackbar.LENGTH_LONG
          ).show()
     }

     private fun displayData(users: List<User>?) {
          binding.progressBar.isVisible = false
          binding.recyclerView.isVisible = true
          users?.let {
               userAdapter.submitList(it)
          }
     }

     private fun setUpRecyclerView() {
          binding.recyclerView.apply {
               layoutManager = LinearLayoutManager(this@MainActivity)
               adapter = userAdapter
          }
     }

     private fun showBottomSheet(user: User) {
          val bottomSheetFragment = BottomSheetFragment.newInstance(user)
          bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
     }
}