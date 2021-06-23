# TelegramBotAPI Behaviour Builder Extensions

This extension was created to make it more simple to build bot steps handling. Usually, you must use something like:

```kotlin
val bot = telegramBot(TOKEN)
bot.startGettingFlowsUpdatesByLongPolling {
    messagesFlow.subscribeSafelyWithoutExceptions {
        // ...
    }
    // here I already tired to write this example 😫
}
```

This library offer other way to do a lot of routine in more simple way:

```kotlin
telegramBotWithBehaviour(token) {
    onCommand("start".regex) {
        execute(SendTextMessage(it.chat.id, "This bot can ...")) // replaceable with reply(it, "This bot can ...") when you are using `tgbotapi.extensions.api`
    }
}
```

## Triggers

In terminology of this project the `Triggers` are things which have no initial message, may have own filter for incoming
messages and filter messages for context which will be used in subcontext. Full syntax with `onText` as an example:

```kotlin
telegramBotWithBehaviour(TOKEN) {
    onText(
        includeFilterByChatInBehaviourSubContext = true, // if false - last lambda will receive all messages instead of filtered by chat messages
        additionalFilter = { message: CommonMessage<TextContent> ->
            // here you may check incoming message for any requirements before it will be passed to the main lambda
        }
    ) { message: CommonMessage<TextContent> -> // this here is `BehaviourContext`
        // here put your actions and additional waiters
    }
}
```

## Waiters

Waiters targeted to get some content "here and now", they must be used inside some trigger main lambda:

```kotlin
telegramBotWithBehaviour(TOKEN) {
    onCommand("start") { message: CommonMessage<TextContent> ->
        val userPhotos = waitPhoto(
            SendTextMessage(it.chat.id, "Ok, send me some photo, please"), // init request, can be any `Request` object
            { update: Update -> // That is update which is NOT passed requirements. In current context we expect some photo, but received something else
                SendTextMessage(it.chat.id, "Excuse me, but I can accept only photos") // it could be null
            },
            2, // some count of photos
            includeMediaGroups = true, // if false, messages related to some media group will be skipped and recognized as incorrect
        ) { message: CommonMessate<PhotoContent> -> // this method is optional and you can use it in case you want to add some additional requirements checks
            message.content // return null if message didn't passed requirements
        }
    }
}
```
