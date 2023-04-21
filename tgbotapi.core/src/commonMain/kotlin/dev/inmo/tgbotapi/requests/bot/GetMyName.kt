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
class GetMyName(
    @SerialName(languageCodeField)
    @Serializable(IetfLanguageCodeSerializer::class)
    override val ietfLanguageCode: IetfLanguageCode? = null
) : SimpleRequest<BotName>, WithOptionalLanguageCode {
    override fun method(): String = "getMyName"
    override val resultDeserializer: DeserializationStrategy<BotName>
        get() = BotName.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
