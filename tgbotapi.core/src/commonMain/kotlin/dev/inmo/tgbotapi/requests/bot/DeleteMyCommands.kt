package dev.inmo.tgbotapi.requests.bot

import dev.inmo.micro_utils.language_codes.IetfLang
import dev.inmo.micro_utils.language_codes.IetfLangSerializer

import dev.inmo.tgbotapi.types.commands.*
import dev.inmo.tgbotapi.types.languageCodeField
import dev.inmo.tgbotapi.types.scopeField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
data class DeleteMyCommands(
    @SerialName(scopeField)
    @Serializable(BotCommandScopeSerializer::class)
    override val scope: BotCommandScope = BotCommandScopeDefault,
    @SerialName(languageCodeField)
    @Serializable(IetfLangSerializer::class)
    override val ietfLanguageCode: IetfLang? = null
) : MyCommandsRequest<Unit> {
    override fun method(): String  = "deleteMyCommands"
    override val requestSerializer: SerializationStrategy<DeleteMyCommands>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer

    constructor(
        scope: BotCommandScope = BotCommandScopeDefault,
        languageCode: String?
    ) : this(
        scope,
        languageCode ?.let(::IetfLang)
    )

    companion object : MyCommandsRequest<Unit> by DeleteMyCommands()
}
