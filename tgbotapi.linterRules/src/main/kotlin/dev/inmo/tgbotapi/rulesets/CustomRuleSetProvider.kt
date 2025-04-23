package dev.inmo.tgbotapi.rulesets

import com.pinterest.ktlint.cli.ruleset.core.api.RuleSetProviderV3
import com.pinterest.ktlint.rule.engine.core.api.RuleProvider
import com.pinterest.ktlint.rule.engine.core.api.RuleSetId

class CustomRuleSetProvider : RuleSetProviderV3(RuleSetId("tgbotapi")) {
    override fun getRuleProviders(): Set<RuleProvider> = setOf(
        RuleProvider { NoNewLineAfterEqualsRule() },
    )
}
