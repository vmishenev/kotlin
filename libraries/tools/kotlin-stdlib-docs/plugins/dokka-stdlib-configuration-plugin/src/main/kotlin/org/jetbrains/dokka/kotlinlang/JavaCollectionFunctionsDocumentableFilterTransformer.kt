/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.dokka.kotlinlang

import org.jetbrains.dokka.base.transformers.documentables.SuppressedByConditionDocumentableFilterTransformer
import org.jetbrains.dokka.model.DFunction
import org.jetbrains.dokka.model.Documentable
import org.jetbrains.dokka.plugability.DokkaContext

/**
 * Suppress some methods inherited from [java.util.Collection]:
 * - The old StdLib doesn't contain them
 * - Source code of [kotlin.collections.Collection] and other classes of (Java mapping)[https://kotlinlang.org/docs/java-interop.html#mapped-types]
 * don't have them, although these methods can be called
 */
class JavaCollectionFunctionsDocumentableFilterTransformer(context: DokkaContext) :
    SuppressedByConditionDocumentableFilterTransformer(context) {
    data class FullyQualifiedMethodName(
        val packageName: String, val classNames: String, val methodName: String
    )

    private val methodNames = listOf(
        FullyQualifiedMethodName("kotlin.collections", "Collection", "stream"),
        FullyQualifiedMethodName("kotlin.collections", "Collection", "parallelStream"),
        FullyQualifiedMethodName("kotlin.collections", "Collection", "spliterator"),
        FullyQualifiedMethodName("kotlin.collections", "Collection", "toArray"),
        FullyQualifiedMethodName("kotlin.collections", "Iterable", "forEach"),
        FullyQualifiedMethodName("kotlin.collections", "MutableCollection", "removeIf")
    )

    override fun shouldBeSuppressed(d: Documentable): Boolean = d is DFunction && methodNames.firstOrNull {
        it.packageName == d.dri.packageName && it.classNames == d.dri.classNames && it.methodName == d.dri.callable?.name
    } != null
}