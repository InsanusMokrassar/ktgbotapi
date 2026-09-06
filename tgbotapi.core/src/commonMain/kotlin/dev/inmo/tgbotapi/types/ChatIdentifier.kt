@file:Suppress("unused")

package dev.inmo.tgbotapi.types

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlin.jvm.JvmInline

/** Telegram URI-scheme prefix used for links handled by Telegram applications. */
const val internalTgAppLinksBeginning = "tg://"

/** Base URL used for public Telegram links. */
const val internalLinkBeginning = "https://t.me"

/** Telegram URI prefix used to open a user by numeric identifier. */
const val internalUserLinkBeginning = "${internalTgAppLinksBeginning}user?id="

/** Reserved Telegram username used as the base of managed-bot creation links. */
const val managedBotNewBotUsername = "newbot"

/** Identifies a Telegram chat either by numeric [IdChatIdentifier] or by [Username]. */
@Serializable(ChatIdentifierSerializer::class)
@ClassCastsIncluded
sealed interface ChatIdentifier

/**
 * Also used as User Identifier
 */
@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatIdentifierSerializer::class)
sealed interface IdChatIdentifier : ChatIdentifier {
    /** Numeric Telegram chat identifier. */
    val chatId: RawChatId

    /** Message thread carried as default request context, when available. */
    val threadId: MessageThreadId?
        get() = null

    /** Business connection carried as default request context, when available. */
    val businessConnectionId: BusinessConnectionId?
        get() = null

    /** Direct-message thread carried as default request context, when available. */
    val directMessageThreadId: DirectMessageThreadId?
        get() = null

    /** User receiving an ephemeral message, when available. */
    val receiverUser: UserId?
        get() = null

    /** Existing ephemeral message targeted by an operation, when available. */
    val ephemeralMessageId: EphemeralMessageId?
        get() = null

    companion object {
        /**
         * Creates [ChatIdWithThreadId] when [threadId] is present, [BusinessChatId] when only
         * [businessConnectionId] is present, or [ChatId] when both optional values are absent.
         * A non-null [threadId] takes precedence over [businessConnectionId].
         */
        operator fun invoke(chatId: RawChatId, threadId: MessageThreadId? = null, businessConnectionId: BusinessConnectionId? = null) = threadId ?.let {
            ChatIdWithThreadId(chatId, threadId)
        } ?: businessConnectionId ?.let {
            BusinessChatId(chatId, businessConnectionId)
        } ?: ChatId(chatId)

        /** Creates an identifier carrying the supplied [threadId]. */
        operator fun invoke(chatId: RawChatId, threadId: MessageThreadId) = ChatIdWithThreadId(chatId, threadId)

        /** Creates an identifier carrying the supplied [businessConnectionId]. */
        operator fun invoke(chatId: RawChatId, businessConnectionId: BusinessConnectionId) = BusinessChatId(chatId, businessConnectionId)

        /** Creates an identifier carrying ephemeral delivery context for [receiverUser]. */
        operator fun invoke(chatId: RawChatId, receiverUser: UserId, ephemeralMessageId: EphemeralMessageId? = null) = EphemeralChatId(chatId, receiverUser, ephemeralMessageId)
    }
}

/**
 * Numeric Telegram chat identifier without embedded request context.
 *
 * @property chatId Raw numeric identifier.
 */
@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatIdentifierSerializer::class)
@JvmInline
value class ChatId(override val chatId: RawChatId) : IdChatIdentifier

/**
 * Numeric Telegram chat identifier carrying a default message thread.
 *
 * @property chatIdWithThreadId Raw chat identifier and message-thread identifier pair.
 */
@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatIdentifierSerializer::class)
@JvmInline
value class ChatIdWithThreadId(val chatIdWithThreadId: Pair<RawChatId, MessageThreadId>) : IdChatIdentifier {
    override val chatId: RawChatId
        get() = chatIdWithThreadId.first
    override val threadId: MessageThreadId
        get() = chatIdWithThreadId.second

    /** Creates an identifier from separate [chatId] and [threadId] values. */
    constructor(chatId: RawChatId, threadId: MessageThreadId): this(chatId to threadId)
}

/**
 * Numeric Telegram chat identifier carrying a default channel direct-message thread.
 *
 * @property chatIdWithThreadId Raw chat identifier and direct-message-thread identifier pair.
 */
@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatIdentifierSerializer::class)
@JvmInline
value class ChatIdWithChannelDirectMessageThreadId(val chatIdWithThreadId: Pair<RawChatId, DirectMessageThreadId>) : IdChatIdentifier {
    override val chatId: RawChatId
        get() = chatIdWithThreadId.first
    override val directMessageThreadId: DirectMessageThreadId
        get() = chatIdWithThreadId.second

    /** Creates an identifier from separate [chatId] and [threadId] values. */
    constructor(chatId: RawChatId, threadId: DirectMessageThreadId): this(chatId to threadId)
}

/**
 * Numeric Telegram chat identifier carrying a default business connection.
 *
 * @property chatIdWithBusinessConnectionId Raw chat identifier and business-connection identifier pair.
 */
@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatIdentifierSerializer::class)
@JvmInline
value class BusinessChatId(val chatIdWithBusinessConnectionId: Pair<RawChatId, BusinessConnectionId>) : IdChatIdentifier {
    override val chatId: RawChatId
        get() = chatIdWithBusinessConnectionId.first
    override val businessConnectionId: BusinessConnectionId
        get() = chatIdWithBusinessConnectionId.second

    /** Creates an identifier from separate [chatId] and [businessConnectionId] values. */
    constructor(chatId: RawChatId, businessConnectionId: BusinessConnectionId): this(chatId to businessConnectionId)
}

/**
 * Chat id of group/supergroup additionally carrying ephemeral [receiverUser] (and optional [ephemeralMessageId]);
 * used to default `receiverUserId`/`ephemeralMessageId` params of ephemeral-related requests
 *
 * @property chatIdWithReceiverUserAndEphemeralMessageId Raw chat identifier, receiver identifier, and optional
 * ephemeral-message identifier triple.
 */
@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(ChatIdentifierSerializer::class)
@JvmInline
value class EphemeralChatId(
    val chatIdWithReceiverUserAndEphemeralMessageId: Triple<RawChatId, UserId, EphemeralMessageId?>
) : IdChatIdentifier {
    override val chatId: RawChatId
        get() = chatIdWithReceiverUserAndEphemeralMessageId.first
    override val receiverUser: UserId
        get() = chatIdWithReceiverUserAndEphemeralMessageId.second
    override val ephemeralMessageId: EphemeralMessageId?
        get() = chatIdWithReceiverUserAndEphemeralMessageId.third

    /** Creates an identifier from separate chat, receiver, and optional ephemeral-message identifiers. */
    constructor(chatId: RawChatId, receiverUser: UserId, ephemeralMessageId: EphemeralMessageId? = null): this(Triple(chatId, receiverUser, ephemeralMessageId))
}

/** Returns the embedded message thread, or `null` when no message thread is available. */
val ChatIdentifier.threadId: MessageThreadId?
    get() = (this as? IdChatIdentifier) ?.threadId

/** Returns the embedded channel direct-message thread, or `null` when no direct-message thread is available. */
val ChatIdentifier.directMessageThreadId: DirectMessageThreadId?
    get() = (this as? IdChatIdentifier) ?.directMessageThreadId

/** Returns the embedded business connection, or `null` when no business connection is available. */
val ChatIdentifier.businessConnectionId: BusinessConnectionId?
    get() = (this as? IdChatIdentifier) ?.businessConnectionId

/** Returns the embedded ephemeral-message receiver, or `null` when no receiver is available. */
val ChatIdentifier.receiverUser: UserId?
    get() = (this as? IdChatIdentifier) ?.receiverUser

/** Returns the embedded ephemeral-message identifier, or `null` when no identifier is available. */
val ChatIdentifier.ephemeralMessageId: EphemeralMessageId?
    get() = (this as? IdChatIdentifier) ?.ephemeralMessageId

/** Drops all embedded request context and returns a plain [ChatId]. */
fun IdChatIdentifier.toChatId() = when (this) {
    is ChatId -> this
    is ChatIdWithThreadId -> ChatId(chatId)
    is ChatIdWithChannelDirectMessageThreadId -> ChatId(chatId)
    is BusinessChatId -> ChatId(chatId)
    is EphemeralChatId -> ChatId(chatId)
}

/** Replaces embedded request context with the supplied message [threadId]. */
fun IdChatIdentifier.toChatWithThreadId(threadId: MessageThreadId) = IdChatIdentifier(chatId, threadId)

/** Replaces embedded request context with the supplied channel direct-message [threadId]. */
fun IdChatIdentifier.toChatIdWithChannelDirectMessageThreadId(threadId: DirectMessageThreadId) = ChatIdWithChannelDirectMessageThreadId(chatId, threadId)

/** Replaces embedded request context with the supplied [businessConnectionId]. */
fun IdChatIdentifier.toBusinessChatId(businessConnectionId: BusinessConnectionId) = IdChatIdentifier(chatId, businessConnectionId)

/** Replaces embedded request context with ephemeral delivery context for [receiverUser]. */
fun IdChatIdentifier.toEphemeralChatId(receiverUser: UserId, ephemeralMessageId: EphemeralMessageId? = null) = IdChatIdentifier(chatId, receiverUser, ephemeralMessageId)

/**
 * https://core.telegram.org/bots/api#formatting-options
 */
@Warning("This API have restrictions in Telegram System")
val RawChatId.userLink: String
    get() = "$internalUserLinkBeginning$this"
/**
 * https://core.telegram.org/bots/api#formatting-options
 */
@Warning("This API have restrictions in Telegram System")
val UserId.userLink: String
    get() = chatId.userLink

/** Telegram URI opening the represented [User]. */
val User.userLink: String
    get() = id.toChatId().userLink

/** Numeric chat identifier used where Telegram expects a user identifier. */
typealias UserId = IdChatIdentifier

/** Wraps a raw numeric identifier as [ChatId]. */
fun RawChatId.toChatId(): ChatId = ChatId(this)

/** Converts a [Long] value to [ChatId]. */
fun Long.toChatId(): ChatId = ChatId(RawChatId(this))

/** Converts an [Int] value to a numeric chat identifier. */
fun Int.toChatId(): IdChatIdentifier = RawChatId(toLong()).toChatId()

/** Converts a [Byte] value to a numeric chat identifier. */
fun Byte.toChatId(): IdChatIdentifier = RawChatId(toLong()).toChatId()

/**
 * A value class representing a username that always starts with the "@" symbol.
 *
 * This class is used to encapsulate the concept of a username, enforce its format,
 * and ensure consistency when dealing with usernames throughout the application.
 *
 * @property full The full username string, guaranteed to start with "@".
 * @throws IllegalArgumentException if the provided [full] value doesn't start with "@" during initialization.
 */
@Suppress("SERIALIZER_TYPE_INCOMPATIBLE", "RemoveRedundantQualifierName")
@Serializable(ChatIdentifierSerializer::class)
@JvmInline
value class Username (
    val full: String
) : ChatIdentifier {
    /**
     * Retrieves the full username as a string.
     *
     * This property provides the complete username, which is guaranteed to start with the "@" symbol.
     * It represents the raw value of the username, ensuring consistency and adherence to the required format.
     */
    val username: String
        get() = full
    /**
     * A property that returns the username string without the leading "@" symbol.
     *
     * This property removes any consecutive "@" symbols at the beginning of the `full` property
     * and provides the rest of the username as a plain string.
     */
    val withoutAt
        get() = full.dropWhile { it == '@' }

    init {
        if (!full.startsWith("@")) {
            throw IllegalArgumentException("Username must starts with `@`")
        }
    }

    override fun toString(): String {
        return full
    }

    companion object {
        /** Serializes [Username] without the leading `@` and accepts input with or without the prefix. */
        object WithoutAtSerializer : KSerializer<Username> {
            override val descriptor: SerialDescriptor = String.serializer().descriptor
            override fun deserialize(decoder: Decoder): Username = Username.prepare(decoder.decodeString())
            override fun serialize(encoder: Encoder, value: Username) = encoder.encodeString(value.withoutAt)
        }
        /**
         * Prepares a valid instance of [Username] by ensuring the given string starts with "@".
         *
         * @param full The input string representing the username. If the string does not start with "@",
         * it will be prefixed with "@".
         * @return A [Username] instance constructed using the provided or modified input string.
         */
        fun prepare(full: String): Username = if (full.startsWith("@")) {
            Username(full)
        } else {
            Username("@$full")
        }
    }
}

/** Converts a string with or without a leading `@` to [Username]. */
fun String.toUsername(): Username = Username.prepare(this)

/**
 * A custom serializer for the [ChatIdentifier] sealed interface.
 *
 * This serializer manages the conversion between the [ChatIdentifier] data structure
 * and its JSON representation, enabling compatibility with the serialization and deserialization
 * processes.
 *
 * It supports two primary types of [ChatIdentifier]: [IdChatIdentifier] and [Username].
 * - For [IdChatIdentifier], it serializes to a numeric ID (e.g., `Long`).
 * - For [Username], it serializes to a string prefixed with "@".
 *
 * Deserialization logic determines whether the input is a numeric ID or a string,
 * and converts it to the corresponding subtype of [ChatIdentifier].
 *
 * Marked with the [RiskFeature] annotation, this class may have certain limitations
 * and specific usage considerations tied to the underlying Telegram system.
 */
@RiskFeature
object ChatIdentifierSerializer : KSerializer<ChatIdentifier> {
    private val internalSerializer = JsonPrimitive.serializer()
    override val descriptor: SerialDescriptor = internalSerializer.descriptor
    override fun deserialize(decoder: Decoder): ChatIdentifier {
        val id = internalSerializer.deserialize(decoder)

        return id.longOrNull ?.let {
            ChatId(RawChatId(it))
        } ?: id.content.let {
            Username.prepare(it)
        }
    }

    override fun serialize(encoder: Encoder, value: ChatIdentifier) {
        when (value) {
            is IdChatIdentifier -> encoder.encodeLong(value.chatId.long)
            is Username -> encoder.encodeString(value.full)
        }
    }
}

/**
 * Serializes every [ChatIdentifier] subtype while preserving embedded request context.
 *
 * Numeric identifiers use the following JSON representations:
 * - [ChatId]: a JSON number;
 * - [ChatIdWithThreadId]: `"<chatId>/<threadId>"`;
 * - [ChatIdWithChannelDirectMessageThreadId]: `"<chatId>/cdm/<directMessageThreadId>"`;
 * - [BusinessChatId]: `"<chatId>//<businessConnectionId>"`;
 * - [EphemeralChatId]: `"<chatId>/eph/<receiverUserId>/<ephemeralMessageId>"`.
 *
 * [Username] uses the complete username string. Unlike [ChatIdentifierSerializer], numeric subtype context survives a
 * serialization round trip.
 */
@Suppress("unused")
@RiskFeature
object FullChatIdentifierSerializer : KSerializer<ChatIdentifier> {
    private val internalSerializer = JsonPrimitive.serializer()
    override val descriptor: SerialDescriptor = internalSerializer.descriptor
    override fun deserialize(decoder: Decoder): ChatIdentifier {
        val id = internalSerializer.deserialize(decoder)

        return id.longOrNull ?.let {
            ChatId(RawChatId(it))
        } ?:let {
            val splitted = id.content.split("/")
            when (splitted.size) {
                2 -> {
                    val (chatId, threadId) = splitted
                    ChatIdWithThreadId(
                        chatId.toLongOrNull() ?.let(::RawChatId) ?: return@let null,
                        threadId.toLongOrNull() ?.let(::MessageThreadId) ?: return@let null
                    )
                }
                3 -> {
                    val (chatId, intermediateDelimiter, additionalId) = splitted
                    val additionalIdAsLong by lazy {
                        additionalId.toLongOrNull()
                    }
                    when {
                        intermediateDelimiter == "cdm" && additionalIdAsLong != null -> ChatIdWithChannelDirectMessageThreadId(
                            chatId.toLongOrNull() ?.let(::RawChatId) ?: return@let null,
                            additionalIdAsLong ?.let(::DirectMessageThreadId) ?: return@let null
                        )
                        else -> BusinessChatId(
                            chatId.toLongOrNull() ?.let(::RawChatId) ?: return@let null,
                            additionalId.let(::BusinessConnectionId)
                        )
                    }
                }
                4 -> {
                    val (chatId, intermediateDelimiter, receiverUserChatId, ephemeralMessageId) = splitted
                    when (intermediateDelimiter) {
                        "eph" -> EphemeralChatId(
                            chatId.toLongOrNull() ?.let(::RawChatId) ?: return@let null,
                            receiverUserChatId.toLongOrNull() ?.let { ChatId(RawChatId(it)) } ?: return@let null,
                            ephemeralMessageId.toLongOrNull() ?.let(::EphemeralMessageId)
                        )
                        else -> null
                    }
                }
                else -> null
            }
        } ?: id.content.let {
            Username.prepare(it)
        }
    }

    override fun serialize(encoder: Encoder, value: ChatIdentifier) {
        when (value) {
            is ChatId -> encoder.encodeLong(value.chatId.long)
            is ChatIdWithThreadId -> encoder.encodeString("${value.chatId}/${value.threadId}")
            is BusinessChatId -> encoder.encodeString("${value.chatId}//${value.businessConnectionId}")
            is ChatIdWithChannelDirectMessageThreadId -> encoder.encodeString("${value.chatId}/cdm/${value.directMessageThreadId}")
            is EphemeralChatId -> encoder.encodeString("${value.chatId}/eph/${value.receiverUser.chatId}/${value.ephemeralMessageId?.long ?: ""}")
            is Username -> encoder.encodeString(value.full)
        }
    }
}
