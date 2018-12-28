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
