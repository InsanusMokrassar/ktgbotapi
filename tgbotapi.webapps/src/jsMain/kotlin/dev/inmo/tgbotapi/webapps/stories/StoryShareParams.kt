package dev.inmo.tgbotapi.webapps.stories

external interface StoryShareParams {
    val text: String
    @JsName("widget_link")
    val widgetLink: StoryWidgetLink?
}

fun StoryShareParams(
    text: String,
    widgetLink: StoryWidgetLink?
): StoryShareParams {
    val result: dynamic = js("{}")
    result["text"] = text
    widgetLink ?.let { result["widgetLink"] = it }
    return result.unsafeCast<StoryShareParams>()
}
