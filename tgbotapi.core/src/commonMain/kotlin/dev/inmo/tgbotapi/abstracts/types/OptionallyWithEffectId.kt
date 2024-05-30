package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.EffectId

interface OptionallyWithEffectId {
    val effectId: EffectId?
}