//[docs](../../index.md)/[dev.inmo.tgbotapi.requests.send.polls](index.md)/[createRequest](create-request.md)



# createRequest  
[common]  
Content  
fun [Poll](../dev.inmo.tgbotapi.types.polls/-poll/index.md).[createRequest](create-request.md)(chatId: [ChatIdentifier](../dev.inmo.tgbotapi.types/-chat-identifier/index.md), disableNotification: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, replyToMessageId: [MessageIdentifier](../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FMessageIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)? = null, allowSendingWithoutReply: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = null, replyMarkup: [KeyboardMarkup](../dev.inmo.tgbotapi.types.buttons/-keyboard-markup/index.md)? = null): [SendPoll](-send-poll/index.md)  
More info  


#### Return  


[SendPoll](-send-poll/index.md) in case when all is right. It can return [SendRegularPoll](-send-regular-poll/index.md) for [QuizPoll](../dev.inmo.tgbotapi.types.polls/-quiz-poll/index.md) in case if [QuizPoll.correctOptionId](../dev.inmo.tgbotapi.types.polls/-quiz-poll/correct-option-id.md) equal to null

  



