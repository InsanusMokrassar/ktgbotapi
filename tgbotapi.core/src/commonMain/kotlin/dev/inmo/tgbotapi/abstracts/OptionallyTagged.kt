package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.UserTag

interface OptionallyTagged {
    val tag: UserTag?
}