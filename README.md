[Participate in our common survey ☺](https://forms.gle/q6Xf8K3fD1pPsYUw9)

# TelegramBotAPI

- [TelegramBotAPI](#telegrambotapi)
  * [Examples](#examples)
    + [Most common example](#most-common-example)
    + [Handling only last messages](#handling-only-last-messages)
    + [Build a little bit more complex behaviour](#build-a-little-bit-more-complex-behaviour)
    + [More examples](#more-examples)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

Hello! This is a set of libraries for working with Telegram Bot API.

| Common info                           | [![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin) [![Build Status](https://github.com/InsanusMokrassar/TelegramBotAPI/workflows/Build/badge.svg)](https://github.com/InsanusMokrassar/TelegramBotAPI/actions) [Small survey](https://forms.gle/2Hex2ynbHWHhi1KY7)|
| -------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Useful links | [![Chat in Telegram](https://img.shields.io/static/v1?label=Talk&message=Telegram&color=blue)](https://t.me/InMoTelegramBotAPI) [![Create bot](https://img.shields.io/static/v1?label=Github&message=Template&color=blue)](https://github.com/InsanusMokrassar/TelegramBotAPI-bot_template/generate) [![KDocs](https://img.shields.io/static/v1?label=Open&message=kdocs&color=blue)](https://tgbotapi.inmo.dev/index.html) [Examples](https://github.com/InsanusMokrassar/TelegramBotAPI-examples/), [Mini tutorial](https://bookstack.inmo.dev/books/telegrambotapi/chapter/introduction-tutorial) |
| TelegramBotAPI Core status                 | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.core) |
| TelegramBotAPI API Extensions status      | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.api) |
| TelegramBotAPI Util Extensions status | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.utils/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.utils) |
| TelegramBotAPI Behaviour Builder Extensions status | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.behaviour_builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.behaviour_builder) |
| TelegramBotAPI Behaviour Builder FSM Extensions status | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.behaviour_builder.fsm/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.behaviour_builder.fsm) |
| TelegramBotAPI All status                 | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi)                                                             |

## Examples

There are several things you need to do to launch examples below:

* Add `mavenCentral()` to your project repositories
    * [Maven variant](https://github.com/InsanusMokrassar/TelegramBotAPI/wiki/Including-in-your-project#pomxml)
* Add dependency `implementation "dev.inmo:tgbotapi:$tgbotapi_version"` 
    * Replace `tgbotapi_version` with exact version (see last one in the table above) or put variable with this name in project
    * Alternative variant for maven [here](https://github.com/InsanusMokrassar/TelegramBotAPI/wiki/Including-in-your-project#telegrambotapi)

More including instructions [available here](https://github.com/InsanusMokrassar/TelegramBotAPI/wiki/Including-in-your-project).
Other configuration examples:

* [For multiplatform](https://github.com/InsanusMokrassar/TelegramBotAPI-examples/tree/master/ResenderBot)
* [For JVM](https://github.com/InsanusMokrassar/TelegramBotAPI-examples/blob/master/GetMeBot/build.gradle)

### Most common example

```kotlin
suspend fun main() {
  val bot = telegramBot(TOKEN)

  bot.buildBehaviour {
    println(getMe())
  
    onCommand("start") {
      reply(it, "Hi:)")
    }
  }.join()
}
```

In this example you will see information about this bot at the moment of starting and answer with `Hi:)` every time it
gets message `/start`

### Handling only last messages

```kotlin
suspend fun main() {
  val bot = telegramBot(TOKEN)

  val flowsUpdatesFilter = FlowsUpdatesFilter()
  bot.buildBehaviour(flowUpdatesFilter = flowsUpdatesFilter) {
    println(getMe())
  
    onCommand("start") {
      reply(it, "Hi:)")
    }

    retrieveAccumulatedUpdates(this).join()
  }
}
```

The main difference with the previous example is that bot will get only last updates (accumulated before bot launch
and maybe some updates it got after launch)

### Build a little bit more complex behaviour

```kotlin
suspend fun main() {
  val bot = telegramBot(TOKEN)

  bot.buildBehaviour {
    println(getMe())

    val nameReplyMarkup = ReplyKeyboardMarkup(
      matrix {
        row {
          +SimpleKeyboardButton("nope")
        }
      }
    )
    onCommand("start") {
      val photo = waitPhoto(
        SendTextMessage(it.chat.id, "Send me your photo please")
      ).first()

      val name = waitText(
        SendTextMessage(
          it.chat.id,
          "Send me your name or choose \"nope\"",
          replyMarkup = nameReplyMarkup
        )
      ).first().text.takeIf { it != "nope" }

      sendPhoto(
        it.chat,
        photo.mediaCollection,
        entities = buildEntities {
          if (name != null) regular(name) // may be collapsed up to name ?.let(::regular)
        }
      )
    }
  }.join()
}
```

### More examples

You may find examples in [this project](https://github.com/InsanusMokrassar/TelegramBotAPI-examples). Besides, you are
always welcome in our [wiki](https://github.com/InsanusMokrassar/TelegramBotAPI/wiki/About-this-project) and
[chat](https://t.me/InMoTelegramBotAPIChat).
