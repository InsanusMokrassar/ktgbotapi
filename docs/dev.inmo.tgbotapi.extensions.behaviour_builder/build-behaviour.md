//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.behaviour_builder](index.md)/[buildBehaviour](build-behaviour.md)



# buildBehaviour  
[common]  
Content  
suspend fun [TelegramBot](../dev.inmo.tgbotapi.bot/index.md#%5Bdev.inmo.tgbotapi.bot%2FTelegramBot%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081).[buildBehaviour](build-behaviour.md)(scope: , flowUpdatesFilter: [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md), block: [BehaviourContextReceiver](index.md#%5Bdev.inmo.tgbotapi.extensions.behaviour_builder%2FBehaviourContextReceiver%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>)  
More info  


Use this method in case you wish to make some additional actions with [flowUpdatesFilter](build-behaviour.md).



**WARNING** This method WILL NOT launch any listening of updates. Use something like [startGettingOfUpdatesByLongPolling](../dev.inmo.tgbotapi.extensions.utils.updates.retrieving/start-getting-of-updates-by-long-polling.md) or tools for work with webhooks



## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext](-behaviour-context/index.md)| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>[startGettingOfUpdatesByLongPolling](../dev.inmo.tgbotapi.extensions.utils.updates.retrieving/start-getting-of-updates-by-long-polling.md)| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>|
  
  


[common]  
Content  
suspend fun [TelegramBot](../dev.inmo.tgbotapi.bot/index.md#%5Bdev.inmo.tgbotapi.bot%2FTelegramBot%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081).[buildBehaviour](build-behaviour.md)(scope: , block: [BehaviourContextReceiver](index.md#%5Bdev.inmo.tgbotapi.extensions.behaviour_builder%2FBehaviourContextReceiver%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)<[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>):   
More info  


Use this method to build bot behaviour and run it via long polling. In case you wish to get [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md) for additional manipulations, you must provide external [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md) in other [buildBehaviour](build-behaviour.md) function.



## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>[buildBehaviour](build-behaviour.md)| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext](-behaviour-context/index.md)| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>[startGettingOfUpdatesByLongPolling](../dev.inmo.tgbotapi.extensions.utils.updates.retrieving/start-getting-of-updates-by-long-polling.md)| <a name="dev.inmo.tgbotapi.extensions.behaviour_builder//buildBehaviour/dev.inmo.tgbotapi.bot.RequestsExecutor#kotlinx.coroutines.CoroutineScope#kotlin.coroutines.SuspendFunction1[dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext,kotlin.Unit]/PointingToDeclaration/"></a>|
  
  



