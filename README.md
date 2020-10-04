# TelegramBotAPI

| Common info                           | [![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin) [![Build Status](https://travis-ci.com/InsanusMokrassar/TelegramBotAPI.svg?branch=master)](https://travis-ci.com/InsanusMokrassar/TelegramBotAPI) [Small survey](https://forms.gle/tnjuExdSKEr32ygKA)|
| -------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Useful links | [![Chat in Telegram](badges/chat.svg)](https://t.me/InMoTelegramBotAPI) [![KDocs](badges/kdocs.svg)](https://tgbotapi.inmo.dev/docs/index.html) [Examples](https://github.com/InsanusMokrassar/TelegramBotAPI-examples/), [Mini tutorial](https://bookstack.inmo.dev/books/telegrambotapi/chapter/introduction-tutorial) |
| TelegramBotAPI Core status                 | [![Download](https://api.bintray.com/packages/insanusmokrassar/TelegramBotAPI/TelegramBotAPI-core/images/download.svg)](https://bintray.com/insanusmokrassar/TelegramBotAPI/TelegramBotAPI-core/_latestVersion) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo.tgbotapi/TelegramBotAPI-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo.tgbotapi/TelegramBotAPI) |
| TelegramBotAPI Extensions status      | [![Download](https://api.bintray.com/packages/insanusmokrassar/TelegramBotAPI/TelegramBotAPI-extensions-api/images/download.svg)](https://bintray.com/insanusmokrassar/TelegramBotAPI/TelegramBotAPI-extensions-api/_latestVersion) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo.tgbotapi/TelegramBotAPI-extensions-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo.tgbotapi/TelegramBotAPI-extensions-api) |
| TelegramBotAPI Util Extensions status | [![Download](https://api.bintray.com/packages/insanusmokrassar/TelegramBotAPI/TelegramBotAPI-extensions-utils/images/download.svg)](https://bintray.com/insanusmokrassar/TelegramBotAPI/TelegramBotAPI-extensions-utils/_latestVersion) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo.tgbotapi/TelegramBotAPI-extensions-utils/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo.tgbotapi/TelegramBotAPI-extensions-utils) |
| TelegramBotAPI All status                 | [![Download](https://api.bintray.com/packages/insanusmokrassar/TelegramBotAPI/TelegramBotAPI/images/download.svg)](https://bintray.com/insanusmokrassar/TelegramBotAPI/TelegramBotAPI/_latestVersion) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo.tgbotapi/TelegramBotAPI/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo.tgbotapi/TelegramBotAPI-all)                                                             |

**At the time of publication of version `0.28.0` there are errors in serialization plugins like
[kotlinx.serialization#1004](https://github.com/Kotlin/kotlinx.serialization/issues/1004). It is possible, that both JVM
and JS version may work improperly in some cases with `kotlinx.serialization` version `1.0.0-RC`**


It is a complex of libraries for working with `TelegramBotAPI` in type-safe and strict way as much as it possible. In
the list of this complex currently next projects:

* [TelegramBotAPI-core](TelegramBotAPI-core/README.md) - core of library. In fact it is independent library and can be used alone
  without any additional library
* [TelegramBotAPI Extensions](TelegramBotAPI-extensions-api/README.md) - contains extensions (mostly for
  `RequestsExecutor`), which allows to use the core library in more pleasant way
* [TelegramBotAPI Util Extensions](TelegramBotAPI-extensions-utils/README.md) - contains extensions for more comfortable
work with commands, updates and other different things
* [TelegramBotAPI](TelegramBotAPI/README.md) - concentration of all previously mentioned libraries

Most part of some specific solves or unuseful
moments are describing by official [Telegram Bot API](https://core.telegram.org/bots/api).

## JavaScript notes

### Versions before `0.28.0`

In case if you are want to use this library inside of browser, you will need additional settings (thanks for help to [Alexander Nozik](https://research.jetbrains.org/researchers/altavir)):

<details>
<summary>Gradle build script help (for versions before 0.28.0)</summary>

```groovy
dependencies {
    /* ... */

    implementation "dev.inmo.tgbotapi:TelegramBotAPI:$tgbot_api_version"
    implementation "dev.inmo.tgbotapi:TelegramBotAPI-extensions-api:$tgbot_api_version" // optional
    implementation "dev.inmo.tgbotapi:TelegramBotAPI-extensions-utils:$tgbot_api_version" // optional

    /* Block of dependencies for correct building in browser */
    implementation(npm("fs"))
    implementation(npm("bufferutil"))
    implementation(npm("utf-8-validate"))
    implementation(npm("abort-controller"))
    implementation(npm("text-encoding"))
}

/* ... */

kotlin {
    target {
        browser {
            /* Block for fix of exception in absence of some functionality, https://github.com/ktorio/ktor/issues/1339 */
            dceTask {
                dceOptions {
                    keep("ktor-ktor-io.\$\$importsForInline\$\$.ktor-ktor-io.io.ktor.utils.io")
                }
            }
        }
    }
}
```

</details>

## Ok, where should I start?

![Libraries hierarchy](resources/TelegramBotAPI-libraries-hierarchy.svg)

In most cases, the most simple way will be to implement [TelegramBotAPI](TelegramBotAPI/README.md) - it contains
all necessary tools for comfort usage of this library. If you want to exclude some libraries, you can implement just
[TelegramBotAPI API Extensions](TelegramBotAPI-extensions-api/README.md),
[TelegramBotAPI Util Extensions](TelegramBotAPI-extensions-utils/README.md) or even
[TelegramBotAPI-core](TelegramBotAPI-core/README.md).

If you want to dive deeper in the core of library or develop something for it - welcome to learn more from
[TelegramBotAPI-core](TelegramBotAPI-core/README.md) and our [Telegram Chat](https://teleg.one/InMoTelegramBotAPIChat).

Anyway, all libraries are very typical inside of them. Examples:

* In `TelegramBotAPI` common request look like `requestsExecutor.execute(SomeRequest())`
* `TelegramBotAPI-extensions-api` typical syntax look like `requestsExecutor.someRequest()` (in most cases it would be
better to use `bot` name instead of `requestsExecutor`)
* `TelegramBotAPI-extensions-utils` will look like `filter.filterBaseMessageUpdates(chatId).filterExactCommands(Regex("^.*$"))...`

## Build instruction

If you want to build this project or to contribute, there are several recommendations:

### Build

In case if you want to just build project, run next command:

```bash
./gradlew clean build
```

On windows:

```
gradlew.bat clean build
```

### Publishing for work with your version locally

In case, if you want to work in your other projects using your modification (or some state) of this library,
you can use next code:

```bash
./gradlew clean build publishToMavenLocal
```

On windows:

```
gradlew.bat clean build publishToMavenLocal
```

But you must remember, that in this case your local maven repo must be the first one from
your project retrieving libraries:

```groovy
repositories {
    mavenLocal() // that must be the first one
    jcenter()
    mavenCentral()
}
```

Besides, for your own version you can change variable `library_version` in the file [gradle.properties](./gradle.properties).
