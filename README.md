# TelegramBotAPI [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi) [![Supported version](https://img.shields.io/badge/Telegram%20Bot%20API-8.3-blue)](https://core.telegram.org/bots/api-changelog#february-12-2025)

|          Docs          |                                   [![KDocs](https://img.shields.io/static/v1?label=Dokka&message=KDocs&color=blue&logo=kotlin)](https://tgbotapi.inmo.dev/index.html) [![Mini tutorial](https://img.shields.io/static/v1?label=Mk&message=Docs&color=blue&logo=mkdocs)](https://docs.inmo.dev/tgbotapi/index.html)                                    |
|:----------------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|      Useful repos      |                           [![Create bot](https://img.shields.io/static/v1?label=Github&message=Template&color=blue&logo=github)](https://github.com/InsanusMokrassar/TelegramBotAPI-bot_template/generate) [![Examples](https://img.shields.io/static/v1?label=Github&message=Examples&color=blue&logo=github)](https://github.com/InsanusMokrassar/TelegramBotAPI-examples/)                           |
|          Misc          |                              [![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin) [![Small survey](https://img.shields.io/static/v1?label=Google&message=Survey&color=blue&logo=google-sheets)](https://docs.google.com/forms/d/e/1FAIpQLSctdJHT_aEniyYT0-IUAEfo1hsIlezX2owlkEAYX4KPl2V2_A/viewform?usp=sf_link)                               |
|       Platforms        |                                                                                                      ![JVM](https://img.shields.io/badge/JVM-red?style=plastic&logo=openjdk&logoColor=white) ![Js](https://img.shields.io/badge/JavaScript-323330?style=plastic&logo=javascript&logoColor=F7DF1E)                                                                                                       |
| Experimental Platforms |                                                                                                [![Linux x64](https://img.shields.io/badge/LinuxX64-FCC624?style=plastic&logo=linux&logoColor=black)](https://kotlinlang.org/docs/native-target-support.html#tier-1) [![MinGW x64](https://img.shields.io/badge/MinGWX64-black?style=plastic&logo=windows&logoColor=green)](https://kotlinlang.org/docs/native-target-support.html#tier-1)                                                                                                 |

<!--- [![Telegram Channel](./resources/tg_channel_qr.jpg)](https://t.me/ktgbotapi) --->

<p align="center">
  <a href="https://t.me/ktgbotapi">
    <img src="./resources/tg_channel_qr.jpg">
  </a>
</p>

Hello! This is a set of libraries for working with Telegram Bot API.

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

  bot.buildBehaviourWithLongPolling {
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

  bot.buildBehaviourWithLongPolling {
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
always welcome in our [docs](https://docs.inmo.dev/tgbotapi/index.html) and
[chat](https://t.me/InMoTelegramBotAPIChat).
