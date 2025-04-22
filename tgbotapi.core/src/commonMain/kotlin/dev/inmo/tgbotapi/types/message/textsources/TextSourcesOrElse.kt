package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature
inline fun textSourcesOrElse(
    textSources: TextSourcesList,
    block: () -> TextSourcesList,
): TextSourcesList = textSources.takeIf { it.isNotEmpty() } ?: block()

@RiskFeature
inline fun textSourcesOrElseTextSource(
    textSources: TextSourcesList,
    block: () -> TextSource,
): TextSourcesList = textSources.takeIf { it.isNotEmpty() } ?: listOf(block())
