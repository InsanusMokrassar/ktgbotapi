package com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.utils.StorageFile
import java.io.File

fun File.toInputFile(): InputFile = MultipartFile(StorageFile(this))
