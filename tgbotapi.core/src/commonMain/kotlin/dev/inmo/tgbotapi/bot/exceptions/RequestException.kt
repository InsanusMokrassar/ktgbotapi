@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.inmo.tgbotapi.bot.exceptions

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.Response
import dev.inmo.tgbotapi.types.RetryAfterError
import kotlinx.coroutines.CopyableThrowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.io.IOException

fun newRequestException(
    response: Response,
    plainAnswer: String,
    message: String? = null,
    cause: Throwable? = null
) = response.description ?.let { description ->
    when {
        description == "Bad Request: reply message not found" || description == "Bad Request: replied message not found" -> ReplyMessageNotFoundException(response, plainAnswer, message, cause)
        description == "Bad Request: message to edit not found" -> MessageToEditNotFoundException(response, plainAnswer, message, cause)
        description.contains("Bad Request: message is not modified") -> MessageIsNotModifiedException(response, plainAnswer, message, cause)
        description == "Unauthorized" -> UnauthorizedException(response, plainAnswer, message, cause)
        description.contains("PHOTO_INVALID_DIMENSIONS") -> InvalidPhotoDimensionsException(response, plainAnswer, message, cause)
        description.contains("wrong file identifier") -> WrongFileIdentifierException(response, plainAnswer, message, cause)
        description.contains("Too Many Requests") -> TooMuchRequestsException(
            (response.parameters ?.error as? RetryAfterError) ?: RetryAfterError(60, DateTime.now().unixMillisLong),
            response,
            plainAnswer,
            message,
            cause
        )
        description.contains("Conflict: terminated by other getUpdates request") -> GetUpdatesConflict(
            response,
            plainAnswer,
            message,
            cause
        )
        else -> null
    }
} ?: CommonRequestException(response, plainAnswer, message, cause)

sealed class BotException(message: String = "Something went wrong", cause: Throwable? = null) : IOException(message, cause), CopyableThrowable<BotException>

class CommonBotException(message: String = "Something went wrong", cause: Throwable? = null) : BotException(message, cause) {
    override fun createCopy(): BotException = CommonBotException(message!!, cause)
}

sealed class RequestException (
    val response: Response,
    val plainAnswer: String,
    message: String? = null,
    cause: Throwable? = null
) : BotException(
    message ?: "Something went wrong",
    cause
)

class CommonRequestException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause) {
    override fun createCopy(): BotException = CommonRequestException(response, plainAnswer, message, cause)
}

class UnauthorizedException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause) {
    override fun createCopy(): BotException = UnauthorizedException(response, plainAnswer, message, cause)
}

class ReplyMessageNotFoundException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause) {
    override fun createCopy(): BotException = ReplyMessageNotFoundException(response, plainAnswer, message, cause)
}

class MessageIsNotModifiedException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause) {
    override fun createCopy(): BotException = MessageIsNotModifiedException(response, plainAnswer, message, cause)
}

class MessageToEditNotFoundException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause) {
    override fun createCopy(): BotException = MessageToEditNotFoundException(response, plainAnswer, message, cause)
}

class InvalidPhotoDimensionsException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause) {
    override fun createCopy(): BotException = InvalidPhotoDimensionsException(response, plainAnswer, message, cause)
}

class WrongFileIdentifierException(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause) {
    override fun createCopy(): BotException = WrongFileIdentifierException(response, plainAnswer, message, cause)
}

class TooMuchRequestsException(val retryAfter: RetryAfterError, response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause) {
    override fun createCopy(): BotException = TooMuchRequestsException(retryAfter, response, plainAnswer, message, cause)
}

class GetUpdatesConflict(response: Response, plainAnswer: String, message: String?, cause: Throwable?) :
    RequestException(response, plainAnswer, message, cause) {
    override fun createCopy(): BotException = GetUpdatesConflict(response, plainAnswer, message, cause)
}
