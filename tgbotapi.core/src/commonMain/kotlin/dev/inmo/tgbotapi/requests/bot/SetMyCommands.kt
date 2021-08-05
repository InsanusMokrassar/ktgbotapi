package dev.inmo.tgbotapi.requests.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.micro_utils.language_codes.IetfLanguageCodeSerializer
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.commands.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
class SetMyCommands(
    @SerialName(botCommandsField)
    val commands: List<BotCommand>,
    @SerialName(scopeField)
    @Serializable(BotCommandScopeSerializer::class)
    override val scope: BotCommandScope = BotCommandScopeDefault,
    @SerialName(languageCodeField)
    @Serializable(IetfLanguageCodeSerializer::class)
    override val ietfLanguageCode: IetfLanguageCode? = null
) : MyCommandsRequest<Boolean> {
    override fun method(): String = "setMyCommands"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    constructor(
        commands: List<BotCommand>,
        scope: BotCommandScope = BotCommandScopeDefault,
        languageCode: String?
    ) : this(
        commands,
        scope,
        languageCode ?.let(::IetfLanguageCode)
    )

    init {
        if (commands.size !in botCommandsLimit) {
            error("Bot commands list size able to be in range $botCommandsLimit, but incoming size is ${commands.size}")
        }
    }
}
