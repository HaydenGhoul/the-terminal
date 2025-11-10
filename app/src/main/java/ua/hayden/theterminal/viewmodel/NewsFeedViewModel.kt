package ua.hayden.theterminal.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.Advertisement
import ua.hayden.theterminal.model.NewsFeedRepository

class NewsFeedViewModel(
    private val repository: NewsFeedRepository = NewsFeedRepository
) : ViewModel() {
    private val _articles = mutableStateOf(emptyList<Article>())
    private val _advertisement =  mutableStateOf(Advertisement())
    val articles = _articles
    val advertisement = _advertisement

    init {
        loadNewsFeed()
    }

    private fun loadNewsFeed() {
        _articles.value = repository.articleList
        _advertisement.value = repository.rigaAdvertisement
    }
}