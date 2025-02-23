/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.test

/**
 * Serves as a bridge to a testing framework.
 *
 * The tests structure is defined using internal functions suite and test, which delegate to corresponding functions of a [FrameworkAdapter].
 * Sample test layout:
 *
 * ```
 * suite("top-level-package1", ignored = false) {
 *     suite("TestClass1", ignored = false) {
 *         suite("a subsuite", ignored = false) {
 *             test("a test", ignored = false) {...}
 *             test("an ignored/pending test", ignored = true) {...}
 *         }
 *         suite("an ignored/pending test", ignored = true) {...}
 *     }
 * }
 * ```
 */
public interface FrameworkAdapter {

    /**
     * Declares a test suite.
     *
     * @param name the name of the test suite, e.g. a class name
     * @param ignored whether the test suite is ignored, e.g. marked with [Ignore] annotation
     * @param suiteFn defines nested suites by calling [kotlin.test.suite] and tests by calling [kotlin.test.test]
     */
    public fun suite(name: String, ignored: Boolean, suiteFn: () -> Unit)

    /**
     * Declares a test.
     *
     * @param name the test name.
     * @param ignored whether the test is ignored
     * @param testFn contains test body invocation
     */
    public fun test(name: String, ignored: Boolean, testFn: () -> Any?)
}
