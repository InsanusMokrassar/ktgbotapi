package dev.inmo.tgbotapi.requests.bot

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
    override val languageCode: String? = null
) : MyCommandsRequest<Boolean> {
    override fun method(): String  = "deleteMyCommands"
    override val requestSerializer: SerializationStrategy<DeleteMyCommands> = serializer()
    override val resultDeserializer: DeserializationStrategy<Boolean> = Boolean.serializer()

    companion object : MyCommandsRequest<Boolean> by DeleteMyCommands()
}
