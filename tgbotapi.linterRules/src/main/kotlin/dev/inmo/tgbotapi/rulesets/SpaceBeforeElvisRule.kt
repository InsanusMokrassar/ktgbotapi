package dev.inmo.tgbotapi.rulesets

import com.pinterest.ktlint.rule.engine.core.api.*
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace

class SpaceBeforeElvisRule :
    Rule(
        RuleId("style:space-before-elvis"),
        About(),
    ) {
    @Deprecated("Marked for removal in Ktlint 2.0. Please implement interface RuleAutocorrectApproveHandler.")
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        // Check if the node is the safe access operator '?.'
        if (node.elementType == ElementType.SAFE_ACCESS) {
            val prevSibling = node.prevSibling()
            if (prevSibling !is PsiWhiteSpace) {
                val errorMessage = "Missing space before '?.'"

                emit(node.startOffset, errorMessage, true)

                if (autoCorrect) {
                    // Insert or update the whitespace before the current node to be a single space
                    node.upsertWhitespaceBeforeMe(" ")
                }
            }
        }
    }
}
