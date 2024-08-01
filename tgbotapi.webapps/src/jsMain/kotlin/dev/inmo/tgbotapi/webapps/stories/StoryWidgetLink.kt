package dev.inmo.tgbotapi.webapps.stories

import kotlin.js.json

external interface StoryWidgetLink {
    val url: String
    val name: String?
}

fun StoryWidgetLink(
    url: String,
    name: String?
): StoryWidgetLink {
    val result: dynamic = js("{}")
    result["url"] = url
    name ?.let { result["name"] = it }
    return result.unsafeCast<StoryWidgetLink>()
}
