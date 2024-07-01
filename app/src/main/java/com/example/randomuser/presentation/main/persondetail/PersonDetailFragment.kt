package com.example.randomuser.presentation.main.persondetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.randomuser.databinding.FragmentPersonDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPersonDetailBinding
    private lateinit var args: String
    private val viewModel: PersonDetailViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        args = arguments?.getString("email").toString()

        viewModel.fetchData(args)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPersonDetailBinding.inflate(inflater, container, false)

        val person = viewModel.personDetailsStateFlow.value
        val age = viewModel.getAge().toString()
        val birthday = viewModel.getFormattedBirthDay()

        if (person != null) {
            val address = "${person.city}, ${person.state}, ${person.country}"

            binding.etLastName.setText(person.lastName)
            binding.etFirstName.setText(person.firstName)
            binding.etBirthday.setText(birthday)
            binding.etAge.setText(age)
            binding.etEmail.setText(person.email)
            binding.etMobile.setText(person.mobileNumber)
            binding.etAddress.setText(address)
            binding.etContactPerson.setText(person.contactPerson ?: " ")
            binding.etContactPersonPhone.setText(person.contactPersonPhoneNum ?: " ")
        }

        return binding.root
    }
}
