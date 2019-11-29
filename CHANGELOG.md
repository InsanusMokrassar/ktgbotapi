# TelegramBotAPI changelog

## 0.18.0 Raws cleaning

* Made internal and not available outside of library:
    * `RawMessage`
    * `RawUpdate`
    * `RawChatMember`
    * `RawMessageEntity`
    * `RawInlineQuery`
    * `RawCallbackQuery`
    * `RawChosenInlineResult`

* All `RawMessage` usages was replaced with `Message` interface (with some of other raw classes was made the same things)
    * `TelegramBotAPIMessageDeserializationStrategy` was created. It was used for deserialization of Telegram Bot API
    incoming messages
    * `TelegramBotAPIMessageDeserializeOnlySerializer` was created. It **MUST NOT** be used to serialize messages
* Update of description
* Make `Game` object a little bit more standartizated
* `Game` now is not serializable and have no additional trash, related to serialization
* `TelegramFile` was removed

### 0.18.1 Libraries update

* Update libraries:
    * `kotlin`: 1.3.41 -> 1.3.61
    * `kotlin coroutines`: 1.2.2 -> 1.3.2
    * `kotlin serialization`: 0.11.1 -> 0.14.0
    * `joda time`: 2.10.3 -> 2.10.5
    * `ktor`: 1.2.3 -> 1.2.5
* `BotAction` now will be deserialized in a little bit other way
    * `BotActionSerializer` now is internal
* Most part of serializers now are objects (instead of classes as was previously)

## 0.17.0 July 29, 2019 API Update

Libraries updates:

* Kotlin version `1.3.31` -> `1.3.41`
* Kotlin Coroutines version `1.2.1` -> `1.2.2`
* Kotlin Serialization version `0.11.0` -> `0.11.1`
* Joda Time version `2.10.1` -> `2.10.3`
* ktor version `1.1.4` -> `1.2.3`

Changes according to [July 29, 2019 Telegram Bot API update](https://core.telegram.org/bots/api#july-29-2019):

* `Sticker` and `StickerSet` now have field `isAnimated`
* `ChatPermissions` object was added, `GroupChat` interface got `permissions` field, request `SetChatPermissions` was added
* `GroupChat` object now have no field `allMembersAreAdmins`
* `SpecialRightsChatMember` was added for administrators and restricted members rights union, chat members abstractions
was replaced into `abstracts` package and available permissions was updated
* `RestrictChatMember` request now accept `permissions` object instead of separated permissions
* All `GroupChat` instances have description

Other important changes:

* Totally reworked chats hierarchy. `Extended` abstractions was added for cases when called `GetChat` request
* `RawChat` boilerplate was removed and replaced by serializers
* `BotCommandMessageEntity#command` will not contain `/`/`!` parts and also will cut outside of command begin token (`/`
or `!`) and username token (`@`) or end of command (any space character)
* `RequestsExecutor` now is `Closeable`
* `TelegramAPIUrlsKeeper` was added to provide more comfortable work with file urls and other things
like this

## 0.16.0 Bot API 4.3

* `LoginURL` and `LoginURLInlineKeyboardButton` has been added
* `replyMarkup` field was added to the `CommonMessage` objects via `AbleToBeMarkedUp` interface
* `SwitchInlineQueryCurrentChatInlineKeyboardButton#switchInlineQueryCurrentChat` field fixed
* `InlineKeyboardButton` now is sealed class and all its possible realisations are inside of its class file
* `String#asUsername` method renamed to `String#toUsername`
* Several `toChatId` extensions added

### 0.16.1

* Now old uncommon `CaptionedMediaContent` and `CaptionedInputMedia` are replaced by almost the same
interfaces `CaptionedInput` and `CaptionedOutput`. They are both implementing `Captioned` interface
* `AnimationContent` now is `CaptionedInput`

## 0.15.0

* Old `UpdatesPoller` removed (was deprecated)
* `UpdatesPoller` renamed to `KtorUpdatesPoller`
* Now `KtorUpdatesPoller` do not use additional delay between requests and await answer from Telegram all timeout time
* Added abstraction `UpdatesPoller`
* Changed signature of the most count of `startGettingOfUpdates`:
    * They are not `suspend` for now
    * They are return `UpdatesPoller`
    * They are using `timeoutMillis` instead of `requestsDelayMillis`
* Added `CIO` ktor client engine as lightweight default engine for long-polling

## 0.14.0

* Now library have no default engine for both webhooks and requests executor. It is required for clients to set
some default library
* All proxy help methods was removed . They are will be replaced in separated project
* `Ktor` version `1.1.3` -> `1.1.4`
* Requests results now always decoding as `UTF-8`
* `AbstractRequestCallFactory` was added with cache of methods urls to avoid memory leaks
* Small refactoring of work with response in `KtorRequestsExecutor`
* Kotlin version `1.3.30` -> `1.3.31`
* Kotlin coroutines `1.2.0` -> `1.2.1`
* `CommonForwardedMessage` was renamed to `UserForwardedMessage`
* All forwarded messages are now just childs of `ForwardedMessage`:
    * `AnonymousForwardedMessage` - for messages without forwarded info
    * `UserForwardedMessage` - for messages from users and groups (contains not message id)
    * `ForwardedFromChannelMessage` - for messages from channels
* Changed logic of forwarded messages preparing

### 0.14.1

* Replace `UpdatesFilter` and `UpdatesPoller` into another package
* Replace `WebhookPrivateKeyConfig`
* Added `FlowsUpdatesFilter`
* `UpdatesFilter` now have additional callback for polls
* `StopPoll#replyMarkup` now is optional

### 0.14.2 MediaGroups edit hotfixes

* `convertWithMediaGroupUpdates` extension added
* All media group converting extensions are internal for now
* Fixes according to updates in converting of updates to media group updates

## 0.13.0 Telegram Polls

* Type `PollOption` and `AnonymousPollOption` added
* Type `Poll` added
* Type `PollUpdate` added and implemented in `RawUpdate`. Now `PollUpdate` can be retrieved from `RawUpdate`
* Type `PollContent` added - now it can be a value of `ContentMessage#content`
* Request `SendPoll` added and `PollContent#createResend` now use it
* `ByInlineMessageId` is deprecated (use `InlineMessageAction` instead)
* `ByMessageId` is deprecated (use `MessageAction` instead)
* Most part of requests which are working with identifiers of messages now implement `MessageAction` directly or
by their parents
* `StopPoll` implemented
* All current `Chat` abstractions are deprecated and rewritten as typealiases. Use `Chat` abstractions from
`com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts` package
* Common Groups now may have pinned message
* `is_member` field added into `RestrictedChatMember`
* **BREAK CHANGES** Now `ForwardedMessages` can be `AnonymousForwardedMessage` and `PublicForwardedMessage`. Old
implementations now extend `PublicForwardedMessage`

## 0.12.0 Webhooks

* Added `DataRequest` interface which replace `Data` interface
* `MultipartRequestImpl` now use `DataRequest`
* All requests which implements `Data` now implement `DataRequest`
* Added class `SetWebhook` and its factory
* Added class `UpdatesFilter` which can help to filter updates by categories
* Added function `accumulateByKey` which work as debounce for keys and send list of received values
* Added webhooks functions and workaround for `Reverse Proxy` mode
* Added new type of updates `MediaGroupUpdate`, which can be received only from filters
* `UpdatesFilter` now use new type of updates for mediagroups
* Add `GetWebhookInfo` request and `WebhookInfo` type
* Replace updates types into separated place in types
* Now default `RequestException` will contain plain answer from telegram
* Added `UnauthorizedException`
* `RequestException` now is sealed
* Rename `ReplyMessageNotFound` to `ReplyMessageNotFoundException`
* Added `List<BaseMessageUpdate>#mediaGroupId` extension
* Added utility `T#asReference(): WeakReference(T)` extension
* Added `UpdatesPoller` class which can be instantiated for manage updates polling
* Separated execute extensions (now they are in file `Executes`) and poller creating extensions
* `BaseMessageUpdate#toMediaGroupUpdate()` will also check condition when update-receiver already is `MediaGroupUpdate`

### 0.12.1 Hotfix for media groups

* Added additional media group types (like `MessageMediaGroupUpdate`)
* Fixed handling of media group updates in `UpdatesFilter`

### 0.12.2

* New in `MediaGroupUpdate`:
    * It is subtype of `Update` and can be use as regular update with list of messages
    * Data now is list with `MediaGroupMessage`
    * Added field `origins` which represent origin updates for `MediaGroupMessage`
    * `updateId` now represent LAST id of origins updates
* `UpdatesFilter` and other objects now work with `UpdateReceiver<Update>` as common supertype
for receivers.

### 0.12.3 Cleaning

* Refactor, optimizing and cleaning of code
* Removed deprecated method `T#toJsonWithoutNulls()`
* Renamed instances of `MediaGroupMessage`s and refactored their interfaces. `ChannelMediaGroupMessage`
will not contain `user` field (but `CommonMediaGroupMessage` will have)
* Now `MediaCollectionContent` is `MediaContent` (classes of this interface must choose best
media for present out)
    * `PhotoContent` now choose biggest photo size from its collection as `media`
* Fix in order of media group messages which was received by webhooks

### 0.12.4

* Optimized preparing of media group in `UpdatesPoller`
* Add `CommonLimiter`
* Add `MessageEntity#asHtmlSource` and `String#toHtml`
* Add tools for work with html captions and texts
* `MessageContent` which using captions or text now have default parse mode `HTMLParseMode` due to issue with escaping
of `]` in links titles
* Added `Markdown` and `HTML` type aliases which actually means `MarkdownParseMode` and `HTMLParseMode`
* `ChatId` now have extension `link` which will automatically create link like `tg://user?id=<chatId>`
* Created a few of methods for all supported formats of text like bold, italic, links and others
* Rewritten `MessageEntities` to use new formatting options

### 0.12.5 `MediaContent` improvements

* Now `MediaGroupContent` is `MediaContent`
* All `MedaContent` now have no generics and have basic `TelegramMediaFile` media field

### 0.12.6 Libraries updates

* `kotlin` version `1.3.21` -> `1.3.30`
* `kotlin coroutines` version `1.1.1` -> `1.2.0`
* `kotlin serialization` version `0.10.0` -> `0.11.0`
* `ktor` version `1.1.2` -> `1.1.3`
* Added `DeleteWebhook` request
* All default `startGettingOfUpdates` (in fact - method `start` of `UpdatesPoller`) are suspend and
will try to delete webhook

### 0.12.7 Hotfix version

* Now temporary all requests of input media will contains `file` field

## 0.11.0

* Kotlin `1.3.11` -> `1.3.21`
* Kotlin coroutines `1.1.0` -> `1.1.1`
* Kotlin serialization `0.9.1` -> `0.10.0`
* Ktor `1.1.1` -> `1.1.2`

## 0.10.0

* Most part of abstractions was replaced from `requests` and `types` on more high level
* Added abstraction `CommonVenueData`
* Added abstraction `CommonContactData`
* Added `InputMessageContent`
* Update some types and requests according to abstractions replacing
* Add all `InlineQueryResult`, `InputMessageContent` and other inline mode types
* Fixes in edition of inline messages and their result types
* Replace basic exception and add `ReplyMessageNotFound` exception

### 0.10.1

* Change algorithm of `executeUnsafe`: now it use loop instead of recursive calling
* Add additional `startGettingUpdates` with better management of received updates for media groups
* Now `MediaGroupMessage` is `CommonMessage` with `MediaGroupContent` content
* Added extensions `replyTo`, `forwarded` and `chat` for `List<BaseMessageUpdated>` for comfortable
work with media groups lists
* Fix `parseMode` of `InputTextMessageContent`

### 0.10.2

* Fixes in `Username`
    * Now you can create username object using string which is not starting with `@`
    * Now `Username` correctly comparing with strings, which are not starting with `@`
* Now most part of usernames in library have type `Username`
* Fix `replyMarkup` in `InlineQueryResultArticle`

### 0.10.3

* Hotfix for username data class

## 0.9.0

* Old extension `OkHttpClient.Builder#useWith` now deprecated and must be replaced by the same in
`com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor` package
* Replace `ProxySettings` data class in `settings` package, deprecate old link
* `BaseRequestsExecutor` now have no it's own scope
* Add `RequestLimiter` and base realisations
* Now `KtorRequestsExecutor` can receive as one of parameters `RequestLimiter` (by default - `EmptyLimiter`)

### 0.9.1

* Updated built-in lengths restrictions
* Apply restrictions of text limit for sending messages
* Add `RegularTextMessageEntity` which is useful for representing regular text message entity
* Add `convertToFullMessageEntityList` which create list of entities with `RegularTextMessageEntity` on places where
must be regular text
* Change signature of `createMarkdownText`: now it will return list of strings
* Deprecate old signatures of `createMarkdownText`, `toMarkdownCaption`, `toMarkdownText`
* Add `ResendableContent#createResends` which create adapted list of resends for content
* Add `TextContent` own `createResends` realisation

### 0.9.2

* `RequestsExecutor#executeAsync(Request, CoroutineScope)` now will return `Deferred` for cases when you need result
* `RequestsExecutor#executeUnsafe` will automatically retry request if it was unsuccessful and retries > 0

### 0.9.3

* `KtorRequestsExecutor` now can use custom `JSON` string formatter (by default - non strict)
* `ResponseParameters` renamed to `Response`
* Add `RequestError` sealed class and described in documentation known errors
* Add `ResponseParametersRaw` which can create error based on input parameters
* Add `parameters` field in `Response` and remove useless fields from `Response`
* Add `leftToRetry` parameter in `RetryAfterError`
* Add handling of `RetryAfterError` in `KtorRequestsExecutor`

### 0.8.5

* Add extension `String#toMarkdown`
* Fix of inserting of text when create Markdown-adapted text from text and text entities
* Fix default realisation of MessageEntity#asMarkdownSource

### 0.8.4

* Added `createMarkdownText` and extensions for `CaptionedMediaContent` and `TextContent`
* Added `ResendableContent` and realize in different contents
    * Animation
    * Audio
    * Document
    * Photo
    * Sticker
    * Video
    * VideoNote
    * Voice
* `MessageContent` now is `ResendableContent`
* Now all media sending factories which contains `thumb` have default `null` value
* `ChatIdentifier` classes now are `data` classes
* Now `MediaGroupContent` interface contains `toMediaGroupMemberInputMedia` method for easily creating mirror input media
* Change signature of `Update`
    * Now `Update` is untyped and data is `Any`
* Media groups now are separated type of updates and you can subscribe on that receiving directly
* Now `AdministratorChatMember` is interface and `CreatorChatMember` implement it

### 0.8.3

* Now `ForwardedMessage` contains nullable `from`

### 0.8.2

* Add `FromUserMessage` which must be implemented in all messages realisations which have `user` field
* Add `CommonMediaGroupMessage` which in fact extension of `MediaGroupMessage` with implementation of `FromUserMessage`
* `CommonMessageImpl` now implementing `FromUserMessage`

### 0.8.1

* Update `MediaGroupMessage` interface
* Add implementation of `MediaGroupMessage`
* Add generating of `MediaGroupMessage` in `RawMessage`
