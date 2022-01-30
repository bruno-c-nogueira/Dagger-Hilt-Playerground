package com.codingwithmitch.daggerhiltplayground.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codingwithmitch.daggerhiltplayground.model.Blog
import com.codingwithmitch.daggerhiltplayground.repository.MainRepository
import com.codingwithmitch.daggerhiltplayground.util.DataState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repository: MainRepository
) : ViewModel() {

    private val _blogs: MutableLiveData<List<Blog>> = MutableLiveData()
    val blog: LiveData<List<Blog>>
        get() = _blogs

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean>
        get() = _loading

    fun setStateEvent() {
        viewModelScope.launch {
            repository.getBlog().collect {
                when (it) {
                    is DataState.Success -> {
                        _blogs.postValue(it.data)
                    }
                    is DataState.Error -> {
                        print("Error")
                    }
                    is DataState.Loading -> {
                        _loading.postValue(true)
                    }

                }
            }
        }
    }


}

