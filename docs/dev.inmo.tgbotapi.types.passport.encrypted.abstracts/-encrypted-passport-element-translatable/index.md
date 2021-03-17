//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.passport.encrypted.abstracts](../index.md)/[EncryptedPassportElementTranslatable](index.md)



# EncryptedPassportElementTranslatable  
 [common] interface [EncryptedPassportElementTranslatable](index.md) : [EncryptedPassportElement](../-encrypted-passport-element/index.md)   


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.passport.encrypted.abstracts/EncryptedPassportElementTranslatable/hash/#/PointingToDeclaration/"></a>[hash](index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FEncryptedPassportElementTranslatable%2Fhash%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081)| <a name="dev.inmo.tgbotapi.types.passport.encrypted.abstracts/EncryptedPassportElementTranslatable/hash/#/PointingToDeclaration/"></a> [common] abstract val [hash](index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FEncryptedPassportElementTranslatable%2Fhash%2F%23%2FPointingToDeclaration%2F%5D%2FProperties%2F625018081): [PassportElementHash](../index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FPassportElementHash%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|
| <a name="dev.inmo.tgbotapi.types.passport.encrypted.abstracts/EncryptedPassportElementTranslatable/translations/#/PointingToDeclaration/"></a>[translations](translations.md)| <a name="dev.inmo.tgbotapi.types.passport.encrypted.abstracts/EncryptedPassportElementTranslatable/translations/#/PointingToDeclaration/"></a> [common] abstract val [translations](translations.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[PassportFile](../../dev.inmo.tgbotapi.types.passport.encrypted/-passport-file/index.md)>   <br>|


## Inheritors  
  
|  Name | 
|---|
| <a name="dev.inmo.tgbotapi.types.passport.encrypted/EncryptedPassportElementWithTranslatableFilesCollection///PointingToDeclaration/"></a>[EncryptedPassportElementWithTranslatableFilesCollection](../../dev.inmo.tgbotapi.types.passport.encrypted/-encrypted-passport-element-with-translatable-files-collection/index.md)|
| <a name="dev.inmo.tgbotapi.types.passport.encrypted/EncryptedPassportElementWithTranslatableIDDocument///PointingToDeclaration/"></a>[EncryptedPassportElementWithTranslatableIDDocument](../../dev.inmo.tgbotapi.types.passport.encrypted/-encrypted-passport-element-with-translatable-i-d-document/index.md)|
| <a name="dev.inmo.tgbotapi.types.passport.encrypted/Passport///PointingToDeclaration/"></a>[Passport](../../dev.inmo.tgbotapi.types.passport.encrypted/-passport/index.md)|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.passport//createFileError/dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementTranslatable#kotlin.String#kotlin.ByteArray/PointingToDeclaration/"></a>[createFileError](../../dev.inmo.tgbotapi.types.passport/create-file-error.md)| <a name="dev.inmo.tgbotapi.types.passport//createFileError/dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementTranslatable#kotlin.String#kotlin.ByteArray/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [EncryptedPassportElementTranslatable](index.md).[createFileError](../../dev.inmo.tgbotapi.types.passport/create-file-error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), unencryptedFileHash: [PassportElementHash](../index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FPassportElementHash%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)): [PassportElementErrorTranslationFile](../../dev.inmo.tgbotapi.types.passport/-passport-element-error-translation-file/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.types.passport//createFilesError/dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementTranslatable#kotlin.String#kotlin.collections.List[kotlin.ByteArray]/PointingToDeclaration/"></a>[createFilesError](../../dev.inmo.tgbotapi.types.passport/create-files-error.md)| <a name="dev.inmo.tgbotapi.types.passport//createFilesError/dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElementTranslatable#kotlin.String#kotlin.collections.List[kotlin.ByteArray]/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [EncryptedPassportElementTranslatable](index.md).[createFilesError](../../dev.inmo.tgbotapi.types.passport/create-files-error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), unencryptedFileHashes: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[PassportElementHash](../index.md#%5Bdev.inmo.tgbotapi.types.passport.encrypted.abstracts%2FPassportElementHash%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)>): [PassportElementErrorTranslationFiles](../../dev.inmo.tgbotapi.types.passport/-passport-element-error-translation-files/index.md)  <br><br><br>|

