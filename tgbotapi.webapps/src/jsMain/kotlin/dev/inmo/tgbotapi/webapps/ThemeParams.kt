package dev.inmo.tgbotapi.webapps

external interface ThemeParams {
    @JsName("bg_color")
    val backgroundColor: String?
    @JsName("text_color")
    val textColor: String?
    @JsName("hint_color")
    val hintColor: String?
    @JsName("link_color")
    val linkColor: String?
    @JsName("button_color")
    val buttonColor: String?
    @JsName("button_text_color")
    val buttonTextColor: String?
}
