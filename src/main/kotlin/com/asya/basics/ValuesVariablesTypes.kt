package com.asya.basics

fun main() {
    // Kotlin is a statically-typed language.
    val meaningOfLife: Int = 42
    // meaningOfLife = 100 // val cannot be reassigned.

    var objectiveInLife: Int = 32
    objectiveInLife = 24 // var can be reassigned.

    // Kotlin has type inference.
    // Compiler figures out the type from the right-hand side of the assignment.
    val meaningOfLive_v2 = 42
    val meaningOfLife_v3 = 40 + 2

    // Common types: int, boolean, char, string, short, long, float, double.
    val aBoolean: Boolean = true // false
    val aChar: Char = 'K'
    val aByte: Byte = 127 // 1 byte representation.
    val aShort: Short = 1234 // 2 bytes representation.
    val anInt: Int = 78 // 4 bytes representation.
    val aLong: Long = 56785678235678L // 8 bytes representation.
    val aFloat: Float = 2.4f // 4 bytes representation.
    val aDouble: Double = 3.14 // 8 bytes representation.

    val aString: String = "I love Asya."
}

// Top-level values - constants.
// These values computed first.
const val appWideMeaningOfLife: Int = 42