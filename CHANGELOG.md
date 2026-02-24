# TelegramBotAPI changelog

## 31.0.0

**THIS UPDATE CONTAINS ADDING SUPPORT OF [Telegram Bots API 9.3](https://core.telegram.org/bots/api-changelog#december-31-2025)**

**THIS UPDATE CONTAINS ADDING SUPPORT OF [Telegram Bots API 9.4](https://core.telegram.org/bots/api-changelog#february-9-2026)**

**THIS UPDATE CONTAINS BREAKING CHANGES**

* `Core`:
    * **THIS IS BREAKING CHANGE** All media files with sizes changed type of `fileSize` field from `Long` to `FileSize` (value class)
    * **THIS IS BREAKING CHANGE** Class `GiftSentOrReceived` have been renamed to `GiftSentOrReceivedEvent` to clarify naming
    * **THIS IS BREAKING CHANGE** New interface `OwnedGift` have been created
      * **THIS IS BREAKING CHANGE** `OwnedGifts` have changed its generic type to `OwnedGift`
    * **THIS IS BREAKING CHANGE** For `CheclistTask` have been made several changes:
      * `ChecklistTask.Done` -> `ChecklistTask.Completed`
      * `ChecklistTask.Undone` -> `ChecklistTask.Uncompleted`
      * Added several inheritors of `ChecklistTask.Completed` - for completed by chat, by user or just completed tasks 

## 30.0.2

* `Version`:
    * `Kotlin`: `2.2.20` -> `2.2.21`
    * `Ktor`: `3.3.1` -> `3.3.2`
    * `KSP`: `2.2.20-2.0.4` -> `2.3.2`
    * `MicroUtils`: `0.26.6` -> `0.26.8`
    * `KSLog`: `1.5.1` -> `1.5.2`
* `Core`:
    * Allow to use `SetWebhook` with `maxAllowedConnections` up to `100000` (fix of [#1019](https://github.com/InsanusMokrassar/ktgbotapi/issues/1019))
* `KSP`:
    * Fixed annotation property access for KSP2 compatibility using `withNoSuchElementWorkaround`
    * Removed `ksp.useKSP2=false` workaround from `gradle.properties` (KSP2 is now properly supported)
* `Utils`:
    * Regenerated class casts extensions
    * Allow to use custom `GetUpdates` in `longPollingFlow`

## 30.0.1

* `Core`:
  * Potential fix of [#989](https://github.com/InsanusMokrassar/ktgbotapi/issues/989) by:
    * In long polling have been added check for causing by unresolved address exception
    * Add `TelegramBotPipelinesHandler.onRequestExceptionInLimiter` which will be triggered in ANY exception during
    request execution

## 30.0.0

**THIS UPDATE MAY CONTAINS BREAKING CHANGES**

* `Version`:
    * `Kotlin`: `2.2.10` -> `2.2.20`
    * `Ktor`: `3.2.3` -> `3.3.1`
    * `MicroUtils`: `0.26.3` -> `0.26.6`
    * `KSLog`: `1.5.0` -> `1.5.1`
* `BehaviourBuilder`:
    * `DefaultCustomBehaviourContextAndTypeReceiver` now extends `suspend (BC, U) -> R` instead of `CustomBehaviourContextAndTypeReceiver<BC, R, U>` (no changes in api in fact)

## 29.0.1

* `Core`:
  * Fix of [#917](https://github.com/InsanusMokrassar/ktgbotapi/issues/917): all `OrderInfo` fields now have defaults nulls

## 29.0.0

**THIS UPDATE CONTAINS ADDING SUPPORT OF [Telegram Bots API 9.2](https://core.telegram.org/bots/api-changelog#august-15-2025)**

**THIS UPDATE CONTAINS BREAKING CHANGES**

* `Core`:
  * Add function `firstOfOrNull(vararg suspend () -> T): T?`
  * Change logic of `firstOf` - now it works based on merged flows and __do not require__ `CoroutineScope` as receiver

## 28.0.3

* `Core`:
  * Add passing of default engines in `HttpClient` constructors

## 28.0.2

* `Core`:
  * [#1001](https://github.com/InsanusMokrassar/ktgbotapi/issues/1001) - `[Bug] Invalid detection of isCausedByCancellation()`
  * [#1002](https://github.com/InsanusMokrassar/ktgbotapi/issues/1002) - `Unable to handle UnauthorizedException with buildBehaviourWithLongPolling`

## 28.0.1

* `Version`:
    * `Kotlin`: `2.2.0` -> `2.2.10`
    * `MicroUtils`: `0.26.2` -> `0.26.3`
    * `Ktor`: `3.2.2` -> `3.2.3`
* `Core`:
    * Let all `OptionallyMessageThreadRequest` inheritors to use `@EncodeDefault` annotation to fix default value passing

## 28.0.0

**THIS UPDATE CONTAINS BREAKING CHANGES**

* `Core`:
  * Add `firstOf` extension
* `BehaviourBuilder`:
  * All builders for behaviours got boolean `useDefaultSubcontextInitialAction`
  * Add opportunity to pass `BehaviourContextData` in `DefaultBehaviourContext` constructor and `copy` method
  * By default, commands to other bots will be ignored in triggers and waiters

## 27.1.2

* `Core`:
  * Try to fix cancelling on timeout for long polling
  * Since this update phrase `Something web wrong` will not happen from this library 😭

## 27.1.1

* `Version`:
  * `MicroUtils`: `0.26.1` -> `0.26.2`
* `Core`:
  * Make `BusinessLocation#location` optional (fix of [#990](https://github.com/InsanusMokrassar/ktgbotapi/issues/990))

## 27.1.0

* `Core`:
  * Improve support of local bot api servers files. Next call factories will try to resolve file locally before real call:
    * [DownloadFileRequestCallFactory.kt](tgbotapi.core/src/commonMain/kotlin/dev/inmo/tgbotapi/bot/ktor/base/DownloadFileRequestCallFactory.kt) (for [DownloadFile.kt](tgbotapi.core/src/commonMain/kotlin/dev/inmo/tgbotapi/requests/DownloadFile.kt) as well)
    * [DownloadFileChannelRequestCallFactory.kt](tgbotapi.core/src/commonMain/kotlin/dev/inmo/tgbotapi/bot/ktor/base/DownloadFileChannelRequestCallFactory.kt) (for [DownloadFileStream.kt](tgbotapi.core/src/commonMain/kotlin/dev/inmo/tgbotapi/requests/DownloadFileStream.kt) as well)

## 27.0.0

**THIS UPDATE MAY CONTAIN BREAKING CHANGES. IN CASE OF ANY MIGRATION PROBLEMS FEEL FREE TO ASK IN [OUR CHAT](https://t.me/ktgbotapi_chat)**

* `Version`:
  * `Kotlin`: `2.1.20` -> `2.2.0`
  * `Serialization`: `1.8.1` -> `1.9.0`
  * `Ktor`: `3.1.3` -> `3.2.2`
  * `MicroUtils`: `0.25.7` -> `0.26.1`
  * `KSLog`: `1.4.1` -> `1.5.0`
* `Common`:
  * In most `data` classes with non-public constructors has been added `ConsistentCopyVisibility` annotation, preventing
  public nature in difference with constructor
  * Absence of several API methods for requests has been fixed

## 26.1.0

**THIS UPDATE CONTAINS ADDING SUPPORT OF [Telegram Bots API 9.1](https://core.telegram.org/bots/api-changelog#july-3-2025)**

**THIS UPDATE _MAY_ CONTAINS BREAKING CHANGES**

## 26.0.0

**THIS UPDATE CONTAINS BREAKING CHANGES IN BEHAVIOUR BUILDER AND CORE. BE CAREFUL ON UPDATE**

* `Version`:
  * `Serialization`: `1.8.0` -> `1.8.1`
  * `Coroutines`: `1.10.1` -> `1.10.2`
  * `Ktor`: `3.1.1` -> `3.1.3`
  * `MicroUtils`: `0.25.3` -> `0.25.7`
* `Core`:
  * **POTENTIALLY BREAKING CHANGE** Long polling has been reworked a bit
  * **BREAKING CHANGE** `RequestsExecutor` got property `RequestsExecutor.Log: KSLog`
* `BehaviourContext`:
  * **BREAKING CHANGE** All triggers and waiters become non-suspend functions
  * **BREAKING CHANGE** Behaviour of counted extensions (commands, data callback queries, etc.) has been changed a bit: now each one will
  create subcontext and work in it
  * New extension `BehaviourContext.launchInNewSubContext` which will launch some job in subcontext of receiver

## 25.0.1

**THIS UPDATE CONTAINS ADDING SUPPORT OF [Telegram Bots API 9.0](https://core.telegram.org/bots/api-changelog#april-11-2025)**

* `Core`:
  * Previously named `Gift` now presented by `Gift.Regular`
  * Fixes in `MultipartRequestCallFactory` for correct handling of all `MultipartRequest.Common` inheritors
  * `Username` now can be safely created with `Username.prepare`
  * Add extensions `String.splitFor(Text|Caption|StoryCaption)` for preparing several texts for several
    messages/captions/story captions

## 24.0.2

* `Version`:
  * `Kotlin`: `2.1.10` -> `2.1.20`
  * `Ktor`: `3.1.0` -> `3.1.1`
  * `MicroUtils`: `0.24.7` -> `0.25.3`
* `DefaultKTgBotAPIKSLog` will drop `CancellationException`s by default
* You may configure `DefaultKTgBotAPIKSLog` in simple way with `SetDefaultKTgBotAPIKSLog`
* `BehaviourBuilder`:
  * `FSM`:
    * Fix chains cancelling on their ends

## 24.0.1

* `Core`:
  * Now you may upload photos with `EditChatMessageMedia`

## 24.0.0

**THIS UPDATE CONTAINS BREAKING CHANGES IN `subcontextUpdatesFilter` WORK. TAKE CARE IN MIGRATION**

* `Version`:
  * `MicroUtils`: `0.24.6` -> `0.24.7`
* `Core`:
  * `ForumContentMessage` got property `threadCreatingInfo` which represents information about topic where message has been sent
* `BehaviourBuilder`:
  * Fix of overall `subcontextUpdatesFilter` behaviour. In fact, this update will fix its affection on scenaries

## 23.2.1

* `Core`:
  * Fix of `year` field in `Birthdate`

## 23.2.0

**THIS UPDATE CONTAINS ADDING SUPPORT OF [Telegram Bots API 8.3](https://core.telegram.org/bots/api-changelog#february-12-2025)**

* `Version`:
  * `MicroUtils`: `0.24.5` -> `0.24.6`
  * `Ktor`: `3.0.3` -> `3.1.0`

## 23.1.2

* `Version`:
  * `Kotlin`: `2.1.0` -> `2.1.10`
  * `Serialization`: `1.7.3` -> `1.8.0`
  * `MicroUtils`: `0.24.4` -> `0.24.5`
  * `KSLog`: `1.4.0` -> `1.4.1`

## 23.1.1

* `Version`:
  * `MicroUtils`: `0.24.0` -> `0.24.4`
* `Core`:
  * Replace `removeFirst` with `removeAt(0)` in `createTextSources`

## 23.1.0

**THIS UPDATE CONTAINS ADDING SUPPORT OF [Telegram Bots API 8.2](https://core.telegram.org/bots/api-changelog#january-1-2025)**

## 23.0.0

* `Version`:
  * `Coroutines`: `1.9.0` -> `1.10.1`
  * `MicroUtils`: `0.23.2` -> `0.24.0`
  * `KSLog`: `1.3.6` -> `1.4.0`
  * `Ktor`: `3.0.2` -> `3.0.3`
* `Core`:
  * All old factory methods for `TextSource`s took suffix `TextSource`. For example: `regular` -> `regularTextSource`.
  That has been for excluding names resolution ambiguity with extensions for `EntitiesBuilder`

## 22.0.0

**THIS UPDATE CONTAINS BREAKING CHANGES**

**IN THIS UPDATE HAS BEEN REWORKED EVENTS API IN WEBAPPS**

**THIS UPDATE ADDING SUPPORT OF [BOT API 8.0](https://core.telegram.org/bots/api-changelog#november-17-2024) AND [BOT API 8.1](https://core.telegram.org/bots/api-changelog#december-4-2024)**

* `Version`:
  * `MicroUtils`: `0.23.1` -> `0.23.2`
  * `Ktor`: `3.0.1` -> `3.0.2`

## 21.0.1

* `Core`:
  * Improvements in `Update.sourceUser` extension
  * Add extension `PrivateChat.toUser`
  * Builder methods of `TextSource`s (like `pre` which returns `PreTextSource`) lost suppression of `redundant inline`
due to its redundancy
  * Add `mention` builders for `EntitiesBuilder` with `UserId` as variant instead of `User`
* `BehaviourBuilder`:
  * Add support of several rounds for `CombinedSubcontextInitialAction`. Now it will try hard to execute all its
`SubAction` while all actions will not be completed successfully OR all left actions will not complete with errors

## 21.0.0

* `Version`:
  * `Kotlin`: `2.0.21` -> `2.1.0`
  * `MicroUtils`: `0.23.0` -> `0.23.1`
* `BehaviourBuilder`:
  * Add special `val data: BehaviourContextData` into all realizations of `BehaviourContext`
  * Add `CombinedSubcontextInitialAction` and functions
    `buildSubcontextInitialAction`/`buildSubcontextInitialActionWithSubActions`
  * Add `subcontextInitialAction` for all the functions-builders of `BehaviourContext` and
    `additionalSubcontextInitialAction` to all triggers

## 20.0.1

* `Core`:
  * Fix of logger propagation (fix of [#860](https://github.com/InsanusMokrassar/ktgbotapi/issues/860))
  * Add opportunity to modify keyboards (fix of [#761](https://github.com/InsanusMokrassar/ktgbotapi/issues/761))
  * Fields of `OrderInfo` became nullable (fix of [#917](https://github.com/InsanusMokrassar/ktgbotapi/issues/917))
  * Add default `toString` to `ChatType` (fix of [#919](https://github.com/InsanusMokrassar/ktgbotapi/issues/919))
  * Fix of method in `CreateInvoiceLink` (merging of [#920](https://github.com/InsanusMokrassar/ktgbotapi/issues/920), made by [byteduck-exploit](https://github.com/byteduck-exploit))

## 20.0.0

**THIS UPDATE CONTAINS SUPPORT OF [BOTS API 7.11](https://core.telegram.org/bots/api-changelog#october-31-2024)**

* `All`:
  * **ALL SEND METHODS GOT PARAMETER `allowPaidBroadcast` WHICH POTENTIALLY MAY SPEND MONEYS FROM BOT BALANCE**

## 19.0.0

**THIS UPDATE CONTAINS BREAKING CHANGES**

**THIS UPDATE CONTAINS UPGRADE UP TO KTOR 3.0 (thanks to [@d1snin](https://github.com/d1snin))**

* `Version`:
  * `Ktor`: `2.3.12` -> `3.0.1`
  * `MicroUtils`: `0.22.7` -> `0.23.0`

## 18.2.3

* `Core`:
  * Add default middleware `ExceptionsThrottlerTelegramBotMiddleware`
  * Make `TelegramBotMiddlewaresPipelinesHandler` to be default `TelegramBotPipelinesHandler`
  * Make `DefaultKtorRequestsExecutor` now uses `runCatching` instead of `runCatchingSafely`
  * `onRequestResultPresented` lambda now accepts non-nullable `result`

## 18.2.2

* `Version`:
  * `Kotlin`: `2.0.20` -> `2.0.21`
  * `MicroUtils`: `0.22.4` -> `0.22.7`
* `Core`:
  * Fixes in blockquotes serializations
  * Now `RawMessageEntity` is public. It is still under `Warning` annotation and is subject of changes
* `BehaviourBuilder`:
  * Add `CommonMessageFilterExcludeCommand` to filter commands in messages
  * Add `minus` operation for `SimpleFilter`s

## 18.2.1

* `Version`:
  * `Serialization`: `1.7.2` -> `1.7.3`
  * `Coroutines`: `1.8.1` -> `1.9.0`
  * `MicroUtils`: `0.22.2` -> `0.22.4`

## 18.2.0

**THIS UPDATE CONTAINS SUPPORT OF [BOTS API 7.10](https://core.telegram.org/bots/api-changelog#september-6-2024)**

* `Core`:
  * Customize `GiveawayCreated` to use stars in some cases
  * `GiveawayInfo` lost parameter `premiumMonths` because of it is no included by default
  * `GiveawayPublicResults` lost parameter `publicWinners` because of its abcence in Telegram Bot API
  * Fix `GiveawayPublicResults` to be correctly deserializable
  * Fix `GiveawayPrivateResults` to be correctly deserializable
  * Fixes in deserialization of updates with giveaways
* `BehaviourBuilder`:
  * Extension `buildBehaviourWithLongPolling` will not report absence of `/privacy` handling
* `WebApp`:
  * `MainButton` has been deprecated in favor to `BottomButton`. Their functionality is equal

## 18.1.0

* `BehaviourBuilder`:
  * Add `createSubContextAndDoSynchronouslyWithUpdatesFilter` as old logic of `createSubContextAndDoWithUpdatesFilter`
  * `createSubContextAndDoWithUpdatesFilter` has been renamed to `createSubContextAndDoAsynchronouslyWithUpdatesFilter`

## 18.0.0

**THIS UPDATE CONTAINS BREAKING CHANGES**

This update contains solution for [issue #888](https://github.com/InsanusMokrassar/ktgbotapi/issues/888), made by
[@Nik-mmzd](https://github.com/Nik-mmzd)

---

**Migration (step-by-step)**

Replace in a whole project:

1. `RestrictedChatMember` -> `RestrictedMemberChatMember`
2. `BannedChatMember` -> `RestrictedChatMember`

---

**Changes:**

* `Version`:
  * `Kotlin`: `2.0.10` -> `2.0.20`
  * `Serialization`: `1.7.1` -> `1.7.2`
  * `MicroUtils`: `0.22.0` -> `0.22.2`
  * `KSLog`: `1.3.5` -> `1.3.6`
  * `Ktor`: `2.3.11` -> `2.3.12`
* `Core`:
  * `RestrictedChatMember` has been renamed to `RestrictedMemberChatMember`
  * `BannedChatMember` has been renamed to `RestrictedChatMember`
  * `KickedChatMember` now implements `RestrictedChatMember` (due to rename)
  * All `ChatMember`s now use `PreviewUser`s instead of `User`s
* `Utils`:
  * Add a lot of extensions like `ChatMember.isMember`
  * Add a lot of extensions like `ChatMemberUpdated.joinedChat`

## 17.0.0

**THIS UPDATE CONTAINS SUPPORT OF BOTS API 7.9**

* Add support of subscriptions links handling
* Add support of info about sender chat. **It is important, that for channels will be actual `senderChat` instead of
`from` field due to `User` type of the last one**. You also may use extensions `Message.sender_chat` or `Any.withSenderChatMessageOrNull`
to access sender chat
* Add `Reaction.Paid`

Additional changes:

* `Core`:
    * Add top level interfaces `OptionallyWithUser` and `OptionallyFromUser`. Old `WithUser` and `FromUser` interfaces
    extending them with following overrides of `user` and `from` fields
* `API`:
    * Change order of delay and sending action in [SendActionDSL](tgbotapi.api/src/commonMain/kotlin/dev/inmo/tgbotapi/extensions/api/send/SendActionDSL.kt)
    (thanks to [DRSchlaubi](https://github.com/DRSchlaubi), [PR #833](https://github.com/InsanusMokrassar/ktgbotapi/pull/883)).
    Besides, there has been changed way to create parallel sending of action and it must not lead to memory leaks anymore

## 16.0.0

**THIS UPDATE CONTAINS KOTLIN UPDATE UP TO 2.0**

* `Version`:
  * `Kotlin`: `1.9.23` -> `2.0.10`
  * `Serialization`: `1.6.3` -> `1.7.1`
  * `MicroUtils`: `0.21.2` -> `0.22.0`
  * `KSLog`: `1.3.4` -> `1.3.5`

## 15.3.0

**THIS UPDATE CONTAINS ADDING OF SUPPORT FOR BOTS API 7.8**

* `Core`:
  * `OptionallyBusinessConnectionRequest` now extends `WithOptionalBusinessConnectionId`
  * Add `hasMainWebApp` in `ExtendedBot`
* `API`:
  * Add `BusinessConnectionId` to pin/unpin methods
* `WebApp`:
  * Add `shareToStory` in `WebApp`

## 15.2.0

* `API`:
  * Enabled an `explicit mode` for `API` module ([PR #876](https://github.com/InsanusMokrassar/ktgbotapi/pull/876))
* `WebApps`:
  * Built-in `onClick` and `offClick` of `MainButton` become public ([PR #875](https://github.com/InsanusMokrassar/ktgbotapi/pull/875))
  * Old `MainButton.onClick` extension **has been removed** to avoid collisions of types

## 15.1.0

**THIS UPDATE CONTAINS BREAKING CHANGES**

**THIS UPDATE CONTAINS ADDING OF SUPPORT FOR BOTS API 7.7** (thanks to [bpavuk](https://threads.net/b_pavuchok))

**THIS UPDATE CONTAINS ADDING OF SUPPORT FOR BOTS API 7.6** (with participation and help of [bpavuk](https://threads.net/b_pavuchok))

* Add support of paid media
* Update web apps
* `Version`:
  * `MicroUtils`: `0.21.1` -> `0.21.2`
* `Core`:
  * `TelegramMedia` has been separated onto two interfaces: `TelegramFreeMedia` and `TelegramPaidMedia`
    * `TelegramFreeMedia` has replaced `TelegramMedia` on all old places where it have been used
  * `Photo` has been renamed to `PhotoFile`
* `BehaviourBuilder`:
  * Add notification when handling of `/privacy` command is absence
  * Add several extensions to simplify setting up `/privacy` reaction

## 15.0.0

**THIS UPDATE CONTAINS BREAKING CHANGES**

**THIS UPDATE CONTAINS ADDING OF SUPPORT FOR BOTS API 7.5**

* `Version`:
    * `MicroUtils`: `0.21.0` -> `0.21.1`
* A lot of `edit` methods and classes has been changed to support business connection id

## 14.1.0

* `Version`:
    * `Coroutines`: `1.8.0` -> `1.8.1`
    * `Ktor`: `2.3.10` -> `2.3.11`
    * `MicroUtils`: `0.20.45` -> `0.21.0`
    * `KSLog`: `1.3.3` -> `1.3.4`
* `BehaviourBuilder`:
    * `BehaviourContext` updates listening job will be weakly subscribed. It means that `BehaviourContext` scope will
    not be prevented from cancelling by this job anymore
    * `BehaviourContext.doInContext` will not stop automatically current `BehaviourContext` job anymore
    * `BehaviourContext.createSubContextAndDoWithUpdatesFilter` will use `supervisorScope` instead of
    `LinkedSupervisorScope`. That will prevent scope leaking in runtime

## 14.0.1

* In `core` and `api` modules related to invoices changed their APIs to suite current API

## 14.0.0

**Add support of [Telegram Bots API 7.4](https://core.telegram.org/bots/api-changelog#may-28-2024)**

**THIS UPDATE CONTAINS BREAKING CHANGES**

* `Core`:
    * `TelegramPaymentChargeId` has been added as value class and replaced raw strings in `SuccessfulPayment` type of `telegramPaymentChargeId`
* All the methods/classes related to sending of messages got `effectId` parameter
* All the methods/classes related to sending of photos/animations/videos got `showCaptionAboveMedia` parameter

## 13.0.0

**Add support of [Telegram Bots API 7.3](https://core.telegram.org/bots/api-changelog#may-6-2024)**

**THIS UPDATE CONTAINS BREAKING CHANGES**

* `Core`:
  * For polls, `textSources` now means `question` text sources. For `QuizPoll` there are `explanation` and `explanationTextSources`
  for hinting
* `API`:
  * A lot of API related to `Poll`s has been changed to include opportunity to pass `ParseMode` and `TextSource`s list

## 12.0.1

* `Version`:
  * `Ktor`: `2.3.9` -> `2.3.10`
  * `MicroUtils`: `0.20.39` -> `0.20.45`
  * `UUID`: `0.8.2` -> `0.8.4`

## 12.0.0

**Add support of [Telegram Bots API 7.2](https://core.telegram.org/bots/api-changelog#march-31-2024)**

**THIS UPDATE CONTAINS A LOT OF BREAKING CHANGES**

## 11.0.0

**THIS UPDATE CONTAINS REMOVES OF DEPRECATED THINGS**

**THIS UPDATE CONTAINS A LOT OF BREAKING CHANGES**

* `Core`:
  * `MessageId` now is `value class`. `MessageIdentifier` become deprecated
  * `MessageThreadId` now is `value class`
  * `InlineQueryIdentifier` now is `value class`
  * `MediaGroupIdentifier` has been renamed to `MediaGroupId` and now is `value class`
  * `CallbackQueryIdentifier` has been renamed to `CallbackQueryId` and now is `value class`
  * `WebAppQueryId` now is `value class`
  * `PreCheckoutQueryId` now is `value class`
  * `FileUniqueId` has been renamed to `TgFileUniqueId` and now is `value class`
  * `UpdateIdentifier` has been renamed to `UpdateId` and now is `value class`
  * `InlineMessageIdentifier` has been renamed to `InlineMessageId` and now is `value class`
  * `ShippingQueryIdentifier` has been renamed to `ShippingQueryId` and now is `value class`
  * `Identifier` has been renamed to `RawChatId` and now is `value class`
  * `ShippingOptionIdentifier` has been renamed to `ShippingOptionId` and now is `value class`
  * `PollIdentifier` has been renamed to `PollId` and now is `value class`
  * `StickerSetName` now is `value class`

## 10.1.2

* `Version`:
  * `Kotlin`: `1.9.22` -> `1.9.23`
  * `Korlibs`: `5.3.2` -> `5.4.0`
  * `Ktor`: `2.3.8` -> `2.3.9`
  * `MicroUtils`: `0.20.37` -> `0.20.39`
  * `KSLog`: `1.3.2` -> `1.3.3`

## 10.1.1

* `Version`:
  * `Serialization`: `1.6.2` -> `1.6.3`
  * `MicroUtils`: `0.20.34` -> `0.20.37`
  * `Korlibs`: `5.3.1` -> `5.3.2`

## 10.1.0

**Add support of [Telegram Bots API 7.1](https://core.telegram.org/bots/api-changelog#february-16-2024)**

* `Version`:
  * `Coroutines`: `1.7.3` -> `1.8.0`
  * `MicroUtils`: `0.20.32` -> `0.20.34`

## 10.0.1

* `Version`:
  * `Ktor`: `2.3.7` -> `2.3.8`
  * `MicroUtils`: `0.20.26` -> `0.20.32`
  * `Korlibs`: `5.3.0` -> `5.3.1`
  * `KSLog`: `1.3.1` -> `1.3.2`

## 10.0.0

**Add support of [Telegram Bots API 7.0](https://core.telegram.org/bots/api-changelog#december-29-2023)**

**IN THIS UPDATE KLOCK DEPENDENCY CHANGED TO `com.soywiz.korge:korlibs-time` UP TO 5.3.0 VERSION**

**IN THIS UPDATE KRYPTO DEPENDENCY CHANGED TO `com.soywiz.korge:korlibs-crypto` UP TO 5.3.0 VERSION**

* `Version`:
  * `MicroUtils`: `0.20.23` -> `0.20.26`
  * `Korlibs`: `4.0.10` -> `5.3.0`
* `Core`:
  * `Message` now inherited by two variants: `AccessibleMessage` and `InaccessibleMessage`
* `Common`:
  * In most places `disableWebPagePreview` has been replaced by new `LinkPreviewOptions`
  * In most places arguments `replyToMessageId` and `allowSendingWithoutReply` has been replaced with
    `ReplyParameters`
    * In `reply` extension two parameters have been added: `replyInChatId` and `replyInThreadId`

## 9.4.3

**IetfLanguageCode has been renamed to IetfLang in MicroUtils**

* `Version`:
  * `Kotlin`: `1.9.21` -> `1.9.22`
  * `MicroUtils`: `0.20.19` -> `0.20.23`

## 9.4.2

* `Version`:
  * `Serialization`: `1.6.1` -> `1.6.2`
  * `Ktor`: `2.3.6` -> `2.3.7`
  * `MicroUtils`: `0.20.15` -> `0.20.19`
  * `UUID`: `0.8.1` -> `0.8.2`

## 9.4.1

* Replace warning about two bots from `LongPolling` to `DefaultKtorRequestsExecutor`

## 9.4.0

* `Version`:
  * `Kotlin`: `1.9.20` -> `1.9.21`
  * `Serialization`: `1.6.0` -> `1.6.1`
  * `Ktor`: `2.3.5` -> `2.3.6`
  * `MicroUtils`: `0.20.12` -> `0.20.15`

## 9.3.0

This release become possible thanks to [Anton Lakotka](https://youtrack.jetbrains.com/users/anton.lakotka)

**THIS RELEASE CONTAINS UPDATES UP TO RELEASE CANDIDATES VERSIONS**

**UPDATE MAY HAVE BREAKING CHANGES**

**SINCE THIS UPDATE IT WILL BE REQUIRED TO USE JDK 17+ FOR DEVELOPMENT**

* `Version`:
  * `Kotlin`: `1.8.22` -> `1.9.20`
  * `Serialization`: `1.5.1` -> `1.6.0`
  * `KorLibs`: `4.0.3` -> `4.0.10`
  * `UUID`: `0.7.1` -> `0.8.1`
  * `Ktor`: `2.3.4` -> `2.3.5`
  * `MicroUtils`: `0.19.9` -> `0.20.12`

## 9.2.4

* `Utils`:
  * New extensions `*.parseCommandsWithNamedArgs`
* `BehaviourBuilder`:
  * In expectaters and triggers of `commands` add `*WithNamedArgs` variants
  * In expectaters and triggers of `commands` add opportunity to use custom separator

## 9.2.3

* `Core`:
  * Fix in `VoiceContent#createResend`

## 9.2.2

* `Core`:
  * Fix of [#793](https://github.com/InsanusMokrassar/ktgbotapi/issues/793): Add `PreviewChat`

## 9.2.1

* `Version`:
  * `Ktor`: `2.3.3` -> `2.3.4`
* `Core`:
  * All `ChatMember` inheritors have fixes `status` field

## 9.2.0

**Add support of [Telegram Bots API 6.9](https://core.telegram.org/bots/api-changelog#september-22-2023)**

* Rename `ChatAdministratorRightsImpl` -> `ChatCommonAdministratorRights`
* All the request chat keyboards has changed their parameters `ChatAdministratorRights` to `ChatCommonAdministratorRights`

## 9.1.2

* `Core`:
  * Fix of `MessageContent` serialization

## 9.1.1

* `Core`:
  * Potential fix of incorrect parsing in `RawMessageEntity`

## 9.1.0

**This update contains adding of [Telegram Bot API 6.8](https://core.telegram.org/bots/api-changelog#august-18-2023) support**

* `Version`:
  * `Coroutines`: `1.7.2` -> `1.7.3`
  * `Ktor`: `2.3.2` -> `2.3.3`
  * `MicroUtils`: `0.19.7` -> `0.19.9`

## 9.0.0

**THIS UPDATE CONTAINS BREAKING CHANGES: USERNAMES OF BOTS NOW BECAME NULLABLE**

* `Version`:
  * `Coroutines`: `1.6.4` -> `1.7.1`
  * `Ktor`: `2.3.1` -> `2.3.2`
  * `MicroUtils`: `0.19.4` -> `0.19.7`
* `Core`:
    * **All bots now have nullable usernames just like common users ([#772](https://github.com/InsanusMokrassar/ktgbotapi/issues/772))**
    * Decrease possible errors in updates handling by additional handling of update deserialization wrapping ([#773](https://github.com/InsanusMokrassar/ktgbotapi/issues/773))
    * New interface `GetUpdatesRequest`. You may implement it to show default telegram bot ktor executor that this
      request is an updates request and should be handled in a different way
        * Now it is possible to get raw updates with `GetUpdatesRaw` request
* `Utils`:
    * Improve extension `Update.sourceChat` to add opportunity to select some chats by logic different with the default

## 8.1.0

**PARTIALLY BREAKING CHANGES: Exclude `.*Impl` classcasts from `ClassCastsNew`**

* `Version`:
  * `MicroUtils`: `0.19.2` -> `0.19.4`
* `Utils`:
    * Add deep links formatting for internal `tg://` prefix (thanks to [@klimatov](https://github.com/klimatov))
    * Exclude `.*Impl` classcasts from `ClassCastsNew`

## 8.0.1

* `Version`:
    * `UUID`: `0.7.0` -> `0.7.1`
    * `Ktor`: `2.3.0` -> `2.3.1`
    * `MicroUtils`: `0.19.1` -> `0.19.2`

## 8.0.0

**THIS UPDATE CONTAINS BREAKING CHANGES**

**ALL PROJECT DEPRECATIONS HAVE BEEN REMOVED**

**IN THIS UPDATE KORLIBS HAVE BEEN UPDATED TO VERSION `4.0.2`. SINCE THAT VERSION A LOT OF PACKAGES HAVE BEEN RENAMED.
MIGRATIONS USED IN THIS LIB:**

* `com.soywiz.klock` -> `korlibs.time`
* `com.soywiz.krypto` -> `korlibs.crypto`

* `Versions`:
    * `Korlibs`: `3.4.0` -> `4.0.3`
    * `MicroUtils`: `0.18.4` -> `0.19.1`

## 7.1.3

* `Versions`:
    * `Serialization`: `1.5.0` -> `1.5.1`
    * `MicroUtils`: `0.18.1` -> `0.18.4`
* `Core`:
    * Actualize kdocs in `InputFile`
* `BehaviourBuilder`:
    * Now it is possible to use `waitMediaContent`/`waitMediaContentMessage`/`onMediaContent`
    * Add `onMention`/`waitMention` functionality
    * Add opportunity to map content with extensions to `Flow`

## 7.1.2

* `Versions`:
  * `MicroUtils`: `0.18.0` -> `0.18.1`
* `Core`:
    * Now it is possible to serialize `Sticker`s

## 7.1.1

* `Versions`:
  * `Kotlin`: `1.8.20` -> `1.8.21`
  * `MicroUtils`: `0.17.8` -> `0.18.0`
* `Utils`:
    * Fixes in `makeLinkToMessage`

## 7.1.0

**This update contains changes according to the [Telegram Bot API 6.7](https://core.telegram.org/bots/api-changelog#april-21-2023)**

* `API`:
    * Rename `editMessageCaption` to `editMessageMedia` due to wrong old naming
    * Add `edit` extensions for `InlineMessageIdentifier`s
* `BehaviourBuilder`:
    * `BehaviourContext` extensions `onDeepLink` and `waitDeepLinks` now can be used with `Regex` or `String` as first parameters

## 7.0.2

_This update brings experimental support of `linuxX64` and `mingwX64` platforms_

* `Versions`:
    * `Kotlin`: `1.8.10` -> `1.8.20`
    * `MicroUtils`: `0.17.5` -> `0.17.8`
    * `Ktor`: `2.2.4` -> `2.3.0`
* `Core`:
    * New `RequestsExecutor` - `MultipleClientKtorRequestsExecutor`
    * Old `KtorRequestsExecutor` has been renamed to `DefaultKtorRequestsExecutor`
    * `KtorRequestsExecutor` now is `expect class`
        * On `JS`, `JVM` and `MinGWX64` platforms it is `DefaultKtorRequestsExecutor`
        * On `LinuxX64` platform it is `MultipleClientKtorRequestsExecutor`

## 7.0.1

* `Core`:
    * New interface `WithChat` which contains `chat` field
        * `Message` now inherits `WithChat`
        * `ChatMemberUpdated` now inherits `WithChat`
* `Utils`:
    * Improvements in `same`-notations

## 7.0.0

This update contains support of [Telegram Bot API 6.6](https://core.telegram.org/bots/api-changelog#march-9-2023)

**THIS VERSION CONTAINS BREAKING CHANGES**:

* All previous deprecations have been removed
* Fully reworked mechanism of stickers creating and adding
    * All separations of stickers types like `Animeted` have been replaces with type `StickerFormat`
    * New `InputSticker` type (and all subtypes) as replacements for old raw fields in methods
* Reworked mechanism of files uploading

Other changes

* `Versions`:
  * `MicroUtils`: `0.17.3` -> `0.17.5`

## 6.1.0

* `Versions`:
  * `MicroUtils`: `0.17.2` -> `0.17.3`
* `API`:
    * Fix of [#732](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/732)

## 6.0.3

* `Versions`:
    * `MicroUtils`: `0.17.1` -> `0.17.2`
* `Core`:
    * `User` in `CallbackQuery` now is `CommonUser` as well as in `from`
    * `User` in `InlineQuery` now is `CommonUser` as well as in `from`
* `BehaviourBuilder`:
    * Fixes in `DeepLink` triggers and waiters

## 6.0.2

* `Core`:
    * Long polling now uses media groups debounce as in webhooks

## 6.0.1

* `Versions`:
    * `Ktor`: `2.2.3` -> `2.2.4`
    * `MicroUtils`: `0.17.0` -> `0.17.1`

## 6.0.0

* `Versions`:
    * `Kotlin`: `1.7.22` -> `1.8.10`
    * `MicroUtils`: `0.16.10` -> `0.17.0`
    * `Serialization`: `1.4.1` -> `1.5.0`
    * `uuid`: `0.6.0` -> `0.7.0`
* `Core`:
    * `*.link` extensions have been deprecated with renaming to avoid collisions with `link` methods
* `API`:
    * Add `TelegramBot.resend` methods
* `BehaviourBuilder`:
    * Add triggers and waiters for `VisualMediaGroupPartContent`
* `Utils`:
    * `*.link` extensions have been deprecated with renaming to avoid collisions with `link` methods

## 5.2.1

* `Core`:
    * All the `CallbackQuery`es now will receive `CommonUser` instead of `User` due inability of bots to trigger any
      inline interaction with others bots
* `API`:
    * Now `sentMessageFlow` will take each sent message in `handleLiveLocation`

## 5.2.0

* `Versions`:
    * `MicroUtils`: `0.16.8` -> `0.16.10`

## 5.1.1

* `Core`:
    * Add opportunity to get user link with `makeUserLink`
* `BehaviourBuilder`:
    * Fixes in content waiting expectators

## 5.1.0

[Bot API 6.5](https://core.telegram.org/bots/api-changelog#february-3-2023) support

* `Core`:
    * `ChatPermissions` now is interface and have two main realizations: `ChatPermissions.Granular` and
      `ChatPermissions.Common`
    * `RestrictedChatMember` now implements `ChatPermissions` too
* `API`:
    * Now it is possible to pass all long polling parameters in all places used it
* `Issues`:
    * Fix of [#697](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/697)

## 5.0.2

* `Versions`:
    * `MicroUtils`: `0.16.6` -> `0.16.8`
    * `Ktor`: `2.2.2` -> `2.2.3`
* `BehaviourBuilder`:
    * Fixes in `BehaviourContext.onEditedContentMessage` - now it will trigger callback on channel post edits too

## 5.0.1

* `Versions`:
    * `MicroUtils`: `0.16.4` -> `0.16.6`
    * `Ktor`: `2.2.1` -> `2.2.2`
* `Core`:
    * Fixes in `SendMediaGroup` request
    * Fixes in `SetChatAdministratorCustomTitle` request (thanks to [@madhead](https://github.com/madhead))

## 5.0.0

[Bot API 6.4](https://core.telegram.org/bots/api-changelog#december-30-2022) support!

* Long-polling improvements

## 4.2.4

* `Core`:
    * Fixes in webhook parts adapter
* `BehaviourBuilderWithFSM`:
    * Fixes in `DefaultBehaviourContextWithFSM`

## 4.2.3

* `Versions`:
  * `MicroUtils`: `0.16.2` -> `0.16.4`
* `Core`:
    * Simplify default `RequestsLimiter` (`ExceptionsOnlyLimiter`) (thanks to [@y9san9](https://github.com/y9san9) for help)

## 4.2.2

* `Versions`:
    * `MicroUtils`: `0.16.0` -> `0.16.2`
* `Core`:
    * Fix of [#694](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/694): add opportunity to create `ChatId` and `ChatIdWithThreadId` from `IdChatIdentifier`

## 4.2.1

* `Versions`:
    * `MicroUtils`: `0.15.0` -> `0.16.0`
    * `Ktor`: `2.1.3` -> `2.2.1`
* `Utils`:
    * Improve support of `makeLinkToMessage` extensions

## 4.2.0

* `Versions`:
    * `Kotlin`: `1.7.21` -> `1.7.22`
    * `MicroUtils`: `0.14.4` -> `0.15.0`

## 4.1.3

* `Versions`:
    * `MicroUtils`: `0.14.2` -> `0.14.4`
* `Core`:
    * `ContentMessage`, `CommonMessage`, `PossiblyMediaGroupMessage` and `PossiblySentViaBotCommonMessage` got `out`
      variance
    * `UserId` now is `ChatId` instead of `IdChatIdentififer`

## 4.1.2

* `Versions`:
    * `MicroUtils`: `0.14.1` -> `0.14.2`
* `BehaviourBuilder`:
    * Fixes in `CallbackQuery` waiters

## 4.1.1

* `Core`:
  * Add opportunity to create `IdChatIdentifier` with optional `threadId`
  * New serializer `FullChatIdentifierSerializer` with serialization of `ChatIdWithThreadId`

## 4.1.0

* `Versions`:
    * `Kotlin`: `1.7.20` -> `1.7.21`
    * `MicroUtils`: `0.14.0` -> `0.14.1`
    * `Korlibs`: `3.3.1` -> `3.4.0`
    * `UUID`: `0.5.0` -> `0.6.0`
* `Core`:
  * All the chats identifiers has been rewritten as value classes
  * New chat identifier: `ChatIdWithThreadId`
  * `RawMessage` will create `ChatIdWithThreadId` chat id under the hood by default
  * All the methods which potentially using `threadId` will try to take it from `chatId`
* `API`:
  * All default `threadId` null values has been replaced with auto-calculated threadId from chats/chat ids

## 4.0.0

**!!! THIS UPDATE CONTAINS FULL REWORK OF MEDIA GROUPS FUNCTIONALITY !!!**

**THIS UPDATE CONTAINS BREAKING CHANGES**

* `Common`:
  * All the media groups have been rewritten. Since this update, there are no separated messages types for media groups,
    but new type of content (`MediaGroupContent`) has been introduced
  * [Bot API 6.3](https://core.telegram.org/bots/api-changelog#november-5-2022) support
* `Versions`:
  * `MicroUtils`: `0.13.2` -> `0.14.0`
* `Core`:
  * New requests

## 3.3.1

* `Versions`:
    * `Ktor`: `2.1.2` -> `2.1.3`
    * `Klock`: `3.2.0` -> `3.3.1`
    * `MicroUtils`: `0.13.1` -> `0.13.2`
* `Utils`:
    * New extensions on `CommonMessage`: `hasCommands` and `hasNoCommands`. Useful for the `initialFilter` parameter in behaviour builder triggers.

## 3.3.0

**THIS VERSION CONTAINS UPGRADE KOTLIN (AND ALL RELATED LIBRARIES) UP TO 1.7.20**

* `Versions`:
    * `Kotlin`: `1.7.10` -> `1.7.20`
    * `Kotlin Serialization`: `1.4.0` -> `1.4.1`
    * `Korlibs`: `3.1.0` -> `3.2.0`
    * `MicroUtils`: `0.12.17` -> `0.13.1`
* `Core`:
    * Add opportunity to create command text source and add command in entities builder
      via `BotCommamd` (thanks to [d1shin](https://github.com/InsanusMokrassar/TelegramBotAPI/pull/664))
* `API`:
    * New extensions `TelegramBot#getStickerSetOrNull` and `TelegramBot#getStickerSetOrThrow`
        * Old `TelegramBot#getStickerSet` has been deprecated
* `Behaviour Builder`:
    * Add opportunity to use triggers and waiters with `BotCommand` (thanks to [d1shin](https://github.com/InsanusMokrassar/TelegramBotAPI/pull/664))

## 3.2.7

* `Versions`:
    * `MicroUtils`: `0.12.13` -> `0.12.17`
    * `Ktor`: `2.1.1` -> `2.1.2`
* `Utils`:
  * Next classes become typealiases instead of classes:
    * `ReplyKeyboardBuilder`
    * `ReplyKeyboardRowBuilder`
    * `InlineKeyboardBuilder`
    * `InlineKeyboardRowBuilder`

## 3.2.6

* `Core`:
    * Fixes in `ChatMemberSerializer#serialize` method
    * Migration of `EntitiesBuilder` from `Utils` to `Core`
* `API`:
    * All the extensions related to text messages (replies, sending, editing) got their duplicates with `buildEntities` lambda and separator
* `Utils`:
    * Migration of `EntitiesBuilder` from `Utils` to `Core`

## 3.2.5

* `Common`:
  * Improve support of new exceptions recovering mechanism

## 3.2.4

* `API`:
    * New `edit`'s with chats and message ids

## 3.2.3

* `Core`:
  * Fixes in `DeleteMyCommands`

## 3.2.2

* `Versions`:
  * `MicroUtils`: `0.12.10` -> `0.12.13`
* `Core`:
  * `MessageIdentifier` has been renamed to `MessageId`
* `API`:
  * New `reply`'es with chats and message ids

## 3.2.1

* `Versions`:
    * `Ktor`: `2.1.0` -> `2.1.1`
    * `Korlibs`: `3.0.0` -> `3.1.0`
    * `MicroUtils`: `0.12.4` -> `0.12.10`

## 3.2.0

**Since this update, `RequestsExecutor#execute` may throw only `BotException`. In case you wish to handle some exceptions from `execute` you must catch `BotException` and handle its `cause`**

* `Versions`:
    * `Serialization`: `1.4.0-RC` -> `1.4.0`
    * `MicroUtils`: `0.12.1` -> `0.12.4`
* `Core`:
  * `SetWebhook#allowedUpdates` now is `ALL_UPDATES_LIST` by default instead of `null`
* `API`:
  * Extension `TelegramBot#setWebhook` parameter `allowedUpdates` now is `ALL_UPDATES_LIST` by default instead of `null`
* `Utils`:
  * All related to long polling extensions parameters `allowedUpdates` now are `ALL_UPDATES_LIST` by default instead of `null`

## 3.1.1

* `Common`:
    * Complete Bot API 6.2 implementation

## 3.1.0

**This update contains including of Bot API 6.2**

* `Versions`:
    * `Ktor`: `2.0.3` -> `2.1.0`
    * `MicroUtils`: `0.12.0` -> `0.12.1`
* `Core`:
  * Add support of `custom emoji`s
  * Add support of `sticker_type`

## 3.0.2

**ALL OLD DEPRECATIONS HAVE BEEN REMOVED**

**`copyMessage` HAVE CHANGED THEIR SIGNATURE BY SWAPPING FROM AND TO CHAT IDS**

* `Versions`:
  * `Kotlin`: `1.6.21` -> `1.7.10`
  * `Serialization`: `1.3.3` -> `1.4.0-RC`
  * `Korlibs`: `2.7.0` -> `3.0.0`
  * `UUID`: `0.4.1` -> `0.5.0`
  * `MicroUtils`: `0.11.13` -> `0.12.0`
* `Core`:
    * Interface `ReplyMakrup` has been renamed to `WithReplyMarkup` to correspond its purpose
    * Data class `LeftChatMember` has been renamed to `LeftChatMemberEvent` to avoid type ambiguite with the other `LeftChatMember`
    * `ForwardInfo` hierarchy has been fully reworked:
      * `AnonymousForwardInfo` -> `ForwardInfo.ByAnonymous`
      * `UserForwardInfo` -> `ForwardInfo.ByUser`
      * `ForwardFromPublicChatInfo` -> `ForwardInfo.PublicChat`
      * `ForwardFromChannelInfo` -> `ForwardInfo.PublicChat.FromChannel`
      * `ForwardFromSupergroupInfo` -> `ForwardInfo.PublicChat.FromSupergroup`
      * `ForwardInfo.PublicChat.SentByChannel` ___has been created___
* `API`:
    * Add new `Flow`-based live locations API
    * Add `sendLocation` for sending live locations
* `Utils`:
  * **BREAKING CHANGES** Now all new classcasts (like `Chat.ifPrivateChat` etc.) have been rewritten to be generated with `ksp` and `kotlin poet`

*Note: Versions 3.0.0 and 3.0.1 have been published with errors and didn't recommend to use*

## 2.2.2

* `Utils`:
  * `buildEntities` now is inline
* `Behaviour Builder`:
  * Fixes in `CallbackQuery` triggers

## 2.2.1

* `Versions`:
    * `Coroutines`: `1.6.3` -> `1.6.4`
    * `MicroUtils`: `0.11.12` -> `0.11.13`
* `Core`:
    * New `typealiase`s for `CommonMessage` with all supported content types, more info: [PR](https://github.com/InsanusMokrassar/TelegramBotAPI/pull/629)
* `Utils`:
    * New extensions for all types of messages `*.withContentOrNull` and `*.withContentOrThrow` as analogies to the same ones from class casts

## 2.2.0

* `Core`:
  * For `CopyMessage` order of parameters has been changed
  * `ReplyForce` defaults changes:
    * All old companion properties (like `ReplyForce.ReplyForceSelective`) have been renamed:
      * `ReplyForceSelective` -> `Selective`
      * `ReplyForceNonSelective` -> `NonSelective`
      * `ReplyForceDefault` -> `Default`
    * New companion functions:
      * `ReplyForce#Selective`
      * `ReplyForce#NonSelective`
  * Rename `CreatorChatMember` -> `OwnerChatMember`
* `API`:
  * For `copyMessage` order of parameters has been changed
* `Utils`:
  * New extension `Message#sameChat`
  * New extension `Message#sameMessage`
  * New functions `flatInlineKeyboard` and `flatReplyKeyboard`
  * Experimentally add new triple of class casts: `if*`, `*OrNull` and `*OrThrow`

## 2.1.3

* `Versions`:
  * `MicroUtils`: `0.11.9` -> `0.11.12`
* `Utils`:
  * Rename `UserId` extension `link` to `userLink`
* `Core`
  * `ChatJoinRequest#inviteLink` is nullable due to the fact that join requests without link do not require invite link

## 2.1.2

* `Versions`:
  * `Coroutines`: `1.6.1` -> `1.6.3`
  * `Ktor`: `2.0.2` -> `2.0.3`
  * `MicroUtils`: `0.11.6` -> `0.11.9`

## 2.1.1

* `API`:
  * Now it is possible to edit any message text via `editMessageText` (but with warning)
  * Now it is possible to edit any message caption via `editMessageCaption` (but with warning)
  * Media message caption edit method (`editMessageCaption`) now returns the message with the same generic type (as it must be in telegram system)
  * New extensions `TelegramBot#edit` has been added for all possible editions types
  * New extensions `TelegramBot#send` has been added for all possible sending types
  * New extensions `TelegramBot#delete` has been added
* `Versions`:
    * `MicroUtils`: `0.11.3` -> `0.11.6`

## 2.1.0

__This update contains including of [Telegram Bot API 6.1](https://core.telegram.org/bots/api-changelog#june-20-2022)__

* Add support of functionality for `WebApp`s from [Bot API 6.1](https://core.telegram.org/bots/api-changelog#june-20-2022)
* Add support of functionality for premium feature from [Bot API 6.1](https://core.telegram.org/bots/api-changelog#june-20-2022)
* Add support of `addedToAttachmentMenu` in `CommonUser` from [Bot API 6.1](https://core.telegram.org/bots/api-changelog#june-20-2022)
* Add support of `secret_token` in `SetWebhook` request from [Bot API 6.1](https://core.telegram.org/bots/api-changelog#june-20-2022)
* Add support of `createInvoiceLink` request from [Bot API 6.1](https://core.telegram.org/bots/api-changelog#june-20-2022)

## 2.0.3

* `Core`:
  * New function `regularln` for simple creating of `RegularTextSource` with new line in the end
* `API`:
  * New function `downloadFileToTemp` for simple downloading of file in filesystem and manipulation with avoiding of direct using input streams and other low-level things
* `Versions`:
    * `MicroUtils`: `0.11.0` -> `0.11.3`

## 2.0.2

* `Versions`:
    * `MicroUtils`: `0.10.8` -> `0.11.0`
    * `UUID`: `0.4.0` -> `0.4.1`

## 2.0.1

* `Versions`:
    * `Ktor`: `2.0.1` -> `2.0.2`
    * `MicroUtils`: `0.10.5` -> `0.10.8`
* `Utils`:
    * `TelegramBot#longPolling` now accepts `UpdatesFilter` instead of `FlowsUpdatesFilter`

## 2.0.0

___ALL PREVIOUS DEPRECATIONS HAVE BEEN REMOVED___

* `Behaviour Builder`:
    * Mappers have been removed from waiters extensions
    * Triggers extensions now will use filtering inside of context receiver instead of passing the filters into `BehaviourContext`. That means that in the subcontext will not be used preinstalled filters for their `BehaviourContext` and filter of trigger will not be used in subcontext
    * Waiters do not take count parameter anymore
    * Waiters do not take filter parameter anymore. Use flows filters
* `Utils`:
    * Add opportunity to get event messages with specific `ChatEvent` type using `withEvent`/`requireWithEvent` (by analog with `withEvent` and `requireWithEvent`)

## 1.1.3

* `Behaviour Builder with FSM`:
    * Typealias `StateHandlingErrorHandler` from `1.1.2` has been deprecated and should be replaced with the new one from microutils

## 1.1.2

* `Core`:
    * Rename of `TelegramAPIUrlsKeeper#checkWebAppLink` -> `TelegramAPIUrlsKeeper#checkWebAppData` (fix of [#591](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/591))
* `Behaviour Builder with FSM`:
    * New typealias `StateHandlingErrorHandler`
    * `BehaviourBuilderWithFSM` now accepts new parameter `onStateHandlingErrorHandler` which will be used in case if state has not been successfully completed

## 1.1.1

* `Versions`:
    * `MicroUtils.Crypto` will not be provided with that library anymore. Instead, it is recommended to use `Korlibs.Krypto`. You still can add crypto from microutils using next groovy dependency: `dev.inmo:micro_utils.crypto:$micro_utils_version`
* `Core`:
    * Improvements in `TelegramAPIUrlsKeeper#checkWebAppLink`
    * New field in `TelegramAPIUrlsKeeper#webAppDataSecretKeyHash`
* `Behaviour Builder`:
    * Extension `TelegramBot#buildBehaviour` now returns `BehaviourContext`

## 1.1.0

* `Utils`:
    * New parameter `additionalApplicationEngineEnvironmentConfigurator` in `RequestsExecutor#setWebhookInfoAndStartListenWebhooks` and `startListenWebhooks`

## 1.0.1

* `Versions`:
    * `Serialization`: `1.3.2` -> `1.3.3`
    * `MicroUtils`: `0.10.3` -> `0.10.4`

## 1.0.0

__All the `tgbotapi.extensions.*` packages have been removed__

* `Versions`:
    * `Kotlin`: `1.6.10` -> `1.6.21`
    * `Ktor`: `1.6.8` -> `2.0.1`
    * `MicroUtils`: `0.9.24` -> `0.10.3`
* `Core`:
    * **`Ktor` package renamed. Migration:** `dev.inmo.tgbotapi.bot.Ktor` -> `dev.inmo.tgbotapi.bot.ktor`
    * **`CallbackQuery` package renamed. Migration:** `dev.inmo.tgbotapi.types.CallbackQuery([\s\\.])` -> `dev.inmo.tgbotapi.types.queries.callback$1`
    * **`ChatMember` package renamed. Migration:** `dev.inmo.tgbotapi.types.ChatMember([\s\\.])` -> `dev.inmo.tgbotapi.types.chat.member$1`
    * **`ChatAdministratorRightsImpl` replaced. Migration:** `dev.inmo.tgbotapi.types.ChatAdministratorRightsImpl` -> `dev.inmo.tgbotapi.types.chat.member.ChatAdministratorRightsImpl`
    * **`chat.abstract.extended` replaced. Migration:** `dev.inmo.tgbotapi.types.chat.abstract.extended` -> `dev.inmo.tgbotapi.types.chat.member.chat`
    * **`chat.abstract` replaced. Migration:** `dev.inmo.tgbotapi.types.chat.abstract` -> `dev.inmo.tgbotapi.types.chat.member.chat`
    * **`chat.extended` replaced. Migration:** `dev.inmo.tgbotapi.types.chat.extended` -> `dev.inmo.tgbotapi.types.chat.member.chat`
    * **User interfaces have been fully replaced**:
        * `dev.inmo.tgbotapi.types.User` -> `dev.inmo.tgbotapi.types.chat.User`
        * `dev.inmo.tgbotapi.types.CommonUser` -> `dev.inmo.tgbotapi.types.chat.CommonUser`
        * `dev.inmo.tgbotapi.types.ExtendedUser` -> `dev.inmo.tgbotapi.types.chat.ExtendedUser`
        * `dev.inmo.tgbotapi.types.Bot` -> `dev.inmo.tgbotapi.types.chat.Bot`
        * `dev.inmo.tgbotapi.types.CommonBot` -> `dev.inmo.tgbotapi.types.chat.CommonBot`
        * `dev.inmo.tgbotapi.types.ExtendedBot` -> `dev.inmo.tgbotapi.types.chat.ExtendedBot`
        * `dev.inmo.tgbotapi.types.UserSerializer` -> `dev.inmo.tgbotapi.types.chat.UserSerializer`
    * **All `InputMedia` has been renamed as `TelegramMedia`. Migration:** `InputMedia` -> `TelegramMedia`
        * `dev.inmo.tgbotapi.types.InputMedia` -> `dev.inmo.tgbotapi.types.media`
    * **Replaces of `MessageContent`. Migrations:**
        * `dev.inmo.tgbotapi.types.message.content.abstracts` -> `dev.inmo.tgbotapi.types.message.content`
        * `dev.inmo.tgbotapi.types.message.content.media` -> `dev.inmo.tgbotapi.types.message.content`
    * **Replaces of `TextSource`s. Migrations:** `dev.inmo.tgbotapi.types.MessageEntity.textsources` -> `dev.inmo.tgbotapi.types.message.textsources`
    * **Replaces of `ParseMode`s. Migrations:** `dev.inmo.tgbotapi.types.ParseMode` -> `dev.inmo.tgbotapi.types.message`
    * **Replaces of `MediaGroupUpdate`s. Migrations:** `dev.inmo.tgbotapi.types.update.MediaGroupUpdates` -> `dev.inmo.tgbotapi.types.update.media_group`
    * **`InvoiceContent` has been replaced: `dev.inmo.tgbotapi.types.message.payments.InvoiceContent` -> `dev.inmo.tgbotapi.types.message.content.InvoiceContent`**
    * **`PossiblySentViaBotCommonMessage` has been replaced: `dev.inmo.tgbotapi.types.message.content.abstracts.PossiblySentViaBotCommonMessage` -> `dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage`**
    * **Edit live location requests have been replaced:**
        * `dev.inmo.tgbotapi.requests.edit.LiveLocation` -> `dev.inmo.tgbotapi.requests.edit.location.live`
        * `dev.inmo.tgbotapi.extensions.api.edit.LiveLocation` -> `dev.inmo.tgbotapi.extensions.api.edit.location.live`
    * **Edit reply markup requests have been replaced:**
        * `dev.inmo.tgbotapi.requests.edit.ReplyMarkup` -> `dev.inmo.tgbotapi.requests.edit.reply_markup`
        * `dev.inmo.tgbotapi.extensions.api.edit.ReplyMarkup` -> `dev.inmo.tgbotapi.extensions.api.edit.reply_markup`
    * **Common abstracts have been replaced: `dev.inmo.tgbotapi.CommonAbstracts` -> `dev.inmo.tgbotapi.abstracts`**
    * Constructor of `UnknownInlineKeyboardButton` is not internal and can be created with any `json` ([#563](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/563))
    * All the interfaces from `dev.inmo.tgbotapi.types.files.abstracts` have been replaced to `dev.inmo.tgbotapi.types.files` and converted to sealed ([#550](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/550))
    * `PassportFile` has been replaced to `dev.inmo.tgbotapi.types.files`
    * `StorageFile` has been deprecated (fix of [#556](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/556))
        * `MultipartFile` do not require `StorageFile` anymore
        * `InputFile` companion got functions to simplify creation of `InputFile`s
        * New typealias `FileUrl` (represents `FileId` but declare that they are the same)
* `BehaviourBuilder`:
    * `SimpleFilter` now is a `fun interface` instead of just callback (fix of [#546](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/546))
    * New extension `BehaviourContext#createSubContext`. It uses separated `AccumulatorFlow` and will retrieve updates by itself
    * New extension `BehaviourContext#doInContext`
    * Extension `BehaviourContext#doInSubContextWithUpdatesFilter` has been renamed to `BehaviourContext#createSubContextAndDoWithUpdatesFilter`
    * Extension `BehaviourContext#doInSubContext` has been deprecated
* `BehaviourContextWithFSM`:
    * `launchStateHandling` lost its parameter `contextUpdatesFlow: Flow`
    * `handleState` of `BehaviourContextWithFSM` now will get/create sub context for the state and launch handling in it
    * `BehaviourWithFSMStateHandler` now extends `StatesHandler`
    * `BehaviourWithFSMStateHandlerHolder` now extends `CheckableHandlerHolder` and `BehaviourWithFSMStateHandler`
        * Function `checkHandleable` of `BehaviourWithFSMStateHandlerHolder` now is `suspend`

## 0.38.23

* `BehaviourHandler`:
    * Add support of fallback triggers (fix of [#560](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/560))

## 0.38.22

* `Core`:
    * New constant `tgWebAppStartParamField`
    * All keyboards builders and rows blocks becomes not crossinline

## 0.38.21

* `WebApps`:
    * `WebAppInitData#queryId` now have correct js name of field
    * New function `sendDataOrWorkWithQueryId`

## 0.38.20

* `BehaviourBuilder FSM`:
    * Hotfixes
* `WebApps`:
    * New extension `TelegramBot#answerWebAppQuery`
    * New function `handleResult`

## 0.38.19

* `BehaviourBuilder`:
    * Hotfixes
* `BehaviourBuilder FSM`:
    * `BehaviourContextWithFSMBuilder` deprecated in favor to `BehaviourContextWithFSM`
    * Now it is possible to define additional handlers in subcontexts of `BehaviourBuilderWithFSM`

## 0.38.18

* `Core`:
    * Add support of test servers (fix of [#577](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/577))
* `BehaviourBuilder`:
    * Fixes in extension `BehaviourContext#doInSubContextWithUpdatesFilter` (thanks to [xzima](https://github.com/xzima))

## 0.38.17

* `Core`:
    * Add `BotCommandScopeChat` as new `BotCommandScope` (fix of [#574](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/574))
    * `BotCommandScope` companion got several properties and functions for more useful scope creation

## 0.38.16

* `Core`:
    * `TelegramAPIUrlsKeeper` now have two new things: properties `webAppDataSecretKey` and fun `checkWebAppLink`

## 0.38.15

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.9.20` -> `0.9.24`
* `Core`:
    * Fixes in `MessageContent#serializationModule`
* `BehaviourBuilder`:
    * Add triggers for `DataCallbackQuery` and subtypes with regex checking of data

## 0.38.14

__This update contains including of [Telegram Bot API 6.0](https://core.telegram.org/bots/api-changelog#april-16-2022)__

* `Core`:
    * Constructor of `UnknownInlineKeyboardButton` is not internal and can be created with any `json`
* `WebApps`:
    * Created 🎉

## 0.38.13

* `Core`:
    * Fixes in `mention` creation
    * Deprecate `StorageFileInfo`
* `BehaviourBuilder`:
    * In the expectations a lot of `on*Message` extensions have been added (like `onContentMessage`). These extensions could be useful when with the `Content` its message info is important

## 0.38.12

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.9.17` -> `0.9.19`
        * `Coroutines`: `1.6.0` -> `1.6.1`
* `Core`:
    * New type `TextedMediaContent` which will unite `TextedInput` and `MediaContent`
    * `MediaGroupContent` and all subsequent inheritors have been replaced to the package `dev.inmo.tgbotapi.types.message.content.media`
    * `MediaGroupContent` Now extends `TextedMediaContent` instead of `MediaContent`
    * Add `reply` functions with the texted content with including of text
* `Utils`:
    * Improve work with retrieving of accumulated updates

## 0.38.11

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.9.16` -> `0.9.17`
        * `Klock`: `2.6.3` -> `2.7.0`
* `Core`:
    * Fixes in `TextSourcesList` creating in from `RawMessageEntities`
    * Old ways to create keyboards (`matrix` and `row`) have been deprecated
* `API`:
    * Add ability to `reply` with `Poll`
    * Add ability to `reply` with any `MessageContent`
    * Add ability to `reply` with any `TelegramMediaFile`

## 0.38.10

* `API`:
    * All `with*Action` extensions got a contracts which declare that `block` will be called once
    * Add several extensions `TelegramBot#sendPhoto` with `PhotoSize`
    * Add several extensions `TelegramBot#reply` with `PhotoSize`

## 0.38.9

* `Core`:
    * New function `MessageContent.Companion#serializationModule`
    * Now it is possible to create `TelegramBot` (`RequestsExecutor`) with several bots under the hood and opportunity
      for bots requests load balancing or fault-fix via sending of the requests via another bot
* `API`:
    * Add replies which will use another message as a source for reply (`copyMessage`)

## 0.38.8

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.9.12` -> `0.9.16`
        * `Klock`: `2.6.2` -> `2.6.3`
        * `Ktor`: `1.6.7` -> `1.6.8`
* `BehaviourBuilder`:
    * Fixes in `onMediaGroup` and dependent functions
    * Add several new extensions for `SimpleFilter`:
        * `SimpleFilter#listAll`
        * `SimpleFilter#listAny`
        * `SimpleFilter#listNone`

## 0.38.7

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.9.9` -> `0.9.12`
        * `Klock`: `2.5.2` -> `2.6.2`
* `Core`:
    * `SimplePollOption#votes` now is `0` by default
    * New function `PollOption.Companion#simple`

## 0.38.6

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.9.6` -> `0.9.9`
        * `Klock`: `2.4.13` -> `2.5.2`
* `Core`:
    * New member of `MentionTextSource` - `username`

## 0.38.5

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.9.5` -> `0.9.6`
* `Core`:
    * `Username` got new property `usernameWithoutAt` which will return `username` without leading `@`
* `Utils`:
    * Several new functions for working with deep links:
        * `makeUsernameDeepLinkPrefix`
        * `makeTelegramDeepLink`
        * `makeDeepLink`

## 0.38.4

__This update contains including of [Telegram Bot API 5.7](https://core.telegram.org/bots/api-changelog#january-31-2022)__

* `Core`:
    * Support of new fields `Sticker`
    * Support of new fields `StickerSet`
    * Support of new fields in creating of sticker set and sticker
* `Utils`:
    * Rename `PathedFile` to avoid clash with core file (fix of [#529](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/529))

## 0.38.3

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.9.1` -> `0.9.2`
        * `Klock`: `2.4.10` -> `2.4.12`
        * `UUID`: `0.3.1` -> `0.4.0`
* `API`
    * New extensions `TelegramBot#send*` for media groups with contents

## 0.38.2

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.9.0` -> `0.9.1`
* `API`
    * New extensions `TelegramBot#copyMessages` for media groups

## 0.38.1

* `Core`:
    * `MessageCallbackQuery` (and all implementers as well) has changed the type of `message`: now it is `ContentMessage<MessageContent>` instead of `Message`
    * New type `ForwardFromPublicChatInfo` as extender of `ForwardInfo`:
        * `ForwardFromChannelInfo` now extends `ForwardFromPublicChatInfo`
        * `ForwardFromSupergroupInfo` now extends `ForwardFromPublicChatInfo`
    * New type of events: `UserLoggedIn`
* `Utils`:
    * ([#511](https://github.com/InsanusMokrassar/TelegramBotAPI/issues/511)) New extensions properties (raw fields as in original API) for several types have been added:
        * `Message`
        * `CallbackQuery`
        * `ChosenInlineResult`
        * `InlineQuery`
        * `Poll`

## 0.38.0

_This update contains [Telegram Bot API 5.6](https://core.telegram.org/bots/api-changelog#december-30-2021) implementation_

_This update contains migration onto Kotlin 1.6_

_This update has changed constructors of all `CommonMessage` implementations_


* `Common`:
    * `Version`:
        * `Kotlin`: `1.5.31` -> `1.6.10`
        * `Coroutines`: `1.5.2` -> `1.6.0`
        * `Serialization`: `1.3.1` -> `1.3.2`
        * `Klock`: `2.4.8` -> `2.4.10`
        * `Ktor`: `1.6.5` -> `1.6.7`
        * `MicroUtils`: `0.8.7` -> `0.9.0`
* `Core`:
    * Add `SpoilerTextSource` (as part of `Telegram Bot API 5.6` update)
    * Add support of `protect_content` as a field `protectContent` in all send message requests and parameter in all
      functions related to that requests (as part of `Telegram Bot API 5.6` update)
    * **ALL IMPLEMENTERS OF `CommonMessage` HAS CHANGED THEIR CONSTRUCTOR: NOW THEY GET `hasProtectedContent` instead of
      `forwardable` (inversed) field**
        * `ChannelContentMessageImpl`
        * `ChannelMediaGroupMessage`
        * `CommonMediaGroupMessage`
        * `GroupContentMessage` implementers
            * `ConnectedFromChannelGroupContentMessageImpl`
            * `UnconnectedFromChannelGroupContentMessageImpl`
            * `AnonymousGroupContentMessageImpl`
            * `CommonGroupContentMessageImpl`
        * `PrivateContentMessageImpl`

## 0.37.4

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.8.7` -> `0.8.9`
* `Core`:
    * New `SupergroupEvent` subtype: `MigratedToSupergroup`. This event is sent when a group is converted to a supergroup while bot is in the group.
    * Helper extenstion functions on `ChatEvent` to cast it to `MigratedToSupergroup`.

## 0.37.3 Hotfix of 0.37.2

* `Core`:
    * Fixes in hierarchy (and creating) of messages from channels

---

`0.37.2` changelog:

_This update contains [Telegram Bot API 5.5](https://core.telegram.org/bots/api-changelog#december-7-2021) implementation_

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.8.2` -> `0.8.7`
* `Core`:
    * New request type: `ChatSenderRequest`
        * New request `BanChatSenderChat`
        * New request `UnbanChatSenderChat`
    * `ExtendedPrivateChat` got new properties: `hasPrivateForwards` and `allowCreateUserIdLink` (same as `hasPrivateForwards`)
    * All `ContentMessage` got field `forwardable` (old constructors marked as `Deprecated`)
    * `FromChannelGroupContentMessage` has been divided for two interfaces (and corresponding classes):
        * `ConnectedFromChannelGroupContentMessage` (and `ConnectedFromChannelGroupContentMessageImpl`) for connected to the group channels messages
        * `UnconnectedFromChannelGroupContentMessage` (and `UnconnectedFromChannelGroupContentMessageImpl`) for unconnected channels
* `API`:
    * New extensions `TelegramBot#banChatSenderChat`
    * New extensions `TelegramBot#unbanChatSenderChat`
* `Utils`:
    * Fix of `EntitiesBuilder#linkln`

## 0.37.2 Telegram Bot API 5.5

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.8.2` -> `0.8.7`
* `Core`:
    * New request type: `ChatSenderRequest`
        * New request `BanChatSenderChat`
        * New request `UnbanChatSenderChat`
    * `ExtendedPrivateChat` got new properties: `hasPrivateForwards` and `allowCreateUserIdLink` (same as `hasPrivateForwards`)
    * All `ContentMessage` got field `forwardable` (old constructors marked as `Deprecated`)
    * `ChannelContentMessage` has been divided for two interfaces (and corresponding classes):
        * `ConnectedChannelContentMessage` (and `ConnectedChannelContentMessageImpl`) for connected to the group channels messages
        * `UnconnectedChannelContentMessage` (and `UnconnectedChannelContentMessageImpl`) for unconnected channels
* `API`:
    * New extensions `TelegramBot#banChatSenderChat`
    * New extensions `TelegramBot#unbanChatSenderChat`
* `Utils`:
    * Fix of `EntitiesBuilder#linkln`

## 0.37.1

* `Common`:
    * `Version`:
        * `Serialization`: `1.3.0` -> `1.3.1`
        * `Klock`: `2.4.7` -> `2.4.8`
        * `MicroUtils`: `0.8.1` -> `0.8.2`

## 0.37.0 Telegram Bot API 5.4

**ALL DEPRECATIONS WERE REMOVED**

* `Common`:
    * `Version`:
        * `Klock`: `2.4.6` -> `2.4.7`
        * `Ktor`: `1.6.4` -> `1.6.5`
        * `MicroUtils`: `0.7.3` -> `0.8.1`
* `Core`:
    * Replacement of simple `CreateChatInviteLink` and `EditChatInviteLink` with several new:
        * `CreateChatInviteLinkSimple`
        * `CreateChatInviteLinkWithLimitedMembers`
        * `CreateChatInviteLinkWithJoinRequest`
        * `EditChatInviteLinkSimple`
        * `EditChatInviteLinkWithLimitedMembers`
        * `EditChatInviteLinkWithJoinRequest`
    * New `BotAction`: `ChooseStickerAction`
    * Now requester will throw exceptions related to responses decoding directly instead of wrapping in
      `RequestException`
    * 
* `BehaviourBuilder FSM`:
    * **Incompatible changes** (now generics are used in state machines)
    * `strictlyOn` and `onStateOrSubstate` now are part of `BehaviourContextWithFSMBuilder`

## 0.36.1

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.7.2` -> `0.7.3`
* `API`:
    * Fix `getMyCommands` overloads conflict when no arguments provided
* `Utils`:
    * `buildEntities` DSL now have parameter `separator` to specify `TextSource` that will be inserted between other sources

## 0.36.0

**ALL PREVIOUS DEPRECATIONS HAVE BEEN REMOVED**
**ALL EXTENSION PACKAGES HAS BEEN RENAMES**. Old packages are still available, but will be removed in next major update:

* `tgbotapi.extensions.api` -> `tgbotape.api`
* `tgbotapi.extensions.utils` -> `tgbotape.utils`
* `tgbotapi.extensions.behaviour_builder` -> `tgbotape.behaviour_builder`

* `Common`:
    * `Version`:
        * `Serialization`: `1.2.2` -> `1.3.0`
        * `MicroUtils`: `0.5.28` -> `0.7.2`
        * `Klock`: `2.4.3` -> `2.4.6`
        * `Ktor`: `1.6.3` -> `1.6.4`
* `Core`:
    * `PrivateContentMessageImpl#paymentInfo` now is deprecated and will always be null
    * `PayInlineKeyboardButton#pay` now is deprecated
    * `RowBuilder` and `MatrixBuilder` now are open and available for extending
    * `MatrixBuilder#matrix` will return read-only new list instead of original internal `mutMatrix`
    * Introduced new type of events `SuccessfulPaymentEvent` instead of putting of payment inside of message
    * New type of events union: `PublicChatEvent`. `CommonEvent` is still union of any `ChatEvent`
    * New `AbstractFlowsUpdatesFilter` with default `lazy` realization for all typed flows
    * `FlowsUpdatesFilter` fun now have `onBufferOverflow` and `upstreamUpdatesFlow` as incoming params
        * `DefaultFlowsUpdatesFilter` now use additional `upstreamUpdatesFlow` as source of updates
* `Utils`:
    * Two new dsl:
        * `inlineKeyboard` for creating `InlineKeyboardMarkup`
        * `replyKeyboard` for creating `ReplyKeyboardMarkup`
    * Cast helpers for `Message` (thanks to [madhead](https://github.com/madhead)):
        * `asPossiblyReplyMessage`: tries to cast a `Message` to `PossiblyReplyMessage`, returns `null` if the message is not of that type
        * `requirePossiblyReplyMessage`: casts a `Message` to `PossiblyReplyMessage`, fails if the message is not of that type
        * `whenPossiblyReplyMessage`: tries to cast a `Message` to `PossiblyReplyMessage` and runs the given block of code with it, if the cast is successful
    * New type `WithUser` for unioning of all types with `user`
        * `FromUser` now extends `WithUser`
        * Cast helpers for type `WithUser`: `asWithUser`, `whenWithUser`, `requireWithUser`
* `Behaviour Builder`:
    * New expecters and waiters:
        * `waitShippingQueries`/`onShippingQuery`
        * `waitPreCheckoutQueries`/`onPreCheckoutQuery`
        * `waitChosenInlineResult`/`onChosenInlineResult`
        * `waitPollUpdates`/`onPollUpdates`
* `Behaviour Builder FSM extension`:
    * See [Difference between old Behaviour Builder and new one with FSM](https://telegra.ph/Difference-between-old-Behaviour-Builder-and-new-one-with-FSM-10-18)

## 0.35.9

* `Common`:
    * `Version`:
        * `Kotlin`: `1.5.30` -> `1.5.31`
        * `Klock`: `2.4.1` -> `2.4.2`
        * `MicroUtils`: `0.5.25` -> `0.5.28`
* `Core`:
    * New `BotAction` implementation - `CustomBotAction`
    * `LocationContent` has been divided to two different types: `LiveLocationContent` and `StaticLocationContent`
* `API`:
    * Two new extensions: `TelegramBot#answer` with `CallbackQuery` and `InlineQuery`
* `Behaviour Builder`:
    * All triggers have been changed to use two filters: filter for in subcontext data and filter for incoming data
    * New waiters for edited content
    * New extension `BehaviourContext#followLocation`
    * New factory-functions:
        * `BehaviourContextReceiver`
        * `BehaviourContextAndTypeReceiver`
        * `BehaviourContextAndTwoTypesReceiver`
    * Old API for triggers with the flags like `includeFilterByChatInBehaviourSubContext` have been deprecated

## 0.35.8

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.5.24` -> `0.5.25`
        * `UUID`: `0.3.0` -> `0.3.1`
* `Core`:
    * `MultipartRequestCallFactory` now will use file name as multipart `filename` parameter instead of generated file id
    * New extension `MPPFile#asMultipartFile`
* `API`
    * Fixes in `TelegramBot#withAction`
* `Behaviour Builder`:
    * New extensions `BehaviourContext#commandWithArgs` and `BehaviourContext#onCommandWithArgs`

## 0.35.7

* `Common`:
    * `Version`:
        * `Kotlin`: `1.5.21` -> `1.5.30`
        * `Klock`: `2.3.3` -> `2.4.1`
        * `Ktor`: `1.6.2` -> `1.6.3`
        * `Coroutines`: `1.5.1` -> `1.5.2`
        * `MicroUtils`: `0.5.21` -> `0.5.24`

## 0.35.6

* `Common`:
    * `Version`:
        * `Klock`: `2.3.1` -> `2.3.3`
        * `MicroUtils`: `0.5.19` -> `0.5.21`
* `Core`:
    * All `FlowsUpdatesFilter` flows have been renamed and updated
* `Utils`:
    * Extensions `allSentMessagesFlow` and `allSentMediaGroupsFlow` have been deprecated

## 0.35.5

**MIME TYPES FOR REQUESTS HAVE BEEN DEPRECATED DUE TO REDUNDANCY OF MIME TYPES IN FILES SENDING**

* `Core`:
    * Several new extensions `ByteReadChannel#asStorageFile` and `ByteReadChannelAllocator#asStorageFile`
    * Several new extensions `ByteArray#asMultipartFile`, `ByteReadChannel#asMultipartFile` and
    `ByteReadChannelAllocator#asMultipartFile`
    * New extension `StorageFile#asMultipartFile`
* `API`:
    * New extensions `TelegramBot#downloadFile` for writing of incoming bytes to the file
    * New extensions `TelegramBot#downloadFileStream` and `TelegramBot#downloadFileStreamAllocator` for getting of input
    streams instead of whole bytes arrays
    * Old extensions `TelegramBot#downloadFile` has been replaced to the new package. Migration: replace in your project
    `import dev.inmo.tgbotapi.extensions.api.downloadFile` with `import dev.inmo.tgbotapi.extensions.api.files.downloadFile`
    * `PathedFile#filename` extension has been deprecated, and new property `PathedFile#fileName` has been included
      directly in `PathedFile`
* `Utils`:
    * Add several functions `convertToStorageFile` and extensions `TelegramBot#convertToStorageFile`

## 0.35.4 Hotfix

* `Common`:
    * `Version`:
        * `MicroUtils`: `0.5.18` -> `0.5.19`

## 0.35.3

* `Common`:
    * `Version`:
        * `Klock`: `2.2.0` -> `2.3.1`
        * `Ktor`: `1.6.1` -> `1.6.2`
        * `MicroUtils`: `0.5.16` -> `0.5.18`
* `Core`:
    * **`SimpleRequestCallFactory` and `MultipartRequestCallFactory` became a classes instead of objects to avoid
    collisions in different bots**
    * Support of strongly-typed ietf language codes has been added
* `API`:
    * New extension `TelegramBot#downloadFile` for any `MediaContent`
* `Behaviour Builder`:
    * New provider `defaultCoroutineScopeProvider`
        * Now it is not necessary to provide `CoroutineScope` to `TelegramBot#buildBehaviour`
        extension
    * New `TelegramBot#buildBehaviour` extension with `FlowUpdatesFilter` and `CoroutineScope` with
    default `CoroutineScope`
    * New typealias `SimpleFilter` for unifying triggers filter signatures
         * All waiters got real filters (`SimpleFilter`) and rename old filters as mappers
    * New extensions for `Any`: `as`/`when`/`require` for `WithOptionalLanguageCode` and `WithLanguageCode`

## 0.35.2

* `Common`:
    * `Version`:
        * `Kotlin`: `1.5.20` -> `1.5.21`
        * `Coroutines`: `1.5.0` -> `1.5.1`
        * `Serialization`: `1.2.1` -> `1.2.2`
        * `Klock`: `2.1.2` -> `2.2.0`
        * `Ktor`: `1.6.0` -> `1.6.1`
        * `MicroUtils`: `0.5.15` -> `0.5.16`

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
