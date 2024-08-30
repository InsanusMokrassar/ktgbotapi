package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.types.chat.member.*

/**
 * User joined a chat
 */
val ChatMemberUpdated.joinedChat get() = oldChatMemberState.isLeftOrKicked && !newChatMemberState.isLeftOrKicked

/**
 * Member left a chat for any reason
 */
val ChatMemberUpdated.leftChat get() = !oldChatMemberState.isLeftOrKicked && newChatMemberState.isLeftOrKicked

/**
 * Member became a chat subscriber
 */
val ChatMemberUpdated.subscribed: Boolean get() = !oldChatMemberState.isSubscriberMember && newChatMemberState.isSubscriberMember

/**
 * Member became a chat subscriber or renewed their subscription
 */
val ChatMemberUpdated.subscriptionUpdated: Boolean get() = newChatMemberState.isSubscriberMember

/**
 * Member subscription was expired. User still can be a member
 *
 * @see unsubscribedAndLeft
 */
val ChatMemberUpdated.unsubscribed: Boolean get() = oldChatMemberState.isSubscriberMember && !newChatMemberState.isSubscriberMember

/**
 * Member subscription was expired and user left the chat
 */
val ChatMemberUpdated.unsubscribedAndLeft: Boolean get() = oldChatMemberState.isSubscriberMember && !newChatMemberState.isMember

/**
 * Member was promoted to chat administrator (or owner)
 */
val ChatMemberUpdated.gotPromoted: Boolean get() = !oldChatMemberState.isAdministrator && newChatMemberState.isAdministrator

/**
 * Member was promoted to chat administrator (or owner) or got it permissions/title changed
 */
val ChatMemberUpdated.gotPromotionChanged: Boolean get() = newChatMemberState.isAdministrator

/**
 * Member was demoted from administrators (or ceased chat ownership)
 */
val ChatMemberUpdated.gotDemoted: Boolean get() = oldChatMemberState.isAdministrator && !newChatMemberState.isAdministrator

/**
 * Member became a chat owner
 */
val ChatMemberUpdated.becameOwner: Boolean get() = !oldChatMemberState.isOwner && newChatMemberState.isOwner

/**
 * Member ceased their chat ownership
 */
val ChatMemberUpdated.ceasedOwnership: Boolean get() = oldChatMemberState.isOwner && !newChatMemberState.isOwner

/**
 * Member was restricted or some restrictions have changed
 */
val ChatMemberUpdated.gotRestricted: Boolean get() = !oldChatMemberState.isRestricted && newChatMemberState.isRestricted

/**
 * Member restrictions were changed (but not removed)
 */
val ChatMemberUpdated.gotRestrictionsChanged: Boolean get() = newChatMemberState.isRestricted

/**
 * All member restrictions were removed
 */
val ChatMemberUpdated.gotUnrestricted: Boolean get() = oldChatMemberState.isRestricted && !newChatMemberState.isRestricted

/**
 * [ChatMemberUpdated.newChatMemberState] of [this] [isKicked]
 */
val ChatMemberUpdated.kicked: Boolean get() = newChatMemberState.isKicked
