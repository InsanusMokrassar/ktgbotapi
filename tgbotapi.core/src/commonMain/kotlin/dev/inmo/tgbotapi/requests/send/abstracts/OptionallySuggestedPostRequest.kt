package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.types.message.SuggestedPostParameters

interface OptionallySuggestedPostRequest {
    val suggestedPostParameters: SuggestedPostParameters?
}
