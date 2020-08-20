# TelegramBotAPI-core

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI-core/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-core/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI)

## What is it?

Library for Object-Oriented and type-safe work with Telegram Bot API. Most part of some specific solves or unuseful
moments are describing by official [Telegram Bot API](https://core.telegram.org/bots/api).

## Compatibility

This version compatible with [4th of June 2020 update of TelegramBotAPI (version 4.9)](https://core.telegram.org/bots/api#june-4-2020).
There is only one exception of implemented functionality - Telegram Passport API, which was presented in
[August 2018 update of TelegramBotAPI](https://core.telegram.org/bots/api-changelog#august-27-2018) update. It will be implemented
as soon as possible.

## How to implement library?

Common ways to implement this library are presented here. In some cases it will require additional steps
like inserting of additional libraries (like `kotlin stdlib`). In the examples will be used variable
`telegrambotapi.version`, which must be set up by developer. Available versions are presented on
[bintray](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-core), next version is last published:

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI-core/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-core/_latestVersion)

Currently, last versions of library can be available from the Maven repository with errors (for the reason difficult in publishing
of signed artifacts in Bintray). You can:

* Use earlier version (available version you can find
[here](https://mvnrepository.com/artifact/com.github.insanusmokrassar/TelegramBotAPI) (before 0.28.0) or [here](https://mvnrepository.com/artifact/com.github.insanusmokrassar/TelegramBotAPI-core))
* Add `jCenter` repository in build config

### Maven

Dependency config presented here:

```xml
<dependency>
  <groupId>com.github.insanusmokrassar</groupId>
  <artifactId>TelegramBotAPI-core</artifactId>
  <version>${telegrambotapi.version}</version>
</dependency>
```

### Gradle

To use last versions you will need to add one line in repositories block of your `build.gradle`:

`jcenter()` or `mavenCentral()`

And add next line to your dependencies block:

```groovy
implementation "com.github.insanusmokrassar:TelegramBotAPI-core:$telegrambotapi_version"
```

or for old gradle:

```groovy
compile "com.github.insanusmokrassar:TelegramBotAPI-core:$telegrambotapi_version"
```

## How to work with library?

For now, this library have no some API god-object. Instead of this, this library has several
important objects:

* [RequestsExecutor](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/TelegramBotAPI-core/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/bot/RequestsExecutor.kt)
* [Requests](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/TelegramBotAPI-core/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/requests)
* [Types](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/TelegramBotAPI-core/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/types)

### Types

Types declare different objects representation. For example, `Chat` for now represented as
interface and has several realisations:

* `PrivateChat`
* `GroupChat`
* `SupergroupChat`
* `ChannelChat`

Instead of common garbage with all information as in original [Chat](https://core.telegram.org/bots/api#chat),
here it was separated for more obvious difference between chats types and their possible content.

The same principle work with a lot of others things in this Telegram bot API. 

### Requests

Requests usually are very simple objects, but some of them are using their own
build factories. For example, the next code show, how to get information about bot:

```kotlin
val requestsExecutor: RequestsExecutor = ...
requestsExecutor.execute(GetMe())
``` 

Also there is an alternative syntax for requests (like `requestsExecutor.getMe()` in project
[TelegramBotAPI-extensions-api](../TelegramBotAPI-extensions-api/README.md))

The result type of [GetMe (and getMe extension)](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/TelegramBotAPI-core/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/requests/GetMe.kt)
request is
[ExtendedBot](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/TelegramBotAPI-core/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/types/User.kt).

### RequestsExecutor

It is base object which can be used to execute requests in API. For now by default included Ktor
realisation of `RequestsExecutor`, but it is possible, that in future it will be extracted in separated
project. How to create `RequestsExecutor`:

```kotlin
val requestsExecutor = KtorRequestsExecutor(
    TelegramAPIUrlsKeeper(TOKEN)
)
```

Here:

* `KtorRequestsExecutor` - default realisation with [ktor](https://ktor.io)
* `TelegramAPIUrlsKeeper` - special keeper, which  you can save and use for getting files full urls (`resolveFileURL`
extension inside of `PathedFile.kt`)
* `TOKEN` is just a token of bot which was retrieved according to
[instruction](https://core.telegram.org/bots#3-how-do-i-create-a-bot).

By default, for JVM there is implemented `CIO` client engine, but there is not server engine. Both can be changed like
here:

```groovy
dependencies {
    // ...
    implementation "io.ktor:ktor-server-cio:$ktor_version" // for implementing of server engine
    implementation "io.ktor:ktor-client-okhttp:$ktor_version" // for implementing of additional client engine
    // ...
}
```

You can avoid using of `server` dependency in case if you will not use `Webhook`s. In this case,
dependencies list will be simplify:

```groovy
dependencies {
    // ...
    implementation "io.ktor:ktor-client-okhttp:$ktor_version" // for implementing of additional client engine
    // ...
}
```

Here was used `okhttp` realisation of client, but there are several others engines for Ktor. More information
available on ktor.io site for [client](https://ktor.io/clients/http-client/engines.html) and [server](https://ktor.io/quickstart/artifacts.html)
engines.

