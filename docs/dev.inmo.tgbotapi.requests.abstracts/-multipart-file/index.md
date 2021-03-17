//[docs](../../../index.md)/[dev.inmo.tgbotapi.requests.abstracts](../index.md)/[MultipartFile](index.md)



# MultipartFile  
 [common] data class [MultipartFile](index.md)(**file**: [StorageFile](../../dev.inmo.tgbotapi.utils/-storage-file/index.md), **mimeType**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **filename**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [InputFile](../-input-file/index.md)

Contains info about file for sending

   


## Constructors  
  
| | |
|---|---|
| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/MultipartFile/#dev.inmo.tgbotapi.utils.StorageFile#kotlin.String#kotlin.String/PointingToDeclaration/"></a>[MultipartFile](-multipart-file.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/MultipartFile/#dev.inmo.tgbotapi.utils.StorageFile#kotlin.String#kotlin.String/PointingToDeclaration/"></a> [common] fun [MultipartFile](-multipart-file.md)(file: [StorageFile](../../dev.inmo.tgbotapi.utils/-storage-file/index.md), mimeType: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = file.storageFileInfo.contentType, filename: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = file.storageFileInfo.fileName)   <br>|


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/file/#/PointingToDeclaration/"></a>[file](file.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/file/#/PointingToDeclaration/"></a> [common] val [file](file.md): [StorageFile](../../dev.inmo.tgbotapi.utils/-storage-file/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/fileId/#/PointingToDeclaration/"></a>[fileId](file-id.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/fileId/#/PointingToDeclaration/"></a> [common] open override val [fileId](file-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/filename/#/PointingToDeclaration/"></a>[filename](filename.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/filename/#/PointingToDeclaration/"></a> [common] val [filename](filename.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|
| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/mimeType/#/PointingToDeclaration/"></a>[mimeType](mime-type.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile/mimeType/#/PointingToDeclaration/"></a> [common] val [mimeType](mime-type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>|

