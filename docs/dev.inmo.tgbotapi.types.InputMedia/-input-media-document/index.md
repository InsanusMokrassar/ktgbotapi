//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.InputMedia](../index.md)/[InputMediaDocument](index.md)



# InputMediaDocument  
 [common] data class [InputMediaDocument](index.md) : [InputMedia](../-input-media/index.md), [DocumentMediaGroupMemberInputMedia](../-document-media-group-member-input-media/index.md), [ThumbedInputMedia](../-thumbed-input-media/index.md)

Represents a general file to be sent. See https://core.telegram.org/bots/api#inputmediadocument

   


## See also  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument///PointingToDeclaration/"></a>[dev.inmo.tgbotapi.requests.abstracts.InputFile](../../dev.inmo.tgbotapi.requests.abstracts/-input-file/index.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument///PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument///PointingToDeclaration/"></a>[dev.inmo.tgbotapi.requests.abstracts.MultipartFile](../../dev.inmo.tgbotapi.requests.abstracts/-multipart-file/index.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument///PointingToDeclaration/"></a>|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument///PointingToDeclaration/"></a>[dev.inmo.tgbotapi.requests.abstracts.FileId](../../dev.inmo.tgbotapi.requests.abstracts/-file-id/index.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument///PointingToDeclaration/"></a>|
  


## Parameters  
  
common  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument///PointingToDeclaration/"></a>disableContentTypeDetection| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument///PointingToDeclaration/"></a><br><br>Disables automatic server-side content type detection for files uploaded using multipart/form-data. Always used by Telegram system as true, if the document is sent as part of an album.<br><br>|
  


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/serialize/#kotlinx.serialization.StringFormat/PointingToDeclaration/"></a>[serialize](serialize.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/serialize/#kotlinx.serialization.StringFormat/PointingToDeclaration/"></a>[common]  <br>Content  <br>open override fun [serialize](serialize.md)(format: ): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/disableContentTypeDetection/#/PointingToDeclaration/"></a>[disableContentTypeDetection](disable-content-type-detection.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/disableContentTypeDetection/#/PointingToDeclaration/"></a> [common] val [disableContentTypeDetection](disable-content-type-detection.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? = nullDisables automatic server-side content type detection for files uploaded using multipart/form-data.   <br>|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/entities/#/PointingToDeclaration/"></a>[entities](entities.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/entities/#/PointingToDeclaration/"></a> [common] open override val [entities](entities.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[TextSource](../../dev.inmo.tgbotapi.CommonAbstracts/-text-source/index.md)>?   <br>|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/file/#/PointingToDeclaration/"></a>[file](file.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/file/#/PointingToDeclaration/"></a> [common] open override val [file](file.md): [InputFile](../../dev.inmo.tgbotapi.requests.abstracts/-input-file/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/media/#/PointingToDeclaration/"></a>[media](media.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/media/#/PointingToDeclaration/"></a> [common] open override val [media](media.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/parseMode/#/PointingToDeclaration/"></a>[parseMode](parse-mode.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/parseMode/#/PointingToDeclaration/"></a> [common] open override val [parseMode](parse-mode.md): [ParseMode](../../dev.inmo.tgbotapi.types.ParseMode/-parse-mode/index.md)? = null   <br>|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/text/#/PointingToDeclaration/"></a>[text](text.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/text/#/PointingToDeclaration/"></a> [common] open override val [text](text.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/thumb/#/PointingToDeclaration/"></a>[thumb](thumb.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/thumb/#/PointingToDeclaration/"></a> [common] open override val [thumb](thumb.md): [InputFile](../../dev.inmo.tgbotapi.requests.abstracts/-input-file/index.md)? = null   <br>|
| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/type/#/PointingToDeclaration/"></a>[type](type.md)| <a name="dev.inmo.tgbotapi.types.InputMedia/InputMediaDocument/type/#/PointingToDeclaration/"></a> [common] open override val [type](type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|

