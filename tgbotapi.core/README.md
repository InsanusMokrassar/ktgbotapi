# TelegramBotAPI Core

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.core)

- [TelegramBotAPI Core](#telegrambotapi-core)
    * [What is it?](#what-is-it-)
    * [Compatibility](#compatibility)
    * [How to implement library?](#how-to-implement-library-)
        + [Maven](#maven)
        + [Gradle](#gradle)
    * [How to work with library?](#how-to-work-with-library-)
        + [Types](#types)
        + [Requests](#requests)
        + [RequestsExecutor](#requestsexecutor)
        + [Passport](#passport)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

## What is it?

Library for Object-Oriented and type-safe work with Telegram Bot API. Most part of some specific solves or unuseful
moments are describing by official [Telegram Bot API](https://core.telegram.org/bots/api).

## Compatibility

This version compatible with [25th of June 2021 update of TelegramBotAPI (version 5.3)](https://core.telegram.org/bots/api-changelog#june-25-2021).

## How to implement library?

Common ways to implement this library are presented here. In some cases it will require additional steps
like inserting of additional libraries (like `kotlin stdlib`). In the examples will be used variable
`telegrambotapi.version`, which must be set up by developer. Available versions are presented on
[bintray](https://bintray.com/insanusmokrassar/TelegramBotAPI/tgbotapi.core), next version is last published:

[![Download](https://api.bintray.com/packages/insanusmokrassar/TelegramBotAPI/tgbotapi.core/images/download.svg) ](https://bintray.com/insanusmokrassar/TelegramBotAPI/tgbotapi.core/_latestVersion)

Currently, last versions of library can be available from the Maven repository with errors (for the reason difficult in publishing
of signed artifacts in Bintray). You can:

* Use earlier version (available version you can find
[here](https://mvnrepository.com/artifact/com.github.insanusmokrassar/TelegramBotAPI) (before 0.28.0) or [here](https://mvnrepository.com/artifact/dev.inmo/tgbotapi.core))
* Add `mavenCentral` repository in build config

### Maven

Dependency config presented here:

```xml
<dependency>
  <groupId>dev.inmo</groupId>
  <artifactId>tgbotapi.core</artifactId>
  <version>${telegrambotapi.version}</version>
</dependency>
```

### Gradle

To use last versions you will need to add one line in repositories block of your `build.gradle`:

`mavenCentral()`

And add next line to your dependencies block:

```groovy
implementation "dev.inmo:tgbotapi.core:$telegrambotapi_version"
```

or for old gradle:

```groovy
compile "dev.inmo:tgbotapi.core:$telegrambotapi_version"
```

## How to work with library?

For now, this library have no some API god-object. Instead of this, this library has several
important objects:

* [RequestsExecutor](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/tgbotapi.core/src/commonMain/kotlin/dev/inmo/tgbotapi/bot/RequestsExecutor.kt)
* [Requests](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/tgbotapi.core/src/commonMain/kotlin/dev/inmo/tgbotapi/requests)
* [Types](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/tgbotapi.core/src/commonMain/kotlin/dev/inmo/tgbotapi/types)

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
[tgbotapi.api](../tgbotapi.api/README.md))

The result type of [GetMe (and getMe extension)](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/tgbotapi.core/src/commonMain/kotlin/com/github/insanusmokrassar/tgbotapi/requests/GetMe.kt)
request is
[ExtendedBot](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/tgbotapi.core/src/commonMain/kotlin/dev/inmo/tgbotapi/types/User.kt).

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

### Passport

In case you wish to work with `Telegram Passport`, currently there are several useful things, but most part of working
with decryption and handling is available only on JVM. Next snippet contains example of data decryption on JVM platform:

```kotlin
passportMessage.passportData.doInDecryptionContextWithPKCS8Key(privateKey) {
    val passportDataSecureValue = passport ?.data ?: return@doInDecryptionContextWithPKCS8Key
    val passportData = (passportMessage.passportData.data.firstOrNull { it is CommonPassport } ?: return@doInDecryptionContextWithPKCS8Key) as CommonPassport
    val decrypted = passportDataSecureValue.decrypt(
        passportData.data
    ) ?.decodeToString() ?: return@doInDecryptionContextWithPKCS8Key
    println(decrypted)
}
```
