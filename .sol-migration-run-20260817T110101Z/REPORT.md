# Sol migration report: TelegramBotAPI

## Outcome

- repository: `/home/aleksey/projects/own/TelegramBotAPI`
- cutoff: `2025-08-17`
- starting branch: `36.0.0`
- starting commit: `8bee8982bb904b80f1f563774a73e5a455f4d7c0`
- upstream relation: `36.0.0...origin/36.0.0`, ahead `0`, behind `0`
- starting status: dirty non-master; root status entry=` M codex-master-baseline`; nested checkout status=`## HEAD (no branch)` plus untracked `.codex-gradle/`
- master: local and remote equal at `c7761fd29494e6586a449163a2e48cd7a70a6cef`
- branch gate: failed; dirty non-master checkout cannot be switched or modified
- migration work: not started
- reason: dirty non-master branch is an explicit blocking condition; existing user state was preserved

## Active/legacy classification

- result: active
- substantive activity after cutoff: release branches `28.0.1` through `36.0.0` include commits from `2025-09-04` through `2026-08-17`; `master` activity=`2026-07-01`; `init_kdocs` activity=`2026-07-05`; `kdocs` activity=`2026-07-01`; current `36.0.0` activity=`2026-08-17`
- Maven-primary criterion: satisfied by static master configuration; `gradle/templates/mpp_publish.gradle` applies `maven-publish`, seven library modules apply the publication template, and root configuration uses Maven Central/NMCP aggregation
- legacy result: false

## Branch inventory and classification

- Git verification: local refs=`279`; remote `origin` refs excluding symbolic `origin/HEAD`=`375`; unique logical branch names=`401`
- helper inventory: `.sol-migration-run-20260817T110101Z/inventory/logical-branches.txt` contains all `401` logical names plus the non-branch pseudo-entry `HEAD`; missing logical names=`0`
- release-style logical names: `311`; classification=`substantive` for every release-style name in the helper inventory because version/release-style names are substantive regardless of diff
- dependency-update-only logical names: `76`; classification=`ignored`; complete branch-to-dependency mapping appears below
- remaining non-release logical names: `14`; classification=`substantive`; complete mapping appears below
- total classified: `311 + 76 + 14 = 401`; equality with verified unique logical count=`true`
- local/remote tip mismatches: `0.24.0`, `0.32.10`, `10.0.1`, `12.0.0`, `12.0.1`, `14.0.0`, `14.0.2`, `15.1.0`, `24.0.0`, `24.0.2`, `26.0.0`, `29.0.0`, `3.2.1`, `3.3.0`, `33.0.0`, `33.2.0`, `4.0.0`, `4.1.3`, `4.2.4`, `5.0.0`, `6.0.2`, `6.0.3`, `8.0.0`, `9.0.0`, `9.1.0`, `9.4.0`, `task/676-rework_of_media_groups`; every version-style mismatch remains substantive by name; both task-branch tips contain substantive media-group work

### Ignored dependency-update-only branches

Actual base for every entry below is the branch tip's first parent. Diff review found only the named logical dependency version update; coordinated alias/wrapper changes remain one logical dependency.

- `gradle-wrapper/6.9` → Gradle wrapper (`6.8.3` → `6.9`)
- `renovate/com.benasher44-uuid-0.x` → `com.benasher44:uuid`
- `renovate/com.jfrog.bintray.gradle-gradle-bintray-plugin-1.x` → Gradle Bintray plugin
- `renovate/com.soywiz.korlibs.klock-klock-1.x` → Korlibs Klock
- `renovate/com.soywiz.korlibs.klock-klock-2.x` → Korlibs Klock
- `renovate/dev.inmo-micro_utils.coroutines-0.x` → MicroUtils family
- `renovate/dev.inmo-micro_utils.crypto-0.x` → MicroUtils family
- `renovate/dev.inmo-micro_utils.serialization.base64-0.x` → MicroUtils family
- `renovate/dev.inmo-micro_utils.serialization.encapsulator-0.x` → MicroUtils family
- `renovate/dev.inmo-micro_utils.serialization.typed_serializer-0.x` → MicroUtils family
- `renovate/dokka` → Dokka
- `renovate/dokka_version` → Dokka
- `renovate/github.release.plugin` → GitHub Release Gradle plugin
- `renovate/github_release_plugin_version` → GitHub Release Gradle plugin
- `renovate/gradle-6.x` → Gradle wrapper
- `renovate/gradle-7.x` → Gradle wrapper
- `renovate/gradle-8.x` → Gradle wrapper
- `renovate/gradle-9.x` → Gradle wrapper
- `renovate/io.ktor-ktor-client-cio-1.x` → Ktor family
- `renovate/io.ktor-ktor-client-core-1.x` → Ktor family
- `renovate/io.ktor-ktor-server-1.x` → Ktor family
- `renovate/io.ktor-ktor-server-host-common-1.x` → Ktor family
- `renovate/klock_version` → Korlibs Klock
- `renovate/korlibs` → Korlibs family
- `renovate/kotlin` → Kotlin family
- `renovate/kotlin-coroutines` → kotlinx.coroutines
- `renovate/kotlin-monorepo` → Kotlin family
- `renovate/kotlin-poet` → KotlinPoet
- `renovate/kotlin-serialization` → kotlinx.serialization
- `renovate/kotlin.coroutines` → kotlinx.coroutines
- `renovate/kotlin.poet` → KotlinPoet
- `renovate/kotlin.serialization` → kotlinx.serialization
- `renovate/kotlin_coroutines_version` → kotlinx.coroutines
- `renovate/kotlin_serialisation_runtime_version` → kotlinx.serialization
- `renovate/kotlin_version` → Kotlin family
- `renovate/kotlinx-coroutines-monorepo` → kotlinx.coroutines
- `renovate/kslog` → KSLog
- `renovate/ksp` → Kotlin Symbol Processing
- `renovate/ksp-monorepo` → Kotlin Symbol Processing
- `renovate/ktor` → Ktor family
- `renovate/ktor-monorepo` → Ktor family
- `renovate/ktor_version` → Ktor family
- `renovate/major-dokka` → Dokka
- `renovate/major-kotlin` → Kotlin family
- `renovate/major-kotlin-monorepo` → Kotlin family
- `renovate/major-kotlin.poet` → KotlinPoet
- `renovate/major-ksp` → Kotlin Symbol Processing
- `renovate/major-ktor-monorepo` → Ktor family
- `renovate/major-ktor_version` → Ktor family
- `renovate/micro_utils_version` → MicroUtils family
- `renovate/microutils` → MicroUtils family
- `renovate/nmcp` → NMCP aggregation plugin
- `renovate/org.jetbrains.dokka-1.x` → Dokka
- `renovate/org.jetbrains.dokka-dokka-gradle-plugin-0.x` → Dokka
- `renovate/org.jetbrains.dokka-dokka-gradle-plugin-1.x` → Dokka
- `renovate/org.jetbrains.dokka-gfm-plugin-1.x` → Dokka
- `renovate/org.jetbrains.dokka-org.jetbrains.dokka.gradle.plugin-0.x` → Dokka
- `renovate/org.jetbrains.dokka-org.jetbrains.dokka.gradle.plugin-1.x` → Dokka
- `renovate/org.jetbrains.kotlin-kotlin-gradle-plugin-1.x` → Kotlin family
- `renovate/org.jetbrains.kotlin-kotlin-serialization-1.x` → Kotlin family
- `renovate/org.jetbrains.kotlin.multiplatform-1.x` → Kotlin family
- `renovate/org.jetbrains.kotlin.multiplatform-org.jetbrains.kotlin.multiplatform.gradle.plugin-1.x` → Kotlin family
- `renovate/org.jetbrains.kotlin.plugin.serialization-1.x` → Kotlin family
- `renovate/org.jetbrains.kotlin.plugin.serialization-org.jetbrains.kotlin.plugin.serialization.gradle.plugin-1.x` → Kotlin family
- `renovate/org.jetbrains.kotlinx-kotlinx-coroutines-core-1.x` → kotlinx.coroutines
- `renovate/org.jetbrains.kotlinx-kotlinx-serialization-json-1.x` → kotlinx.serialization JSON
- `renovate/org.jetbrains.kotlinx-kotlinx-serialization-properties-1.x` → kotlinx.serialization
- `renovate/org.jetbrains.kotlinx-kotlinx-serialization-properties-common-0.x` → kotlinx.serialization
- `renovate/org.jetbrains.kotlinx-kotlinx-serialization-properties-js-0.x` → kotlinx.serialization
- `renovate/org.jetbrains.kotlinx-kotlinx-serialization-runtime-0.x` → kotlinx.serialization
- `renovate/org.jetbrains.kotlinx-kotlinx-serialization-runtime-common-0.x` → kotlinx.serialization
- `renovate/org.jetbrains.kotlinx-kotlinx-serialization-runtime-js-0.x` → kotlinx.serialization
- `renovate/uuid` → `com.benasher44:uuid`
- `renovate/uuid_version` → `com.benasher44:uuid`
- `renovate/validator` → Kotlin binary compatibility validator
- `renovate/versions` → Ben Manes Versions plugin

### Substantive non-release branches

- `feature/fsm` → feature history plus README change
- `feature/passport` → passport types and Base64 implementation
- `garbage` → `CHANGELOG.md` change; changelog rule forces substantive classification
- `implement_telegram_passport` → request/response implementation plus `CHANGELOG.md`
- `init_kdocs` → extensive KDoc source changes; activity after cutoff
- `kdocs` → generated documentation deployment; activity after cutoff
- `klassindex` → documentation/build/test implementation changes
- `lib_to_common` → publication implementation changes
- `master` → primary integration branch; activity after cutoff
- `new_branch` → `CHANGELOG.md` plus dependency changes; changelog rule forces substantive classification
- `play_with_dependabot` → comments out `apply from: "publish.gradle"`; behavior change is not a dependency-version update
- `task/28-migration_to_mpp` → multiplatform migration implementation
- `task/676-rework_of_media_groups` → media-group API/core implementation on both unequal local and remote tips
- `tg_channel_imh` → repository image asset history

## Gradle project/module discovery

- Gradle execution: skipped because the dirty non-master branch gate failed and unrelated Gradle processes were present; no daemon/process was reused, stopped, or modified
- master static project paths from `settings.gradle`: `:`, `:tgbotapi.core`, `:tgbotapi.ksp`, `:tgbotapi.api`, `:tgbotapi.utils`, `:tgbotapi.behaviour_builder`, `:tgbotapi.behaviour_builder.fsm`, `:tgbotapi`, `:tgbotapi.webapps`, `:docs`
- pre-migration dynamic module list: unavailable because Gradle execution was prohibited by the branch gate
- pre-migration reliable static module list: `[:, :tgbotapi.core, :tgbotapi.ksp, :tgbotapi.api, :tgbotapi.utils, :tgbotapi.behaviour_builder, :tgbotapi.behaviour_builder.fsm, :tgbotapi, :tgbotapi.webapps, :docs]`
- post-migration module list: unavailable because no migration occurred
- pre/post module equality: not evaluated; no equality claim

## `publishToMavenLocal` and Maven manifests

- `publishToMavenLocal` existence: static yes for `:tgbotapi.core`, `:tgbotapi.api`, `:tgbotapi.utils`, `:tgbotapi.behaviour_builder`, `:tgbotapi.behaviour_builder.fsm`, `:tgbotapi`, `:tgbotapi.webapps`; each module applies `gradle/templates/mpp_publish.gradle`, which applies `maven-publish`
- static no for `:tgbotapi.ksp` and `:docs`; root `:` does not directly apply `maven-publish`
- dynamic task existence verification: not run because the branch gate failed
- baseline Maven repository: not created
- baseline publication: not run
- baseline manifest: unavailable; no GAV/packaging/artifact/module mapping claim
- post Maven repository: not created
- post publication: not run
- post manifest: unavailable; no GAV/packaging/artifact/module mapping claim
- baseline/post manifest equality: not evaluated; no equality claim

## Files and changes

- tracked files changed by migration worker: none
- migration files changed: none
- pre-existing root worktree change preserved: `codex-master-baseline` gitlink reports nested untracked `.codex-gradle/`
- report artifact created: `.sol-migration-run-20260817T110101Z/REPORT.md`
- pre-existing inventory artifact retained: `.sol-migration-run-20260817T110101Z/inventory/logical-branches.txt`
- scope incident: branch-audit command accidentally created `/tmp/tgbot-logical.2283369.txt` outside the assigned repository
- scope remediation: exact accidental file `/tmp/tgbot-logical.2283369.txt` deleted with `apply_patch`; subsequent read-only absence check passed
- repository Gradle/Maven artifact paths created by migration worker: none

## Commands and validation results

- Git ref inventory/classification commands: exit `0`
- Git status/branch-gate commands: exit `0`; result=`dirty non-master blocked`
- static master settings/build/publication inspection: exit `0`
- Gradle module discovery: not run
- `kspCommonMainKotlinMetadata`: not run
- `apiDump`: not run
- build/test commands: not run
- `publishToMavenLocal`: not run
- baseline/post artifact equality: not evaluated
- module equality: not evaluated

## Final status

- final status: blocked
- reason: checkout started on dirty non-master branch `36.0.0`; explicit branch gate prohibited checkout, Gradle execution, baseline publication, tracked modification, and migration
