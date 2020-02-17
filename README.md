# TelegramBotAPI

[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![Chat in Telegram](badges/chat.svg)](https://t.me/InMoTelegramBotAPI)

## TelegramBotAPI status

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI)
[![Build Status](https://jenkins.insanusmokrassar.com/buildStatus/icon?job=TelegramBotAPI_master__publishing)](https://jenkins.insanusmokrassar.com/job/TelegramBotAPI_master__publishing/)

## TelegramBotAPI extensions status

[![Download](https://api.bintray.com/packages/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-api/images/download.svg) ](https://bintray.com/insanusmokrassar/StandardRepository/TelegramBotAPI-extensions-api/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI-extensions-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.insanusmokrassar/TelegramBotAPI-extensions-api)
[![Build Status](https://jenkins.insanusmokrassar.com/buildStatus/icon?job=TelegramBotAPI-extensions-api_master__publishing)](https://jenkins.insanusmokrassar.com/job/TelegramBotAPI-extensions-api_master__publishing/)

## What is it?

It is a complex of libraries for working with `TelegramBotAPI` in type-safe and strict way as much as it possible. In
the list of this complex currently next projects:

* [TelegramBotAPI](TelegramBotAPI/README.md) - core of library. In fact it is independent library and can be used alone
without any additional library
* [TelegramBotAPI Extensions](TelegramBotAPI-extensions-api/README.md) - contains extensions (mostly for
`RequestsExecutor`), which allows to use the core library in more pleasant way

Most part of some specific solves or unuseful
moments are describing by official [Telegram Bot API](https://core.telegram.org/bots/api).

## Ok, where should I start?

Firstly, look at the [TelegramBotAPI](TelegramBotAPI/README.md). Here you can find all information about currently
covered Telegram Bot API and other things. After this you can look at the
[TelegramBotAPI Extensions](TelegramBotAPI-extensions-api/README.md).

Anyway, all libraries are very typical inside of them. For example, any request in TelegramBotAPI look like
`requestsExecutor.execute(SomeRequest())`.
