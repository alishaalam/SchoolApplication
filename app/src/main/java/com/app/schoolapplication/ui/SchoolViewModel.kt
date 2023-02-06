package com.app.schoolapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.schoolapplication.domain.repository.SchoolRepository
import com.app.schoolapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val schoolRepository: SchoolRepository,
) : ViewModel() {

    private val _viewState = MutableStateFlow(SchoolUiState())
    val viewState = _viewState.asStateFlow()

    init {
        getSchoolsList()
    }

    fun getSchoolsList(fetchFromRemote: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            schoolRepository
                .getSchoolsList(fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { list ->
                                _viewState.update { currentUiState ->
                                    currentUiState.copy(
                                        schools = list,
                                        errorMessage = null
                                    )
                                }
                            }
                        }
                        is Resource.Error -> {
                            _viewState.update { currentUiState ->
                                currentUiState.copy(
                                    schools = emptyList(),
                                    errorMessage = result.message,
                                    isLoading = false
                                )
                            }
                        }
                        is Resource.Loading -> {
                            _viewState.update { currentUiState ->
                                currentUiState.copy(
                                    isLoading = result.isLoading,
                                )
                            }
                        }
                    }
                }
        }
    }

    fun sortByNameAscending() {
        _viewState.update { currentUiState ->
            currentUiState.copy(
                schools = _viewState.value.schools.sortedBy { school -> school.schoolName }
            )
        }
    }

    fun sortByNameDescending() {
        _viewState.update { currentUiState ->
            currentUiState.copy(
                schools = _viewState.value.schools.sortedByDescending { school -> school.schoolName }
            )
        }
    }
}