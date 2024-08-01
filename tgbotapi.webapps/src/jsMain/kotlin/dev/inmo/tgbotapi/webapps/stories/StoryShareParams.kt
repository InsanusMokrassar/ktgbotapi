package dev.inmo.tgbotapi.webapps.stories

import kotlin.js.json

external interface StoryShareParams {
    val text: String
    @JsName("widget_link")
    val widgetLink: StoryWidgetLink?
}

fun StoryShareParams(
    text: String,
    widgetLink: StoryWidgetLink?
): StoryShareParams {
    val json = json()
    json["text"] = text
    widgetLink ?.let { json["widget_link"] = it }
    return json.unsafeCast<StoryShareParams>()
}
