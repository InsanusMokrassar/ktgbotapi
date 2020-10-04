package dev.inmo.tgbotapi.types.payments.abstracts

import dev.inmo.tgbotapi.types.payments.LabeledPrice

interface Priced {
    val prices: List<LabeledPrice>
}