package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import kotlin.js.JsName
import kotlin.jvm.JvmName

@RiskFeature
inline fun textSourcesOrElse(
    textSources: TextSourcesList,
    block: () -> TextSourcesList
): TextSourcesList = textSources.takeIf { it.isNotEmpty() } ?: block()

@RiskFeature
inline fun textSourcesOrElseTextSource(
    textSources: TextSourcesList,
    block: () -> TextSource
): TextSourcesList = textSources.takeIf { it.isNotEmpty() } ?: listOf(block())
