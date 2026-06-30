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

---

When you need to fill a changelog, you must follow the common changelog style:

```markdown
## 34.0.0 // number of version
* `Core`:
    * // list of changes
* `Utils`:
    * // list of changes
* `API`:
    * // list of changes
* `BehaviourBuilder`:
    * // list of changes
* `WebApps`:
    * // list of changes
* `BehaviourBuilderWithFSM`:
    * // list of changes
// etc...
```

Each section can be ommited if there are no any changes in the section. In case of filling some known telegram bots api changelog part - you may add previx with its title in point with change. For example:

```markdown
* `Core`:
    * (`Guest mode`) // change data
```

---

When a property (or method) can be declared on an interface/class and implemented by each inheritor - it MUST be declared there and overridden per implementation. DO NOT implement such behaviour as a common extension property/function that dispatches with a `when (this)` over every subtype: those branches silently drift out of sync when new subtypes are added and are not part of the type contract. If a part of the implementation is reused across several inheritors, keep that part in a shared helper (a companion-object function of the owner, or an `internal` top-level function for logic shared between different types) and let the overrides reuse it.
