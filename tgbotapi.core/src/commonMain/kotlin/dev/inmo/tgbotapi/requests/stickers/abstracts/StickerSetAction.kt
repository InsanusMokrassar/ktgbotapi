package dev.inmo.tgbotapi.requests.stickers.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.StickerSetName
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer

interface StickerSetAction : SimpleRequest<Unit> {
    val name: StickerSetName

    override val resultDeserializer: KSerializer<Unit>
        get() = UnitFromBooleanSerializer
}
