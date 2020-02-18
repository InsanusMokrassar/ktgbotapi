# TelegramBotAPI extensions

[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-api/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-api/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI-extensions-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI-extensions-api)
[![Build Status](https://jenkins.insanusmokrassar.com/buildStatus/icon?job=TelegramBotAPI-extensions-api_master__publishing)](https://jenkins.insanusmokrassar.com/job/TelegramBotAPI-extensions-api_master__publishing/)

## What is it?

It is wrapper library for [TelegramBotAPI](../TelegramBotAPI/README.md). Here you can find extensions for
`RequestsExecutor`, which are more look like Telegram Bot API requests and in the same time have more obvious signatures
to help understand some restrictions in Telegram system.

## Compatibility

This library always compatible with original `TelegramBotAPI` library version

## How to implement library?

Common ways to implement this library are presented here. In some cases it will require additional steps
like inserting of additional libraries (like `kotlin stdlib`). In the examples will be used variable
`telegrambotapi-extensions-api.version`, which must be set up by developer. Available versions are presented on
[bintray](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-api), next version is last published:

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-api/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-api/_latestVersion)

### Maven

Dependency config presented here:

```xml
<dependency>
  <groupId>com.github.insanusmokrassar</groupId>
  <artifactId>TelegramBotAPI-extensions-api</artifactId>
  <version>${telegrambotapi-extensions-api.version}</version>
</dependency>
```

### Gradle

To use last versions you will need to add one line in repositories block of your `build.gradle`:

`jcenter()` or `mavenCentral()`

And add next line to your dependencies block:

```groovy
implementation "com.github.insanusmokrassar:TelegramBotAPI-extensions-api:$telegrambotapi_extensions_api_version"
```

or for old gradle:

```groovy
compile "com.github.insanusmokrassar:TelegramBotAPI-extensions-api:$telegrambotapi_extensions_api_version"
```

## Example of usage and comparison with `TelegramBotAPI`

As said in [TelegramBotAPI](../TelegramBotAPI/README.md#Requests), it is possible to use next syntax for requests:

```kotlin
val requestsExecutor: RequestsExecutor = ...
requestsExecutor.execute(GetMe())
``` 

This library offer a little bit another way for this:

```kotlin
val bot: RequestsExecutor = ...
bot.getMe()
```

The result type of [GetMe (and getMe extension)](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/TelegramBotAPI/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/requests/GetMe.kt)
request is
[ExtendedBot](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/TelegramBotAPI/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/types/User.kt).
