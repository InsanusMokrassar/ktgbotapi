//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.files](../index.md)/[DocumentFile](index.md)



# DocumentFile  
 [common] data class [DocumentFile](index.md)(**fileId**: [FileId](../../dev.inmo.tgbotapi.requests.abstracts/-file-id/index.md), **fileUniqueId**: [FileUniqueId](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FFileUniqueId%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **fileSize**: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?, **thumb**: [PhotoSize](../-photo-size/index.md)?, **mimeType**: [MimeType](../../dev.inmo.tgbotapi.utils/-mime-type/index.md)?, **fileName**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : [TelegramMediaFile](../../dev.inmo.tgbotapi.types.files.abstracts/-telegram-media-file/index.md), [MimedMediaFile](../../dev.inmo.tgbotapi.types.files.abstracts/-mimed-media-file/index.md), [ThumbedMediaFile](../../dev.inmo.tgbotapi.types.files.abstracts/-thumbed-media-file/index.md), [CustomNamedMediaFile](../../dev.inmo.tgbotapi.types.files.abstracts/-custom-named-media-file/index.md)   


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/fileId/#/PointingToDeclaration/"></a>[fileId](file-id.md)| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/fileId/#/PointingToDeclaration/"></a> [common] open override val [fileId](file-id.md): [FileId](../../dev.inmo.tgbotapi.requests.abstracts/-file-id/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/fileName/#/PointingToDeclaration/"></a>[fileName](file-name.md)| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/fileName/#/PointingToDeclaration/"></a> [common] open override val [fileName](file-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/fileSize/#/PointingToDeclaration/"></a>[fileSize](file-size.md)| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/fileSize/#/PointingToDeclaration/"></a> [common] open override val [fileSize](file-size.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null   <br>|
| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/fileUniqueId/#/PointingToDeclaration/"></a>[fileUniqueId](file-unique-id.md)| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/fileUniqueId/#/PointingToDeclaration/"></a> [common] open override val [fileUniqueId](file-unique-id.md): [FileUniqueId](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FFileUniqueId%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|
| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/mimeType/#/PointingToDeclaration/"></a>[mimeType](mime-type.md)| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/mimeType/#/PointingToDeclaration/"></a> [common] open override val [mimeType](mime-type.md): [MimeType](../../dev.inmo.tgbotapi.utils/-mime-type/index.md)? = null   <br>|
| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/thumb/#/PointingToDeclaration/"></a>[thumb](thumb.md)| <a name="dev.inmo.tgbotapi.types.files/DocumentFile/thumb/#/PointingToDeclaration/"></a> [common] open override val [thumb](thumb.md): [PhotoSize](../-photo-size/index.md)? = null   <br>|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.InputMedia//toInputMediaDocument/dev.inmo.tgbotapi.types.files.DocumentFile#kotlin.String?#dev.inmo.tgbotapi.types.ParseMode.ParseMode?/PointingToDeclaration/"></a>[toInputMediaDocument](../../dev.inmo.tgbotapi.types.InputMedia/to-input-media-document.md)| <a name="dev.inmo.tgbotapi.types.InputMedia//toInputMediaDocument/dev.inmo.tgbotapi.types.files.DocumentFile#kotlin.String?#dev.inmo.tgbotapi.types.ParseMode.ParseMode?/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [DocumentFile](index.md).[toInputMediaDocument](../../dev.inmo.tgbotapi.types.InputMedia/to-input-media-document.md)(caption: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, parseMode: [ParseMode](../../dev.inmo.tgbotapi.types.ParseMode/-parse-mode/index.md)? = null): [InputMediaDocument](../../dev.inmo.tgbotapi.types.InputMedia/-input-media-document/index.md)  <br>fun [DocumentFile](index.md).[toInputMediaDocument](../../dev.inmo.tgbotapi.types.InputMedia/to-input-media-document.md)(textSources: [TextSourcesList](../../dev.inmo.tgbotapi.CommonAbstracts/index.md#%5Bdev.inmo.tgbotapi.CommonAbstracts%2FTextSourcesList%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081) = emptyList()): [InputMediaDocument](../../dev.inmo.tgbotapi.types.InputMedia/-input-media-document/index.md)  <br><br><br>|

