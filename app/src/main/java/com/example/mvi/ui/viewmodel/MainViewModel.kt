package com.example.mvi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi.data.repo.GetUserRepo
import com.example.mvi.ui.intent.MainIntent
import com.example.mvi.ui.viewstate.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUserRepo: GetUserRepo): ViewModel() {

    // flow
    // now we will pass intent through this channel and observe the intent and fetch the data
    val mainIntent : Channel<MainIntent> = Channel(Channel.UNLIMITED)

    private val _state = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val state : StateFlow<MainViewState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect{
                when(it){
                    is MainIntent.GetPosts -> fetchPosts()
                }
            }
        }
    }

    private fun fetchPosts(){
        // when using viewmodel scope by default it works on main thread if i don't pass thread type.
        viewModelScope.launch {
            _state.value = MainViewState.Loading
            try {
                _state.value = MainViewState.Success(getUserRepo.getPosts())
            }catch (e : Exception){
                _state.value = MainViewState.Error(e.message.toString())
            }
        }
    }


}