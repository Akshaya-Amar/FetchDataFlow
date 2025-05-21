package com.amar.fetchdataflow.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.amar.fetchdataflow.data.model.User
import com.amar.fetchdataflow.data.model.UserData
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
               val unknown = "Unknown"
               val completeName = "Name: ${user.firstName ?: unknown} ${user.lastName ?: unknown}"
               binding.nameText.text = completeName

               val completeAddress = user.address?.let { address ->
                    listOfNotNull(
                         address.address,
                         address.city,
                         address.state,
                         address.postalCode,
                         address.country
                    ).joinToString(", ").ifEmpty { unknown }
               }

               val userInfo = buildString {
                    appendLine("Age: ${user.age ?: unknown}")
                    appendLine("Gender: ${user.gender ?: unknown}")
                    appendLine("Email: ${user.email ?: unknown}")
                    appendLine("Phone Number: ${user.phone ?: unknown}")
                    appendLine("Address: ${completeAddress ?: unknown}")
               }

               binding.userInfoText.text = userInfo
          }
     }

     companion object {
          private const val USER_DATA = "user_data"
          fun newInstance(user: User): BottomSheetFragment = BottomSheetFragment().apply {
               arguments = bundleOf(USER_DATA to user)
          }
     }
}