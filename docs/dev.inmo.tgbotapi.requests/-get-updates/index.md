//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests](../index.md)/[GetUpdates](index.md)



# GetUpdates  
 [common] data class [GetUpdates](index.md)(**offset**: [UpdateIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUpdateIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?, **limit**: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), **timeout**: [Seconds](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?, **allowed_updates**: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>?) : [SimpleRequest](../../dev.inmo.tgbotapi.requests.abstracts/-simple-request/index.md)<[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Update](../../dev.inmo.tgbotapi.types.update.abstracts/-update/index.md)>> 

Request updates from Telegram Bot API system. It is important, that the result updates WILL NOT include [dev.inmo.tgbotapi.types.update.MediaGroupUpdates.MediaGroupUpdate](../../dev.inmo.tgbotapi.types.update.MediaGroupUpdates/-media-group-update/index.md) objects due to the fact, that it is internal abstraction and in fact any [dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-media-group-message/index.md) is just a common [dev.inmo.tgbotapi.types.message.abstracts.Message](../../dev.inmo.tgbotapi.types.message.abstracts/-message/index.md)

   


## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates///PointingToDeclaration/"></a>[dev.inmo.tgbotapi.extensions.utils.updates.retrieving.updateHandlerWithMediaGroupsAdaptation](../../dev.inmo.tgbotapi.extensions.utils.updates.retrieving/update-handler-with-media-groups-adaptation.md)| <a name="dev.inmo.tgbotapi.requests/GetUpdates///PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates///PointingToDeclaration/"></a>dev.inmo.tgbotapi.utils.convertWithMediaGroupUpdates| <a name="dev.inmo.tgbotapi.requests/GetUpdates///PointingToDeclaration/"></a>|
  


## Constructors  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates/GetUpdates/#kotlin.Long?#kotlin.Int#kotlin.Int?#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a>[GetUpdates](-get-updates.md)| <a name="dev.inmo.tgbotapi.requests/GetUpdates/GetUpdates/#kotlin.Long?#kotlin.Int#kotlin.Int?#kotlin.collections.List[kotlin.String]?/PointingToDeclaration/"></a> [common] fun [GetUpdates](-get-updates.md)(offset: [UpdateIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUpdateIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, limit: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = getUpdatesLimit.last, timeout: [Seconds](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, allowed_updates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>? = ALL_UPDATES_LIST)   <br>|


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests/GetUpdates/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates/allowed_updates/#/PointingToDeclaration/"></a>[allowed_updates](allowed_updates.md)| <a name="dev.inmo.tgbotapi.requests/GetUpdates/allowed_updates/#/PointingToDeclaration/"></a> [common] val [allowed_updates](allowed_updates.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>?   <br>|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates/limit/#/PointingToDeclaration/"></a>[limit](limit.md)| <a name="dev.inmo.tgbotapi.requests/GetUpdates/limit/#/PointingToDeclaration/"></a> [common] val [limit](limit.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates/offset/#/PointingToDeclaration/"></a>[offset](offset.md)| <a name="dev.inmo.tgbotapi.requests/GetUpdates/offset/#/PointingToDeclaration/"></a> [common] val [offset](offset.md): [UpdateIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FUpdateIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null   <br>|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](request-serializer.md)| <a name="dev.inmo.tgbotapi.requests/GetUpdates/requestSerializer/#/PointingToDeclaration/"></a> [common] open override val [requestSerializer](request-serializer.md): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests/GetUpdates/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[Update](../../dev.inmo.tgbotapi.types.update.abstracts/-update/index.md)>>   <br>|
| <a name="dev.inmo.tgbotapi.requests/GetUpdates/timeout/#/PointingToDeclaration/"></a>[timeout](timeout.md)| <a name="dev.inmo.tgbotapi.requests/GetUpdates/timeout/#/PointingToDeclaration/"></a> [common] val [timeout](timeout.md): [Seconds](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FSeconds%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null   <br>|

