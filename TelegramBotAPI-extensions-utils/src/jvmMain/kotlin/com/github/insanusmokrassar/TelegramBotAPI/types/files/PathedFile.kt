package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URL

fun PathedFile.asStream(
        telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper
): InputStream = URL(this.fullUrl(telegramAPIUrlsKeeper)).openStream()

fun PathedFile.asFile(
        telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
        dest: File = File.createTempFile(this.fileUniqueId, this.filename),
        defaultBufferSize: Int = 1024,
        buffer: ByteArray = ByteArray(defaultBufferSize)
): File {
    this.asStream(telegramAPIUrlsKeeper).use { input ->
        FileOutputStream(dest).use { out ->
            var read: Int
            while (input.read(buffer, 0, defaultBufferSize).also { read = it } >= 0) {
                out.write(buffer, 0, read)
            }
        }
    }
    return dest
}
