package com.example.randomuser.presentation.main.persondetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.domain.model.Person
import com.example.randomuser.domain.usecase.GetPersonUseCase
import com.example.randomuser.presentation.util.HelperUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(private val getPersonUseCase: GetPersonUseCase) :
    ViewModel() {
    private var _personDetailsMutableStateFlow: MutableStateFlow<Person?> = MutableStateFlow(null)
    val personDetailsStateFlow: StateFlow<Person?> = _personDetailsMutableStateFlow

    fun fetchData(email: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    _personDetailsMutableStateFlow.value = getPersonUseCase.invoke(email)
                }
            } catch (e: Exception) {
                Log.d("DEBUG_TEST", e.localizedMessage as String)
            }
        }
    }

    fun getAge(): Int {
        val birthday = _personDetailsMutableStateFlow.value?.birthDate

        return HelperUtil.calcAge(birthday!!)
    }

    fun getFormattedBirthDay(): String {
        return HelperUtil.formatBirthDay(_personDetailsMutableStateFlow.value?.birthDate!!)
    }
}
