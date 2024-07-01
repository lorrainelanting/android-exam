package com.example.randomuser.presentation.main.persons

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.randomuser.R
import com.example.randomuser.databinding.FragmentPersonsBinding
import com.example.randomuser.domain.model.Person
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonsFragment : Fragment() {
    private lateinit var binding: FragmentPersonsBinding

    private val viewModel: PersonsViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel.fetchPersons()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPersonsBinding.inflate(inflater, container, false)

        val adapter = PersonsAdapter(
            personsAdapterListener = object : PersonsAdapter.PersonsAdapterListener {
                override fun onItemClick(item: Person) {
                    val bundle = bundleOf("email" to item.email)
                    findNavController().navigate(R.id.action_personsFragment_to_personDetailFragment, bundle)
                }
            },
        )

        binding.rvPersons.adapter = adapter

        lifecycle.coroutineScope.launch {
            viewModel.personsPagingDataStateFlow.collect {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
        return binding.root
    }
}
