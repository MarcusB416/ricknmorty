package io.threethirtythree.ricknmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.threethirtythree.ricknmorty.database.Character
import io.threethirtythree.ricknmorty.repository.ServiceRepository
import kotlinx.coroutines.launch
import timber.log.Timber

/** Fetches a response from the Service. */
class ServiceViewModel(private val serviceRepository: ServiceRepository) : ViewModel() {

    val characters = MutableLiveData<List<Character>>()
    fun load() = viewModelScope.launch {
            try {
                val charList = serviceRepository.loadData().character.take(20)
                characters.postValue(charList)
            } catch (e: Exception) {
                Timber.e(e)
            }
    }
}

/** Factory to help us create this viewholder by lazy. */
class ServiceViewModelFactory(private val service: ServiceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ServiceViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}