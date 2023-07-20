/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions

import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirLabel
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

abstract class FirErrorLoop : FirLoop(), FirDiagnosticHolder {
    abstract override val source: KtSourceElement?
    abstract override val typeRef: FirTypeRef
    abstract override val annotations: List<FirAnnotation>
    abstract override val block: FirBlock
    abstract override val condition: FirExpression
    abstract override val label: FirLabel?
    abstract override val diagnostic: ConeDiagnostic

    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R = visitor.visitErrorLoop(this, data)

    @Suppress("UNCHECKED_CAST")
    override fun <E : FirElement, D> transform(transformer: FirTransformer<D>, data: D): E =
        transformer.transformErrorLoop(this, data) as E

    abstract override fun replaceTypeRef(newTypeRef: FirTypeRef)

    abstract override fun replaceAnnotations(newAnnotations: List<FirAnnotation>)

    abstract override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirErrorLoop

    abstract override fun <D> transformBlock(transformer: FirTransformer<D>, data: D): FirErrorLoop

    abstract override fun <D> transformCondition(transformer: FirTransformer<D>, data: D): FirErrorLoop

    abstract override fun <D> transformOtherChildren(transformer: FirTransformer<D>, data: D): FirErrorLoop
}
