//[docs](../../index.md)/[dev.inmo.tgbotapi.types.chat.abstracts](index.md)



# Package dev.inmo.tgbotapi.types.chat.abstracts  


## Types  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.chat.abstracts/ChannelChat///PointingToDeclaration/"></a>[ChannelChat](-channel-chat/index.md)| <a name="dev.inmo.tgbotapi.types.chat.abstracts/ChannelChat///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [ChannelChat](-channel-chat/index.md) : [SuperPublicChat](-super-public-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat.abstracts/Chat///PointingToDeclaration/"></a>[Chat](-chat/index.md)| <a name="dev.inmo.tgbotapi.types.chat.abstracts/Chat///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [Chat](-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat.abstracts/GroupChat///PointingToDeclaration/"></a>[GroupChat](-group-chat/index.md)| <a name="dev.inmo.tgbotapi.types.chat.abstracts/GroupChat///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [GroupChat](-group-chat/index.md) : [PublicChat](-public-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat.abstracts/PrivateChat///PointingToDeclaration/"></a>[PrivateChat](-private-chat/index.md)| <a name="dev.inmo.tgbotapi.types.chat.abstracts/PrivateChat///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [PrivateChat](-private-chat/index.md) : [Chat](-chat/index.md), [UsernameChat](-username-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat.abstracts/PublicChat///PointingToDeclaration/"></a>[PublicChat](-public-chat/index.md)| <a name="dev.inmo.tgbotapi.types.chat.abstracts/PublicChat///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [PublicChat](-public-chat/index.md) : [Chat](-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat.abstracts/SupergroupChat///PointingToDeclaration/"></a>[SupergroupChat](-supergroup-chat/index.md)| <a name="dev.inmo.tgbotapi.types.chat.abstracts/SupergroupChat///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [SupergroupChat](-supergroup-chat/index.md) : [GroupChat](-group-chat/index.md), [SuperPublicChat](-super-public-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat.abstracts/SuperPublicChat///PointingToDeclaration/"></a>[SuperPublicChat](-super-public-chat/index.md)| <a name="dev.inmo.tgbotapi.types.chat.abstracts/SuperPublicChat///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [SuperPublicChat](-super-public-chat/index.md) : [PublicChat](-public-chat/index.md), [UsernameChat](-username-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat.abstracts/UnknownChatType///PointingToDeclaration/"></a>[UnknownChatType](-unknown-chat-type/index.md)| <a name="dev.inmo.tgbotapi.types.chat.abstracts/UnknownChatType///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [UnknownChatType](-unknown-chat-type/index.md)(**id**: [ChatId](../dev.inmo.tgbotapi.types/-chat-id/index.md), **raw**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Chat](-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat.abstracts/UsernameChat///PointingToDeclaration/"></a>[UsernameChat](-username-chat/index.md)| <a name="dev.inmo.tgbotapi.types.chat.abstracts/UsernameChat///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [UsernameChat](-username-chat/index.md) : [Chat](-chat/index.md)  <br><br><br>|

