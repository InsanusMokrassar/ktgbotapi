package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.extensions.*
import dev.inmo.tgbotapi.types.chat.member.*

/**
 * Allows only member [joinedChat] updates
 */
val chatMemberJoinedFilter = SimpleFilter<ChatMemberUpdated> {
    it.joinedChat
}

/**
 * Allows only member [leftChat] updates
 */
val chatMemberLeftFilter = SimpleFilter<ChatMemberUpdated> {
    it.leftChat
}

/**
 * Allows only member [subscribed] updates
 */
val chatMemberSubscribedFilter = SimpleFilter<ChatMemberUpdated> {
    it.subscribed
}

/**
 * Allows only member [subscriptionChanged] updates
 */
val chatMemberSubscriptionChangedFilter = SimpleFilter<ChatMemberUpdated> {
    it.subscriptionChanged
}

/**
 * Allows only member [unsubscribed] updates
 */
val chatMemberUnsubscribedFilter = SimpleFilter<ChatMemberUpdated> {
    it.unsubscribed
}

/**
 * Allows only member [gotPromoted] updates
 */
val chatMemberGotPromotedFilter = SimpleFilter<ChatMemberUpdated> {
    it.gotPromoted
}

/**
 * Allows only member [gotPromotionChanged] updates
 */
val chatMemberGotPromotionChangedFilter = SimpleFilter<ChatMemberUpdated> {
    it.gotPromotionChanged
}

/**
 * Allows only member [gotDemoted] updates
 */
val chatMemberGotDemotedFilter = SimpleFilter<ChatMemberUpdated> {
    it.gotDemoted
}

/**
 * Allows only member [becameOwner] updates
 */
val chatMemberBecameOwnerFilter = SimpleFilter<ChatMemberUpdated> {
    it.becameOwner
}

/**
 * Allows only member [ceasedOwnership] updates
 */
val chatMemberCeasedOwnershipFilter = SimpleFilter<ChatMemberUpdated> {
    it.ceasedOwnership
}

/**
 * Allows only member [gotRestricted] updates
 */
val chatMemberGotRestrictedFilter = SimpleFilter<ChatMemberUpdated> {
    it.gotRestricted
}

/**
 * Allows only member [gotRestrictionsChanged] updates
 */
val chatMemberGotRestrictionsChangedFilter = SimpleFilter<ChatMemberUpdated> {
    it.gotRestrictionsChanged
}

/**
 * Allows only member [gotUnrestricted] updates
 */
val chatMemberGotUnrestrictedFilter = SimpleFilter<ChatMemberUpdated> {
    it.gotUnrestricted
}