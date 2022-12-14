package com.nhanbt.demo_kotlin_native.features.category.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.category.domain.repository.CategoriesResponse
import com.nhanbt.demo_kotlin_native.features.category.domain.usecases.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val useCases: CategoryUseCases
) : ViewModel() {
    var categoriesResponse by mutableStateOf<CategoriesResponse>(BaseResponse.Loading)
        private set

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        useCases.getCategories().collect { response ->
            categoriesResponse = response
        }
    }
}