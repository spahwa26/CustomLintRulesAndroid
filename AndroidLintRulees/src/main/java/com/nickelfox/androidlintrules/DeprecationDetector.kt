package com.nickelfox.androidlintrules

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UAnnotation
import org.jetbrains.uast.UElement

@Suppress("UnstableApiUsage")
class DeprecationDetector : Detector(), SourceCodeScanner {
    override fun applicableAnnotations(): List<String> = listOf(
        "java.lang.Deprecated"
    )

    override fun visitAnnotationUsage(
        context: JavaContext,
        usage: UElement,
        type: AnnotationUsageType,
        annotation: UAnnotation,
        qualifiedName: String,
        method: PsiMethod?,
        referenced: PsiElement?,
        annotations: List<UAnnotation>,
        allMemberAnnotations: List<UAnnotation>,
        allClassAnnotations: List<UAnnotation>,
        allPackageAnnotations: List<UAnnotation>
    ) {
        method?.let {
            if (it.isDeprecated || it.containingClass?.isDeprecated == true)
                reportUsage(context, usage)
        }
    }



    /**
     * Reports an issue.
     */
    private fun reportUsage(
        context: JavaContext,
        usage: UElement
    ) {
        //val useAnnotation = (annotation.uastParent as? UClass)?.qualifiedName ?: return

        context.report(
            ISSUE, usage, context.getNameLocation(usage), """
            Use of deprecated methods is prohibited in Nickelfox, Please use non deprecated alternates.
        """.trimIndent()
        )


    }

    companion object {
        private val IMPLEMENTATION = Implementation(
            DeprecationDetector::class.java,
            Scope.JAVA_FILE_SCOPE
        )

        @Suppress("DefaultLocale")
        val ISSUE: Issue = Issue.create(
            id = "UnsafeDeprecationUsageError",
            briefDescription = "Unsafe deprecation usage",
            explanation = """
                This API has been flagged as deprecation.
                Any declaration annotated with this marker is considered part of an unstable API \
                surface and its call sites should accept the deprecation aspect of it either by \
                using `@UseDeprecation`, or by being annotated with that marker themselves, \
                effectively causing further propagation of that deprecation aspect.
            """,
            category = Category.CORRECTNESS,
            priority = 9,
            severity = Severity.ERROR,
            implementation = IMPLEMENTATION
        )
    }
}
