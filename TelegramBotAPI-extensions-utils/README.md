# TelegramBotAPI Util  Extensions

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
val filter = bot.startGettingUpdates(
    scope = CoroutineScope(Dispatchers.Default)
) {
    // place code from examples here with replacing of `filter` by `this`
}
```

### Getting of only text incoming messages

```kotlin
filter.asContentMessagesFlow().onlyTextContentMessages().onEach {
    println(it.content)
    println(it.fullEntitiesList())
}.launchIn(
    CoroutineScope(Dispatchers.Default)
)
```

As a result, each received message which will be just text message will be printed out with full list of its internal entities