package dev.inmo.tgbotapi.webapps.stories

external interface StoryShareParams {
    val text: String
    @JsName("widget_link")
    val widgetLink: StoryWidgetLink?
}