package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature
inline fun textSourcesOrElse(
    textSources: TextSourcesList,
    block: () -> TextSourcesList
) = dev.inmo.tgbotapi.types.message.textsources.textSourcesOrElse(textSources, block)

@RiskFeature
inline fun textSourcesOrElseTextSource(
    textSources: TextSourcesList,
    block: () -> TextSource
) = dev.inmo.tgbotapi.types.message.textsources.textSourcesOrElseTextSource(textSources, block)
