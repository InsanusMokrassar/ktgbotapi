package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.Meters

interface ProximityAlertable {
    val proximityAlertRadius: Meters?
}