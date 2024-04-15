package dev.inmo.tgbotapi.types

const val UPDATE_MESSAGE = "message"
const val UPDATE_EDITED_MESSAGE = "edited_message"
const val UPDATE_CHANNEL_POST = "channel_post"
const val UPDATE_EDITED_CHANNEL_POST = "edited_channel_post"
const val UPDATE_CHOSEN_INLINE_RESULT = "chosen_inline_result"
const val UPDATE_INLINE_QUERY = "inline_query"
const val UPDATE_CALLBACK_QUERY = "callback_query"
const val UPDATE_SHIPPING_QUERY = "shipping_query"
const val UPDATE_PRE_CHECKOUT_QUERY = "pre_checkout_query"
const val UPDATE_POLL = "poll"
const val UPDATE_POLL_ANSWER = "poll_answer"
const val UPDATE_MY_CHAT_MEMBER = "my_chat_member"
const val UPDATE_CHAT_MEMBER = "chat_member"
const val UPDATE_CHAT_JOIN_REQUEST = "chat_join_request"
const val UPDATE_MESSAGE_REACTION = "message_reaction"
const val UPDATE_MESSAGE_REACTION_COUNT = "message_reaction_count"
const val UPDATE_CHAT_BOOST = "chat_boost"
const val UPDATE_REMOVE_CHAT_BOOST = "removed_chat_boost"
const val UPDATE_BUSINESS_CONNECTION = "business_connection"

val ALL_UPDATES_LIST_WITHOUT_REACTIONS = listOf(
    UPDATE_MESSAGE,
    UPDATE_EDITED_MESSAGE,
    UPDATE_CHANNEL_POST,
    UPDATE_EDITED_CHANNEL_POST,
    UPDATE_INLINE_QUERY,
    UPDATE_CHOSEN_INLINE_RESULT,
    UPDATE_CALLBACK_QUERY,
    UPDATE_SHIPPING_QUERY,
    UPDATE_PRE_CHECKOUT_QUERY,
    UPDATE_POLL,
    UPDATE_POLL_ANSWER,
    UPDATE_MY_CHAT_MEMBER,
    UPDATE_CHAT_MEMBER,
    UPDATE_CHAT_JOIN_REQUEST,
    UPDATE_CHAT_BOOST,
    UPDATE_REMOVE_CHAT_BOOST,
    UPDATE_BUSINESS_CONNECTION
)

val ALL_UPDATES_LIST = ALL_UPDATES_LIST_WITHOUT_REACTIONS + listOf(
    UPDATE_MESSAGE_REACTION,
    UPDATE_MESSAGE_REACTION_COUNT
)
