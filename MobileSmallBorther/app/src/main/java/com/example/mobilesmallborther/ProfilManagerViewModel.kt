package com.example.mobilesmallborther

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesmallborther.utils.RetrofitHelper
import com.example.mobilesmallbrother.dtos.DtoInputPost
import com.example.mobilesmallbrother.repositories.PostRepository
import kotlinx.coroutines.launch

class ProfilManagerViewModel : ViewModel() {
    private val animalRepository = RetrofitHelper.newInstance().create(PostRepository::class.java)

    // Equivalent au pattern Observer dans un objet
    val mutableLiveDataListPost: MutableLiveData<List<DtoInputPost>> = MutableLiveData()
    val mutableLiveDataCreatePost: MutableLiveData<DtoInputPost> = MutableLiveData()

    fun launchFetchAllPost() {
        viewModelScope.launch {
            // Ici nous sommes en asynchrone (visible grâce à la flèche de l'IDE à gauche)
            val profilList = animalRepository.fetchAllNotFound()
            // Tous ceux qui observe le mutable vont donc recevoir la postList
            mutableLiveDataListPost.postValue(profilList)
        }
    }
}