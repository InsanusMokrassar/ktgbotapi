package dev.inmo.tgbotapi.rulesets

import com.pinterest.ktlint.rule.engine.core.api.Rule
import com.pinterest.ktlint.rule.engine.core.api.RuleId
import com.pinterest.ktlint.rule.engine.core.api.nextSibling
import com.pinterest.ktlint.rule.engine.core.api.upsertWhitespaceAfterMe
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.psi.psiUtil.endOffset

class NoNewLineAfterEqualsRule : Rule(RuleId("style:nonewlineafterequals"), About()) {
    @Deprecated("Marked for removal in Ktlint 2.0. Please implement interface RuleAutocorrectApproveHandler.")
    override fun beforeVisitChildNodes(node: ASTNode, autoCorrect: Boolean, emit: (Int, String, Boolean) -> Unit) {
        val next = node.nextSibling() ?: return
        if (node is PsiElement &&
            node.textMatches("=") &&
            next is PsiWhiteSpace &&
            next.textContains('\n')
        ) {
            emit(node.endOffset, "Unexpected new line symbol", true)

            if (autoCorrect) {
                node.upsertWhitespaceAfterMe(" ")
            }
        }
    }
}
