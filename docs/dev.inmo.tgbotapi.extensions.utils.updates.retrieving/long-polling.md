//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.updates.retrieving](index.md)/[longPolling](long-polling.md)



# longPolling  
[common]  
Content  
fun [TelegramBot](../dev.inmo.tgbotapi.bot/index.md#%5Bdev.inmo.tgbotapi.bot%2FTelegramBot%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081).[longPolling](long-polling.md)(flowsUpdatesFilter: [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md), timeoutSeconds: [Seconds](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081) = 30, scope:  = CoroutineScope(Dispatchers.Default), exceptionsHandler: <[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>? = null):   
More info  


Will [startGettingOfUpdatesByLongPolling](start-getting-of-updates-by-long-polling.md) using incoming [flowsUpdatesFilter](long-polling.md). It is assumed that you ALREADY CONFIGURE all updates receivers, because this method will trigger getting of updates and.

  


[common]  
Content  
fun [TelegramBot](../dev.inmo.tgbotapi.bot/index.md#%5Bdev.inmo.tgbotapi.bot%2FTelegramBot%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081).[longPolling](long-polling.md)(timeoutSeconds: [Seconds](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081) = 30, scope:  = CoroutineScope(Dispatchers.Default), exceptionsHandler: <[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>? = null, flowsUpdatesFilterUpdatesKeeperCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 100, flowUpdatesPreset: [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)):   
More info  


Will enable [longPolling](long-polling.md) by creating [FlowsUpdatesFilter](../dev.inmo.tgbotapi.updateshandlers/-flows-updates-filter/index.md) with [flowsUpdatesFilterUpdatesKeeperCount](long-polling.md) as an argument and applied [flowUpdatesPreset](long-polling.md). It is assumed that you WILL CONFIGURE all updates receivers in [flowUpdatesPreset](long-polling.md), because of after [flowUpdatesPreset](long-polling.md) method calling will be triggered getting of updates.

  



