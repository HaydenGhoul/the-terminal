package ua.hayden.theterminal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.hayden.theterminal.R
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.Advertisement
import ua.hayden.theterminal.model.NewsFeed
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.model.ResourceProvider

class NewsFeedViewModel(
    private val repository: NewsFeedRepository,
    private val resProvider: ResourceProvider
) : ViewModel() {
    private val _newsFeed = MutableStateFlow<List<NewsFeed>>(emptyList())
    private val _expandedCards = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    private val _events = MutableSharedFlow<UiEvent>()
    val newsFeed = _newsFeed.asStateFlow()
    val expandedCards = _expandedCards.asStateFlow()
    val events = _events.asSharedFlow()


    init { loadNewsFeed() }

    private fun loadNewsFeed() {
        viewModelScope.launch {
            val mappedArticles = repository.articleList.map { it.toUi(resProvider) }
            val mappedAds = repository.adsList.map { it.toUi(resProvider) }
            _newsFeed.value = mappedArticles + mappedAds
        }
    }

    private suspend fun handleUrlClick(url: String?) {
            if (url != null) {
                _events.emit(UiEvent.OpenUrl(url))
            } else {
                _events.emit(
                    UiEvent.ShowSnackbar(
                        resProvider.getString(R.string.snackbar_access_denied)
                    )
                )
            }
    }

    fun toggleCardExpandedState(id: Int) {
        _expandedCards.value = _expandedCards.value.toMutableMap().also {
            val current = it[id] ?: false
            it[id] = !current
        }
    }

    fun openUrlOrSnackbar(url: String?) {
        viewModelScope.launch {
            handleUrlClick(url)
        }
    }
}

sealed interface UiEvent {
    data class OpenUrl(val url: String) : UiEvent
    data class ShowSnackbar(val message: String) : UiEvent
}