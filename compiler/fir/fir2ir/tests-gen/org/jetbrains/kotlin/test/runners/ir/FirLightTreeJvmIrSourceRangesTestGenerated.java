/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.test.runners.ir;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.test.generators.GenerateCompilerTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/testData/ir/sourceRanges")
@TestDataPath("$PROJECT_ROOT")
public class FirLightTreeJvmIrSourceRangesTestGenerated extends AbstractFirLightTreeJvmIrSourceRangesTest {
    @Test
    public void testAllFilesPresentInSourceRanges() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/ir/sourceRanges"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
    }

    @Test
    @TestMetadata("annotations.kt")
    public void testAnnotations() throws Exception {
        runTest("compiler/testData/ir/sourceRanges/annotations.kt");
    }

    @Test
    @TestMetadata("augmentedAssignmentWithExpression.kt")
    public void testAugmentedAssignmentWithExpression() throws Exception {
        runTest("compiler/testData/ir/sourceRanges/augmentedAssignmentWithExpression.kt");
    }

    @Test
    @TestMetadata("comments.kt")
    public void testComments() throws Exception {
        runTest("compiler/testData/ir/sourceRanges/comments.kt");
    }

    @Test
    @TestMetadata("elvis.kt")
    public void testElvis() throws Exception {
        runTest("compiler/testData/ir/sourceRanges/elvis.kt");
    }

    @Test
    @TestMetadata("kt17108.kt")
    public void testKt17108() throws Exception {
        runTest("compiler/testData/ir/sourceRanges/kt17108.kt");
    }

    @Test
    @TestMetadata("kt24258.kt")
    public void testKt24258() throws Exception {
        runTest("compiler/testData/ir/sourceRanges/kt24258.kt");
    }

    @Test
    @TestMetadata("operators.kt")
    public void testOperators() throws Exception {
        runTest("compiler/testData/ir/sourceRanges/operators.kt");
    }

    @Test
    @TestMetadata("postfixIncrementDecrement.kt")
    public void testPostfixIncrementDecrement() throws Exception {
        runTest("compiler/testData/ir/sourceRanges/postfixIncrementDecrement.kt");
    }

    @Nested
    @TestMetadata("compiler/testData/ir/sourceRanges/declarations")
    @TestDataPath("$PROJECT_ROOT")
    public class Declarations {
        @Test
        public void testAllFilesPresentInDeclarations() throws Exception {
            KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/ir/sourceRanges/declarations"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
        }

        @Test
        @TestMetadata("classFuns.kt")
        public void testClassFuns() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/classFuns.kt");
        }

        @Test
        @TestMetadata("classProperties.kt")
        public void testClassProperties() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/classProperties.kt");
        }

        @Test
        @TestMetadata("classes.kt")
        public void testClasses() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/classes.kt");
        }

        @Test
        @TestMetadata("delegatedProperties.kt")
        public void testDelegatedProperties() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/delegatedProperties.kt");
        }

        @Test
        @TestMetadata("fakeOverrides.kt")
        public void testFakeOverrides() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/fakeOverrides.kt");
        }

        @Test
        @TestMetadata("kt29862.kt")
        public void testKt29862() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/kt29862.kt");
        }

        @Test
        @TestMetadata("primaryConstructors.kt")
        public void testPrimaryConstructors() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/primaryConstructors.kt");
        }

        @Test
        @TestMetadata("secondaryConstructors.kt")
        public void testSecondaryConstructors() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/secondaryConstructors.kt");
        }

        @Test
        @TestMetadata("synthesizedDataClassMembers.kt")
        public void testSynthesizedDataClassMembers() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/synthesizedDataClassMembers.kt");
        }

        @Test
        @TestMetadata("topLevelFuns.kt")
        public void testTopLevelFuns() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/topLevelFuns.kt");
        }

        @Test
        @TestMetadata("topLevelProperties.kt")
        public void testTopLevelProperties() throws Exception {
            runTest("compiler/testData/ir/sourceRanges/declarations/topLevelProperties.kt");
        }
    }
}
