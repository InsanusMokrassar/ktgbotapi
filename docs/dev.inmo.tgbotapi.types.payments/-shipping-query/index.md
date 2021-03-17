//[docs](../../../index.md)/[dev.inmo.tgbotapi.types.payments](../index.md)/[ShippingQuery](index.md)



# ShippingQuery  
 [common] data class [ShippingQuery](index.md)(**id**: [ShippingQueryIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FShippingQueryIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **user**: [User](../../dev.inmo.tgbotapi.types/-user/index.md), **invoicePayload**: [InvoicePayload](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FInvoicePayload%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081), **shippingAddress**: [ShippingAddress](../-shipping-address/index.md))   


## Properties  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.types.payments/ShippingQuery/id/#/PointingToDeclaration/"></a>[id](id.md)| <a name="dev.inmo.tgbotapi.types.payments/ShippingQuery/id/#/PointingToDeclaration/"></a> [common] val [id](id.md): [ShippingQueryIdentifier](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FShippingQueryIdentifier%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|
| <a name="dev.inmo.tgbotapi.types.payments/ShippingQuery/invoicePayload/#/PointingToDeclaration/"></a>[invoicePayload](invoice-payload.md)| <a name="dev.inmo.tgbotapi.types.payments/ShippingQuery/invoicePayload/#/PointingToDeclaration/"></a> [common] val [invoicePayload](invoice-payload.md): [InvoicePayload](../../dev.inmo.tgbotapi.types/index.md#%5Bdev.inmo.tgbotapi.types%2FInvoicePayload%2F%2F%2FPointingToDeclaration%2F%5D%2FClasslikes%2F625018081)   <br>|
| <a name="dev.inmo.tgbotapi.types.payments/ShippingQuery/shippingAddress/#/PointingToDeclaration/"></a>[shippingAddress](shipping-address.md)| <a name="dev.inmo.tgbotapi.types.payments/ShippingQuery/shippingAddress/#/PointingToDeclaration/"></a> [common] val [shippingAddress](shipping-address.md): [ShippingAddress](../-shipping-address/index.md)   <br>|
| <a name="dev.inmo.tgbotapi.types.payments/ShippingQuery/user/#/PointingToDeclaration/"></a>[user](user.md)| <a name="dev.inmo.tgbotapi.types.payments/ShippingQuery/user/#/PointingToDeclaration/"></a> [common] val [user](user.md): [User](../../dev.inmo.tgbotapi.types/-user/index.md)   <br>|


## Extensions  
  
|  Name |  Summary | 
|---|---|
| <a name="dev.inmo.tgbotapi.requests.answers.payments//createAnswerError/dev.inmo.tgbotapi.types.payments.ShippingQuery#kotlin.String/PointingToDeclaration/"></a>[createAnswerError](../../dev.inmo.tgbotapi.requests.answers.payments/create-answer-error.md)| <a name="dev.inmo.tgbotapi.requests.answers.payments//createAnswerError/dev.inmo.tgbotapi.types.payments.ShippingQuery#kotlin.String/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [ShippingQuery](index.md).[createAnswerError](../../dev.inmo.tgbotapi.requests.answers.payments/create-answer-error.md)(error: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AnswerShippingQueryError](../../dev.inmo.tgbotapi.requests.answers.payments/-answer-shipping-query-error/index.md)  <br><br><br>|
| <a name="dev.inmo.tgbotapi.requests.answers.payments//createAnswerOk/dev.inmo.tgbotapi.types.payments.ShippingQuery#kotlin.collections.List[dev.inmo.tgbotapi.types.payments.ShippingOption]/PointingToDeclaration/"></a>[createAnswerOk](../../dev.inmo.tgbotapi.requests.answers.payments/create-answer-ok.md)| <a name="dev.inmo.tgbotapi.requests.answers.payments//createAnswerOk/dev.inmo.tgbotapi.types.payments.ShippingQuery#kotlin.collections.List[dev.inmo.tgbotapi.types.payments.ShippingOption]/PointingToDeclaration/"></a>[common]  <br>Content  <br>fun [ShippingQuery](index.md).[createAnswerOk](../../dev.inmo.tgbotapi.requests.answers.payments/create-answer-ok.md)(shippingOptions: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[ShippingOption](../-shipping-option/index.md)>): [AnswerShippingQueryOk](../../dev.inmo.tgbotapi.requests.answers.payments/-answer-shipping-query-ok/index.md)  <br><br><br>|

