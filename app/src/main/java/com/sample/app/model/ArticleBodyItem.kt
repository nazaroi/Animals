package com.sample.app.model


sealed class ArticleBodyItem()

class SectionArticleBodyItem(var section: String) : ArticleBodyItem()

class Heading1ArticleBodyItem(var h1: String) : ArticleBodyItem()
class Heading2ArticleBodyItem(var h2: String) : ArticleBodyItem()
class Heading3ArticleBodyItem(var h3: String) : ArticleBodyItem()
class Heading4ArticleBodyItem(var h4: String) : ArticleBodyItem()

class ImageArticleBodyItem(var src: String, var description: String) : ArticleBodyItem()

class ParagraphArticleBodyItem(var p: String) : ArticleBodyItem()
