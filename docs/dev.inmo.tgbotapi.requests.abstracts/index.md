//[docs](../../index.md)/[dev.inmo.tgbotapi.requests.abstracts](index.md)



# Package dev.inmo.tgbotapi.requests.abstracts  


## Types  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.abstracts/FileId///PointingToDeclaration/"></a>[FileId](-file-id/index.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/FileId///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [FileId](-file-id/index.md)(**fileId**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [InputFile](-input-file/index.md)  <br>More info  <br>Contains file id or file url  <br><br><br>|
| <a name="dev.inmo.tgbotapi.requests.abstracts/InputFile///PointingToDeclaration/"></a>[InputFile](-input-file/index.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/InputFile///PointingToDeclaration/"></a>[common]  <br>Content  <br>sealed class [InputFile](-input-file/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile///PointingToDeclaration/"></a>[MultipartFile](-multipart-file/index.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartFile///PointingToDeclaration/"></a>[common]  <br>Content  <br>data class [MultipartFile](-multipart-file/index.md)(**file**: [StorageFile](../dev.inmo.tgbotapi.utils/-storage-file/index.md), **mimeType**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **filename**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [InputFile](-input-file/index.md)  <br>More info  <br>Contains info about file for sending  <br><br><br>|
| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartRequest///PointingToDeclaration/"></a>[MultipartRequest](-multipart-request/index.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/MultipartRequest///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [MultipartRequest](-multipart-request/index.md)<[T](-multipart-request/index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> : [Request](-request/index.md)<[T](-multipart-request/index.md)>   <br><br><br>|
| <a name="dev.inmo.tgbotapi.requests.abstracts/Request///PointingToDeclaration/"></a>[Request](-request/index.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/Request///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [Request](-request/index.md)<[T](-request/index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)>  <br><br><br>|
| <a name="dev.inmo.tgbotapi.requests.abstracts/SimpleRequest///PointingToDeclaration/"></a>[SimpleRequest](-simple-request/index.md)| <a name="dev.inmo.tgbotapi.requests.abstracts/SimpleRequest///PointingToDeclaration/"></a>[common]  <br>Content  <br>interface [SimpleRequest](-simple-request/index.md)<[T](-simple-request/index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)> : [Request](-request/index.md)<[T](-simple-request/index.md)>   <br><br><br>|


## Functions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.abstracts//toInputFile/java.io.File#/PointingToDeclaration/"></a>| <a name="dev.inmo.tgbotapi.requests.abstracts//toInputFile/java.io.File#/PointingToDeclaration/"></a>[jvm, common]  <br>Content  <br>[jvm]  <br>fun [File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html).[toInputFile](index.md#%5Bdev.inmo.tgbotapi.requests.abstracts%2F%2FtoInputFile%2Fjava.io.File%23%2FPointingToDeclaration%2F%5D%2FFunctions%2F745855401)(): [MultipartFile](-multipart-file/index.md)  <br>[common]  <br>fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[toInputFile](to-input-file.md)(): [FileId](-file-id/index.md)  <br><br><br>|

