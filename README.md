# TelegramBotAPI

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI/_latestVersion)
[![Build Status](https://jenkins.insanusmokrassar.space/buildStatus/icon?job=TelegramBotAPI_master__publishing)](https://jenkins.insanusmokrassar.space/job/TelegramBotAPI_master__publishing/)

## What is it?

It is one more project which wish to be useful and full Telegram Bots API bridge for Kotlin. Most part of some specific
solves or unuseful moments are describing by official [Telegram Bot API](https://core.telegram.org/bots/api).

## How to work with library?

By default in any documentation will be meaning that you have variable in scope with names

| Name of variable | Description | Where to get? (Examples) |
|:----------------:|:-----------:|:------------------------:|
| executor | [RequestsExecutor](src/main/kotlin/com/github/insanusmokrassar/TelegramBotAPI/bot/RequestsExecutor.kt) | [Ktor RequestExecutor realisation](src/main/kotlin/com/github/insanusmokrassar/TelegramBotAPI/bot/Ktor/KtorRequestsExecutor.kt) |

## Requests Examples

### Get Me

```kotlin
executor.execute(GetMe())
```

As a result you will receive `User` object. This object used as is now (as in API documentation), but it is possible
that this class will be renamed to `RawUser` and you will be able to get real realisation of this object like `Bot` (in
cases when `isBot` == `true`) or `User` (otherwise)

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

For webhook you must provide `File` with public part of certificate, `URL` where bot placed and inner `PORT` which
will be used to start receiving of updates.
