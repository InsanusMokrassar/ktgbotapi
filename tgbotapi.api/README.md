# TelegramBotAPI API extensions

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.api)

## What is it?

It is wrapper library for [TelegramBotAPI Core](../tgbotapi.core/README.md). Here you can find extensions for
`RequestsExecutor`, which are more look like Telegram Bot API requests and in the same time have more obvious signatures
to help understand some restrictions in Telegram system.

## Compatibility

This library always compatible with original `tgbotapi.core` library version

## How to implement library?

Common ways to implement this library are presented here. In some cases it will require additional steps
like inserting of additional libraries (like `kotlin stdlib`). In the examples will be used variable
`telegrambotapi-extensions-api.version`, which must be set up by developer.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.api)

### Maven

Dependency config presented here:

```xml
<dependency>
  <groupId>dev.inmo</groupId>
  <artifactId>tgbotapi.api</artifactId>
  <version>${telegrambotapi-extensions-api.version}</version>
</dependency>
```

### Gradle

To use last versions you will need to add one line in repositories block of your `build.gradle`:

`mavenCentral()`

And add next line to your dependencies block:

```groovy
implementation "dev.inmo:tgbotapi.api:$telegrambotapi_extensions_api_version"
```

or for old gradle:

```groovy
compile "dev.inmo:tgbotapi.api:$telegrambotapi_extensions_api_version"
```

## Example of usage and comparison with `TelegramBotAPI`

Here presented review table for comparison of api from original [TelegramBotAPI](../TelegramBotAPI/README.md#Requests)
and extensions-api library. First of all, this library allow to create bot instance in a new way:

```kotlin
val bot = telegramBot("IT IS YOUR TOKEN")
```

There are a lot of signature for this. For example, you can create bot with next code:

```kotlin
val bot = telegramBot("IT IS YOUR TOKEN") {
    proxy = ProxyBuilder.socks("127.0.0.1", 1080)
}
```

In all examples supposed that you have created bot.

| tgbotapi.core | tgbotapi.api |
|---------------------|-------------------------------|
| bot.execute(GetMe) |    bot.getMe()          |
| bot.execute(SendTextMessage(someChatId, text)) | bot.sendTextMessage(chat, text) |

## Updates

**Currently, these paragraphs almost outdated due to the fact that extensions for listening of updates and webhooks were
replaced into `tgbotapi.utils`. But, most part of information below is correct with small fixes and
adding of `tgbotapi.utils` dependency.**

Usually, it is more comfortable to use filter object to get separated types of updates:

```kotlin
val filter = FlowsUpdatesFilter(100)
```

In this case you will be able:

* Separate types of incoming updates (even media groups)
* Simplify launch of getting updates:
```kotlin
bot.startGettingOfUpdates(
    filter,
    scope = CoroutineScope(Dispatchers.Default)
)
```
* Use `filter` flows to comfortable filter, map and do other operations with the whole
getting updates process:
```kotlin
filter.messageFlow.mapNotNull {
    it.data as? ContentMessage<*>
}.onEach {
    println(it)
}.launchIn(
    CoroutineScope(Dispatchers.Default)
)
```

### Alternative way

There is an alternative way to get updates. In fact it is almost the same, but could be more useful for some cases:

```kotlin
val filter = bot.startGettingOfUpdates(
    scope = CoroutineScope(Dispatchers.Default)
) { // Here as reveiver will be FlowsUpdatesFilter
    messageFlow.mapNotNull {
        it.data as? ContentMessage<*>
    }.onEach {
        println(it)
    }.launchIn(
        CoroutineScope(Dispatchers.Default)
    )
}
```
