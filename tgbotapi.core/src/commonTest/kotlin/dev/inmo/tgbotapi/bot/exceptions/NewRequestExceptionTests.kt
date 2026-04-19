package dev.inmo.tgbotapi.bot.exceptions

import dev.inmo.tgbotapi.types.Response
import kotlin.test.Test
import kotlin.test.assertIs

/**
 * Regression tests for https://github.com/InsanusMokrassar/TelegramBotAPI/issues/1008 —
 * Telegram's "Too Many Requests" response is case-inconsistent (both
 * `Too Many Requests` and `too Many Requests` have been observed), so the
 * description match must be case-insensitive.
 */
class NewRequestExceptionTests {
    private fun buildException(description: String) = newRequestException(
        response = Response(ok = false, description = description, errorCode = 429),
        plainAnswer = "{\"ok\":false}"
    )

    @Test
    fun `TooMuchRequestsException is created for canonical casing`() {
        assertIs<TooMuchRequestsException>(
            buildException("Bad Request: Too Many Requests: retry after 8")
        )
    }

    @Test
    fun `TooMuchRequestsException is created for lowercase first-letter casing`() {
        assertIs<TooMuchRequestsException>(
            buildException("Bad Request: too Many Requests: retry after 8")
        )
    }

    @Test
    fun `TooMuchRequestsException is created for all-lowercase casing`() {
        assertIs<TooMuchRequestsException>(
            buildException("Bad Request: too many requests: retry after 8")
        )
    }
}
