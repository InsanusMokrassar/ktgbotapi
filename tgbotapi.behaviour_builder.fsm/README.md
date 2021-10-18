# TelegramBotAPI Behaviour Builder FSM Extensions

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.behaviour_builder.fsm/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.behaviour_builder.fsm)

## What is it?


This extension has been created to integrate finite state machine in [BehaviourBuilder](../tgbotapi.behaviour_builder/README.md).
In case you wish to use some custom store for steps (states), you may extend `StatesManager` or use `DefaultStatesManager`
with custom `DefaultStatesManagerRepo`. See [Examples repo](https://github.com/InsanusMokrassar/TelegramBotAPI-examples/tree/master/FSMBot)
to get more info and see how it works on base level.

Lets see several examples:

```kotlin
data class StateRealization(override val context: ChatId) : State

bot.telegramBotWithBehaviourAndFSMAndStartLongPolling(TOKEN) {
    strictlyOn<StateRealization> {
        // here your logic of state
        it // you must return from state handler some other state as a result of this one or null if you want to complete the chain
    }

    onCommand("start") {
        startChain(StateRealization(it.chat.id)) // starting of chain with StateRealization state
    }
}
```

Currently, for the states there is only one restriction - your state must implements `State` interface and override `context` to define it. It may be important that `context` will be used under the hood for comparison with contexts of other states in some situations in it may be important to correctly realize `equals` method.

It is important, that all other methods are the same as for [BehaviourBuilder](../tgbotapi.behaviour_builder/README.md).
