//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.local](../index.md)/[Close](index.md)



# Close  
 [common] object [Close](index.md) : [SimpleRequest](../../dev.inmo.tgbotapi.requests.abstracts/-simple-request/index.md)<[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)> 

Use this method to close the bot instance before moving it from one local server to another. You need to delete the webhook before calling this method to ensure that the bot isn't launched again after server restart. The method will return error 429 in the first 10 minutes after the bot is launched.

   


## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.local/Close///PointingToDeclaration/"></a>| <a name="dev.inmo.tgbotapi.requests.local/Close///PointingToDeclaration/"></a>|
  


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.local/Close/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests.local/Close/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.local/Close/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](request-serializer.md)| <a name="dev.inmo.tgbotapi.requests.local/Close/requestSerializer/#/PointingToDeclaration/"></a> [common] open override val [requestSerializer](request-serializer.md): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.local/Close/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests.local/Close/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)>   <br>|

