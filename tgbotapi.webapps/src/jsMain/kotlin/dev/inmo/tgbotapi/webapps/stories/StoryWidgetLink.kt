package dev.inmo.tgbotapi.webapps.stories

import kotlin.js.json

external interface StoryWidgetLink {
    val url: String
    val name: String?
}

fun StoryWidgetLink(
    url: String,
    name: String? = null,
): StoryWidgetLink {
    val json = json()
    json["url"] = url
    name ?.let { json["name"] = it }
    return json.unsafeCast<StoryWidgetLink>()
}
