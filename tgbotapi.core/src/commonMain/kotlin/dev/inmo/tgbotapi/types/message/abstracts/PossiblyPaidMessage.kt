package dev.inmo.tgbotapi.types.message.abstracts

interface PossiblyPaidMessage : Message {
    val cost: Int?
}