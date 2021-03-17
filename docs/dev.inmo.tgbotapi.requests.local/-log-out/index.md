//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.local](../index.md)/[LogOut](index.md)



# LogOut  
 [common] object [LogOut](index.md) : [SimpleRequest](../../dev.inmo.tgbotapi.requests.abstracts/-simple-request/index.md)<[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)> 

Use this method to log out from the cloud Bot API server before launching the bot locally. You **must** log out the bot before running it locally, otherwise there is no guarantee that the bot will receive updates. After a successful call, you will not be able to log in again using the same token for 10 minutes

   


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.local/LogOut/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests.local/LogOut/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.local/LogOut/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](request-serializer.md)| <a name="dev.inmo.tgbotapi.requests.local/LogOut/requestSerializer/#/PointingToDeclaration/"></a> [common] open override val [requestSerializer](request-serializer.md): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.local/LogOut/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests.local/LogOut/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)>   <br>|

