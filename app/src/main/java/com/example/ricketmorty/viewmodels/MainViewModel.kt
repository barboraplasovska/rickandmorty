package com.example.ricketmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ricketmorty.data.FilterData
import com.example.ricketmorty.models.Character
import com.example.ricketmorty.models.Filter
import com.example.ricketmorty.models.SearchInfo
import com.example.ricketmorty.network.RickEtMortyApi
import kotlinx.coroutines.launch

enum class RickEtMortyApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private val _status = MutableLiveData<RickEtMortyApiStatus>()
    val status: LiveData<RickEtMortyApiStatus> = _status

    private val _filter_data = MutableLiveData<List<Filter>>()
    val filter_data: LiveData<List<Filter>> = _filter_data

    private val _info = MutableLiveData<SearchInfo>()
    val info: LiveData<SearchInfo> = _info

    private val _characters = MutableLiveData<List<Character>>(mutableListOf())
    val characters: LiveData<List<Character>> = _characters

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    var _page_number = -1;

    init {
        getCharacters()
        fetchMoreItems()
        _filter_data.postValue(FilterData)
    }

    fun getCharacters() {
        viewModelScope.launch {
            _status.postValue(RickEtMortyApiStatus.LOADING)
            try {
                val res = RickEtMortyApi.retrofitService.getCharacters()
                _characters.postValue(res.results)
                _info.postValue(res.info)
                _status.postValue(RickEtMortyApiStatus.DONE)
                _page_number = 1
            } catch (e: Exception) {
                _status.postValue(RickEtMortyApiStatus.ERROR)
                _characters.postValue(mutableListOf())
            }
        }
    }


    fun fetchMoreItems() {
        if (_page_number == -1) {
            return
        }
        viewModelScope.launch {
            _page_number += 1
            _status.postValue(RickEtMortyApiStatus.LOADING)
            try {
                val nextPage =
                    RickEtMortyApi.retrofitService.getCharactersPage(pageNumber = _page_number)
                val temp = _characters.value?.toMutableList()
                if (temp != null) {
                    temp.addAll(nextPage.results)
                    _characters.postValue(temp.toList())
                }

                _info.postValue(nextPage.info)
                _status.postValue(RickEtMortyApiStatus.DONE)

                if (nextPage.info.next == null) {
                    _page_number = -1
                }
            } catch (e: Exception) {
                _status.postValue(RickEtMortyApiStatus.ERROR)
                _characters.postValue(mutableListOf())
            }
        }
    }

    fun selectCharacter(character: Character) {
        _character.postValue(character)
    }

    fun updateFilterData(filter: Filter, selected: String?) {
        _filter_data.value?.map {
            if (it == filter) {
                filter.copy(selected = selected)
            } else {
                it
            }
        }?.let { _filter_data.postValue(it) }
    }
}
