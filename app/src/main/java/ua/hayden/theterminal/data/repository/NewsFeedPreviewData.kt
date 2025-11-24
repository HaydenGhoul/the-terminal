package ua.hayden.theterminal.data.repository

import android.content.Context
import ua.hayden.theterminal.data.repository.NewsFeedRepository.ads
import ua.hayden.theterminal.data.repository.NewsFeedRepository.articles
import ua.hayden.theterminal.domain.model.NewsFeed
import ua.hayden.theterminal.utils.ResourceProviderImpl

object NewsFeedPreviewData {
    fun getNewsFeedItemsForPreview(
        context: Context,
        expandArticles: Boolean = true
    ): Pair<List<NewsFeed>, Map<Int, Boolean>> {
        val resourceProvider = ResourceProviderImpl(context)
        val mappedArticles = articles.map { it.toArticle(resourceProvider) }
        val mappedAds = ads.map { it.toAdvertisement(resourceProvider) }
        val newsFeedItems = mappedArticles + mappedAds

        val expandedMap = mappedArticles.associate { it.id to expandArticles }

        return newsFeedItems to expandedMap
    }

    fun getArticleForPrev(content: Context, index: Int): NewsFeed.Article =
        articles[index].toArticle(ResourceProviderImpl(content))

    fun getAdvertisementForPrev(context: Context): NewsFeed.Advertisement =
        ads[0].toAdvertisement(ResourceProviderImpl(context))
}