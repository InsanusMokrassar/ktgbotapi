package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.message.SuggestedPostParameters

interface OptionallyPaidPost {
    val suggestedPostParameters: SuggestedPostParameters?
}