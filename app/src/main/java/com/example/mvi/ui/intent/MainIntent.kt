package com.example.mvi.ui.intent
/*
   we use sealed class to restrict class hierarchy

 */
sealed class MainIntent {

    data object GetPosts: MainIntent()
}