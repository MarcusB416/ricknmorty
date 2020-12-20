package io.threethirtythree.ricknmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.threethirtythree.ricknmorty.database.Character
import io.threethirtythree.ricknmorty.repository.CharactersRepository
import kotlinx.coroutines.launch

/** ViewModel that accesses our repository to collect Results information */
class CharacterViewModel(private val repository: CharactersRepository) : ViewModel() {

    /** Converting a flow to LiveData allows us to query the single source of truth
     * and not make repeated queries to the db as its updated every time a query happens. */
    val characters: LiveData<List<Character>> = repository.allCharacters.asLiveData()

    fun insertAll(charList: List<Character>) = viewModelScope.launch{
        repository.insertAll(charList)
    }

    fun insert(character: Character) = viewModelScope.launch {
        repository.insert(character)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}

/** Factory to help us create this viewholder by lazy. */
class CharacterViewModelFactory(private val repository: CharactersRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}