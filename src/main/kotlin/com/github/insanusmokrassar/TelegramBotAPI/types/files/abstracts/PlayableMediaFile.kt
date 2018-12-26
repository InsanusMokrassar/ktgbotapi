package com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts

interface PlayableMediaFile : TelegramMediaFile {
    val duration: Long?
}
