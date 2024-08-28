package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.extensions

import dev.inmo.tgbotapi.types.chat.member.*

val ChatMember.isLeft: Boolean get() = this is LeftChatMember
val ChatMember.isKicked: Boolean get() = this is KickedChatMember
val ChatMember.isLeftOrKicked: Boolean get() = isLeft || isKicked
val ChatMember.isRestricted: Boolean get() = this is RestrictedChatMember
val ChatMember.isMember: Boolean get() = this is MemberChatMember
val ChatMember.isOwner: Boolean get() = this is OwnerChatMember
val ChatMember.isSubscriber: Boolean get() = this is SubscriptionMemberChatMember
val ChatMember.isAdministrator: Boolean get() = this is AdministratorChatMember

/**
 * Checks if member is strictly [MemberChatMember], not any derivatives
 */
val ChatMember.isMemberStrict: Boolean get() = this is MemberChatMemberImpl
/**
 * Checks if member is strictly [AdministratorChatMember], not any derivatives
 */
val ChatMember.isAdministratorStrict: Boolean get() = this is AdministratorChatMemberImpl

val ChatMember.hasSpecialRights: Boolean get() = this is SpecialRightsChatMember
val ChatMember.isKickedOrRestricted: Boolean get() = this is BannedChatMember
