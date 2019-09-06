package com.sample.app.util

import org.xmlpull.v1.XmlPullParser

fun XmlPullParser.nextTextClean(): String {
    return this.nextText().textClean()
}

fun String.textClean(): String {
    return this.trim { it <= ' ' }.replace("\\s{2,}".toRegex(), " ")
}