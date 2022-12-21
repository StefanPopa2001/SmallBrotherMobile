package com.example.mobilesmallborther

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesmallborther.utils.RetrofitHelper
import com.example.mobilesmallbrother.dtos.DtoInputAnimal
import com.example.mobilesmallbrother.dtos.DtoOutputCreateAnimal
import com.example.mobilesmallbrother.repositories.AnimalRepository
import kotlinx.coroutines.launch

class AnimalManagerViewModel : ViewModel() {
    private val animalRepository = RetrofitHelper.newInstance().create(AnimalRepository::class.java)

    val mutableLiveDataByIdClientAnimal: MutableLiveData<List<DtoInputAnimal>?> = MutableLiveData()
    val mutableLiveDataCreateAnimal: MutableLiveData<DtoInputAnimal> = MutableLiveData()

    fun launchFetchByIdClient(idClient: Int) {
        viewModelScope.launch {
            try {
                val animalByIdClient = animalRepository.fetchByIdClient(idClient)
                mutableLiveDataByIdClientAnimal.postValue(animalByIdClient)
            }
            catch(e: Exception) {
                mutableLiveDataByIdClientAnimal.postValue(null)
            }
        }
    }

    fun launchCreate(dto: DtoOutputCreateAnimal) {
        viewModelScope.launch {
            val animal = animalRepository.create(dto)
            mutableLiveDataCreateAnimal.postValue(animal)
        }
    }
}