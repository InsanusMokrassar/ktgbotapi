package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.LinkPreviewOptions

interface LinkPreviewOptionsContainer {
    val linkPreviewOptions: LinkPreviewOptions?
    val disableWebPagePreview: Boolean?
        get() = linkPreviewOptions ?.isDisabled != true
}

@Deprecated("Renamed", ReplaceWith("LinkPreviewOptionsContainer", "dev.inmo.tgbotapi.abstracts.types.LinkPreviewOptionsContainer"))
typealias DisableWebPagePreview = LinkPreviewOptionsContainer
