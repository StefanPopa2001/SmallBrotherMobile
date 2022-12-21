package com.example.mobilesmallborther

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilesmallborther.utils.RetrofitHelper
import com.example.mobilesmallbrother.dtos.DtoInputClient
import com.example.mobilesmallbrother.dtos.DtoOutputLoginClient
import com.example.mobilesmallbrother.repositories.ClientRepository
import kotlinx.coroutines.launch

class ClientManagerViewModel : ViewModel() {
    private val clientRepository = RetrofitHelper.newInstance().create(ClientRepository::class.java)

    val mutableLiveDataByIdClient: MutableLiveData<DtoInputClient> = MutableLiveData()
    val mutableLiveDataLoginClient: MutableLiveData<DtoInputClient?> = MutableLiveData()

    var acceptLogin: Boolean = false

    fun launchFetchById() {
        viewModelScope.launch {
            val clientById = clientRepository.fetchById()
            mutableLiveDataByIdClient.postValue(clientById)
        }
    }

    fun launchFetchByLogin(dto: DtoOutputLoginClient) {
        viewModelScope.launch {
            try {
                val clientLogin = clientRepository.fetchByLogin(dto)
                mutableLiveDataLoginClient.postValue(clientLogin)
                acceptLogin = true
            }
            catch(e: Exception) {
                mutableLiveDataLoginClient.postValue(null)
                acceptLogin = false
            }
        }
    }
}