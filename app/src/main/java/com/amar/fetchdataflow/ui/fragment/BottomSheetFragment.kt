package com.amar.fetchdataflow.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.amar.fetchdataflow.data.model.Address
import com.amar.fetchdataflow.data.model.User
import com.amar.fetchdataflow.databinding.BottomSheetUserItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {
     private lateinit var binding: BottomSheetUserItemBinding
     override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
     ): View {
          binding = BottomSheetUserItemBinding.inflate(inflater, container, false)
          return binding.root
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)
          val userDetail: User? = arguments?.getParcelable(USER_DATA)

          userDetail?.let { user ->
               binding.nameText.text = getCompleteName(user)
               binding.userInfoText.text = getUserInfo(user)
          }
     }

     private fun getCompleteName(user: User): String {
          val unknown = "Unknown"
          return "Name: ${user.firstName ?: unknown} ${user.lastName ?: unknown}"
     }

     private fun getUserInfo(user: User): String {
          val unknown = "Unknown"
          val userAddress = getCompleteAddress(user.address)
          return buildString {
               appendLine("Age: ${user.age ?: unknown}")
               appendLine("Gender: ${user.gender ?: unknown}")
               appendLine("Email: ${user.email ?: unknown}")
               appendLine("Phone Number: ${user.phone ?: unknown}")
               appendLine("Address: ${userAddress ?: unknown}")
          }
     }

     private fun getCompleteAddress(address: Address?): String? {
          val unknown = "Unknown"
          return address?.let {
               listOfNotNull(
                    it.address,
                    it.city,
                    it.state,
                    it.postalCode,
                    it.country
               ).joinToString(", ").ifEmpty { unknown }
          }
     }

     companion object {
          private const val USER_DATA = "user_data"
          fun newInstance(user: User): BottomSheetFragment = BottomSheetFragment().apply {
               arguments = bundleOf(USER_DATA to user)
          }
     }
}