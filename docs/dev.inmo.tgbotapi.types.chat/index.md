//[docs](../../index.md)/[dev.inmo.tgbotapi.types.chat](index.md)



# Package dev.inmo.tgbotapi.types.chat  


## Types  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.chat/ChannelChatImpl///PointingToDeclaration/"></a>[ChannelChatImpl](-channel-chat-impl/index.md)| <a name="dev.inmo.tgbotapi.types.chat/ChannelChatImpl///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [ChannelChatImpl](-channel-chat-impl/index.md)(**id**: [ChatId](../dev.inmo.tgbotapi.types/-chat-id/index.md), **title**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **username**: [Username](../dev.inmo.tgbotapi.types/-username/index.md)?) : [ChannelChat](../dev.inmo.tgbotapi.types.chat.abstracts/-channel-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat/ChatPermissions///PointingToDeclaration/"></a>[ChatPermissions](-chat-permissions/index.md)| <a name="dev.inmo.tgbotapi.types.chat/ChatPermissions///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [ChatPermissions](-chat-permissions/index.md)(**canSendMessages**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **canSendMediaMessages**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **canSendPolls**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **canSendOtherMessages**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **canAddWebPagePreviews**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **canChangeInfo**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **canInviteUsers**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), **canPinMessages**: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat/GroupChatImpl///PointingToDeclaration/"></a>[GroupChatImpl](-group-chat-impl/index.md)| <a name="dev.inmo.tgbotapi.types.chat/GroupChatImpl///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [GroupChatImpl](-group-chat-impl/index.md)(**id**: [ChatId](../dev.inmo.tgbotapi.types/-chat-id/index.md), **title**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [GroupChat](../dev.inmo.tgbotapi.types.chat.abstracts/-group-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat/PrivateChatImpl///PointingToDeclaration/"></a>[PrivateChatImpl](-private-chat-impl/index.md)| <a name="dev.inmo.tgbotapi.types.chat/PrivateChatImpl///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [PrivateChatImpl](-private-chat-impl/index.md)(**id**: [ChatId](../dev.inmo.tgbotapi.types/-chat-id/index.md), **username**: [Username](../dev.inmo.tgbotapi.types/-username/index.md)?, **firstName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **lastName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [PrivateChat](../dev.inmo.tgbotapi.types.chat.abstracts/-private-chat/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.chat/SupergroupChatImpl///PointingToDeclaration/"></a>[SupergroupChatImpl](-supergroup-chat-impl/index.md)| <a name="dev.inmo.tgbotapi.types.chat/SupergroupChatImpl///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [SupergroupChatImpl](-supergroup-chat-impl/index.md)(**id**: [ChatId](../dev.inmo.tgbotapi.types/-chat-id/index.md), **title**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **username**: [Username](../dev.inmo.tgbotapi.types/-username/index.md)?) : [SupergroupChat](../dev.inmo.tgbotapi.types.chat.abstracts/-supergroup-chat/index.md)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.chat//LeftRestrictionsChatPermissions/#/PointingToDeclaration/"></a>[LeftRestrictionsChatPermissions](-left-restrictions-chat-permissions.md)| <a name="dev.inmo.tgbotapi.types.chat//LeftRestrictionsChatPermissions/#/PointingToDeclaration/"></a> [common] val [LeftRestrictionsChatPermissions](-left-restrictions-chat-permissions.md): [ChatPermissions](-chat-permissions/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.types.chat//RestrictionsChatPermissions/#/PointingToDeclaration/"></a>[RestrictionsChatPermissions](-restrictions-chat-permissions.md)| <a name="dev.inmo.tgbotapi.types.chat//RestrictionsChatPermissions/#/PointingToDeclaration/"></a> [common] val [RestrictionsChatPermissions](-restrictions-chat-permissions.md): [ChatPermissions](-chat-permissions/index.md)   <br>|

