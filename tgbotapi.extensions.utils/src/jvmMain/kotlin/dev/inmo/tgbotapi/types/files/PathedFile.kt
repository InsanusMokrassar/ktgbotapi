package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper
import java.io.*
import java.io.File
import java.net.URL

fun PathedFile.asStream(
        telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper
): InputStream = URL(this.fullUrl(telegramAPIUrlsKeeper)).openStream()

@Deprecated("This api will be removed soon. Use `downloadFile` instead")
fun PathedFile.asFile(
        telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
        dest: File = File.createTempFile(this.fileUniqueId, this.filename),
        defaultBufferSize: Int = DEFAULT_BUFFER_SIZE
): File {
    this.asStream(telegramAPIUrlsKeeper).use { input ->
        FileOutputStream(dest).use { out ->
            input.copyTo(out, defaultBufferSize)
        }
    }
    return dest
}

@Deprecated("This api will be removed soon. Use `downloadFile` instead")
fun PathedFile.asBytes(
        telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper
): ByteArray = this.asStream(telegramAPIUrlsKeeper)
        .use { input -> input.readBytes() }
