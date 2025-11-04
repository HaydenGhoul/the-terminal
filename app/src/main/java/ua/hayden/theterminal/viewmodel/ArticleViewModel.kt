package ua.hayden.theterminal.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.ArticleRepository

class ArticleViewModel(
    private val repository: ArticleRepository = ArticleRepository
) : ViewModel() {
    private val _articles = mutableStateOf(emptyList<Article>())
    val articles = _articles

    init {
        loadArticles()
    }

    private fun loadArticles() {
        _articles.value = repository.articleList
    }
}