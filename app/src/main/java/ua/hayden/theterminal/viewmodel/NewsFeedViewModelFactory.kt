package ua.hayden.theterminal.viewmodel

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ua.hayden.theterminal.data.repository.NewsFeedRepository
import ua.hayden.theterminal.utils.ResourceProviderImpl

val newsFeedViewModelFactory = viewModelFactory {
    initializer {
        val newsFeedRepository = NewsFeedRepository
        val resourceProvider = ResourceProviderImpl(this[APPLICATION_KEY]!!)
        NewsFeedViewModel(newsFeedRepository, resourceProvider)
    }
}