package dev.inmo.tgbotapi.requests.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.micro_utils.language_codes.IetfLanguageCodeSerializer
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.abstracts.WithOptionalLanguageCode
import dev.inmo.tgbotapi.types.commands.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
class GetMyDescription(
    @SerialName(languageCodeField)
    @Serializable(IetfLanguageCodeSerializer::class)
    override val ietfLanguageCode: IetfLanguageCode? = null
) : SimpleRequest<BotDescription>, WithOptionalLanguageCode {
    override fun method(): String = "getMyDescription"
    override val resultDeserializer: DeserializationStrategy<BotDescription>
        get() = BotDescription.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
