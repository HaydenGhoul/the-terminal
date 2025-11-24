package ua.hayden.theterminal.data.repository

import ua.hayden.theterminal.R
import ua.hayden.theterminal.data.model.AdvertisementRes
import ua.hayden.theterminal.data.model.ArticleRes

object NewsFeedRepository {
    val articles = listOf(
        ArticleRes(
            id = 0,
            headline = R.string.article_headline_1,
            subheadline = R.string.article_subheadline_1,
            author = R.string.article_author_1,
            newsOrg = R.string.news_org_the_terminal,
            image = R.drawable.breen,
            imageCaption = R.string.article_image_caption_1,
            text = R.string.article_1,
            url = R.string.article_url_1_3
        ),
        ArticleRes(
            id = 1,
            headline = R.string.article_headline_2,
            subheadline = R.string.article_subheadline_2,
            author = R.string.article_author_2,
            newsOrg = R.string.news_org_the_terminal,
            image = R.drawable.portal_storm_map,
            imageCaption = R.string.article_image_caption_2,
            text = R.string.article_2,
            url = R.string.article_url_1_3
        ),
        ArticleRes(
            id = 2,
            headline = R.string.article_headline_3,
            subheadline = R.string.article_subheadline_3,
            author = R.string.article_author_3,
            newsOrg = R.string.news_org_the_terminal,
            image = R.drawable.market_state,
            imageCaption = R.string.article_image_caption_3,
            text = R.string.article_3,
            url = R.string.article_url_1_3
        ),
        ArticleRes(
            id = 3,
            headline = R.string.article_headline_4,
            subheadline = R.string.article_subheadline_4,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_4,
            url = null
        ),
        ArticleRes(
            id = 4,
            headline = R.string.article_headline_5,
            subheadline = R.string.article_subheadline_5,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_5,
            url = null
        ),
        ArticleRes(
            id = 5,
            headline = R.string.article_headline_6,
            subheadline = R.string.article_subheadline_6,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_6,
            url = null
        ),
        ArticleRes(
            id = 6,
            headline = R.string.article_headline_7,
            subheadline = R.string.article_subheadline_7,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_7,
            url = null
        ),
        ArticleRes(
            id = 7,
            headline = R.string.article_headline_8,
            subheadline = R.string.article_subheadline_8,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_8,
            url = null
        ),
        ArticleRes(
            id = 8,
            headline = R.string.article_headline_9,
            subheadline = R.string.article_subheadline_9,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_9,
            url = null
        ),
        ArticleRes(
            id = 9,
            headline = R.string.article_headline_10,
            subheadline = R.string.article_subheadline_10,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_10,
            url = null
        ),
        ArticleRes(
            id = 10,
            headline = R.string.article_headline_11,
            subheadline = R.string.article_subheadline_11,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_11,
            url = null
        ),
        ArticleRes(
            id = 11,
            headline = R.string.article_headline_12,
            subheadline = R.string.article_subheadline_12,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_12,
            url = null
        ),
        ArticleRes(
            id = 12,
            headline = R.string.article_headline_13,
            subheadline = R.string.article_subheadline_13,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_13,
            url = null
        ),
        ArticleRes(
            id = 13,
            headline = R.string.article_headline_14,
            subheadline = R.string.article_subheadline_14,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_14,
            url = null
        ),
        ArticleRes(
            id = 14,
            headline = R.string.article_headline_15,
            subheadline = R.string.article_subheadline_15,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_15,
            url = null
        ),
        ArticleRes(
            id = 15,
            headline = R.string.article_headline_16,
            subheadline = R.string.article_subheadline_16,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_16,
            url = null
        ),
        ArticleRes(
            id = 16,
            headline = R.string.article_headline_17,
            subheadline = R.string.article_subheadline_17,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_17,
            url = null
        ),
        ArticleRes(
            id = 17,
            headline = R.string.article_headline_18,
            subheadline = R.string.article_subheadline_18,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_18,
            url = null
        ),
        ArticleRes(
            id = 18,
            headline = R.string.article_headline_19,
            subheadline = R.string.article_subheadline_19,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_19,
            url = null
        ),
        ArticleRes(
            id = 19,
            headline = R.string.article_headline_20,
            subheadline = R.string.article_subheadline_20,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_20,
            url = null
        ),
        ArticleRes(
            id = 20,
            headline = R.string.article_headline_21,
            subheadline = R.string.article_subheadline_21,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_21,
            url = null
        ),
        ArticleRes(
            id = 21,
            headline = R.string.article_headline_22,
            subheadline = R.string.article_subheadline_22,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_22,
            url = null
        ),
        ArticleRes(
            id = 22,
            headline = R.string.article_headline_23,
            subheadline = R.string.article_subheadline_23,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_23,
            url = null
        ),
        ArticleRes(
            id = 23,
            headline = R.string.article_headline_24,
            subheadline = R.string.article_subheadline_24,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_24,
            url = null
        ),
        ArticleRes(
            id = 24,
            headline = R.string.article_headline_25,
            subheadline = R.string.article_subheadline_25,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_25,
            url = null
        ),
        ArticleRes(
            id = 25,
            headline = R.string.article_headline_26,
            subheadline = R.string.article_subheadline_26,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_26,
            url = null
        ),
        ArticleRes(
            id = 26,
            headline = R.string.article_headline_27,
            subheadline = R.string.article_subheadline_27,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_27,
            url = null
        ),
        ArticleRes(
            id = 27,
            headline = R.string.article_headline_28,
            subheadline = R.string.article_subheadline_28,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_portrait,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_28,
            url = null
        ),
        ArticleRes(
            id = 28,
            headline = R.string.article_headline_29,
            subheadline = R.string.article_subheadline_29,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_29,
            url = null
        ),
        ArticleRes(
            id = 29,
            headline = R.string.article_headline_30,
            subheadline = R.string.article_subheadline_30,
            author = R.string.article_author_example,
            newsOrg = R.string.news_org_example,
            image = R.drawable.article_image_landscape,
            imageCaption = R.string.article_image_caption_example,
            text = R.string.article_30,
            url = null
        ),
    )

    val ads = listOf(
        AdvertisementRes(
            id = 30,
            image = R.drawable.car_riga,
            headline = R.string.ad_title_1,
            caption = R.string.ad_caption_1,
            model = R.string.ad_model_1,
            url = R.string.ad_url_1
        )
    )
}