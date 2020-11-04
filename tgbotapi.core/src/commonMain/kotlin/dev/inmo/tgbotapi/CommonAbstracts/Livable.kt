package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.Seconds

interface Livable {
    /**
     * Period in [Seconds]
     */
    val livePeriod: Seconds?
}