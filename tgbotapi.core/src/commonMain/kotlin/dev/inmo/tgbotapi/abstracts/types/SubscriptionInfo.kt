package dev.inmo.tgbotapi.abstracts.types

import korlibs.time.TimeSpan

interface SubscriptionInfo : SubscriptionPeriodInfo {
    val subscriptionPrice: UInt?
}