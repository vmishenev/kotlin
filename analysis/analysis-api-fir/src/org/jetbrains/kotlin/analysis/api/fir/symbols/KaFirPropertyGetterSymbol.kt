/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.fir.symbols

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.analysis.api.annotations.KaAnnotationList
import org.jetbrains.kotlin.analysis.api.fir.KaFirSession
import org.jetbrains.kotlin.analysis.api.fir.annotations.KaFirAnnotationListForDeclaration
import org.jetbrains.kotlin.analysis.api.fir.findPsi
import org.jetbrains.kotlin.analysis.api.fir.symbols.pointers.createOwnerPointer
import org.jetbrains.kotlin.analysis.api.impl.base.symbols.pointers.KaBasePropertyGetterSymbolPointer
import org.jetbrains.kotlin.analysis.api.lifetime.KaLifetimeToken
import org.jetbrains.kotlin.analysis.api.lifetime.withValidityAssertion
import org.jetbrains.kotlin.analysis.api.symbols.KaPropertyGetterSymbol
import org.jetbrains.kotlin.analysis.api.symbols.KaReceiverParameterSymbol
import org.jetbrains.kotlin.analysis.api.symbols.KaSymbolModality
import org.jetbrains.kotlin.analysis.api.symbols.KaValueParameterSymbol
import org.jetbrains.kotlin.analysis.api.symbols.pointers.KaPsiBasedSymbolPointer
import org.jetbrains.kotlin.analysis.api.symbols.pointers.KaSymbolPointer
import org.jetbrains.kotlin.analysis.api.types.KaType
import org.jetbrains.kotlin.descriptors.Visibility
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor
import org.jetbrains.kotlin.fir.declarations.utils.*
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol
import org.jetbrains.kotlin.name.CallableId

internal class KaFirPropertyGetterSymbol(
    override val firSymbol: FirPropertyAccessorSymbol,
    override val analysisSession: KaFirSession,
) : KaPropertyGetterSymbol(), KaFirSymbol<FirPropertyAccessorSymbol> {
    override val token: KaLifetimeToken get() = builder.token

    init {
        require(firSymbol.isGetter)
    }

    override val psi: PsiElement? get() = withValidityAssertion { firSymbol.findPsi() }

    override val isActual: Boolean get() = withValidityAssertion { firSymbol.isActual }
    override val isExpect: Boolean get() = withValidityAssertion { firSymbol.isExpect }
    override val isDefault: Boolean get() = withValidityAssertion { firSymbol.fir is FirDefaultPropertyAccessor }
    override val isInline: Boolean get() = withValidityAssertion { firSymbol.isInline }
    override val isOverride: Boolean
        get() = withValidityAssertion {
            if (firSymbol.isOverride) return@withValidityAssertion true
            return firSymbol.fir.propertySymbol.isOverride
        }

    override val hasBody: Boolean get() = withValidityAssertion { firSymbol.fir.hasBody }

    override val modality: KaSymbolModality get() = withValidityAssertion { firSymbol.kaSymbolModality }
    override val compilerVisibility: Visibility get() = withValidityAssertion { firSymbol.visibility }

    override val returnType: KaType get() = withValidityAssertion { firSymbol.returnType(builder) }

    override val receiverParameter: KaReceiverParameterSymbol?
        get() = withValidityAssertion {
            builder.variableBuilder.buildVariableSymbol(firSymbol.fir.propertySymbol).receiverParameter
        }

    override val annotations: KaAnnotationList
        get() = withValidityAssertion {
            KaFirAnnotationListForDeclaration.create(firSymbol, builder)
        }

    /**
     * Returns [CallableId] of the delegated Java method if the corresponding property of this setter is a synthetic Java property.
     * Otherwise, returns `null`
     */
    override val callableId: CallableId?
        get() = withValidityAssertion {
            val fir = firSymbol.fir
            if (fir is FirSyntheticPropertyAccessor) {
                fir.delegate.symbol.callableId
            } else null
        }

    override val valueParameters: List<KaValueParameterSymbol> get() = withValidityAssertion { emptyList() }

    override val hasStableParameterNames: Boolean
        get() = withValidityAssertion { true }

    override fun createPointer(): KaSymbolPointer<KaPropertyGetterSymbol> = withValidityAssertion {
        KaPsiBasedSymbolPointer.createForSymbolFromSource<KaPropertyGetterSymbol>(this)
            ?: KaBasePropertyGetterSymbolPointer(propertySymbolPointer = analysisSession.createOwnerPointer(this))
    }

    override fun equals(other: Any?): Boolean = symbolEquals(other)
    override fun hashCode(): Int = symbolHashCode()
}
