/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.light.classes.symbol.decompiled.service

import org.jetbrains.kotlin.test.directives.JvmEnvironmentConfigurationDirectives
import org.jetbrains.kotlin.test.directives.LanguageSettingsDirectives
import org.jetbrains.kotlin.test.directives.model.SimpleDirectivesContainer
import org.jetbrains.kotlin.test.model.TestModule
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Path
import kotlin.io.path.absolutePathString
import kotlin.io.path.div
import kotlin.io.path.notExists
import kotlin.streams.toList

object CompilerExecutor {
    fun compileLibrary(sourcesPath: Path, options: List<String>, compilationErrorExpected: Boolean): Path? {
        val library = sourcesPath / "library.jar"
        val sourceFiles = sourcesPath.toFile().walkBottomUp()
        val commands = buildList {
            add("dist/kotlinc/bin/kotlinc")
            sourceFiles.mapTo(this) { it.absolutePath }
            addAll(options)
            add("-d")
            add(library.absolutePathString())
        }
        val (result, errors) = runProcess(commands)
        val compilationError = result != 0 || library.notExists()
        if (compilationError && !compilationErrorExpected) {
            error("Unexpected compilation error while compiling library:\n$errors")
        }
        if (!compilationError && compilationErrorExpected) {
            error("Compilation error expected but, code was compiled successfully")
        }
        return library.takeUnless { compilationError }
    }

    private fun runProcess(commands: List<String>): Pair<Int, String> {
        val process = Runtime.getRuntime().exec(commands.toTypedArray())
        val stdError = BufferedReader(InputStreamReader(process.errorStream))
        val result = process.waitFor()
        return result to stdError.lines().toList().joinToString(separator = "\n")
    }

    fun parseCompilerOptionsFromTestdata(module: TestModule): List<String> = buildList {
        module.directives[LanguageSettingsDirectives.API_VERSION].firstOrNull()?.let { apiVersion ->
            addAll(listOf("-api-version", apiVersion.versionString))
        }

        module.directives[JvmEnvironmentConfigurationDirectives.JVM_TARGET].firstOrNull()?.let { jvmTarget ->
            addAll(listOf("-jvm-target", jvmTarget.description))
        }

        addAll(module.directives[Directives.COMPILER_ARGUMENTS])
    }

    object Directives : SimpleDirectivesContainer() {
        val COMPILER_ARGUMENTS by stringDirective("List of additional compiler arguments")
        val COMPILATION_ERRORS by directive("Is compilation errors expected in the file")
    }
}