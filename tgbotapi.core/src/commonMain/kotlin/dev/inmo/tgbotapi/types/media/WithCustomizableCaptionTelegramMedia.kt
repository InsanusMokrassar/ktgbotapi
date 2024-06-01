package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.abstracts.WithCustomizableCaption

sealed interface WithCustomizableCaptionTelegramMedia : TelegramMedia, WithCustomizableCaption
