package id.kotlin.rickandmortyproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.kotlin.rickandmortyproject.data.DataRepository
import id.kotlin.rickandmortyproject.data.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private val _character = MutableLiveData<Resource<List<MortyCharactersQuery.Result?>>>()
    val characters: LiveData<Resource<List<MortyCharactersQuery.Result?>>>
        get() = _character

    fun getAllCharacters() {
        _character.postValue(Resource.Loading(true))
        viewModelScope.launch {
            try {
                val response = dataRepository.getCall().execute()
                val data = response.data?.characters?.results!!
                _character.postValue(Resource.Success(data))
            } catch (e: Exception) {
                _character.postValue(Resource.Error(e.message.toString()))
                Log.d("MVM", e.message.toString())

            }
        }
    }
}