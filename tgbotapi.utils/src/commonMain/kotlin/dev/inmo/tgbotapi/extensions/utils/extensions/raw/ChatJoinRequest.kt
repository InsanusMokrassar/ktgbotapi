package dev.inmo.tgbotapi.extensions.utils.extensions.raw

import dev.inmo.tgbotapi.types.ChatInviteLink
import dev.inmo.tgbotapi.types.ChatJoinRequest
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature(RawFieldsUsageWarning)
val ChatJoinRequest.invite_link: ChatInviteLink
    get() = inviteLink
