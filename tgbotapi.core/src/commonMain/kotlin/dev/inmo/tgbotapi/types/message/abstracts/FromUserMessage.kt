package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.abstracts.OptionallyFromUser

interface OptionallyFromUserMessage : OptionallyFromUser, AccessibleMessage

interface FromUserMessage : OptionallyFromUserMessage, FromUser
