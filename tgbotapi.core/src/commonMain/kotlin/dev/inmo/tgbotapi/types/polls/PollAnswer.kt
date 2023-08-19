package dev.inmo.tgbotapi.types.polls

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.ChannelChat
import dev.inmo.tgbotapi.types.chat.CommonBot
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(PollAnswer.Companion::class)
sealed interface PollAnswer: FromUser {
    val pollId: PollIdentifier
    override val user: User
    val chosen: List<Int>
    @Transient
    override val from: User
        get() = user

    @Serializable
    data class Common(
        @SerialName(pollIdField)
        override val pollId: PollIdentifier,
        @SerialName(userField)
        override val user: User,
        @SerialName(optionIdsField)
        override val chosen: List<Int>,
    ) : PollAnswer

    @Serializable
    data class InChannel(
        @SerialName(pollIdField)
        override val pollId: PollIdentifier,
        @SerialName(voterChatField)
        val voterChat: ChannelChat,
        @SerialName(optionIdsField)
        override val chosen: List<Int>
    ) : PollAnswer {
        @SerialName(userField)
        override val user: User = defaultUser

        companion object {
            val defaultUser = CommonBot(
                UserId(136817688L),
                "",
                "",
                Username("@Channel_Bot")
            )
        }
    }

    companion object : KSerializer<PollAnswer> {
        @Serializable
        private data class PollAnswerSurrogate(
            @SerialName(pollIdField)
            val pollId: PollIdentifier,
            @SerialName(userField)
            val user: User,
            @SerialName(optionIdsField)
            val chosen: List<Int>,
            @SerialName(voterChatField)
            val voterChat: ChannelChat?
        )
        operator fun invoke(
            pollId: PollIdentifier,
            user: User,
            chosen: List<Int>,
        ) = Common(pollId, user, chosen)

        override val descriptor: SerialDescriptor
            get() = PollAnswerSurrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): PollAnswer {
            val surrogate = PollAnswerSurrogate.serializer().deserialize(decoder)
            return if (surrogate.voterChat != null) {
                InChannel(surrogate.pollId, surrogate.voterChat, surrogate.chosen)
            } else {
                Common(surrogate.pollId, surrogate.user, surrogate.chosen)
            }
        }

        override fun serialize(encoder: Encoder, value: PollAnswer) {
            PollAnswerSurrogate.serializer().serialize(
                encoder,
                PollAnswerSurrogate(
                    value.pollId,
                    value.user,
                    value.chosen,
                    (value as? InChannel) ?.voterChat
                )
            )
        }
    }
}
