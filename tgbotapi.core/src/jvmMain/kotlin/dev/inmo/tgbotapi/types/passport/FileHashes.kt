package dev.inmo.tgbotapi.types.passport

import dev.inmo.micro_utils.crypto.MD5
import java.io.File

val File.passportFileHash: MD5
    get() = readBytes().passportFileHash
