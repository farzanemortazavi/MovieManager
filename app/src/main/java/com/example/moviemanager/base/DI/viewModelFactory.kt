package com.example.moviemanager.base.DI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemanager.features.details.movieDetailViewModel
import com.example.moviemanager.features.search.searchViewModel
import com.example.moviemanager.repository.repositoryClass
import javax.inject.Inject

class viewModelFactory @Inject constructor(val repository:repositoryClass):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(searchViewModel::class.java) -> return searchViewModel(repository) as T
            modelClass.isAssignableFrom(movieDetailViewModel::class.java)->return movieDetailViewModel(repository) as T
            else -> throw IllegalArgumentException("viewModel is not provided")
        }
    }
}