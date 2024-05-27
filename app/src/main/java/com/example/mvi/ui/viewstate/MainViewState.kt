package com.example.mvi.ui.viewstate

import com.example.mvi.data.model.FakeDTO

/*
   we will represent the state of data in this class
 */
sealed class MainViewState {

    data object Idle : MainViewState()
    data object Loading : MainViewState()
    class Error(val message : String) : MainViewState()
    class Success(val data : List<FakeDTO>) : MainViewState()
}