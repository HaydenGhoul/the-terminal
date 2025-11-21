package ua.hayden.theterminal.viewmodel

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.model.ResourceProviderImpl

val newsFeedViewModelFactory = viewModelFactory {
    initializer {
        val repo = NewsFeedRepository
        val resProvider = ResourceProviderImpl(this[APPLICATION_KEY]!!)
        NewsFeedViewModel(repo, resProvider)
    }
}