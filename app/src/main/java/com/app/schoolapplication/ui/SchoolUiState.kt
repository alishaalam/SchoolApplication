package com.app.schoolapplication.ui

import com.app.schoolapplication.domain.model.School

data class SchoolUiState(
    val schools: List<School> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)