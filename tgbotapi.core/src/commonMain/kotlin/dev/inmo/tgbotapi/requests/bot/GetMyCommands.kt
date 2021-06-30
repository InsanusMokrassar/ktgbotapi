package dev.inmo.tgbotapi.requests.bot

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
    override val languageCode: String? = null
) : MyCommandsRequest<List<BotCommand>> {
    override fun method(): String = "getMyCommands"
    override val resultDeserializer: DeserializationStrategy<List<BotCommand>>
        get() = getMyCommandsSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    companion object : MyCommandsRequest<List<BotCommand>> by GetMyCommands()
}
