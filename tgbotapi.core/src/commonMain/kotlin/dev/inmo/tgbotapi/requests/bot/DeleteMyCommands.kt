package dev.inmo.tgbotapi.requests.bot

import dev.inmo.micro_utils.language_codes.IetfLanguageCode
import dev.inmo.micro_utils.language_codes.IetfLanguageCodeSerializer
import dev.inmo.tgbotapi.types.commands.*
import dev.inmo.tgbotapi.types.languageCodeField
import dev.inmo.tgbotapi.types.scopeField
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeleteMyCommands(
    @SerialName(scopeField)
    @Serializable(BotCommandScopeSerializer::class)
    override val scope: BotCommandScope = BotCommandScopeDefault,
    @SerialName(languageCodeField)
    @Serializable(IetfLanguageCodeSerializer::class)
    override val ietfLanguageCode: IetfLanguageCode? = null
) : MyCommandsRequest<Boolean> {
    override fun method(): String  = "deleteMyCommands"
    override val requestSerializer: SerializationStrategy<DeleteMyCommands>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()

    constructor(
        scope: BotCommandScope = BotCommandScopeDefault,
        languageCode: String?
    ) : this(
        scope,
        languageCode ?.let(::IetfLanguageCode)
    )

    companion object : MyCommandsRequest<Boolean> by DeleteMyCommands()
}
