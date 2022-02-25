package com.printoverit.machinetestapp.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.printoverit.machinetestapp.dataclasses.Data
import com.printoverit.machinetestapp.dataclasses.MemeDataClass
import com.printoverit.machinetestapp.repositories.TestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestViewModel(private val testRepository: TestRepository):ViewModel() {

    fun loadMemes() {
    viewModelScope.launch(Dispatchers.IO)
        {
            testRepository.getMemes()
        }
    }
    fun loadOfflineMemes(){
        viewModelScope.launch(Dispatchers.IO) {
            testRepository.getOfflineMemes()
        }
    }

    fun loadSearchedMemes(query:String){
        viewModelScope.launch (Dispatchers.IO) {
            testRepository.getSearchedMemes(query)
        }
    }

     fun getMemes():LiveData<MemeDataClass>{
        return  testRepository.meme
    }

    fun getOfflineMemes():LiveData<MemeDataClass>{
        return testRepository.meme
    }

}