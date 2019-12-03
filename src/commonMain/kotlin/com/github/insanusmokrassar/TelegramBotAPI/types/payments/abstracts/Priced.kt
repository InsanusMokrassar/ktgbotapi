package com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.payments.LabeledPrice

interface Priced {
    val prices: List<LabeledPrice>
}