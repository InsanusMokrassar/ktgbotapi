# TelegramBotAPI

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI/_latestVersion)
[![Build Status](https://jenkins.insanusmokrassar.com/buildStatus/icon?job=TelegramBotAPI_master__publishing)](https://jenkins.insanusmokrassar.com/job/TelegramBotAPI_master__publishing/)

## What is it?

Library for Object-Oriented and type-safe work with Telegram Bot API. Most part of some specific solves or unuseful
moments are describing by official [Telegram Bot API](https://core.telegram.org/bots/api).

## Compatibility

This version compatible with [31th of December 2019 update of TelegramBotAPI (version 4.5)](https://core.telegram.org/bots/api#december-31-2019).
There is Telegram Passport API exception of implemented functionality, which was presented in
[August 2018 update of TelegramBotAPI](https://core.telegram.org/bots/api#august-27-2018) update. It will be implemented
as soon as possible. All APIs that are not included are presented
[wiki](https://github.com/InsanusMokrassar/TelegramBotAPI/wiki/Not-included-API).

## How to implement library?

Common ways to implement this library are presented here. In some cases it will require additional steps
like inserting of additional libraries (like `kotlin stdlib`). In the examples will be used variable
`telegrambotapi.version`, which must be set up by developer. Available versions are presented on
[bintray](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI), next version is last published:

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI/_latestVersion)

Currently, last versions of library are not included into the Maven repository (for the reason difficult in publishing
of signed artifacts in Bintray). You can:

* Use earlier version (available version you can find
[here](https://mvnrepository.com/artifact/com.github.insanusmokrassar/TelegramBotAPI))
* Add `jCenter` repository in build config

### Maven

To use last versions you will need to add several lines in repositories block of your pom.xml:

```xml
<repository>
    <snapshots>
        <enabled>false</enabled>
    </snapshots>
    <id>central</id>
    <name>bintray</name>
    <url>http://jcenter.bintray.com</url>
</repository>
```

Dependency config presented here:

```xml
<dependency>
  <groupId>com.github.insanusmokrassar</groupId>
  <artifactId>TelegramBotAPI</artifactId>
  <version>${telegrambotapi.version}</version>
</dependency>
```

### Gradle

To use last versions you will need to add one line in repositories block of your build.gradle:

```groovy
jcenter()
```

And add next line to your dependencies block:

```groovy
implementation "com.github.insanusmokrassar:TelegramBotAPI:$telegrambotapi_version"
```

or for old gradle:

```groovy
compile "com.github.insanusmokrassar:TelegramBotAPI:$telegrambotapi_version"
```

## How to work with library?

For now, this library have no some API god-object. Instead of this, this library has several
important objects:

* [RequestsExecutor](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/bot/RequestsExecutor.kt)
* [Requests](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/requests)
* [Types](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/types)

### Types

Types declare different objects representation. For example, `Chat` for now represented as
interface and has several realisations:

* PrivateChat
* GroupChat
* SupergroupChat
* ChannelChat

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

The result type of [GetMe](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/requests/GetMe) request is
[User](https://github.com/InsanusMokrassar/TelegramBotAPI/blob/master/src/commonMain/kotlin/com/github/insanusmokrassar/TelegramBotAPI/types/User). In fact, in this result must contain
`isBot` equal to `true` always.


### RequestsExecutor

It is base object which can be used to execute requests in API. For now by default included Ktor
realisation of `RequestsExecutor`, but it is possible, that in future it will be extracted in separated
project. How to create `RequestsExecutor`:

```kotlin
val requestsExecutor = KtorRequestsExecutor(TOKEN)
```

Here `KtorRequestsExecutor` - default realisation with Ktor. `TOKEN` is just a token of bot which was retrieved
according to [instruction](https://core.telegram.org/bots#3-how-do-i-create-a-bot).

Besides, for correct usage of this, you must implement in your project both one of engines for client and server
Ktor libraries:

```groovy
dependencies {
    // ...
    implementation "io.ktor:ktor-server-cio:$ktor_version"
    implementation "io.ktor:ktor-client-okhttp:$ktor_version"
    // ...
}
```

It is able to avoid using of `server` dependency in case if will not be used `Webhook`s. In this case,
dependencies list will be simplify:

```groovy
dependencies {
    // ...
    implementation "io.ktor:ktor-client-okhttp:$ktor_version"
    // ...
}
```

Here was used `okhttp` realisation of client, but there are several others engines for Ktor. More information
available on ktor.io site for [client](https://ktor.io/clients/http-client/engines.html) and [server](https://ktor.io/quickstart/artifacts.html)
engines.

## Getting updates

In this library currently realised two ways to get updates from telegram:

* Polling - in this case bot will request updates from time to time (you can set up delay between requests)
* Webhook via reverse proxy or something like this

### Updates filters

Currently webhook method contains `UpdatesFilter` as necessary argument for getting updates.
`UpdatesFilter` will sort updates and throw their into different callbacks. Currently supporting
separate getting updates for media groups - they are accumulating with debounce in one second
(for being sure that all objects of media group was received).

Updates polling also support `UpdatesFilter` but you must not use it and can get updates directly
in `UpdateReceiver`, which you will provide to `startGettingOfUpdates` method

### Webhook set up

If you wish to use webhook method, you will need:

* White IP - your IP address or host, which available for calling. [TelegramBotAPI](https://core.telegram.org/bots/api#setwebhook)
recommend to use some unique address for each bot which you are using
* SSL certificate. Usually you can obtain the certificate using your domain provider, [Let'sEncrypt](https://letsencrypt.org/) or [create it](https://core.telegram.org/bots/self-signed)
* Nginx or something like this

Template for Nginx server config you can find in [this gist](https://gist.github.com/InsanusMokrassar/fcc6e09cebd07e46e8f0fdec234750c4#file-nginxssl-conf).

For webhook you can provide `File` with public part of certificate, `URL` where bot will be available and inner `PORT` which
will be used to start receiving of updates. Actually, you can skip passing of `File` when you have something like
nginx for proxy forwarding.

In case of using `nginx` with reverse-proxy config, setting up of Webhook will look like:

```kotlin
requestsExecutor.setWebhook(
    WEBHOOK_URL,
    INTERNAL_PORT,
    filter,
    ENGINE_FACTORY
)
``` 

Here:

* `WEBHOOK_URL` - the url which will be used by Telegram system to send updates
* `INTERNAL_PORT` - the port which will be used in bot for listening of updates
* `filter` - instance of [UpdatesFilter](src/main/kotlin/com/github/insanusmokrassar/TelegramBotAPI/utils/extensions/UpdatesFilter.kt),
which will be used to filter incoming updates
* `ENGINE_FACTORY` - used factory name, for example, `CIO` in case of usage `io.ktor:ktor-server-cio` as server engine
