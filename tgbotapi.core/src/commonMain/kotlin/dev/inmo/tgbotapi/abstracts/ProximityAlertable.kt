package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.Meters

interface ProximityAlertable {
    val proximityAlertRadius: Meters?
}
