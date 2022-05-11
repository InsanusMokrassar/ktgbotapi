package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.Seconds

interface Livable {
    /**
     * Period in [Seconds]
     */
    val livePeriod: Seconds?
}
