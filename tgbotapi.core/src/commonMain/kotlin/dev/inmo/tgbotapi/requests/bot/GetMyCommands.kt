package dev.inmo.tgbotapi.requests.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.micro_utils.language_codes.IetfLanguageCodeSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.commands.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer

private val getMyCommandsSerializer = ListSerializer(BotCommand.serializer())

@Serializable
data class GetMyCommands(
    @SerialName(scopeField)
    @Serializable(BotCommandScopeSerializer::class)
    override val scope: BotCommandScope = BotCommandScopeDefault,
    @SerialName(languageCodeField)
    @Serializable(IetfLanguageCodeSerializer::class)
    override val ietfLanguageCode: IetfLanguageCode? = null
) : MyCommandsRequest<List<BotCommand>> {
    override fun method(): String = "getMyCommands"
    override val resultDeserializer: DeserializationStrategy<List<BotCommand>>
        get() = getMyCommandsSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    constructor(
        scope: BotCommandScope = BotCommandScopeDefault,
        languageCode: String?
    ) : this(
        scope,
        languageCode ?.let(::IetfLanguageCode)
    )

    companion object : MyCommandsRequest<List<BotCommand>> by GetMyCommands()
}
