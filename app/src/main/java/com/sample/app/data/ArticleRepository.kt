package com.sample.app.data

import android.content.Context
import android.util.Xml
import com.sample.app.model.*
import com.sample.app.utilities.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

object ArticleRepository {

    fun getArticle(context: Context, id: Int): Article {
        return parseArticle(context, id)
    }

    private fun parseArticle(context: Context, id: Int): Article {

        val xpp = context.resources.getXml(id)
        val attr = Xml.asAttributeSet(xpp)

        val article = Article(id)

        try {
            while (xpp.eventType != XmlPullParser.END_DOCUMENT) {
                if (xpp.eventType == XmlPullParser.START_TAG) {
                    when (xpp.name) {
                        TAG_HEAD -> {
                            xpp.next()
                            while (xpp.name != TAG_HEAD) {
                                when (xpp.name) {
                                    TAG_NAME -> article.name = xpp.nextTextClean()
                                    TAG_DESCRIPTION -> article.description = xpp.nextTextClean()
                                    TAG_IMAGE -> article.image = xpp.nextTextClean()
                                    TAG_GALLERY -> {
                                        xpp.next()
                                        article.galleryUrls.run {
                                            while (xpp.name == TAG_URL) {
                                                add(xpp.nextTextClean())
                                                xpp.next()
                                            }
                                        }
                                    }
                                }
                                xpp.next()
                            }
                        }
                        TAG_BODY -> {
                            xpp.next()
                            article.articleBodyItems.run {
                                while (xpp.name != TAG_BODY) {
                                    when (xpp.name) {
                                        TAG_PARAGRAPH -> {
                                            add(ParagraphArticleBodyItem(xpp.nextTextClean()))
                                        }
                                        TAG_HEADING_1 -> {
                                            add(Heading1ArticleBodyItem(xpp.nextTextClean()))
                                        }
                                        TAG_HEADING_2 -> {
                                            add(Heading2ArticleBodyItem(xpp.nextTextClean()))
                                        }
                                        TAG_HEADING_3 -> {
                                            add(Heading3ArticleBodyItem(xpp.nextTextClean()))
                                        }
                                        TAG_HEADING_4 -> {
                                            add(Heading4ArticleBodyItem(xpp.nextTextClean()))
                                        }
                                        TAG_IMG -> {
                                            val src = attr.getAttributeValue(
                                                null,
                                                ATTR_IMG_SRC
                                            ).textClean()
                                            val description =
                                                attr.getAttributeValue(
                                                    null,
                                                    ATTR_IMG_DESCRIPTION
                                                ).textClean()
                                            add(
                                                ImageArticleBodyItem(
                                                    src,
                                                    description
                                                )
                                            )
                                            xpp.next()
                                        }
                                        TAG_SECTION -> {
                                            add(SectionArticleBodyItem(xpp.nextTextClean()))
                                        }
                                    }
                                    xpp.next()
                                }
                            }
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

        return article
    }
}