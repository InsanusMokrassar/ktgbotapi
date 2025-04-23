package dev.inmo.tgbotapi.extensions.api.passport

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.SetPassportDataErrors
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.passport.PassportElementError
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.utils.passport.Decryptor

public suspend fun TelegramBot.setPassportDataErrors(
    userId: UserId,
    errors: List<PassportElementError>,
): Boolean = execute(SetPassportDataErrors(userId, errors))

public suspend fun TelegramBot.setPassportDataErrors(
    user: User,
    errors: List<PassportElementError>,
): Boolean = setPassportDataErrors(user.id, errors)

public suspend fun TelegramBot.setPassportDataErrors(
    userId: UserId,
    passportData: PassportData,
    decryptor: Decryptor,
    mapper: suspend Decryptor.(EncryptedPassportElement) -> PassportElementError,
): Boolean = setPassportDataErrors(
    userId,
    passportData.data.map { decryptor.mapper(it) }.also {
        if (it.isEmpty()) {
            return@setPassportDataErrors false
        }
    },
)

public suspend fun TelegramBot.setPassportDataErrors(
    user: User,
    passportData: PassportData,
    decryptor: Decryptor,
    mapper: suspend Decryptor.(EncryptedPassportElement) -> PassportElementError,
): Boolean = setPassportDataErrors(user.id, passportData, decryptor, mapper)

public suspend fun TelegramBot.setPassportDataErrors(
    passportMessage: PassportMessage,
    decryptor: Decryptor,
    mapper: suspend Decryptor.(EncryptedPassportElement) -> PassportElementError,
): Boolean = setPassportDataErrors(passportMessage.user, passportMessage.passportData, decryptor, mapper)
