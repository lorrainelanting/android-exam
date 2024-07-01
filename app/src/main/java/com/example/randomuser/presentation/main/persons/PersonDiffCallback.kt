package com.example.randomuser.presentation.main.persons

import androidx.recyclerview.widget.DiffUtil
import com.example.randomuser.domain.model.Person

class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.email == newItem.email
    }
}
