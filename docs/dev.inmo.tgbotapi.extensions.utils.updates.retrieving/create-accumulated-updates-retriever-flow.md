//[docs](../../index.md)/[dev.inmo.tgbotapi.extensions.utils.updates.retrieving](index.md)/[createAccumulatedUpdatesRetrieverFlow](create-accumulated-updates-retriever-flow.md)



# createAccumulatedUpdatesRetrieverFlow  
[common]  
Content  
fun [TelegramBot](../dev.inmo.tgbotapi.bot/index.md#%5Bdev.inmo.tgbotapi.bot%2FTelegramBot%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081).[createAccumulatedUpdatesRetrieverFlow](create-accumulated-updates-retriever-flow.md)(avoidInlineQueries: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, avoidCallbackQueries: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, exceptionsHandler: <[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>? = null, allowedUpdates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = null): <[Update](../dev.inmo.tgbotapi.types.update.abstracts/-update/index.md)>  
More info  


#### Return  


 which will emit updates to the collector while they will be accumulated. Works the same as [retrieveAccumulatedUpdates](retrieve-accumulated-updates.md), but pass  as a callback

  



