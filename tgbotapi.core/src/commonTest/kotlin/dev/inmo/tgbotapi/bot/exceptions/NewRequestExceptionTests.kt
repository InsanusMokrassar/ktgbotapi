package dev.inmo.tgbotapi.bot.exceptions

import dev.inmo.tgbotapi.types.Response
import kotlin.test.Test
import kotlin.test.assertIs

/**
 * Regression tests for [newRequestException] description matching.
 *
 * https://github.com/InsanusMokrassar/TelegramBotAPI/issues/1008 —
 * Telegram's "Too Many Requests" response is case-inconsistent (both
 * `Too Many Requests` and `too Many Requests` have been observed), so the
 * description match must be case-insensitive.
 *
 * Telegram has also changed the wording of the missing reply error over time:
 * `reply message not found`, `replied message not found` and, in current Bot API
 * versions, `message to be replied not found` should all be recognized.
 */
class NewRequestExceptionTests {
    private fun buildException(description: String, errorCode: Int = 429) = newRequestException(
        response = Response(ok = false, description = description, errorCode = errorCode),
        plainAnswer = "{\"ok\":false}"
    )

    @Test
    fun tooMuchRequestsExceptionIsCreatedForCanonicalCasing() {
        assertIs<TooMuchRequestsException>(
            buildException("Bad Request: Too Many Requests: retry after 8")
        )
    }

    @Test
    fun tooMuchRequestsExceptionIsCreatedForLowercaseFirstLetterCasing() {
        assertIs<TooMuchRequestsException>(
            buildException("Bad Request: too Many Requests: retry after 8")
        )
    }

    @Test
    fun tooMuchRequestsExceptionIsCreatedForAllLowercaseCasing() {
        assertIs<TooMuchRequestsException>(
            buildException("Bad Request: too many requests: retry after 8")
        )
    }

    @Test
    fun replyMessageNotFoundExceptionIsCreatedForLegacyReplyWording() {
        assertIs<ReplyMessageNotFoundException>(
            buildException("Bad Request: reply message not found", errorCode = 400)
        )
    }

    @Test
    fun replyMessageNotFoundExceptionIsCreatedForLegacyRepliedWording() {
        assertIs<ReplyMessageNotFoundException>(
            buildException("Bad Request: replied message not found", errorCode = 400)
        )
    }

    @Test
    fun replyMessageNotFoundExceptionIsCreatedForCurrentWording() {
        assertIs<ReplyMessageNotFoundException>(
            buildException("Bad Request: message to be replied not found", errorCode = 400)
        )
    }
}
