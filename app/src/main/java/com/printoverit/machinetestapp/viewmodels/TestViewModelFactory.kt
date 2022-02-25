package com.printoverit.machinetestapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.printoverit.machinetestapp.repositories.TestRepository

class TestViewModelFactory(private val testRepository: TestRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  TestViewModel(testRepository) as T
    }





}