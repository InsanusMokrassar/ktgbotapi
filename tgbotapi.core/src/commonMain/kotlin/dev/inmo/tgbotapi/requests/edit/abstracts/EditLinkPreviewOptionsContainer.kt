package dev.inmo.tgbotapi.requests.edit.abstracts

import dev.inmo.tgbotapi.abstracts.types.LinkPreviewOptionsContainer

interface EditLinkPreviewOptionsContainer : LinkPreviewOptionsContainer

@Deprecated("Renamed", ReplaceWith("EditLinkPreviewOptionsContainer", "dev.inmo.tgbotapi.requests.edit.abstracts.EditLinkPreviewOptionsContainer"))
typealias EditDisableWebPagePreviewMessage = EditLinkPreviewOptionsContainer