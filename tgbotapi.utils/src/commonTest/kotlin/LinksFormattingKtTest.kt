import dev.inmo.tgbotapi.extensions.utils.formatting.managedBotCreationLink
import dev.inmo.tgbotapi.types.Username
import io.ktor.http.encodeURLQueryComponent
import kotlin.test.Test
import kotlin.test.assertEquals

class LinksFormattingKtTest {

    /**
     * Tests for the `managedBotCreationLink` function.
     * These tests verify that the function generates Telegram bot creation links correctly for various inputs.
     */

    @Test
    fun testManagedBotCreationLinkWithStringsWithoutSuggestedName() {
        // Arrange
        val managerBotUsername = "managerBot"
        val suggestedUsername = "testBot"

        // Act
        val result = managedBotCreationLink(managerBotUsername, suggestedUsername)

        // Assert
        val expected = "https://t.me/newbot/managerBot/testBot"
        assertEquals(expected, result)
    }

    @Test
    fun testManagedBotCreationLinkWithStringsWithSuggestedName() {
        // Arrange
        val managerBotUsername = "managerBot"
        val suggestedUsername = "testBot"
        val suggestedName = "Test Bot".encodeURLQueryComponent()

        // Act
        val result = managedBotCreationLink(managerBotUsername, suggestedUsername, suggestedName)

        // Assert
        val expected = "https://t.me/newbot/managerBot/testBot?name=${suggestedName.encodeURLQueryComponent()}"
        assertEquals(expected, result)
    }

    @Test
    fun testManagedBotCreationLinkWithUsernamesWithoutSuggestedName() {
        // Arrange
        val managerBotUsername = Username("@managerBot")
        val suggestedUsername = Username("@testBot")

        // Act
        val result = managedBotCreationLink(managerBotUsername, suggestedUsername)

        // Assert
        val expected = "https://t.me/newbot/managerBot/testBot"
        assertEquals(expected, result)
    }

    @Test
    fun testManagedBotCreationLinkWithUsernamesWithSuggestedName() {
        // Arrange
        val managerBotUsername = Username("@managerBot")
        val suggestedUsername = Username("@testBot")
        val suggestedName = "Test Bot"

        // Act
        val result = managedBotCreationLink(managerBotUsername, suggestedUsername, suggestedName)

        // Assert
        val expected = "https://t.me/newbot/managerBot/testBot?name=${suggestedName.encodeURLQueryComponent()}"
        assertEquals(expected, result)
    }

    @Test
    fun testManagedBotCreationLinkMixedStringAndUsernameWithoutSuggestedName() {
        // Arrange
        val managerBotUsername = "managerBot"
        val suggestedUsername = Username("@testBot")

        // Act
        val result = managedBotCreationLink(managerBotUsername, suggestedUsername)

        // Assert
        val expected = "https://t.me/newbot/managerBot/testBot"
        assertEquals(expected, result)
    }

    @Test
    fun testManagedBotCreationLinkMixedUsernameAndStringWithSuggestedName() {
        // Arrange
        val managerBotUsername = Username("@managerBot")
        val suggestedUsername = "testBot"
        val suggestedName = "Test Bot"

        // Act
        val result = managedBotCreationLink(managerBotUsername, suggestedUsername, suggestedName)

        // Assert
        val expected = "https://t.me/newbot/managerBot/testBot?name=${suggestedName.encodeURLQueryComponent()}"
        assertEquals(expected, result)
    }

    @Test
    fun testManagedBotCreationLinkWithSpecialCharactersInSuggestedName() {
        // Arrange
        val managerBotUsername = "managerBot"
        val suggestedUsername = "testBot"
        val suggestedName = "Test Bot & Co."

        // Act
        val result = managedBotCreationLink(managerBotUsername, suggestedUsername, suggestedName)

        // Assert
        val expected = "https://t.me/newbot/managerBot/testBot?name=${suggestedName.encodeURLQueryComponent()}"
        assertEquals(expected, result)
    }
}