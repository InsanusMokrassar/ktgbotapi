package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import java.io.*
import java.net.URL

fun PathedFile.asStream(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper
): InputStream = URL(this.fullUrl(telegramAPIUrlsKeeper)).openStream()
