@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.inmo.tgbotapi.bot.exceptions

import korlibs.time.DateTime
import dev.inmo.tgbotapi.types.Response
import dev.inmo.tgbotapi.types.RetryAfterError
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
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
        description.contains("Too Many Requests", ignoreCase = true) -> TooMuchRequestsException(
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
        response.errorCode != null -> ApiException(
            response.errorCode,
            plainAnswer,
            response
        )
        else -> null
    }
} ?: CommonRequestException(response, plainAnswer, message, cause)

@ClassCastsIncluded
sealed class BotException(
    override val message: String = "Something went wrong",
    cause: Throwable? = null,
    open val response: Response? = null,
    open val plainAnswer: String? = null,
) : IOException(message, cause), CopyableThrowable<BotException>

sealed class CommonBotException(
    message: String = "Something went wrong",
    cause: Throwable? = null,
    response: Response? = null,
    plainAnswer: String? = null,
) : BotException(message, cause, response, plainAnswer) {
    class Default(message: String = "Something went wrong", cause: Throwable? = null) : CommonBotException(message, cause) {
        override fun createCopy(): Default = Default(message, cause)
    }

    abstract override fun createCopy(): CommonBotException?

    companion object {
        operator fun invoke(message: String = "Something went wrong", cause: Throwable? = null) = Default(message, cause)
    }
}
class ApiException(
    val httpResponseCode: Int?,
    val plainResponse: String,
    response: Response? = null,
) :
    CommonBotException("$httpResponseCode: $plainResponse", null, response, plainResponse) {
    override fun createCopy(): ApiException = ApiException(httpResponseCode, plainResponse, response)
}

sealed class RequestException (
    override val response: Response,
    override val plainAnswer: String,
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
