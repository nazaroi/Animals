package com.sample.app.data

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.res.use
import com.sample.app.R
import com.sample.app.model.*
import com.sample.app.utilities.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

object SearchRepository {

    private lateinit var searchResults: List<SearchResult>

    fun getSearchResults(context: Context): List<SearchResult> {

        if (!::searchResults.isInitialized) {
            val articleIds = getArticleIds(context)
            searchResults = articleIds.mapIndexed { _, id -> parseSearchResult(context, id) }
        }

        return searchResults
    }


    @SuppressLint("Recycle")
    private fun getArticleIds(context: Context): List<Int> {
        val typedArray = context.resources.obtainTypedArray(R.array.article_ids)
        return typedArray.use {
            IntArray(typedArray.length()).mapIndexed { index, _ ->
                typedArray.getResourceId(
                    index,
                    0
                )
            }
        }
    }

    private fun parseSearchResult(context: Context, id: Int): SearchResult {

        val xpp = context.resources.getXml(id)

        val searchResult = SearchResult(id)

        try {
            loop@ while (xpp.eventType != XmlPullParser.END_DOCUMENT) {
                if (xpp.eventType == XmlPullParser.START_TAG) {
                    when (xpp.name) {
                        TAG_HEAD -> {
                            xpp.next()
                            while (xpp.name != TAG_HEAD) {
                                when (xpp.name) {
                                    TAG_NAME -> searchResult.name = xpp.nextTextClean()
                                    TAG_DESCRIPTION -> searchResult.description =
                                        xpp.nextTextClean()
                                    TAG_IMAGE -> searchResult.icon = xpp.nextTextClean()
                                }
                                xpp.next()
                            }
                        }
                        TAG_BODY -> {
                            break@loop
                        }
                    }
                }
                xpp.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return searchResult
    }
}