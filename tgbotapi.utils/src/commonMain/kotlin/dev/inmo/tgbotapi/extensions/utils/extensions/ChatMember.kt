package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.types.chat.member.*

/**
 * Check if receiver object is [LeftChatMember]
 */
val ChatMember.isLeft: Boolean get() = this is LeftChatMember

/**
 * Check if receiver object is [MemberChatMember]
 */
val ChatMember.isMember: Boolean get() = this is MemberChatMember

/**
 * Check if receiver object is [RestrictedMemberChatMember]
 */
val ChatMember.isRestrictedMember: Boolean get() = this is RestrictedMemberChatMember

/**
 * Check if receiver object is [MemberChatMemberImpl]
 */
val ChatMember.isCommonMember: Boolean get() = this is MemberChatMemberImpl

/**
 * Check if receiver object is [SubscriptionMemberChatMember]
 */
val ChatMember.isSubscriberMember: Boolean get() = this is SubscriptionMemberChatMember

/**
 * Check if receiver object is [RestrictedChatMember]
 */
val ChatMember.isRestricted: Boolean get() = this is RestrictedChatMember

/**
 * Check if receiver object is [KickedChatMember]
 */
val ChatMember.isKicked: Boolean get() = this is KickedChatMember

/**
 * Check if receiver object [isLeft] or [isKicked]
 */
val ChatMember.isLeftOrKicked: Boolean get() = isLeft || isKicked

/**
 * Check if receiver object [isRestricted] and not [isKicked]
 */
val ChatMember.isRestrictedAndNotKicked: Boolean get() = isRestricted && !isKicked

/**
 * Check if receiver object is [SpecialRightsChatMember]
 */
val ChatMember.isSpecialRightsMember: Boolean get() = this is SpecialRightsChatMember

/**
 * Check if receiver object is [AdministratorChatMember]
 */
val ChatMember.isAdministrator: Boolean get() = this is AdministratorChatMember

/**
 * Check if receiver object is [OwnerChatMember]
 */
val ChatMember.isOwner: Boolean get() = this is OwnerChatMember

/**
 * Check if receiver object is [AdministratorChatMemberImpl]
 */
val ChatMember.isCommonAdministrator: Boolean get() = this is AdministratorChatMemberImpl

/**
 * Check that member is [KickedChatMember]
 */
val ChatMember.isBanned: Boolean get() = this is KickedChatMember

/**
 * Check that member is [RestrictedChatMember]
 */
val ChatMember.isKickedOrRestricted: Boolean get() = this is RestrictedChatMember
