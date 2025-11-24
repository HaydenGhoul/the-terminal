package ua.hayden.theterminal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.hayden.theterminal.R
import ua.hayden.theterminal.domain.model.NewsFeed
import ua.hayden.theterminal.data.repository.NewsFeedRepository
import ua.hayden.theterminal.utils.ResourceProvider

class NewsFeedViewModel(
    private val repository: NewsFeedRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {
    private val _newsFeedItems = MutableStateFlow<List<NewsFeed>>(emptyList())
    private val _expandedArticleCards = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    private val _uiEvents = MutableSharedFlow<NewsFeedUiEvent>()
    val newsFeedItems = _newsFeedItems.asStateFlow()
    val expandedArticleCards = _expandedArticleCards.asStateFlow()
    val uiEvents = _uiEvents.asSharedFlow()


    init { loadNewsFeedItems() }

    private fun loadNewsFeedItems() {
        viewModelScope.launch {
            val mappedArticles = repository.articles.map { it.toArticle(resourceProvider) }
            val mappedAds = repository.ads.map { it.toAdvertisement(resourceProvider) }
            _newsFeedItems.value = mappedArticles + mappedAds
        }
    }

    private suspend fun handleFeedItemClick(url: String?) {
            if (url != null) {
                _uiEvents.emit(NewsFeedUiEvent.OpenUrl(url))
            } else {
                _uiEvents.emit(NewsFeedUiEvent.ShowSnackbar(resourceProvider.getString(R.string.snackbar_access_denied)))
            }
    }

    /**
     * Toggles the expanded/collapsed state of a specific article card.
     *
     * Used by the UI to expand or collapse a card when the user clicks it.
     *
     * @param id The unique identifier of the article card to toggle.
     */
    fun toggleArticleCardExpandedState(id: Int) {
        _expandedArticleCards.value = _expandedArticleCards.value.toMutableMap().also {
            val current = it[id] ?: false
            it[id] = !current
        }
    }

    /**
     * Handles a click on a URL by either opening it in a browser or showing a snackbar if the URL is null.
     *
     * This method is intended for direct use from the UI (e.g., from Read More buttons or advertisement clicks).
     *
     * @param url The URL to open, or null if unavailable.
     */
    fun onFeedItemClick(url: String?) {
        viewModelScope.launch {
            handleFeedItemClick(url)
        }
    }
}

sealed interface NewsFeedUiEvent {
    data class OpenUrl(val url: String) : NewsFeedUiEvent
    data class ShowSnackbar(val message: String) : NewsFeedUiEvent
}