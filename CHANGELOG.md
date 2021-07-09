# TelegramBotAPI changelog

## 0.35.2

## 0.35.1

* `Common`:
    * `Version`:
        * `Kotlin`: `1.5.10` -> `1.5.20`
        * `MicroUtils`: `0.5.6` -> `0.5.15`
* `Core`:
    * New interface `MyCommandsRequest` (also see `Bot API 5.3` below)
    * New extensions `TextSourcesList#make*String` for all parse modes
    * All `MessageContent` subclasses now serializable
    * `ChosenInlineResult` was replaced and modified to be sealed
    * `ChosenInlineResult` now extends `FromUser`
    * Added `Update#sourceUser` method
    * More types assumed as sent by user types now implements `FromUser` interface
    * Added `Any#whenFromUser`, `Any#asFromUser` and`Any#requireFromUser` extensions
    * `MedaGroupUpdate` and its direct extenders `SentMediaGroupUpdate` and `EditMediaGroupUpdate` became
      `sealed interface`s
    * New built-in `RequestException` implementator `GetUpdatesConflict` has been added
* `Behaviour Builder`:
    * ❗️ All triggers (`on*` extensions) have been modified to work in parallel by some marker by default (new parameter
      `markerFactory`, in most cases will work async for different chats)
    * New extensions `telegramBotWithBehaviour`
    * All behaviour builder extensions got new parameter `defaultExceptionsHandler`
    * Class `BehaviourContext` was rewritten as an interface with default realization `DefaultBehaviourContext` and
      factory `BehaviourContext(TelegramBot, CoroutineScope, FlowsUpdatesFilter)`
    * Extension `buildBehaviour` (and all related extensions/functions) for opportunity to pass
      `defaultExceptionsHandler`
    * Trigger `onContentMessage` and waiter `waitContentMessage` now may include media groups
* `API`:
    * All `reply` and subsequent extensions have been replaced in send package
* `Utils`:
    * With class casts like `as*` and `require*` now you may use `when*` with parameter callback
    * Methods of `EntitiesBuilder` now will return builder itself, so you may create sequences like
      `buildEntities { bold("Hello,") + italic(" world") }` directly in `buildEntities` body
    * New extension `TelegramBot#longPollingFlow` has been added with returning value `Flow` with updates
* `Bot API 5.3`:
    * Add type `BotCommandScope`, its serializer `BotCommandScopeSerializer` and all its children
    * New request `DeleteMyCommands` and updates in `GetMyCommands` and `SetMyCommands`
    * Renames according to `And more` of [June 25, 2021](https://core.telegram.org/bots/api-changelog#june-25-2021) update

## 0.35.0

**ALL PREVIOUS DEPRECATIONS HAVE BEEN REMOVED**
**JS PART NOW USE IR COMPILER ONLY**

* `Common`:
    * `Version`:
        * `Kotlin`: `1.4.72` -> `1.5.10`
        * `MicroUtils`: `0.4.36` -> `0.5.6`
        * `Coroutines`: `1.4.3` -> `1.5.0`
        * `Serialization`: `1.1.0` -> `1.2.1`
        * `Klock`: `2.0.7` -> `2.1.2`
        * `UUID`: `0.2.3` -> `0.3.0`
        * `Ktor`: `1.5.4` -> `1.6.0`
* `Core`:
    * `ForceReply` has been renamed to `ReplyForce`
    * `Captioned` and `Explained` interfaces have been removed
    * `RecordAudioAction` and `UploadAudioAction` (and all related to these actions functionality) have been removed
    * `TextSource` interface and all related things have been replaced
    * `CallbackQuery` interface and all its extenders/implementers become `sealed`
    * `InputMedia` interface and all its extenders/implementers become `sealed`
    * `ParseMode` interface and all its extenders/implementers become `sealed`
    * `ChatMember` becomes `sealed`
    * `KeyboardMarkup` becomes `sealed`
    * `LeftChatMember` and `MemberChatMember` become interfaces. All their code were replaced to the `*Impl` classes
    * Most of `sealed` classes have been modified to be interfaces
    * Most serializers becomes public, but they are still `RistFeature`
    * For `EntitiesBuilder` multilevel text sources builders with callback have been added

## 0.34.1

* `Common`:
    * `Version`:
        * `ktor`: `1.5.3` -> `1.5.4`
        * `MicroUtils`: `0.4.35` -> `0.4.36`
* `Core`
    * Fix in creating of text sources list

## 0.34.0

_**It is recommended to use [0.34.1](https://github.com/InsanusMokrassar/TelegramBotAPI/releases/tag/0.34.1) version due to the bug in 0.34.0 related to rewriting of `TextSource`s creating mechanism.**_

**UPDATE UP TO Telegram Bot API 5.2**

_**ALL OLD DEPRECATIONS WERE REMOVED**_

* `Core`:
    * Type `ChatType` has been added
    * New `ExtendedChat` for unknown messages `UnknownExtendedChat` has been added
    * `SendInvoice#startParameter` becomes optional and replaced in `SendInvoice` constructor
    * New interface `CommonSendInvoiceData` has been added
        * Fields `CommonSendInvoiceData#maxTipAmount` and `CommonSendInvoiceData#suggestedTipAmounts` have been added
    * New type `InputInvoiceMessageContent` has been added
    * New interface `TextedWithTextSources` on top of `Texted` interface
        * Interface `TextedInput` now extends `TextedWithTextSources` with overriding of `textSources` field as not
          nullable
        * `textSources` become main field in `TextedInput`
            * **MIGRATION** Remove all `import dev.inmo.tgbotapi.CommonAbstracts.textSources` in your project
            * `textEntities` become are calculable property in `TextedInput`
  * Interface `Captioned` and `CaptionedInput` now is deprecated
      * Most of captions usages were replaced with texts
  * Interface `Explained` and `ExplainedInput` now is deprecated
      * Most of captions usages were replaced with texts
    * Interface `VoiceChatEvent` now is `CommonEvent`
  * Mechanism of `RawMessageEntity` converting were fully rewritten

## 0.33.4

* `Common`:
    * `Version`:
        * `uuid`: `0.2.3` -> `0.2.4`
        * `MicroUtils`: `0.4.33` -> `0.4.35`
* `Core`:
    * All `TextSource` implementators have become `Serializable`
        * New serializer `TextSourceSerializer`
    * Interface`FromUserMessage` now extends `Message`
    * New interface `FromUser`
        * Interface `FromUserMessage` now extends `FromUser`
* `Extensions Utils`
    * Fixes in `parseCommandsWithParams`

## 0.33.3

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.4.32` -> `0.4.33`
        * `Ktor`: `1.5.2` -> `1.5.3`
* `API`:
    * Bot actions DSL (fix for [#358](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/358))
* `Behaviour Builder`:
    * Rewrite logic of `doInSubContextWithUpdatesFilter` and `doInSubContextWithFlowsUpdatesFilterSetup` extensions
    * All triggers now work with `stopOnCompletion` set up to `false`

## 0.33.2

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.4.30` -> `0.4.32`
* `Behaviour Builder`:
    * New typealias `MediaGroupFilter` has been added for `MediaGroup` expectators
    * Several typealiases became `suspend`:
        * `CallbackQueryMapper`
        * `ChatMemberUpdatedMapper`
        * `InlineQueryMapper`
    * Commands got an additional parameter - `additionalFilter`. It will be called when all command filters were passed

## 0.33.1

* `Common`:
    * `Version`:
        * `Kotlin`: `1.4.31` -> `1.4.32`
        * `MicroUtils`: `0.4.29` -> `0.4.30`
        * `Klocks`: `2.0.6` -> `2.0.7`
* `Utils Extensions`:
    * Add extensions `parseCommandsWithParams`

## 0.33.0

**UPDATE UP TO Telegram Bot API 5.1**
_**ALL DEPRECATIONS WERE REMOVED**_

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.4.28` -> `0.4.29`
* `Core`:
    * `AdministratorChatMemberSerializer` and `ChatMemberSerializer` has changed their visibility: they are public for now
    * Add `ChatInviteLinkRequest` with subrequests like `KnownChatInviteLinkRequest`
    * Add `CreateChatInviteLink`/`EditChatInviteLink`/`RevokeChatInviteLink` requests
    * Update `KickChatMember` to include `revokeMessages` flag
    * Update `PromoteChatMember` to include `canManageVoiceChats` and `canManageChat` flags
    * Add `ChatInviteLink` object
        * Add `PrimaryInviteLink` for `ChatInviteLink` with `isPrimary == true`
        * Add `CommonInviteLink` for `ChatInviteLink` with `isPrimary == false`
    * `AdministratorChatMemberSerializer` has been set as public for several versions
    * `ChatMemberSerializer` has been set as public for several versions
    * Add `ChatMemberUpdated`
    * Add `MessageAutoDeleteTimerChanged`
    * Add `VoiceChatEvent`
        * Add `VoiceChatEnded`
        * Add `VoiceChatParticipantsInvited`
    * Add `VoiceChatStarted`
    * Add `ChatMemberUpdatedUpdate`
        * Add `CommonChatMemberUpdatedUpdate`
        * Add `MyChatMemberUpdatedUpdate`
* `API`:
    * All API extensions has been updated
* `Behaviour Builder`:
    * Now content triggers and expectators will wait for channel posts too
    * New waiters and triggers for `ChatMemberUpdated` and its variations

## 0.32.9

* `Common`:
    * `Version`:
        * `Kotlin`: `1.4.30` -> `1.4.31`
        * `Ktor`: `1.5.1` -> `1.5.2`
        * `MicroUtils`: `0.4.26` -> `0.4.28`
        * `Coroutines`: `1.4.2` -> `1.4.3`

## 0.32.8

* `Common`:
    * `Version`:
        * `Serialization`: `1.1.0-RC` -> `1.1.0`
        * `MicroUtils`: `0.4.25` -> `0.4.26`

## 0.32.7

* `Core`:
    * New variable `LeftRestrictionsChatPermissions` and `RestrictionsChatPermissions`
* `Extensions Utils`:
    * `DiceAnimationType` class casts
* `Behaviour Builder`:
    * Now `doInSubContextWithUpdatesFilter` and `doInSubContext` will automatically subscribe on updates of parent
      `BehaviourContext`
    * `doInSubContextWithFlowsUpdatesFilterSetup`, `doInSubContextWithUpdatesFilter` and `doInSubContext` got new
      parameter `stopOnCompletion` to be able to disable stopping of behaviour context on finishing

## 0.32.6

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.4.24` -> `0.4.25`
* `Extensions API`:
    * New extension `TelegramBot#replyWithDice`
* `Extensions Utils`:
    * `SlotMachineReelImages` has been renamed to `SlotMachineReelImage`
    * `SlotMachineReelImage` got two built-in parameters: `text` and `number`
    * New extension `String#asSlotMachineReelImage`

## 0.32.5

* `Core`:
    * Add `mention` variants for user ids and receiver variants ([#294](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/294))
    * Now `AbstractRequestCallFactory` will set up one-second delay for zero timeouts in `GetUpdate` requests
    * Several extensions for `TelegramBotAPI` like `retrieveAccumulatedUpdates` have been added as a solution for
    [#293](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/293)
    * Links for `tg://user?id=<user_id>` have been updated ([#292](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/292))
    * All usages of captions or texts in resends and same things have been replaced with `textSources`
    * Global `defaultParseMode` has been added ([#291](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/291))

## 0.32.4

* `Common`:
    * `Version`:
        * `Kotlin`: `1.4.21` -> `1.4.30`
        * `Klock`: `2.0.4` -> `2.0.6`
        * `MicroUtils`: `0.4.23` -> `0.4.24`
* `Core`:
    * Renames:
        * `ChannelMessage` -> `ChannelContentMessage`
        * `PublicMessage` -> `PublicContentMessage`
            * `GroupMessage` -> `GroupContentMessage`
                * `FromChannelGroupMessage` -> `FromChannelGroupContentMessage`
                * `AnonymousGroupMessage` -> `AnonymousGroupContentMessage`
                * `CommonGroupMessage` -> `CommonGroupContentMessage`
        * `PrivateMessage` -> `PrivateContentMessage`
* `Extensions Utils`:
    * Renames of extensions in `ClassCasts` according to changes in `Core`

## 0.32.3

* `Behaviour Builder`:
    * Add expectators and waiters for inline queries

## 0.32.2

* `Core`:
    * Fix of [#275](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/275)

## 0.32.1

* `Core`:
    * Fix of [#272](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/272)
* `Utils`:
    * Fix of [#273](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/273)

## 0.32.0

**THIS UPDATE CONTAINS BREAKING CHANGES**

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.4.16` -> `0.4.23`
        * `Klock`: `0.2.3` -> `0.2.4`
        * `Ktor`: `1.5.0` -> `1.5.1`
* `Core`:
    * **BREAKING CHANGE** Now `MediaGroupMessage` have a generic type related to `MediaGroupContent`
        * Methods and types related to `MediaGroupMessage` have been modified according to their meanings
    * **Important Change** `FlowsUpdatesFilter` now is an interface. Old class has been renamed to
      `DefaultFlowsUpdatesFilter` and factory method `FlowsUpdatesFilter` has been added
    * **PASSPORT** Full support of `Telegram Passport API`
        * `PassportData`
        * All variants of `EncryptedPassportElement`
        * All variants of `SecureValue`
        * All variants of `PassportElementError`
        * New request `SetPassportDataErrors`
        * `Credentials`:
            * `EncryptedCredentials`
            * `DeryptedCredentials`
            * `EndDataCredentials`
* `Behaviour Builder`:
    * Trigger and expectation extensions for `MessageContent` (`onContentMessage` and `waitContentMessage`)
    * `onMediaGroup` has been replaced
    * `waitMediaGroup` has been added
    * `onVisualMediaGroup` now is just an alternative to `onVisualGallery`
    * `command` and `onCommand` expectations has been added for commands `String` variant
    * New extensions `BehaviourContext#oneOf`, `BehaviourContext#parallel` and `Deferred<T>#withAction`
    * Several renames:
        * `waitAudioMediaGroup` -> `waitAudioMediaGroupContent`
        * `waitDocumentMediaGroup` -> `waitDocumentMediaGroupContent`
        * `waitMediaGroup` -> `waitAnyMediaGroupContent`
        * `waitVisualMediaGroup` -> `waitVisualMediaGroupContent`
    * New extensions `BehaviourContext#waitPassportMessagesWith` and `BehaviourContext#waitAnyPassportMessages`
    * New extensions `BehaviourContext#onPassportMessage` and `BehaviourContext#onPassportMessageWith`
* `Utils`:
    * New `ClassCasts` for
        * `Message`
        * **PASSPORT** `EncryptedPassportElement`
        * **PASSPORT** `PassportElementError`
        * **PASSPORT** `SecureValue`
    * Several tools for decryption have been added:
        * `AESDecryptor` is available for `JVM` platform
        * Extensions `EncryptedCredentials#decryptWithPKCS8PrivateKey` are available for `JVM`
        platform
        * Extensions `EndDataCredentials#decryptData` and `FileCredentials#decryptFile` have been added
        * Several extensions `createDecryptor`
        * Several extensions `doInDecryptionContextWithPKCS8Key`
    * New extension `Flow#passportMessages`
    * In most of webhook setting up functions/methods now available parameter `mediaGroupsDebounceTimeMillis`
* `API`:
    * **PASSPORT** New extensions `TelegramBot#setPassportDataErrors`

## 0.31.0

**THIS UPDATE CONTAINS BREAKING CHANGES**

* `Common`:
    * **ALL DEPRECATIONS CREATED SINCE 0.30.0 WERE REMOVED**
* `Behaviour Builder`:
    * Extension `TelegramBot#buildBehaviour` have changed its return value: now it is `Job` instead of
      `FlowsUpdatesFilter`
* `Utils`
    * New extensions `TelegramBot#longPolling` were added as new recommended way to start getting updates via long
      polling
        * Old extensions `RequestsExecutor#startGettingFlowsUpdatesByLongPolling` has been deprecated

## 0.30.13

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.4.15` -> `0.4.16`
* `Core`:
    * New variable `FlowsUpdatesFilter#allUpdatesWithoutMediaGroupsGroupingFlow` which will contains updates without
      `SentMediaGroupUpdate`
* `Utils`:
    * Extensions for `ResendableContent` has been added
    * Extensions for `TextSource` has been added
* `Behaviour Builder`:
    * Project has been created :)

## 0.30.12

* `Utils`:
    * Class casts has been added. Now you can write something like `message.asGroupMessage() ?.let { ... }` instead of
    `(message as? GroupMessage<*>) ?.let { ... }`

## 0.30.11

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.4.11` -> `0.4.15`
        * `Klock`: `2.0.1` -> `2.0.3`
        * `Ktor`: `1.4.3` -> `1.5.0`
* `Core`:
    * All bot actions got functions for short calling, like `recordVideo` for `RecordVideoNote`
    * All bot actions got class-cast shortcuts

## 0.30.10

* `Common`:
    * `Version`:
        * `Kotlin`: `1.4.20` -> `1.4.21`
        * `Klock`: `2.0.0` -> `2.0.1`
        * `Ktor`: `1.4.2` -> `1.4.3`
        * `MicroUtils`: `0.4.6` -> `0.4.11`
* `API Extensions`:
    * New function `buildBot`

## 0.30.9

* `Common`:
    * `Version`:
        * `UUID`: `0.2.2` -> `0.2.3`
        * `Coroutines`: `1.4.1` -> `1.4.2`
        * `MicroUtils`: `0.4.3` -> `0.4.6`
* `Core`:
    * Add `BowlingDiceAnimationType`

## 0.30.8

* `Common`:
    * `Version`:
        * `Kotlin`: `1.4.10` -> `1.4.20`
        * `Klock`: `1.12.1` -> `2.0.0`
        * `MicroUtils`: `0.4.1` -> `0.4.3`

## 0.30.7

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.4.0` -> `0.4.1`
* `Core`:
    * `TelegramAPIUrlsKeeper` will fix ending of host url since this version
    * New mechanisms in`PowLimiter` and `CommonLimiter` has been added
    * New builder `KtorRequestsExecutorBuilder`
        * New function `telegramBot`
* `Utils`:
    * Simple function `telegramBot(TelegramAPIUrlsKeeper)` has been deprecated with replacement by almost the same
    function in `Core`

## 0.30.6

* `Core`
    * `TextSource` properties has been renamed:
        * `asMarkdownSource` -> `markdown`
        * `asMarkdownV2Source` -> `markdownV2`
        * `asHtmlSource` -> `html`
    * `PrivateChat` override `id` property with type `UserId`
    * Several new extensions and functions in links creation:
        * New function `makeUsernameLink` with parameter `String`
            * New extension `Username#link` and function `makeLink(Username)`
        * Function `makeLinkToMessage` now able to get any type of chat
            * New extension `Message#link`
        * Old functions `makeLinkToAddStickerSet...` has been deprecated:
            * `makeLinkToAddStickerSet`
            * `makeLinkToAddStickerSetInMarkdownV2`
            * `makeLinkToAddStickerSetInMarkdown`
            * `makeLinkToAddStickerSetInHtml`

## 0.30.5

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.3.3` -> `0.4.0`
* `Core`:
    * Mechanism of `ChatMember` serialization has been changed
        * Since this version any `ChatMember` can be serialized (even outside in case it marked by `@Serializable`)
        * Since this version any `ChatMember` (included in this project) can be deserialized in common way
    * `User` property `id` has changed its type: now it is `UserId` (under the hood it is the same as `ChatId`)

## 0.30.4

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.3.1` -> `0.3.3`
* `Core`:
    * `MultilevelTextSource#textSources` has been safely renamed to `subsources`
    * `TextContent#fullEntitiesList` has been deprecated
    * Now `TextContent` implements `TextedInput`
        * `TextContent#entities` has been deprecated
    * `GroupEventMessage` now overrides `chatEvent` with type `GroupEvent`
    * `SupergroupEventMessage` now overrides `chatEvent` with type `SupergroupEvent`
    * Any `ChatEventMessage` now have generic type of its `chatEvent` (just like messages)
* `Utils`:
    * Old extensions related to chat events are deprecated:
        * `Flow<ChatEventMessage<*>>#divideBySource`
        * `Flow<ChatEventMessage<*>>#onlyChannelEvents`
        * `Flow<ChatEventMessage<*>>#onlyGroupEvents`
        * `Flow<ChatEventMessage<*>>#onlySupergroupEvents`
    * A lot of extensions for `Flow<ChatEventMessage>` has been added:
        * `FlowsUpdatesFilter#events`
        * `FlowsUpdatesFilter#channelEvents`
        * `FlowsUpdatesFilter#groupEvents`
        * `FlowsUpdatesFilter#supergroupEvents`
        * And a lot of other filters with specific types

## 0.30.3

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.3.0` -> `0.3.1`
* `Core`:
    * New type of requests exceptions `TooMuchRequestsException`. In fact it will be rare case when you will get this
    exception
    * `EmptyLimiter` has been renamed to `ExceptionsOnlyLimiter` and currently will stop requests after
    `TooMuchRequestsException` happen until retry time is actual
        * Now `ExceptionsOnlyLimiter` (previously `EmptyLimiter`) is a class
    * `AbstractRequestCallFactory` currently will not look at the response and wait if it have `RetryAfter` error. New
    behaviour aimed on delegating of this work to `RequestsLimiter`

## 0.30.2

* `Common`:
    * `Version`:
        * `Ktor`: `1.4.1` -> `1.4.2`
* `Core`:
    * New sealed class `SetWebhookRequest` which can be used in `SetWebhook` requests
* `Utils`:
    * Extensions `setWebhookInfoAndStartListenWebhooks` has been united in one extension with `SetWebhookRequest`
    incoming parameter

## 0.30.1

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.2.7` -> `0.3.0`
* `Utils`:
    *  Builder-style DSL for text sources - `buildEntities` (thanks to [djaler](https://github.com/djaler))

## 0.30.0 Bot API 5.0

**THIS UPDATE CONTAINS A LOT OF BREAKING CHANGES. PLEASE, BE CAREFUL ON UPGRADING OF YOUR PROJECT**

* `Common`:
    * `Version`:
        * `Coroutine`: `1.4.0` -> `1.4.1`
        * **NEW** `MicroUtils`: `0.2.7`
* `Core`:
    * Support of `logOut` method (`LogOut` object as a `Request`)
    * Support of `close` method (`Close` object as a `Request`)
    * `SetWebhook` updates:
        * New field `ipAddress`. It works the same as `ip_address` in [setWebhook](https://core.telegram.org/bots/api#setwebhook)
        section
        * New field `dropPendingUpdates`. It works the same as `drop_pending_updates` in [setWebhook](https://core.telegram.org/bots/api#setwebhook)
        section
    * New field `ExtendedPrivateChat#bio`
    * New data class `ChatLocation`
    * New field `UnbanChatMember#onlyIfBanned`
    * New fields `ExtendedChannelChat#linkedGroupChatId` and `ExtendedSupergroupChat#linkedChannelChatId`
    * New fields `ExtendedSupergroupChat#location`
    * New fields `AudioFile#fileName` and `VideoFile#fileName`
    * New fields `SendDocument#disableContentTypeDetection` and `InputMediaDocument#disableContentTypeDetection`
    * New request `UnpinAllChatMessages`
    * New parameter for `unpinChatMessage` method: `messageId`
    * New dice type `FootballDiceAnimationType`
    * Limits for dices has been changed
    * `commonDiceResultLimit` has been deprecated
    * New field `DiceAnimationType#valueLimits`
    * Locations updates:
        * New interface `Headed` with property `heading`
        * New interface `HorizontallyAccured` with property `horizontalAccuracy`
        * New interface `ProximityAlertable` with property `proximityAlertRadius`
        * `Location` class has been separated:
            * `StaticLocation` for static locations
            * `LiveLocation` for live locations
        * Property `Livable#livePeriod` now use typealias type `Seconds` (the same by meaning - `Int`)
        * `EditLocationMessage` now extends `Locationed`, `HorizontallyAccured`, `ProximityAlertable` and `Headed` interfaces
            * New properties in `EditChatMessageLiveLocation`: `horizontalAccuracy`, `heading`, `proximityAlertRadius`
            * New properties in `EditInlineMessageLiveLocation`: `horizontalAccuracy`, `heading`, `proximityAlertRadius`
        * Main constructor of `SendLocation` now is internal. Instead of that currently available next factories:
            * `SendLocation` - sending of static location without live parameters
            * `SendStaticLocation` - sending of static location without live parameters
            * `SendLiveLocation` - sending of live location with live parameters
        * `PositionedSendMessageRequest` now extends `Locationed`
        * `LocationContent#createResend` now can create `LiveLocation`
    * Support of `ProximityAlertTriggered`. It is `CommonEvent`
    * Property `pollQuestionTextLength` now have maximum up to `300`
    * Anonymous Admins:
        * New field `AdministratorChatMember#isAnonymous`
    * Several new interfaces of messages:
        * `SignedMessage` - any message which possibly have `authorSignature`
        * `WithSenderChatMessage` - any message which have `senderChat`. Property `senderChat` is not-nullable due to
        separation of implementators
        * `PublicMessage` - all channel messages have property `val chat: PublicChat` instead of common `val chat: Chat`
            * `ChannelMessage` - all channel messages have property `val chat: ChannelChat` instead of common `val chat: Chat`
                * Old `ChannelMessage` was safely renamed to `ChannelMessageImpl` (old name was set as typealias and deprecated)
            * `GroupMessage` - all group messages have property `val chat: GroupChat` instead of common `val chat: Chat`
                * `FromChannelGroupMessage` - instances should have property `val channel: ChannelChat`
                * `AnonymousGroupMessage` - instances may have setup property `authorSignature`
                * `CommonGroupMessage` - just common message
            * `PrivateMessage` - works like previous `CommonMessageImpl`
            * Previous `CommonMessageImpl` safely renamed to `PrivateMessageImpl`
    * New property `PromoteChatMember#isAnonymous`
    * Update all classes which must have `entities`/`caption_entities` fields
    * New request `CopyMessage`
    * New extension `List<TextSource>#makeString` for more comfortable work with new api with entities
    * Support for Google Places identifiers for venues
    * New extensions for text sources separating:
        * `List<TextSource>#separateForMessage`
        * `List<TextSource>#separateForCaption`
        * `List<TextSource>#separateForText`
    * Rewritten work with text sources and text parts:
        * Now any `Message` type with entities will have full list of entities. That means that parts without any
        formatter entities will use `RegularTextSource`
        * `MultilevelTextSource#textParts` has been deprecated. Now each `MultilevelTextSource` have its own
        `textSources` list
    * New dsl for creating of `TextSource` lists
    * Built-in `handleSafely` and `ExceptionHandler` is deprecated
    * New common factories for `StorageFile`
* `API`:
    * Extensions `TelegramBot#pinChatMessage` now support any `Chat` and `Message`s from any `Chat`
    * New extensions `TelegramBot#unpinAllChatMessages`
    * Extensions `TelegramBot#promoteChatMember` got `isAnonymous` parameter
    * All old api methods has been actualized to their analogs in `Core`
    * All `telegramBot` with `token: String` got `apiUrl` parameter
    * Factory `telegramBotWithCustomClientConfig` has been renamed to `telegramBot`

## 0.29.4

* `Core`:
    * `diceResultLimit` now is deprecated, use `commonDiceResultLimit` instead
    * New extension `slotMachineDiceResultLimit`
* `Utils`:
    * New enum `SlotMachineReelImages`
    * New extension `Int#asSlotMachineReelImage`
    * New data class `SlotMachineResult`
        * New extension `Dice#calculateSlotMachineResult`

## 0.29.3

* `Common`:
    * Version updates:
        * `Serialization`: `1.0.0` -> `1.0.1`
* `Core`:
    * New annotation `RiskFeature`. This annotation will be applied to the things which contains unsafe types usage
        * `SendMediaGroup` factory now marked with `RiskFeature`
    * Media groups updates:
        * New functions `SendPlaylist`
        * New functions `SendDocumentsGroup`
        * New functions `SendVisualMediaGroup`
    * New type `VisualMediaGroupMemberInputMedia : MediaGroupMemberInputMedia`
        * `InputMediaPhoto` now implements `VisualMediaGroupMemberInputMedia` instead of `MediaGroupMemberInputMedia`
        * `InputMediaVideo` now implements `VisualMediaGroupMemberInputMedia` instead of `MediaGroupMemberInputMedia`
    * New type `VisualMediaGroupContent : MediaGroupContent`
        * `PhotoContent` now implements `VisualMediaGroupContent` instead of `MediaGroupContent`
        * `VideoContent` now implements `VisualMediaGroupContent` instead of `MediaGroupContent`
    * New type `AudioMediaGroupContent : MediaGroupContent`
        * `AudioContent` now implements `AudioMediaGroupContent` instead of `MediaContent` and `CaptionedInput`
    * New type `DocumentMediaGroupContent : MediaGroupContent`
        * `DocumentContent` now implements `DocumentMediaGroupContent` instead of `MediaContent` and `CaptionedInput`
    * New type `AudioMediaGroupMemberInputMedia : MediaGroupMemberInputMedia`
        * `InputMediaAudio` now implements `AudioMediaGroupMemberInputMedia`
    * New type `DocumentMediaGroupMemberInputMedia : MediaGroupMemberInputMedia`
        * `InputMediaDocument` now implements `DocumentMediaGroupMemberInputMedia`
    * New extension `AudioFile#toInputMediaAudio`
    * `AudioContent` now implements `MediaGroupContent`
    * New extension `DocumentFile#toInputMediaDocument`
    * `DocumentContent` now implements `MediaGroupContent`
    * New dice type `SlotMachineDiceAnimationType`
    * New extension `TelegramMediaFile#asDocumentFile`
    * New extension `VideoFile#toInputMediaVideo`
    * New exception `WrongFileIdentifierException`
    * Extension `String#toInputMediaFileAttachmentName` now is deprecated
    * Property `ThumbedInputMedia#thumbMedia` now is deprecated
* `API`:
    * New extensions for media groups:
        * `TelegramBot#sendPlaylist`
        * `TelegramBot#replyWithPlaylist`
        * `TelegramBot#sendDocumentsGroup`
        * `TelegramBot#replyWithDocumentsGroup`
        * `TelegramBot#sendVisualMediaGroup`
        * `TelegramBot#replyWithVisualMediaGroup`
* `Utils`:
    * New extensions for `Flow`s:
        * `Flow<SentMediaGroupUpdate>#mediaGroupVisualMessages`
        * `Flow<SentMediaGroupUpdate>#mediaGroupAudioMessages`
        * `Flow<SentMediaGroupUpdate>#mediaGroupDocumentMessages`
    * New extensions for `FlowsUpdatesFilter`:
        * `FlowsUpdatesFilter#audioMessagesWithMediaGroups`
        * `FlowsUpdatesFilter#mediaGroupAudioMessages`
        * `FlowsUpdatesFilter#documentMessagesWithMediaGroups`
        * `FlowsUpdatesFilter#mediaGroupDocumentMessages`
        * `FlowsUpdatesFilter#mediaGroupVisualMessages`

## 0.29.2

* `Common`:
    * Version updates:
        * `Coroutines`: `1.3.9` -> `1.4.0`
    * Internal broadcast channels were replaced with `SharedFlow`
* `TelegramBotAPI-extensions-utils`:
    * Extension `ReceiveChannel#debounceByValue` has been deprecated

## 0.29.1

* `Common`:
    * Version updates:
        * `Serialization`: `1.0.0-RC2` -> `1.0.0`

## 0.29.0

* **THIS VERSION CONTAINS BREAKING CHANGES**
* ***PROJECT PACKAGES WERE CHANGED***
    * Packages in the whole project were changed `com.github.insanusmokrassar.TelegramBotAPI` -> `dev.inmo.tgbotapi`
    * Project group in repositories were changed: `com.github.insanusmokrassar` -> `dev.inmo`
    * Migration ([Examples migration](https://github.com/InsanusMokrassar/TelegramBotAPI-examples/pull/11)):
        1. Change implementation in your gradle files:
            * `implementation "com.github.insanusmokrassar:TelegramBotAPI-core:*"` -> `implementation "dev.inmo:tgbotapi.core:*"`
            * `implementation "com.github.insanusmokrassar:TelegramBotAPI-extensions-utils:*"` -> `implementation "dev.inmo:tgbotapi.extensions.utils:*"`
            * `implementation "com.github.insanusmokrassar:TelegramBotAPI-extensions-api:*"` -> `implementation "dev.inmo:tgbotapi.extensions.api:*"`
            * `implementation "com.github.insanusmokrassar:TelegramBotAPI:*"` -> `implementation "dev.inmo:tgbotapi:*"`
        2. Replace packages `com.github.insanusmokrassar.TelegramBotAPI` in the whole project by `dev.inmo.tgbotapi`
        

* `TelegramBotAPI-core`:
    * Now in forward info you can get `ForwardFromSupergroupInfo`
    * **BREAKING CHANGE** `SendVoice` factory function has changed its signature: now it have now `thumb`
    (according to the [documentation](https://core.telegram.org/bots/api#sendvoice))
    * `AudioFile` now can be converted to `VoiceFile`
    * `VoiceFile` now can be converted to `AudioFile`
* `TelegramBotAPI-extensions-api`:
    * ALL REQUESTS EXECUTOR USAGES WERE REPLACED WITH `TelegramBot` TYPEALIAS. It should not bring any break changes
    * Internal changes of `sendRegularPoll` and `sendQuizPoll` extensions
    * Variable `defaultLivePeriodDelayMillis` now is public
    * All `send` extensions for `TelegramBot` got their `reply` variations (issue [#144](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/144))
    * A lot of `send` extensions for `TelegramBot` got their variation with `Chat` instead of `ChatIdentifier`

## 0.28.4

* `Common`:
    * Version updates:
        * `Ktor`: `1.4.0` -> `1.4.1`
* `TelegramBotAPI-core`
    * Interface `GroupEventMessage` has been added ([#140](https://github.com/InsanusMokrassar/TelegramBotAPI/pull/140))
        * Old `GroupEventMessage` was renamed to `CommonGroupEventMessage` ([#140](https://github.com/InsanusMokrassar/TelegramBotAPI/pull/140))
    * Interface `SupergroupEventMessage` has been added ([#140](https://github.com/InsanusMokrassar/TelegramBotAPI/pull/140))
        * Old `SupergroupEventMessage` was renamed to `CommonSupergroupEventMessage` ([#140](https://github.com/InsanusMokrassar/TelegramBotAPI/pull/140))
    * Any `GroupEventMessage` now have `from` field ([#140](https://github.com/InsanusMokrassar/TelegramBotAPI/pull/140))
* `TelegramBotAPI-extensions-utils`
    * Extensions `Flow<ChatEventMessage>#onlyGroupEvents` and `Flow<ChatEventMessage>#onlySupergroupEvents` now returns
    `CommonGroupEventMessage` and `CommonSupergroupEventMessage`

## 0.28.3

* Common:
    * Version updates:
        * `Klock`: `0.12.0` -> `0.12.1`
        * `Kotlin serialization`: `1.0.0-RC` -> `1.0.0-RC2` (dependency `kotlinx-serialization-core` was replaced with
        `kotlinx-serialization-json` due to [kotlinx.serialization library update](https://github.com/Kotlin/kotlinx.serialization/blob/master/CHANGELOG.md#100-rc2--2020-09-21))
* `TelegramBotAPI-core`:
    * All `InlineQueryResult` has changed their type of id for more obvious relation between `InlineQueryResult#id` and
    `ChosenInlineResult#resultId`: `String` -> `InlineQueryIdentifier`
* `TelegramBotAPI-extensions-utils`:
    * Several extensions for updates flows based on `InlineQueryUpdate` has been added:
        * `Flow<InlineQueryUpdate>#onlyBaseInlineQueriesWithUpdates`
        * `Flow<InlineQueryUpdate>#onlyBaseInlineQueries`
        * `Flow<InlineQueryUpdate>#onlyLocationInlineQueriesWithUpdates`
        * `Flow<InlineQueryUpdate>#onlyLocationInlineQueries`
    * Several extensions for updates flows based on `ChosenInlineResultUpdate` has been added:
        * `Flow<ChosenInlineResultUpdate>.onlyBaseChosenInlineResultsWithUpdates`
        * `Flow<ChosenInlineResultUpdate>.onlyBaseChosenInlineResults`
        * `Flow<ChosenInlineResultUpdate>.onlyLocationChosenInlineResultsWithUpdates`
        * `Flow<ChosenInlineResultUpdate>.onlyLocationChosenInlineResults`

## 0.28.2

* `TelegramBotAPI-extensions-utils`:
    * Several commands shortcuts for `Flow<ContentMessage<TextContent>>` has been added:
        * `filterExactCommands`
        * `filterCommandsInsideTextMessages`
        * `filterCommandsWithArgs`
    * Extension `Flow<BaseSentMessageUpdate>.filterCommandsWithArgs` has changed its signature: now it will also have
    original message paired with list of text sources
    * Shortcut method `commonMessages` for `onlyCommonMessages`
    * Shortcuts `onlySentViaBot` and `withoutSentViaBot` now are extensions for any `Flow` with types which implementing
    `ContentMessage`

## 0.28.1

* Common:
    * Versions updates:
        * `Kotlin`: `1.4.0` -> `1.4.10`
        * `UUID`: `0.2.1` -> `0.2.2`
* `TelegramBotAPI-core`:
    * `ExceptionHandler` has changed its incoming type: `Exception` -> `Throwable`
        * `handleSafely` has changed its signature
        * `executeUnsafe` has changed its signature

## 0.28.0

* **THIS VERSION CONTAINS BREAKING CHANGES**
* ***PROJECT PACKAGES WERE CHANGED***
    * Project `TelegramBotAPI` -> `TelegramBotAPI-core`
    * Project `TelegramBotAPI-all` -> `TelegramBotAPI`

* `Common`:
    * Version updates:
        * `Kotlin`: `1.3.72` -> `1.4.0`
        * `Coroutines`: `1.3.8` -> `1.3.9`
        * `Serialization`: `0.20.0` -> `1.0.0-RC`
        * `Klock`: `1.11.14` -> `1.12.0`
        * `UUID`: `0.1.1` -> `0.2.1`
        * `Ktor`: `1.3.2` -> `1.4.0`
    * `buildMimeType` function now is cache-oriented getter which will save already got mime types into internal map
    * All deprecations from previous versions were removed
* `TelegramBotAPI-core`:
    * Typealias `TelegramBot` was added
    * Fully rebuilt `KtorCallFactory` interface to be able to handle custom answers from telegram bot api system
    * New implementation of `KtorCallFactory` was added: `DownloadFileRequestCallFactory`
        * `DownloadFile` request was added
    * All included `KtorCallFactory` realizations (except of abstract) now are objects:
        * `MultipartRequestCallFactory`
        * `SimpleRequestCallFactory`
    * `MediaGroupMemberInputMedia` members now will not have `arguments` property due to redundancy and buggy of that
    * Field `media` now is common for all `InputMedia` objects
* `TelegramBotAPI-extensions-api`:
    * Extensions `TelegramBot#downloadFile` were added
* `TelegramBotAPI-extensions-utils`:
    * All extensions for media groups (except of `mediaGroupId`) have changed their context: `List<MediaGroupMessage>`
    -> `List<CommonMessage<MediaGroupContent>>`
        * `forwardInfo`
        * `replyTo`
        * `chat`
        * `createResend` (several extensions)
    * Several extensions for downloading of files:
        * `HttpClient#loadFile`
        * `PathedFile#download`

## 0.27.11

* `TelegramBotAPI`:
    * Extension `String#filenameFromUrl` was created
        * Extension `PathedFile#filename` was created
* `TelegramBotAPI-extensions-utils`:
    * `Flow<Iterable<T>>.flatMap` extension was added
    * Extensions for `FlowUpdatesFilter` were added:
        * `FlowsUpdatesFilter#allSentMessagesFlow` (combination of `messageFlow` and `channelPostFlow`)
        * `FlowsUpdatesFilter#allSentMediaGroupsFlow` (combination of `messageMediaGroupFlow` and `channelPostMediaGroupFlow`)
        * `FlowsUpdatesFilter#sentMessages`
        * `FlowsUpdatesFilter#sentMessagesWithMediaGroups`
        * `FlowsUpdatesFilter#photoMessagesWithMediaGroups`
        * `FlowsUpdatesFilter#imageMessagesWithMediaGroups`
        * `FlowsUpdatesFilter#videoMessagesWithMediaGroups`
        * `FlowsUpdatesFilter#mediaGroupMessages`
        * `FlowsUpdatesFilter#mediaGroupPhotosMessages`
        * `FlowsUpdatesFilter#mediaGroupVideosMessages`
    * A lot of extensions like `Flow<BaseSentMessageUpdate>#textMessages` were added:
        * `Flow<BaseSentMessageUpdate>#animationMessages`
        * `Flow<BaseSentMessageUpdate>#audioMessages`
        * `Flow<BaseSentMessageUpdate>#contactMessages`
        * `Flow<BaseSentMessageUpdate>#diceMessages`
        * `Flow<BaseSentMessageUpdate>#documentMessages`
        * `Flow<BaseSentMessageUpdate>#gameMessages`
        * `Flow<BaseSentMessageUpdate>#invoiceMessages`
        * `Flow<BaseSentMessageUpdate>#locationMessages`
        * `Flow<BaseSentMessageUpdate>#photoMessages`
            * `Flow<BaseSentMessageUpdate>#imageMessages`
        * `Flow<BaseSentMessageUpdate>#pollMessages`
        * `Flow<BaseSentMessageUpdate>#stickerMessages`
        * `Flow<BaseSentMessageUpdate>#textMessages`
        * `Flow<BaseSentMessageUpdate>#venueMessages`
        * `Flow<BaseSentMessageUpdate>#videoMessages`
        * `Flow<BaseSentMessageUpdate>#videoNoteMessages`
        * `Flow<BaseSentMessageUpdate>#voiceMessages`
        * `Flow<BaseSentMessageUpdate>#mediaGroupMessages`
        * `Flow<BaseSentMessageUpdate>#mediaGroupPhotosMessages`
        * `Flow<BaseSentMessageUpdate>#mediaGroupVideosMessages`

## 0.27.10

* `TelegramBotAPI-extensions-api`:
    * Function `telegramBot(TelegramAPIUrlsKeeper)` was added
* `TelegramBotAPI-extensions-utils`:
    * Extension `Route#includeWebhookHandlingInRouteWithFlows` was added
    * A lot of extensions like `FlowsUpdatesFilter#textMessages` were added:
        * `FlowsUpdatesFilter#animationMessages`
        * `FlowsUpdatesFilter#audioMessages`
        * `FlowsUpdatesFilter#contactMessages`
        * `FlowsUpdatesFilter#diceMessages`
        * `FlowsUpdatesFilter#documentMessages`
        * `FlowsUpdatesFilter#gameMessages`
        * `FlowsUpdatesFilter#invoiceMessages`
        * `FlowsUpdatesFilter#locationMessages`
        * `FlowsUpdatesFilter#photoMessages`
            * `FlowsUpdatesFilter#imageMessages`
        * `FlowsUpdatesFilter#pollMessages`
        * `FlowsUpdatesFilter#stickerMessages`
        * `FlowsUpdatesFilter#textMessages`
        * `FlowsUpdatesFilter#venueMessages`
        * `FlowsUpdatesFilter#videoMessages`
        * `FlowsUpdatesFilter#videoNoteMessages`
        * `FlowsUpdatesFilter#voiceMessages`

## 0.27.9

* `Common`
    * Versions updates:
        * `Gradle Wrapper`: `6.5-all` -> `6.5.1-bin`
        * `Coroutines`: `1.3.7` -> `1.3.8`
        * `Klock`: `1.11.3` -> `1.11.14`
        * `UUID`: `0.1.0` -> `0.1.1`

## 0.27.8

* `TelegramBotAPI`:
    * `UnknownUpdateType` was renamed to `UnknownUpdate`
    * Refactoring and optimization of `FlowsUpdatesFilter`
    * `Venue` type was replaced to a new package: `com.github.insanusmokrassar.TelegramBotAPI.types.venue.Venue`
    * `Venue` type now implements `Locationed` and delegate realisation to its `location` field
    * `FoursquareId` and `FoursquareType` typealiases were added
* `TelegramBotAPI-extensions-utils`:
    * Several new functions `makeLinkToMessage` was added
    * `Foursquare` data class was added
        * Extension `Venue#foursquare` was added
        * Factory function `Venue` with `Foursquare` parameter was added

## 0.27.7

* `TelegramBotAPI`:
    * Operator function `unaryPlus` was added to `RowBuilder`. Now it is possible to write `row { +button }`
    * Function `flatMatrix` was added for single-row columns
    * Operator extension `RowBuilder#plus` was added to be able to write things like `row { this + button }`
* `TelegramBotAPI-extensions-api`:
    * Extensions `RequestsExecutor#sendVenue` with `Location` args were added
* `TelegramBotAPI-extensions-utils`:
    * Function `InlineKeyboardMarkup` for flat keyboards was added
    * Function `ReplyKeyboardMarkup` for flat keyboards was added

## 0.27.6

* `Common`:
    * Versions:
        * `Kotlin Coroutines`: `1.3.6` -> `1.3.7`
* `TelegramBotAPI`:
    * Interface `PossiblySentViaBot` has been added
        * Additional interface `PossiblySentViaBotCommonMessage` was added for more explicit typing declaration for
        compiler
            * Currently, only `ChannelMessage` and `CommonMessageImpl` are implementing the interface
            `PossiblySentViaBotCommonMessage`. It could be changed in future
    * Factory `buildMimeType` was added
    * `BuiltinMimeTypes` was added
    * Abstraction `ThumbedWithMimeTypeInlineQueryResult` with `thumbMimeType` field was added
        * `InlineQueryResultGif` and `InlineQueryResultMpeg4Gif` now extend `ThumbedWithMimeTypeInlineQueryResult`
        instead of `ThumbedInlineQueryResult`
* `TelegramBotAPI-extensions-utils`:
    * New extensions `onlyCommonMessages`, `onlySentViaBot` and `withoutSentViaBot` was added

## 0.27.5

* `Common`:
    * Versions:
        * `Klock`: `1.11.1` -> `1.11.3`
* `TelegramotAPI`:
    * Fix: for sending requests caption and text lengths limits were updated
    * New variant of `row` was added
    * `makeLinkToMessage` extensions has been deprecated (replaced into `TelegramBotAPI-extensions-utils`)
    * Next things was deprecated and replaced into `TelegramBotAPI-extensions-utils`:
        * All `String` formatting public extensions and functions
        * All extensions like `CaptionedInput#toHtmlCaptions`
        * All helper extensions for `List<BaseMessageUpdate>`
        * All `RequestsExecutor#executeAsync` and `RequestsExecutor#executeUnsafe`
    * `BotCommand` now more strictly check commands which passed to it
        * Regex `BotCommandNameRegex` was added
* `TelegramBotAPI-extensions-api`:
    * A lot of `RequesstExecutor#getChat` extensions was added for more explicit types showing
    * New `RequesstExecutor#setMyCommands` extension was added
    * New field `BotBuilder#ktorClientEngineFactory` introduced
        * Field `BotBuilder#ktorClientEngine` now is deprecated
* `TelegramBotAPI-extensions-utils`:
    * `safely` function was introduced. It is in `PreviewFeature` state currently
    * `makeLinkToMessage` extensions has been added
    * `makeLinkToAddStickerSet` function and its variations were added
    * Next tools was added from `TelegramBotAPI`:
        * All `String` formatting extensions and functions
        * All extensions like `CaptionedInput#toHtmlCaptions`
        * All helper extensions for `List<BaseMessageUpdate>`
            * Several new extensions for `SentMediaGroupUpdate` were added:
                * `SentMediaGroupUpdate#forwardInfo`
                * `SentMediaGroupUpdate#replyTo`
                * `SentMediaGroupUpdate#chat`
                * `SentMediaGroupUpdate#mediaGroupId`
            * Several `List<MediaGroupMessage>.createResend` extensions were added
        * `RequestsExecutor#executeAsync` and `RequestsExecutor#executeUnsafe`

## 0.27.4

* `TelegramBotAPI-extensions-utils`:
    * Several extensions for updates was added:
        * `onlyBaseMessageUpdates`
        * `onlySentMessageUpdates`
        * `onlyEditMessageUpdates`
        * `onlyMediaGroupsUpdates`
        * `onlySentMediaGroupUpdates`
        * `onlyEditMediaGroupUpdates`
    * Renames in chat filters extensions:
        * `filterBaseMessageUpdates` -> `filterBaseMessageUpdatesByChatId` and `filterBaseMessageUpdatesByChat`
        * `filterSentMediaGroupUpdates` -> `filterSentMediaGroupUpdatesByChatId` and `filterSentMediaGroupUpdatesByChat`

## 0.27.3

* `TelegramBotAPI`:
    * `UpdateDeserializationStrategy` is publicly available now
    * All `setWebhook` extensions was marked as deprecated, renamed and replaced into `TelegramBotAPI-extensions-utils`
    * Typealias `ExceptionHandler` was added - it will be used for `handleSafely`
    * `SetWebhook` factories signatures was changed (backward compatibility was not broken)
    * `executeUnsafe` now working differently
        * Now it is possible to pass exceptions handler into `executeUnsafe`
    * `BasketballDiceAnimationType` was added
    * `UnknownDiceAnimationType` now is deprecated due to renaming - currently it is typealias for `CustomDiceAnimationType`
        * `CustomDiceAnimationType` now is `data` class instead of common class
    * `FlowsUpdatesFilter` will use size 64 by default for internal broadcast channels
* `TelegramBotAPI-extensions-api`:
    * Long Polling extensions now are deprecated in this project. It was replaced into `TelegramBotAPI-extensions-utils`
    * Several `telegramBot` functions was renamed into `telegramBotWithCustomClientConfig`
    * Add one more `setWebhookInfo` realisation
* `TelegramBotAPI-extensions-utils`:
    * Extension `toTelegramUpdate` was added
    * Long Polling extensions were added
    * Updates utils were added
    * New extensions `startListenWebhooks`, `setWebhookInfoAndStartListenWebhooks` and `includeWebhookHandlingInRoute` was added
    * New extension `CoroutineScope#updateHandlerWithMediaGroupsAdaptation` was added
    * New extension `flowsUpdatesFilter` was added
* `TelegramBotAPI-all`:
    * Project was created

## 0.27.2

* `Common`:
    * Versions:
        * Coroutines: `1.3.5` -> `1.3.6`
        * Klock: `1.10.5` -> `1.11.1`
* `TelegramBotAPI`:
    * Expected class `MimeType` was added
        * Field `MimeTyped#mimeType` now typed by `MimeType` instead of `String`
    * `MediaGroupMemberInputMedia` children now can be deserialized (but only those ones who are declared inside library)
* `TelegramBotAPI-extensions-utils`:
    * Chat events splitters added:
        * Extension `Flow<ChatEventMessage>#onlyChannelEvents` was added
        * Extension `Flow<ChatEventMessage>#onlyGroupEvents` was added
        * Extension `Flow<ChatEventMessage>#onlySupergroupEvents` was added

## 0.27.1

* `TelegramBotAPI`:
    * Interface `Explained` and subsinterfaces `ExplainedInput` and `ExplainedOutput` was added
        * Class `QuizPoll` now implement `ExplainedInput`
        * In `QuizPoll#caption` and `QuizPoll#captionEntities` are deprecated now
        * Class `SendQuizPoll` now implement `ExplainedOutput`
        * In `SendQuizPoll#caption` is deprecated now
    * `explanationLimit` range was added as future replacement of `quizPollExplanationLimit`
        * `quizPollExplanationLimit` now is deprecated
    * Extensions `toMarkdownExplanations`, `toMarkdownV2Explanations` and `toHtmlExplanations` was added
    * Typealias `FullTextSourcesList` was added
        * All extensions `fullEntitiesList` now return `FullTextSourcesList`
        * All extensions of `List<TextSource>` now are extensions for `FullTextSourcesList`
* `TelegramBotAPI-extensions-api`:
    * `sendQuizPoll` now is using `explanation` parameter instead of `caption`

## 0.27.0

* `Common`:
    * Versions updates:
        * `Kotlin`: `1.3.71` -> `1.3.72`
        * `Klock`: `1.10.3` -> `1.10.5`
* `TelegramBotAPI`:
    * Typealias `LongSeconds` was added for correct explanation of seconds in `Long` primitive type
    * Several new fields was added:
        * `explanationField`
        * `explanationEntitiesField`
        * `openPeriodField`
        * `closeDateField`
    * Extension `List<TextPart>#justTextSources` was added for mapping of `List<TextPart>` to `List<TextSource>`
    * Field `SendPoll#closeInfo` was added
        * Range `openPeriodPollSecondsLimit` was added and used in all `SendPoll` requests for checking income data
    * `SendQuizPoll` now able to use fields `caption` and `parseMode` for `explanation` functionality
        * `quizPollExplanationLimit` was added for checking `QuizPoll` explanation size
    * Field `TextLinkTextSource#url` was added
    * Field `TextMentionTextSource#user` was added
    * Sealed class `ScheduledCloseInfo` was added
        * Class `ExactScheduledCloseInfo` was added for cases with `close_date`
        * Class `ApproximateScheduledCloseInfo` was added for cases with `open_period`
    * Field `Poll#scheduledCloseInfo` was added
    * Sealed class `MultipleAnswersPoll` was added
        * Class `RegularPoll` now extends `MultipleAnswersPoll`
    * `Dice` class was replaced into new package
    * Sealed class `DiceAnimationType` was added
        * Field `Dice#animationType` was added as `emoji` API representation
        * `SendDice` now receive `animationType` as second parameter
    * For `List<TextSource>` was added several extensions:
        * `toMarkdownCaptions`
        * `toMarkdownTexts`
        * `toMarkdownV2Captions`
        * `toMarkdownV2Texts`
        * `toHtmlCaptions`
        * `toHtmlTexts`
* `TelegramBotAPI-extensions-api`:
    * All `RequestsExecutor#sendDice` extensions now accept `DiceAnimationType?` as second parameter
    * All `RequestsExecutor#sendRegularPoll` extensions now accept `ScheduledCloseInfo` fourth parameter
    * All `RequestsExecutor#sendQuizPoll` extensions now accept additional parameters `caption: String` and
    `parseMode: ParseMode` for `explanation` functionality and `closeInfo: ScheduledCloseInfo?` for autoclose poll
    functionality
* `TelegramBotAPI-extensions-utils`:
    * Several shortcuts for `ScheduledCloseInfo` was added:
        * `closePollExactAt`
        * `closePollExactAfter`
        * `closePollAfter`

## 0.26.4

* `TelegramBotAPI`:
    * Now any getting of updates will return `UnknownUpdateType` when inside of deserialization will be
    `SerializationException` or `NotImplemented` error
    * `CallbackGame` currently is an object
        * It is possible to use `CallbackGame` for now
            * `CallbackGameInlineKeyboardButton` now will not accept `callbackGame` as income object
    * Now it is possible to pass exception handler in webhook

## 0.26.3

* `TelegramBotAPI`:
    * `CallbackGameInlineKeyboardButton` was added
    ([Issue-79](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/79),
    [PR-80](https://github.com/InsanusMokrassar/TelegramBotAPI/pull/80))
    * `UnknownInlineKeyboardButton` was added. It is unavailable for creating, but you can receive it, for example, in
    `InlineQueryResult`
    * `Update` now will be created even if was `SerializationException` inside of creating the update instance - in this
     case will be created `UnknownUpdateType`
    * `UnknownUpdateType$rawJson` value now is included (`JsonElement`)
    * **EXPERIMENTALLY** `BaseEditMessageUpdate#data` now is `CommonMessage<*>`
    * Suspend inline function `handleSafely` was added
        * `KtorRequestsExecutor` now use `handleSafely` instead of `try` with `supervisorScope`
        * `UpdatesPolling` now use `handleSafely` instead of `try` with `supervisorScope`

## 0.26.2

* `TelegramBotAPI`:
    * Now `EditMediaGroupUpdate` also extends `BaseEditMessageUpdate`
    * **EXPERIMENTALLY** Now all `TextSource` realisations will contain `source` field as a property inside of them
* `TelegramBotAPI-extensions-api`:
    * `startGettingFlowsUpdates` extension which do not require filter (but return a new one) was added
* `TelegramBotAPI-extensions-utils`:
    * Subproject was added
    * `filterBaseMessageUpdates`, `filterSentMediaGroupUpdates` and `filterEditMediaGroupUpdates` extensions was added
    * `filterCommandsWithArgs`, `filterExactCommands` and `filterCommandsInsideTextMessages` extensions was added
    * `asContentMessagesFlow`, `asChatEventsFlow` and `asUnknownMessagesFlow` extensions was added
    * `withContentType` extension was added
        * `onlyAnimationContentMessages` extension was added
        * `onlyAudioContentMessages` extension was added
        * `onlyContactContentMessages` extension was added
        * `onlyDiceContentMessages` extension was added
        * `onlyDocumentContentMessages` extension was added
        * `onlyGameContentMessages` extension was added
        * `onlyInvoiceContentMessages` extension was added
        * `onlyLocationContentMessages` extension was added
        * `onlyPhotoContentMessages` extension was added
        * `onlyPollContentMessages` extension was added
        * `onlyStickerContentMessages` extension was added
        * `onlyTextContentMessages` extension was added
        * `onlyVenueContentMessages` extension was added
        * `onlyVideoContentMessages` extension was added
        * `onlyVideoNoteContentMessages` extension was added
        * `onlyVoiceContentMessages` extension was added

## 0.26.1

* `TelegramBotAPI`:
    * `BotCommand` now will check and throw error in case when command or description lengths is/are incorrect
    * `StorageFile` now is common for all platforms
        * JavaScript realization was removed due to its redundancy
        * JVM realization was replaced with `fun` factory
        * `StorageFile` now able to accept any factory of `Input`
        * `StorageFileInfo` was added to avoid strange collisions with throws in `StorageFile`
    * Fixes issue with `hashTag` for markdown
    * `InvalidPhotoDimensionsException` was added for cases when `PHOTO_INVALID_DIMENSION` answer received
    * Other fixes

## 0.26.0

* `Common`:
    * Versions updates:
        * `Klock`: `1.10.0` -> `1.10.3`
* `TelegramBotAPI`:
    * Request `SendDice` was added (calling [sendDice](https://core.telegram.org/bots/api#senddice))
    * Class `Dice` was added (type [dice](https://core.telegram.org/bots/api#dice))
    * Class `DiceContent` was added (for including it in [message](https://core.telegram.org/bots/api#message) object)
    * `BotCommand` was added
    * `GetMyCommands` request was added
    * `SetMyCommands` request was added
    * `GetMe` now is object instead of class
    * `GetMe` was replaced into package `com.github.insanusmokrassar.TelegramBotAPI.requests.bot.GetMe`
    * `CreateNewStickerSet` renamed to `CreateStaticNewStickerSet`
    * `CreateNewAnimatedStickerSet` request was added (it handle work with `tgs_sticker`)
    * `StickerSet#thumb` was added
    * `AddStickerToSet` renamed to `AddStaticStickerToSet`
    * `AddAnimatedStickerToSet` request was added
    * `SetStickerSetThumb` request was added
    * Most of sticker actions now implements `StandardStickerSetAction` instead of `StickerSetAction`
    * `getUpdatesLimit` was added to be ensure in get updates limit
    * `GetUpdates` now will check count of requesting updates and throw exception if it is not in range `1 .. 100`
    * `GetUpdates#limit` now is not nullable and by default set up to 100
* `TelegramBotAPI-extensions-api`:
    * Extensions `sendDice` was added
    * Extension `getMyCommands` request was added
    * Extension `setMyCommands` request was added
    * Extension `getMe` was replaced into package `com.github.insanusmokrassar.TelegramBotAPI.extensions.api.bot.GetMeKt.getMe`
    * **All extensions `createNewStickerSet` was renamed to `createNewStaticStickerSet`**
    * Extensions `createNewAnimatedStickerSet` was added
    * **All extensions `addStickerToSet` was renamed to `addStaticStickerToSet`**
    * Extensions `addAnimatedStickerToSet` was added
    * Extensions `setStickerSetThumb` was added
    * Extension `startGettingUpdates` now will drop `SentMediaGroupUpdate` in case if it is the last in updates group
    and size of retrieved updates is equal to 100 (max count of retrieved updates)
    * Extensions `getUpdates` now will receive only not nullable `limit` parameter

## 0.25.1

* Update kotlin: `1.3.70` -> `1.3.71`
* Fix of error inside of update utils for media groups

## 0.25.0

* Common:
    * Versions updates:
        * `Kotlin`: `1.3.61` -> `1.3.70`
        * `Kotlin coroutines`: `1.3.3` -> `1.3.5`
        * `Kotlin serialization`: `0.14.0` -> `0.20.0`
        * `Ktor`: `1.3.1` -> `1.3.2`
        * `Klock`: `1.8.7` -> `1.10.0`
        * `UUID`: `0.0.7` -> `0.1.0`
* `TelegramBotAPI`:
    * `Bot` implementations (as and `Bot` itself) now have not nullable `username`
    * `File#toInputFile` extension now will throw error when file does not exists
    * `InlineKeyboardMarkup` will check that `PayInlineKeyboardButton` is the first in case if it is exists in
    `keyboard`
    * `makeLinkToMessage` now is not `PreviewFeature`
    * All deprecations was removed
    * `RequestException` now extends `io.ktor.utils.io.errors.IOException` instead of `kotlinx.io.IOException`
    * `Any#toJson` now is NOT `inline`
    * `FlowsUpdatesFilter` now as marked my annotation `FlowPreview`
    * `PathedFile#fullUrl` now is not `inline` function
    * `SimpleRequest#json` now is not `inline` and `internal` function
    * `FlowsUpdatesFilter` now have two additional flows: `pollAnswerFlow`, `unknownUpdateTypeFlow`
    * `ExtendedUser` (`typealias`) was added as a `PreviewFeature`

## 0.24.1

* `TelegramBotAPI`:
    * `UpdateReceiver` was replaced to the package `com.github.insanusmokrassar.TelegramBotAPI.updateshandlers`
    * All functions inside `com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.UpdatesPolling` are deprecated
    and will be removed in some soon versions. Their replacement are able inside `TelegramBotAPI-extensions-api`
    * `UpdatesFilter` is interface for now
        * Previous `UpdatesFilter` class was renamed to `SimpleUpdatesFilter` and for backward compatibility was added
        builder function `UpdatesFilter`, which will be removed in near releases
        * `FlowsUpdatesFilter` now implements `UpdatesFilter`
    * `BaseSentMessageUpdate` and `BaseEditMessageUpdate` interfaces was added
        * `EditChannelPostUpdate` now is implementing `BaseEditMessageUpdate` interface
        * `EditMessageUpdate` now is implementing `BaseEditMessageUpdate` interface
        * `ChannelPostUpdate` now is implementing `BaseSentMessageUpdate` interface
        * `MessageUpdate` now is implementing `BaseSentMessageUpdate` interface
    * `UpdatesPoller` and all its usages, childs and childs usages now are deprecated
    * `GetUpdates#timeout` type now is `Seconds` (in fact it is `Int` as previously)
    * `KtorRequestsExecutor` now is using a copy of incoming `HttpClient` object and install `HttpTimeout` feature
        * `AbstractRequestCallFactory` now setting up a custom delay in case if request is `GetUpdates`
* `TelegramBotAPI-extensions-api`:
    * All functions from `com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.UpdatesPolling` now available
    in package `com.github.insanusmokrassar.TelegramBotAPI.extensions.api.updates.UpdatesPolling`
    * Now new method of getting updates available: `startGettingUpdates` with `UpdatesFilter` as incoming first
    parameter
    * `startGettingUpdates` with `receiver` and `allowedUpdates` parameters now will handle updates by itself

## 0.24.0

* `TelegramBotAPI`:
    * All suspend functions for `RequestsExecutor` was removed (due to replacement into
    [TelegramBotAPI extensions project](TelegramBotAPI-extensions-api/README.md))
    * `ForwardFromChannelInfo#channelChat` now is `ChannelChat` instead of `Chat`
* `TelegramBotAPI-extensions-api`:
    * Most part of sending media messages functions was removed and replaced with their `InputFile` args analogs

## 0.23.3 Project separating prepare version

__API Extensions__

* Project created
* For `SendPhoto` was added new functions for uploading of `MultipartFile`
* `deleteWebhook` extension for `RequestsExecutor` was added

__Telegram Bot API__

* All `RequestsExecutor` extensions related to Telegram Bots API was replaced into `API Extensions` project

## 0.23.2

* Fixes in `InputMedia` - `media` field was not included to serialization

## 0.23.1

* Versions updates:
    * Klock `1.8.6` -> `1.8.7`
    * Ktor `1.3.0` -> `1.3.1`
* Now it is possible to get updates by polling with custom executor engine
* `CommonMultipartFileRequest` now is internal
* Added `LiveLocation` class for more useful tracking live locations
* `InvoiceOfPayment` is renamed to `InvoiceContent` and now is `MessageContent` instead of `PaymentInfo`
* `SendInvoice` now return `ContentMessage<InvoiceContent>`
* `paymentInfo` inside of `CommonMessageImpl` now can be set only to `SuccessfulPaymentInfo`
* Added `RecordVideoNoteAction` and `UploadVideoNoteAction` for `record_video_note` and `upload_video_note` actions
* For most part of messages was added `RequestsExecutor` extensions for more useful way of usage
* `toInputFile` extensions now will return more exact types
* Now it is possible to send broadcast channels size for `FlowsUpdatesFilter`

## 0.23.0 TelegramBotAPI 4.6

* `Poll` now is sealed class
    * `RegularPoll` type was added to represent polls with type `regular`
    * `QuizPoll` type was added to represent polls with type `quiz`
    * `UnknownPollType` type was added to represent polls which are unknown in current version
* `AnonymousPollOption` was renamed to `SimplePollOption`
* `SendPoll` was rewritten as sealed class
    * `SendRegularPoll` was created and represent `sendPoll` method with type `regular`
    * `SendQuizPoll` was created and represent `sendPoll` method with type `quiz`
* `Poll#createRequest` extension was added
* `PollAnswerUpdate` type of update was added
    * `PollAnswer` type was added
    * `UpdatesFilter` now support work with `PollAnswerUpdate`
* `language` field in PreTextSource now correctly passed from telegram MessageEntities
* `KeyboardButton` now is sealed class
    * Fixed problem of incorrect representation of this class (any type of request can be created separately)
    * Added new types of `KeyboardButton`:
        * `UnknownKeyboardButton`
        * `SimpleKeyboardButton`
        * `RequestContactKeyboardButton`
        * `RequestLocationKeyboardButton`
        * `RequestPollKeyboardButton`
    * Added new type `KeyboardButtonPollType`:
        * `UnknownKeyboardButtonPollType`
        * `RegularKeyboardButtonPollType`
        * `QuizKeyboardButtonPollType`
* `User` now is sealed class
    * `CommonUser` was added as representation of default `User`
    * `Bot` was added as representation of bot user (it is sealed class)
        * `ExtendedBot` with additional info
        * `CommonBot` with simple info
    * `GetMe` now return `ExtendedBot` object
    * Now extension `javaLocale` is extension for `CommonUser`

## 0.22.2 CashTag and independent updates handling

* `cashtag` entity type was added
* Several `Unknown*` realizations was added:
    * `UnknownUpdateType`
    * `UnknownMessageType`
    * `UnknownChatType`
    * `UnknownCallbackQueryType`
* `UpdatesFilter` now have one additional income callback: `unknownUpdateTypeCallback`
    * `createSimpleUpdateFilter` can receive one more callback: `unknownCallback` (for `unknownUpdateTypeCallback`)

## 0.22.1 MediaContent#asInputMedia

* All `MediaContent` instances now can create their `InputMedia` analog
* New annotation `PreviewFeature` was added to mark new thing as preview for the time
while they can work incorrectly
* Added links utils:
    * `makeLinkToMessage` have two signatures - for direct creating using username and for abstract creating using
    chat id

## 0.22.0

* **`KtorCallFactory` must return `HttpStatement` instead of `HttpClientCall`**
* `SendMessage` was renamed to `SendTextMessage` and previous `SendMessage` is deprecated
* All `AbleToBe*` interfaces was renamed to `Possibly*`
    * `AbleToBeEditedMessage` -> `PossiblyEditedMessage`
    * `AbleToBeForwardedMessage` -> `PossiblyForwardedMessage`
    * `AbleToBeMarkedUp` -> `PossiblyMarkedUp`
    * `AbleToBeEditedMessage` -> `PossiblyEditedMessage`
* `ForwardedMessage` type was renamed to `ForwardInfo`
    * `AnonymousForwardedMessage` -> `AnonymousForwardInfo`
    * `UserForwardedMessage` -> `UserForwardInfo`
    * `ForwardedFromChannelMessage` -> `ForwardFromChannelInfo`
    * `PossiblyForwardedMessage#forwarded` field now renamed to `forwardInfo`
* All serializers in library now are `internal`. **If you have used some of them or I have marked as internal by a
mistake - don't hesitate to say this.**
* `EditChatMessage` now have generic type and extends `SimpleRequest<ContentMessage<GenericType>>`
* `ResendableContent` now extends `Request<out Message>` instead of `Request<Message>`
* Most part of requests have changed return type. They are listed below:
    <details>
    
    * `ForwardMessage`
    * `GetChatAdministrators`
    * `EditChatMessageLiveLocation`
    * `StopChatMessageLiveLocation`
    * `EditChatMessageText`
    * `EditChatMessageCaption`
    * `EditChatMessageMedia`
    * `EditChatMessageReplyMarkup`
    * `SendAnimation`
    * `SendAudio`
    * `SendContact`
    * `SendLocation`
    * `SendTextMessage`
    * `SendPoll`
    * `SendVenue`
    * `SendGame`
    * `SendDocument`
    * `SendMediaGroup`
    * `SendPhoto`
    * `SendVideo`
    * `SendVideoNote`
    * `SendVoice`
    * `SendSticker`
    
    </details>
* Changed type of `createResend`
    <details>
    
    * `GameContent`
    * `LocationContent`
    * `PollContent`
    * `TextContent`
    * `VenueContent`
    * `AnimationContent`
    * `AudioContent`
    * `DocumentContent`
    * `ContactContent`
    * `PhotoContent`
    * `VideoContent`
    * `VideoNoteContent`
    * `VoiceContent`
    * `StickerContent`
    
    </details>
* Version updates:
    * Ktor `1.2.6` -> `1.3.0`

## 0.21.0 TelegramBotAPI 4.5

* _**All `MessageEntity`'es now are replaced with `TextPart`**_
* Added support of strikethrough and underline
    * Added `UnderlineTextSource`
    * Added `StrikethroughTextSource`
    * Added support in `RawMessageEntity`
* Added support of `MarkdownV2`
* Now will not be thrown exception when there is income unknown type of `RawMessageEntity`. Instead of this will be
created `RegularTextSource` with the same text
* Fixed problem that usually string formatting did not trigger escaping of control characters
* Actualized work with `pre` type of text - now it is possible to use `language` for formatting of text
* Removed constructor of `TextMentionTextSource`, which was deprecated previously
* All `TelegramMediaFile` instances now have field `fileUniqueId`, which represents `file_unique_id` field from API
* Now `ChatPhoto` have two additional fields: `smallFileUniqueId` and `bigFileUniqueId`
* Now any administrator object instance have `customTitle` nullable field
* Added the new request `SetChatAdministratorCustomTitle` to manage the custom titles of administrators promoted by the
bot.
* Added the field `slowModeDelay` to the `ExtendedSupergroupChat` objects.

* `CaptionedInput` now have extension `fullEntitiesList` which will return list of `TextPart` with `RegularSource`'s
* `TextPart` added - it will be used as part of some text and can be not related to telegram bot
* `MultilevelTextSource` was added - it is type of `TextSource`, which can have subsources as parts of this text
* In all `TextSource`s all fields now are lazy for avoiding of potential risk for performance issues

* Updates in versions:
    * Coroutines `1.3.2` -> `1.3.3`
    * Klock `1.8.0` -> `1.8.6`
    * UUID `0.0.6` -> `0.0.7`

## 0.20.4

* Now `setWebhook` supports setting up of path for listening
* Now `setWebhook` supports custom listen address even if certificate was not provided

## 0.20.3

* Now `LeftChatMamber` is a `CommonEvent`

## 0.20.2

* New exception type `MessageIsNotModifierException` was added
* New exception type `MessageToEditNotFoundException` was added
* Now exceptions in requests will be caught correctly

## 0.20.1

* `User` now implement `PrivateChat`
* `TextMentionMessageEntity` now accept `PrivateChat` instead of `User` in main constructor
    * `TextMentionMessageEntity` now contains not user, but contains `PrivateChat`
    * Fixed: `TextMentionMessageEntity#asHtmlSource` previously worked incorrect
* Abstraction `TextSource`
    * `MessageEntity` now extends `TextSource`
    * `createFormattedText` method now accept `List<TextSource>`
    * `createHtmlText` method now accept `List<TextSource>`
    * `createMarkdownText` method now accept `List<TextSource>`
    * A lot of `TextSource` implementors was added. More info [here](src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/types/MessageEntity/textsources/)
        * All `MessageEntity` implementations now are using new `TextSource` analogues as delegates

## 0.20.0 MPP Migration

* Time library change: `joda-time` -> `com.soywiz.korlibs.klock:klock`
* `Currencied` now using as `currency` value with type `String`
    * For `Java` there is `Currencied#javaCurrency` extension function, which will give an old currency work way
* `User` now have no field `userLocale`
    * For `Java` there is `User#javaLocale` extension function, which will give an old locale work way

## 0.19.0 ImplicitReflection removing

* Total rework of serialization for requests. Now all `SimpleRequest` children have:
    * `requestSerializer` - field, which must provide serializer of current type
    * `resultDeserializer` - field, which must provide opportunity to deserializer result. Previously it was a function
* Removed deprecations:
    * `com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.WebhookPrivateKeyConfig`
    * `com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.UpdatesFilter`
    * `com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.createSimpleUpdateFilter`
    * `com.github.insanusmokrassar.TelegramBotAPI.utils.createMarkdownText`
    * `com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownCaption`
    * `com.github.insanusmokrassar.TelegramBotAPI.utils.toMarkdownText`
    * `com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.KtorUpdatesPoller`
    * `com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.CaptionedMediaContent`
    * `com.github.insanusmokrassar.TelegramBotAPI.types.message.CommonForwardedMessage`
    * `com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.CaptionedInputMedia`
    * `com.github.insanusmokrassar.TelegramBotAPI.types.games.Game#text`
    * `com.github.insanusmokrassar.TelegramBotAPI.types.games.Game#textEntities`
    * `com.github.insanusmokrassar.TelegramBotAPI.types.files.PathedFileKt.makeFileUrl`
    * `com.github.insanusmokrassar.TelegramBotAPI.types.files.PathedFileKt.downloadingFilesBaseUrl`
    * `com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.Data`
    * `com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ByInlineMessageId`
    * `com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.ByMessageId`
    * `com.github.insanusmokrassar.TelegramBotAPI.bot.RequestException`
    * `com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.ReplyMessageNotFound`
    * `com.github.insanusmokrassar.TelegramBotAPI.bot.BaseRequestsExecutor#baseUrl`
    * `com.github.insanusmokrassar.TelegramBotAPI.bot.BaseRequestsExecutor#constructor(token, hostUrl)`
    * `com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorRequestsExecutor#constructor(token, client, hostUrl, callsFactories, excludeDefaultFactories, requestsLimiter, jsonFormatter)`
    * `com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorRequestsExecutor#constructor(token, engine, hostUrl)`

## 0.18.1 Libraries update

* Update libraries:
    * `kotlin`: 1.3.41 -> 1.3.61
    * `kotlin coroutines`: 1.2.2 -> 1.3.2
    * `kotlin serialization`: 0.11.1 -> 0.14.0
    * `joda time`: 2.10.3 -> 2.10.5
    * `ktor`: 1.2.3 -> 1.2.6
* `BotAction` now will be deserialized in a little bit other way
    * `BotActionSerializer` now is internal
* Most part of serializers now are objects (instead of classes as was previously)

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

## 0.16.1

* Now old uncommon `CaptionedMediaContent` and `CaptionedInputMedia` are replaced by almost the same
interfaces `CaptionedInput` and `CaptionedOutput`. They are both implementing `Captioned` interface
* `AnimationContent` now is `CaptionedInput`

## 0.16.0 Bot API 4.3

* `LoginURL` and `LoginURLInlineKeyboardButton` has been added
* `replyMarkup` field was added to the `CommonMessage` objects via `AbleToBeMarkedUp` interface
* `SwitchInlineQueryCurrentChatInlineKeyboardButton#switchInlineQueryCurrentChat` field fixed
* `InlineKeyboardButton` now is sealed class and all its possible realisations are inside of its class file
* `String#asUsername` method renamed to `String#toUsername`
* Several `toChatId` extensions added

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

## 0.14.2 MediaGroups edit hotfixes

* `convertWithMediaGroupUpdates` extension added
* All media group converting extensions are internal for now
* Fixes according to updates in converting of updates to media group updates

## 0.14.1

* Replace `UpdatesFilter` and `UpdatesPoller` into another package
* Replace `WebhookPrivateKeyConfig`
* Added `FlowsUpdatesFilter`
* `UpdatesFilter` now have additional callback for polls
* `StopPoll#replyMarkup` now is optional

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

## 0.12.7 Hotfix version

* Now temporary all requests of input media will contains `file` field

## 0.12.6 Libraries updates

* `kotlin` version `1.3.21` -> `1.3.30`
* `kotlin coroutines` version `1.1.1` -> `1.2.0`
* `kotlin serialization` version `0.10.0` -> `0.11.0`
* `ktor` version `1.1.2` -> `1.1.3`
* Added `DeleteWebhook` request
* All default `startGettingOfUpdates` (in fact - method `start` of `UpdatesPoller`) are suspend and
will try to delete webhook

## 0.12.5 `MediaContent` improvements

* Now `MediaGroupContent` is `MediaContent`
* All `MedaContent` now have no generics and have basic `TelegramMediaFile` media field

## 0.12.4

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

## 0.12.3 Cleaning

* Refactor, optimizing and cleaning of code
* Removed deprecated method `T#toJsonWithoutNulls()`
* Renamed instances of `MediaGroupMessage`s and refactored their interfaces. `ChannelMediaGroupMessage`
will not contain `user` field (but `CommonMediaGroupMessage` will have)
* Now `MediaCollectionContent` is `MediaContent` (classes of this interface must choose best
media for present out)
    * `PhotoContent` now choose biggest photo size from its collection as `media`
* Fix in order of media group messages which was received by webhooks

## 0.12.2

* New in `MediaGroupUpdate`:
    * It is subtype of `Update` and can be use as regular update with list of messages
    * Data now is list with `MediaGroupMessage`
    * Added field `origins` which represent origin updates for `MediaGroupMessage`
    * `updateId` now represent LAST id of origins updates
* `UpdatesFilter` and other objects now work with `UpdateReceiver<Update>` as common supertype
for receivers.

## 0.12.1 Hotfix for media groups

* Added additional media group types (like `MessageMediaGroupUpdate`)
* Fixed handling of media group updates in `UpdatesFilter`

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

## 0.11.0

* Kotlin `1.3.11` -> `1.3.21`
* Kotlin coroutines `1.1.0` -> `1.1.1`
* Kotlin serialization `0.9.1` -> `0.10.0`
* Ktor `1.1.1` -> `1.1.2`

## 0.10.3

* Hotfix for username data class

## 0.10.2

* Fixes in `Username`
    * Now you can create username object using string which is not starting with `@`
    * Now `Username` correctly comparing with strings, which are not starting with `@`
* Now most part of usernames in library have type `Username`
* Fix `replyMarkup` in `InlineQueryResultArticle`

## 0.10.1

* Change algorithm of `executeUnsafe`: now it use loop instead of recursive calling
* Add additional `startGettingUpdates` with better management of received updates for media groups
* Now `MediaGroupMessage` is `CommonMessage` with `MediaGroupContent` content
* Added extensions `replyTo`, `forwarded` and `chat` for `List<BaseMessageUpdated>` for comfortable
work with media groups lists
* Fix `parseMode` of `InputTextMessageContent`

## 0.10.0

* Most part of abstractions was replaced from `requests` and `types` on more high level
* Added abstraction `CommonVenueData`
* Added abstraction `CommonContactData`
* Added `InputMessageContent`
* Update some types and requests according to abstractions replacing
* Add all `InlineQueryResult`, `InputMessageContent` and other inline mode types
* Fixes in edition of inline messages and their result types
* Replace basic exception and add `ReplyMessageNotFound` exception

## 0.9.3

* `KtorRequestsExecutor` now can use custom `JSON` string formatter (by default - non strict)
* `ResponseParameters` renamed to `Response`
* Add `RequestError` sealed class and described in documentation known errors
* Add `ResponseParametersRaw` which can create error based on input parameters
* Add `parameters` field in `Response` and remove useless fields from `Response`
* Add `leftToRetry` parameter in `RetryAfterError`
* Add handling of `RetryAfterError` in `KtorRequestsExecutor`

## 0.9.2

* `RequestsExecutor#executeAsync(Request, CoroutineScope)` now will return `Deferred` for cases when you need result
* `RequestsExecutor#executeUnsafe` will automatically retry request if it was unsuccessful and retries > 0

## 0.9.1

* Updated built-in lengths restrictions
* Apply restrictions of text limit for sending messages
* Add `RegularTextMessageEntity` which is useful for representing regular text message entity
* Add `convertToFullMessageEntityList` which create list of entities with `RegularTextMessageEntity` on places where
must be regular text
* Change signature of `createMarkdownText`: now it will return list of strings
* Deprecate old signatures of `createMarkdownText`, `toMarkdownCaption`, `toMarkdownText`
* Add `ResendableContent#createResends` which create adapted list of resends for content
* Add `TextContent` own `createResends` realisation

## 0.9.0

* Old extension `OkHttpClient.Builder#useWith` now deprecated and must be replaced by the same in
`com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor` package
* Replace `ProxySettings` data class in `settings` package, deprecate old link
* `BaseRequestsExecutor` now have no it's own scope
* Add `RequestLimiter` and base realisations
* Now `KtorRequestsExecutor` can receive as one of parameters `RequestLimiter` (by default - `EmptyLimiter`)

## 0.8.5

* Add extension `String#toMarkdown`
* Fix of inserting of text when create Markdown-adapted text from text and text entities
* Fix default realisation of MessageEntity#asMarkdownSource

## 0.8.4

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

## 0.8.3

* Now `ForwardedMessage` contains nullable `from`

## 0.8.2

* Add `FromUserMessage` which must be implemented in all messages realisations which have `user` field
* Add `CommonMediaGroupMessage` which in fact extension of `MediaGroupMessage` with implementation of `FromUserMessage`
* `CommonMessageImpl` now implementing `FromUserMessage`

## 0.8.1

* Update `MediaGroupMessage` interface
* Add implementation of `MediaGroupMessage`
* Add generating of `MediaGroupMessage` in `RawMessage`
