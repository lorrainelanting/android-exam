package com.example.randomuser.presentation.main.persons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuser.databinding.ItemPersonBinding
import com.example.randomuser.domain.model.Person

class PersonsAdapter(
    private val personsAdapterListener: PersonsAdapterListener? = null,
) : PagingDataAdapter<Person, PersonsAdapter.ViewHolder>(PersonDiffCallback()) {

    class ViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            val name = "${person.firstName} ${person.lastName}"
            binding.tvName.text = name
            binding.tvEmail.text = person.email
        }
    }

    interface PersonsAdapterListener {
        fun onItemClick(item: Person)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.itemView.setOnClickListener {
                // pass item to the personsAdapterListener
                personsAdapterListener?.onItemClick(item)
            }

            holder.bind(item)
        }
    }
}
