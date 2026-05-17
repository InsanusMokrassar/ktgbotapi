FOLLOW COMMON CODE STYLE. DO NOT COMMIT OR PUSH ANY CHANGES IF PROMPT DO NOT CONTAINS DIRECT INSTRUCTION ABOUT IT. FOLLOW LINKS TO SEE DOCUMENTATION IN THE PROMPT. IF YOU ARE NOT ABLE TO FOLLOW LINK (DO NOT SEE CONTENT YOU NEED TO FOLLOW PROMPT) - ASK OPERATOR

---

`@Warning` package is `dev.inmo.micro_utils.common.Warning`. Its signature: `Warning(val message: String)`

---

If you have edited some constructor or function signature - you MUST update documentation accordingly AND all calls of this constructor/function. THIS RULE WORKS RECURSIVELY

---

Before any laucnhing of `compileKotlinJvm` or other building procedure which must confirm that build is working, you MUST launch two tasks: `./gradlew kspCommonMainKotlinMetadata` and `./gradlew apiDump`. Order is important. They must be launched sequentially.

---

If you are adding `Send*` request - you also must add in `API` (`tgbotapi.api`) module:

* Direct indings (in `tgbitapi.api` module). As example you may look at `dev.inmo.tgbotapi.requests.send.SendTextMessage` a,d its bindings `dev.inmo.tgbotapi.extensions.api.send.sendTextMessage`
* Bindings in [Sends.kt](../tgbotapi.api/src/commonMain/kotlin/dev/inmo/tgbotapi/extensions/api/send/Sends.kt)
* Bindings in [Replies.kt](../tgbotapi.api/src/commonMain/kotlin/dev/inmo/tgbotapi/extensions/api/send/Replies.kt)
* Bindings in [RepliesWithChatsAndMessages.kt](../tgbotapi.api/src/commonMain/kotlin/dev/inmo/tgbotapi/extensions/api/send/RepliesWithChatsAndMessages.kt)
