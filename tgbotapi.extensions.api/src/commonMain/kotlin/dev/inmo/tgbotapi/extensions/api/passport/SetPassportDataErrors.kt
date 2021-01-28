package dev.inmo.tgbotapi.extensions.api.passport

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.SetPassportDataErrors
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.passport.PassportElementError
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.utils.passport.Decryptor

suspend fun TelegramBot.setPassportDataErrors(
    userId: UserId,
    errors: List<PassportElementError>
) = execute(SetPassportDataErrors(userId, errors))
suspend fun TelegramBot.setPassportDataErrors(
    user: User,
    errors: List<PassportElementError>
) = setPassportDataErrors(user.id, errors)

suspend fun TelegramBot.setPassportDataErrors(
    userId: UserId,
    passportData: PassportData,
    decryptor: Decryptor,
    mapper: suspend Decryptor.(EncryptedPassportElement) -> PassportElementError
): Boolean = setPassportDataErrors(
    userId,
    passportData.data.map { decryptor.mapper(it) }.also {
        if (it.isEmpty()) {
            return@setPassportDataErrors false
        }
    }
)
suspend fun TelegramBot.setPassportDataErrors(
    user: User,
    passportData: PassportData,
    decryptor: Decryptor,
    mapper: suspend Decryptor.(EncryptedPassportElement) -> PassportElementError
) = setPassportDataErrors(user.id, passportData, decryptor, mapper)

suspend fun TelegramBot.setPassportDataErrors(
    passportMessage: PassportMessage,
    decryptor: Decryptor,
    mapper: suspend Decryptor.(EncryptedPassportElement) -> PassportElementError
) = setPassportDataErrors(passportMessage.user, passportMessage.passportData, decryptor, mapper)
