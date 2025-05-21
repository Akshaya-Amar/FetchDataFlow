package com.amar.fetchdataflow.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amar.fetchdataflow.data.model.User
import com.amar.fetchdataflow.databinding.UserItemBinding

class UserAdapter(
     private val onItemClick: (User) -> Unit
) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

     class UserDiffCallback : ItemCallback<User>() {
          override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
               return oldItem.id == newItem.id
          }

          override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
               return oldItem == newItem
          }
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
          val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return UserViewHolder(binding)
     }

     override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
          getItem(position)?.let { user -> holder.onBind(user, onItemClick) }
     }

     class UserViewHolder(
          private val binding: UserItemBinding
     ) : RecyclerView.ViewHolder(
          binding.root
     ) {
          fun onBind(user: User, onItemClick: (User) -> Unit) {
               binding.nameText.text = user.firstName
               binding.root.setOnClickListener {
                    onItemClick(user)
               }
          }
     }
}