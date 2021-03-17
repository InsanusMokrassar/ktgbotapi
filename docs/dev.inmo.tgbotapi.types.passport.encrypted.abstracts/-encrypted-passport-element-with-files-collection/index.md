//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.passport.encrypted.abstracts](../index.md)/[EncryptedPassportElementWithFilesCollection](index.md)



# EncryptedPassportElementWithFilesCollection  
 [common] interface [EncryptedPassportElementWithFilesCollection](index.md) : [EncryptedPassportElement](../-encrypted-passport-element/index.md)   


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.passport.encrypted.abstracts/EncryptedPassportElementWithFilesCollection/files/#/PointingToDeclaration/"></a>[files](files.md)| <a name="dev.inmo.tgbotapi.types.passport.encrypted.abstracts/EncryptedPassportElementWithFilesCollection/files/#/PointingToDeclaration/"></a> [common] abstract val [files](files.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[PassportFile](../../dev.inmo.tgbotapi.types.passport.encrypted/-passport-file/index.md)>   <br>|
| <a name="dev.inmo.tgbotapi.types.passport.encrypted.abstracts/EncryptedPassportElementWithFilesCollection/hash/#/PointingToDeclaration/"></a>[hash](index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FEncryptedPassportElementWithFilesCollection%2Fhash%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.types.passport.encrypted.abstracts/EncryptedPassportElementWithFilesCollection/hash/#/PointingToDeclaration/"></a> [common] abstract val [hash](index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FEncryptedPassportElementWithFilesCollection%2Fhash%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [PassportElementHash](../index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FPassportElementHash%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.types.passport.encrypted/EncryptedPassportElementWithTranslatableFilesCollection///PointingToDeclaration/"></a>[EncryptedPassportElementWithTranslatableFilesCollection](../../dev.inmo.tgbotapi.types.passport.encrypted/-encrypted-passport-element-with-translatable-files-collection/index.md)|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.passport//createFileError/dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFilesCollection#kotlin.String#kotlin.ByteArray/PointingToDeclaration/"></a>[createFileError](../../dev.inmo.tgbotapi.types.passport/create-file-error.md)| <a name="dev.inmo.tgbotapi.types.passport//createFileError/dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFilesCollection#kotlin.String#kotlin.ByteArray/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [EncryptedPassportElementWithFilesCollection](index.md).[createFileError](../../dev.inmo.tgbotapi.types.passport/create-file-error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), unencryptedFileHash: [PassportElementHash](../index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FPassportElementHash%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [PassportElementErrorFile](../../dev.inmo.tgbotapi.types.passport/-passport-element-error-file/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.passport//createFilesError/dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFilesCollection#kotlin.String#kotlin.collections.List[kotlin.ByteArray]/PointingToDeclaration/"></a>[createFilesError](../../dev.inmo.tgbotapi.types.passport/create-files-error.md)| <a name="dev.inmo.tgbotapi.types.passport//createFilesError/dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementWithFilesCollection#kotlin.String#kotlin.collections.List[kotlin.ByteArray]/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [EncryptedPassportElementWithFilesCollection](index.md).[createFilesError](../../dev.inmo.tgbotapi.types.passport/create-files-error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), unencryptedFileHashes: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[PassportElementHash](../index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FPassportElementHash%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)>): [PassportElementErrorFiles](../../dev.inmo.tgbotapi.types.passport/-passport-element-error-files/index.md)  <br><br><br>|

