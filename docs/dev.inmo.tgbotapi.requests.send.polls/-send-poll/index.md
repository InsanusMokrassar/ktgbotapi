//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.send.polls](../index.md)/[SendPoll](index.md)



# SendPoll  
 [common] sealed class [SendPoll](index.md) : [SendMessageRequest](../../dev.inmo.tgbotapi.requests.send.abstracts/-send-message-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[PollContent](../../dev.inmo.tgbotapi.types.message.content/-poll-content/index.md)>> , [ReplyingMarkupSendMessageRequest](../../dev.inmo.tgbotapi.requests.send.abstracts/-replying-markup-send-message-request/index.md)<[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[PollContent](../../dev.inmo.tgbotapi.types.message.content/-poll-content/index.md)>>    


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/method/#/PointingToDeclaration/"></a>[method](method.md)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/method/#/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [method](method.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/allowSendingWithoutReply/#/PointingToDeclaration/"></a>[allowSendingWithoutReply](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FallowSendingWithoutReply%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/allowSendingWithoutReply/#/PointingToDeclaration/"></a> [common] abstract val [allowSendingWithoutReply](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FallowSendingWithoutReply%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)?   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/chatId/#/PointingToDeclaration/"></a>[chatId](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FchatId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/chatId/#/PointingToDeclaration/"></a> [common] abstract val [chatId](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FchatId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [ChatIdentifier](../../dev.inmo.tgbotapi.types/-chat-identifier/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/closeInfo/#/PointingToDeclaration/"></a>[closeInfo](close-info.md)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/closeInfo/#/PointingToDeclaration/"></a> [common] open val [closeInfo](close-info.md): [ScheduledCloseInfo](../../dev.inmo.tgbotapi.types.polls/-scheduled-close-info/index.md)?   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/disableNotification/#/PointingToDeclaration/"></a>[disableNotification](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FdisableNotification%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/disableNotification/#/PointingToDeclaration/"></a> [common] abstract val [disableNotification](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FdisableNotification%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/isAnonymous/#/PointingToDeclaration/"></a>[isAnonymous](is-anonymous.md)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/isAnonymous/#/PointingToDeclaration/"></a> [common] abstract val [isAnonymous](is-anonymous.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/isClosed/#/PointingToDeclaration/"></a>[isClosed](is-closed.md)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/isClosed/#/PointingToDeclaration/"></a> [common] abstract val [isClosed](is-closed.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/options/#/PointingToDeclaration/"></a>[options](options.md)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/options/#/PointingToDeclaration/"></a> [common] abstract val [options](options.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)>   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/question/#/PointingToDeclaration/"></a>[question](question.md)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/question/#/PointingToDeclaration/"></a> [common] abstract val [question](question.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/replyMarkup/#/PointingToDeclaration/"></a>[replyMarkup](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FreplyMarkup%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/replyMarkup/#/PointingToDeclaration/"></a> [common] abstract val [replyMarkup](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FreplyMarkup%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [KeyboardMarkup](../../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)?   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/replyToMessageId/#/PointingToDeclaration/"></a>[replyToMessageId](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FreplyToMessageId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/replyToMessageId/#/PointingToDeclaration/"></a> [common] abstract val [replyToMessageId](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FreplyToMessageId%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [MessageIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)?   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/requestSerializer/#/PointingToDeclaration/"></a>[requestSerializer](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FrequestSerializer%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/requestSerializer/#/PointingToDeclaration/"></a> [common] abstract val [requestSerializer](index.md#%5Bdev.inmo.tgbotapi.requests.send.polls%2FSendPoll%2FrequestSerializer%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): <*>   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/resultDeserializer/#/PointingToDeclaration/"></a>[resultDeserializer](result-deserializer.md)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/resultDeserializer/#/PointingToDeclaration/"></a> [common] open override val [resultDeserializer](result-deserializer.md): <[ContentMessage](../../dev.inmo.tgbotapi.types.message.abstracts/-content-message/index.md)<[PollContent](../../dev.inmo.tgbotapi.types.message.content/-poll-content/index.md)>>   <br>|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/type/#/PointingToDeclaration/"></a>[type](type.md)| <a name="dev.inmo.tgbotapi.requests.send.polls/SendPoll/type/#/PointingToDeclaration/"></a> [common] abstract val [type](type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendRegularPoll///PointingToDeclaration/"></a>[SendRegularPoll](../-send-regular-poll/index.md)|
| <a name="dev.inmo.tgbotapi.requests.send.polls/SendQuizPoll///PointingToDeclaration/"></a>[SendQuizPoll](../-send-quiz-poll/index.md)|

