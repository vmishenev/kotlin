// IGNORE_BACKEND: NATIVE
// WITH_REFLECT
// IGNORE_BACKEND: ANDROID
// WITH_STDLIB

// FILE: lib.kt
var z1 = false
var z2 = false

// FILE: lib2.kt

val x = foo()

private fun foo(): Int {
    println("DSDSD")
    z1 = true
    return 42
}

// Will be initialized since [x]'s initializer calls a function from the file.
val y = run { z2 = true; 117 }

// FILE: main.kt

fun box(): String {
    return return if (z1 && z2) "OK" else "fail"
}
