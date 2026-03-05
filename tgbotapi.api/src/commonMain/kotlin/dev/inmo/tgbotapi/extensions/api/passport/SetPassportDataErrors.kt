package dev.inmo.tgbotapi.extensions.api.passport

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.SetPassportDataErrors
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.passport.PassportElementError
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.utils.passport.Decryptor

public suspend fun TelegramBot.setPassportDataErrors(
    userId: UserId,
    errors: List<PassportElementError>
): Unit = execute(SetPassportDataErrors(userId, errors))
public suspend fun TelegramBot.setPassportDataErrors(
    user: User,
    errors: List<PassportElementError>
): Unit = setPassportDataErrors(user.id, errors)

public suspend fun TelegramBot.setPassportDataErrors(
    userId: UserId,
    passportData: PassportData,
    decryptor: Decryptor,
    mapper: suspend Decryptor.(EncryptedPassportElement) -> PassportElementError
): Unit = setPassportDataErrors(
    userId,
    passportData.data.map { decryptor.mapper(it) }.also {
        if (it.isEmpty()) {
            return@setPassportDataErrors
        }
    }
)
public suspend fun TelegramBot.setPassportDataErrors(
    user: User,
    passportData: PassportData,
    decryptor: Decryptor,
    mapper: suspend Decryptor.(EncryptedPassportElement) -> PassportElementError
): Unit = setPassportDataErrors(user.id, passportData, decryptor, mapper)

public suspend fun TelegramBot.setPassportDataErrors(
    passportMessage: PassportMessage,
    decryptor: Decryptor,
    mapper: suspend Decryptor.(EncryptedPassportElement) -> PassportElementError
): Unit = setPassportDataErrors(passportMessage.user, passportMessage.passportData, decryptor, mapper)
