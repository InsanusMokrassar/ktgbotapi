package dev.inmo.tgbotapi.webapps

@Suppress("INLINE_CLASS_IN_EXTERNAL_DECLARATION_WARNING", "unused")
external interface ThemeParams {
    @JsName("bg_color")
    val backgroundColor: HEXColor?
    @JsName("secondary_bg_color")
    val secondaryBackgroundColor: HEXColor?
    @JsName("text_color")
    val textColor: HEXColor?
    @JsName("hint_color")
    val hintColor: HEXColor?
    @JsName("link_color")
    val linkColor: HEXColor?
    @JsName("button_color")
    val buttonColor: HEXColor?
    @JsName("button_text_color")
    val buttonTextColor: HEXColor?

    @JsName("bg_color")
    val backgroundColorHex: Color.Hex?
    @JsName("secondary_bg_color")
    val secondaryBackgroundColorHex: Color.Hex?
    @JsName("text_color")
    val textColorHex: Color.Hex?
    @JsName("hint_color")
    val hintColorHex: Color.Hex?
    @JsName("link_color")
    val linkColorHex: Color.Hex?
    @JsName("button_color")
    val buttonColorHex: Color.Hex?
    @JsName("button_text_color")
    val buttonTextColorHex: Color.Hex?

    @JsName("header_bg_color")
    val headerBgColor: Color.Hex?
    @JsName("accent_text_color")
    val accentTextColor: Color.Hex?
    @JsName("section_bg_color")
    val sectionBgColor: Color.Hex?
    @JsName("section_header_text_color")
    val sectionHeaderTextColor: Color.Hex?
    @JsName("subtitle_text_color")
    val subtitleTextColor: Color.Hex?
    @JsName("destructive_text_color")
    val destructiveTextColor: Color.Hex?
    @JsName("section_separator_color")
    val sectionSeparatorColor: Color.Hex?
}
