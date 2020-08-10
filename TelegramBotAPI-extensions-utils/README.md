# TelegramBotAPI Util Extensions

- [TelegramBotAPI Util Extensions](#telegrambotapi-util-extensions)
  * [What is it?](#what-is-it)
  * [How to implement library?](#how-to-implement-library)
    + [Maven](#maven)
    + [Gradle](#gradle)
  * [How to use?](#how-to-use)
    + [Updates](#updates)
      - [Long polling](#long-polling)
      - [WebHooks (currently JVM-only)](#webhooks--currently-jvm-only)
    + [Filters](#filters)
      - [Sent messages](#sent-messages)
        * [Common messages](#common-messages)
        * [Chat actions](#chat-actions)
    + [Shortcuts](#shortcuts)
      - [ScheduledCloseInfo](#scheduledcloseinfo)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-utils/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-utils/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI-extensions-utils/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI-extensions-utils)

## What is it?

It is wrapper library for [TelegramBotAPI](../TelegramBotAPI/README.md). Currently, this library contains some usefull filters for commands, updates types and different others.

## How to implement library?

Common ways to implement this library are presented here. In some cases it will require additional steps
like inserting of additional libraries (like `kotlin stdlib`). In the examples will be used variable
`telegrambotapi-extensions-utils_version`, which must be set up by developer. Available versions are presented on
[bintray](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-utils), next version is last published:

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-utils/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-utils/_latestVersion)

### Maven

Dependency config presented here:

```xml
<dependency>
  <groupId>com.github.insanusmokrassar</groupId>
  <artifactId>TelegramBotAPI-extensions-utils</artifactId>
  <version>${telegrambotapi-extensions-utils_version}</version>
</dependency>
```

### Gradle

To use last versions you will need to add one line in repositories block of your `build.gradle`:

`jcenter()` or `mavenCentral()`

And add next line to your dependencies block:

```groovy
implementation "com.github.insanusmokrassar:TelegramBotAPI-extensions-utils:$telegrambotapi-extensions-utils_version"
```

or for old gradle:

```groovy
compile "com.github.insanusmokrassar:TelegramBotAPI-extensions-utils:$telegrambotapi-extensions-utils_version"
```

## How to use?

Here will be presented several examples of usage. In all cases it is expected that you have created your bot and filter:

```kotlin
val bot: RequestsExecutor = KtorRequestsExecutor(
    TelegramAPIUrlsKeeper(BOT_TOKEN)
)
val filter = FlowsUpdatesFilter(64)
```

Alternative way to use the things below:

```kotlin
val filter = bot.startGettingFlowsUpdatesByLongPolling(
    scope = CoroutineScope(Dispatchers.Default)
) {
    // place code from examples here with replacing of `filter` by `this`
}
```

### Updates

As mentioned in [Telegram Bot API reference](https://core.telegram.org/bots/api#getting-updates), there are two ways for
updates retrieving:

* Webhooks
* Long Polling

Both of them you could use in your project using [TelegramBotAPI](../TelegramBotAPI/README.md), but here there are
several useful extensions for both of them.

Anyway, in both of ways it will be useful to know that it is possible to create `UpdateReceiver` object using function
`flowsUpdatesFilter`:

```kotlin
val internalChannelsSizes = 128
flowsUpdatesFilter(internalChannelsSizes/* default is 64 */) {
    textMessages().onEach {
        println("I have received text message: ${it.content}")
    }.launchIn(someCoroutineScope)
    /* ... */
}
```

#### Long polling

The most simple way is Long Polling and one of the usages was mentioned above:

```kotlin
val filter = bot.startGettingFlowsUpdatesByLongPolling(
    scope = CoroutineScope(Dispatchers.Default)
) {
    // place code from examples here with replacing of `filter` by `this`
}
```

Extension `startGettingFlowsUpdatesByLongPolling` was used in this example, but there are a lot of variations of
`startGettingOfUpdatesByLongPolling` and others for getting the same result. Usually, it is supposed that you already
have created `filter` object (or something like this) and will pass it into extension:

```kotlin
val filter = FlowsUpdatesFilter(64)
bot.startGettingOfUpdatesByLongPolling(
    filter
)
```

But also there are extensions which allow to pass lambdas directly:

```kotlin
bot.startGettingOfUpdatesByLongPolling(
    {
        println("Received message update: $it")
    }
)
```

Anyway, it is strictly recommended to pass your `CoroutineScope` object to this method at least for more comfortable
management of updates.

#### WebHooks (currently JVM-only)

For webhooks there are less number of functions and extensions than for Long Polling (but it is still fully automated):

```kotlin
startListenWebhooks(
    8081,
    CIO // require to implement this engine dependency
) {
    // here will be all updates one by one in $it
}
```

Besides, there are two additional opportunities:

* Extension `Route#includeWebhookHandlingInRoute`, which allow you to include webhook processing inside your ktor
application without creating of new one server (as it is happening in `startListenWebhooks`)
    * Also, you can use `Route#includeWebhookHandlingInRouteWithFlows` to use it like `flowUpdatesFilter` fun, but apply
    `FlowsUpdatesFilter` to the block
* Extension `RequestsExecutor#setWebhookInfoAndStartListenWebhooks`. It is allow to set up full server (in fact, with
`startListenWebhooks`), but also send `SetWebhook` request before and check that it was successful

### Filters

There are several filters for flows.

#### Updates

In the next table it is supposed that you are using some `Flow` with type from `Base type of update` and apply
extension `Extension` and will get `Flow` with type from `Result type of update` column.

| Base type of update | Extension | Result type of update |
| ------------------- | --------- | --------------------- |
| `Update` | `onlyBaseMessageUpdates` | `BaseMessageUpdate` |
|  |  |  |
| `BaseMessageUpdate` | `onlySentMessageUpdates` | `BaseSentMessageUpdate` |
| `BaseMessageUpdate` | `onlyEditMessageUpdates` | `BaseEditMessageUpdate` |
| `BaseMessageUpdate` | `onlyMediaGroupsUpdates` | `MediaGroupUpdate` |
|  |  |  |
| `MediaGroupUpdate` | `onlySentMediaGroupUpdates` | `SentMediaGroupUpdate` |
| `MediaGroupUpdate` | `onlyEditMediaGroupUpdates` | `EditMediaGroupUpdate` |

All of these extensions was made for more simple work with the others:

```kotlin
val flow: Flow<BaseMessageUpdate> = ...; // here we are getting flow from somewhere,
                                        // for example, FlowsUpdatesFilter#messageFlow
flow.onlySentMessageUpdates().filterExactCommands(Regex("start"))
```

Here we have used filter `filterExactCommands` which will pass only `ContentMessage` with only one command `start`

#### Sent messages

All sent messages can be filtered for three types:

| Type | Description | Flow extension |
|:---- |:----------- |:-------------- |
| Common messages | Simple messages with text, media, location, etc. | `asContentMessagesFlow` |
| Chat actions | New chat member, rename of chat, etc. | `asChatEventsFlow` |
| Unknown events | Any other messages, that contain unsupported data | `asUnknownMessagesFlow` |

##### Common messages

Unfortunately, due to the erasing of generic types, when you are using `asContentMessagesFlow` you will retrieve
data with type `ContentMessage<*>`. For correct filtering of content type for retrieved objects, was created special
filters:

| Content type | Result type | Flow extension |
|:---- |:----------- |:-------------- |
| Animation | `ContentMessage<AnimationContent>`| `onlyAnimationContentMessages` |
| Audio | `ContentMessage<AudioContent>` | `onlyAudioContentMessages` |
| Contact | `ContentMessage<ContactContent>` | `onlyContactContentMessages` |
| Dice | `ContentMessage<DiceContent>` | `onlyDiceContentMessages` |
| Document | `ContentMessage<DocumentContent>` | `onlyDocumentContentMessages` |
| Game | `ContentMessage<GameContent>` | `onlyGameContentMessages` |
| Invoice | `ContentMessage<InvoiceContent>` | `onlyInvoiceContentMessages` |
| Location | `ContentMessage<LocationContent>` | `onlyLocationContentMessages` |
| Photo | `ContentMessage<PhotoContent>` | `onlyPhotoContentMessages` |
| Poll | `ContentMessage<PollContent>` | `onlyPollContentMessages` |
| Sticker | `ContentMessage<StickerContent>` | `onlyStickerContentMessages` |
| Text | `ContentMessage<TextContent>` | `onlyTextContentMessages` |
| Venue | `ContentMessage<VenueContent>` | `onlyVenueContentMessages` |
| Video | `ContentMessage<VideoContent>` | `onlyVideoContentMessages` |
| VideoNote | `ContentMessage<VideoNoteContent>` | `onlyVideoNoteContentMessages` |
| Voice | `ContentMessage<VoiceContent>` | `onlyVoiceContentMessages` |

For example, if you wish to get only photo messages from private chats of groups, you should call next code:

```kotlin
filter.messageFlow.asContentMessagesFlow().onlyPhotoContentMessages().onEach {
    println(it.content)
}.launchIn(
    CoroutineScope(Dispatchers.Default)
)
```

##### Chat actions

Chat actions can be divided for three types of events source:

| Type | Flow extension |
|:---- |:-------------- |
| Channel events | `onlyChannelEvents` |
| Group events | `onlyGroupEvents` |
| Supergroup events | `onlySupergroupEvents` |

According to this table, if you want to add filtering by supergroup events, you will use code like this:

```kotlin
filter.messageFlow.asChatEventsFlow().onlySupergroupEvents().onEach {
    println(it.chatEvent)
}.launchIn(
    CoroutineScope(Dispatchers.Default)
)
```

### Shortcuts

With shortcuts you are able to use simple factories for several things.

#### ScheduledCloseInfo

In case if you are creating some poll, you able to use next shortcuts.

Next sample will use info with closing at the 10 seconds after now:

```kotlin
closePollExactAt(DateTime.now() + TimeSpan(10000.0))
```

In this example we will do the same, but in another way:

```kotlin
closePollExactAfter(10)
```

Here we have passed `10` seconds and will get the same result object.

In opposite to previous shortcuts, the next one will create `approximate` closing schedule:

```kotlin
closePollAfter(10)
```

The main difference here is that the last one will be closed after 10 seconds since the sending. With first samples
will be created **exact** time for closing of poll
