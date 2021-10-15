# TelegramBotAPI Behaviour Builder FSM Extensions

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.extensions.behaviour_builder.fsm/badge.svg)](https://maven-badges.herokuapp.com/maven-central/dev.inmo/tgbotapi.extensions.behaviour_builder.fsm)

This extension has been created to integrate finite state machine in [BehaviourBuilder](../tgbotapi.extensions.behaviour_builder/README.md).
In case you wish to use some custom store for steps (states), you may extend `StatesManager` or use `DefaultStatesManager`
with custom `DefaultStatesManagerRepo`. See [Examples repo](https://github.com/InsanusMokrassar/TelegramBotAPI-examples/tree/master/FSMBot)
to get more info and see how it works on base level
