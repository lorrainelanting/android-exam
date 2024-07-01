package com.example.randomuser.presentation.main.persons

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.randomuser.domain.model.Person
import com.example.randomuser.domain.usecase.GetAllPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PersonsViewModel @Inject constructor(
    private val getAllPersonUseCase: GetAllPersonUseCase,
) : ViewModel() {

    private var _personsPagingDataMutableStateFlow: MutableStateFlow<PagingData<Person>> =
        MutableStateFlow(PagingData.empty())
    val personsPagingDataStateFlow: StateFlow<PagingData<Person>> =
        _personsPagingDataMutableStateFlow

    fun fetchPersons(forceUpdate: Boolean = false) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    getAllPersonUseCase.invoke(forceUpdate = forceUpdate)
                        .cachedIn(viewModelScope)
                        .collect {
                            Log.d("DEBUG_TEST", "PersonsViewModel fetchPersons flow update")
                            _personsPagingDataMutableStateFlow.value = it
                        }
                }
            } catch (e: Exception) {
                Log.d("DEBUG_TEST", e.localizedMessage as String)
            }
        }
    }

    fun refresh() {
        fetchPersons(true)
    }
}
