package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts

interface GroupChat : PublicChat {
    val allMembersAreAdmins: Boolean
}
