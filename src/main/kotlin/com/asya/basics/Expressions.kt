package com.asya.basics

fun main() {
    // Expressions are structures that can be turned into a value.
    val meaningOfLife = 40 + 2

    // Mathematical expressions: +, -, *, /, %.
    val mathExpression = 2 + 3 * 4

    // Bitwise operators: shr, shl, and, or, xor, inv.
    val bitwiseExpression = 2 shl 2 // 1000 in binary or 8 in decimal.

    // Comparison expressions: ==, !=, <, <=, >, >=, ===, !==.
    val equalityTest = 1 == 2 // false.

    // Boolean expressions: !, &&, ||.
    val nonEqualityTest = !equalityTest

    // Difference between instructions and expressions.
    // Expressions are evaluated to a value - functional programming.
    // Instructions are executed - imperative programming.

    // Instruction.
    val aCondition = 1 > 2
    if (aCondition) {
        println("The condition is false.")
    } else {
        println("The condition is true.")
    }

    // Expression.
    val anIfExpression = if (aCondition) 42 else 999
    println(anIfExpression)
    println(if (aCondition) 42 else 999)

    // when is a switch on "steroids".
    when (meaningOfLife) {
        42 -> println("The meaning of life.")
        43 -> println("Not the meaning of life, but quite close.")
        else -> println("Something else.")
    }

    // when expression.
    val meaningOfLifeMessage = when (meaningOfLife) {
        42 -> "The meaning of life."
        43 -> "Not the meaning of life, but quite close."
        else -> "Something else."
    }

    // A branch in when with multiple values.
    val meaningOfLifeMessage_v2 = when (meaningOfLife) {
        42, 43 -> "The meaning of life or close enough."
        else -> "Something else."
    }

    // Branches can be arbitrary expressions.
    val meaningOfLifeMessage_v3 = when (meaningOfLife) {
        40 + 2 -> "The meaning of life, computed."
        else -> "Something else."
    }

    // Conditions in branches.
    val meaningOfLifeMessage_v4 = when {
        meaningOfLife < 42 -> "Meaning of life is too small."
        meaningOfLife > 100 -> "Meaning of life is too big."
        else -> "Close enough."
    }

    // Test for types in a when clause.
    val something: Any = 42
    val someExpression = when (something) {
        is Int -> "It is an Integer, maybe the meaning of life!"
        is String -> "It is a String, so maybe not a meaning of life."
        else -> "Not sure what this can be."
    }

    // for loops and while loops are instructions.
    println("Inclusive range.")
    for (i in 1 .. 10) {
        println(i)
    }

    println("Exclusive range.")
    for (i in 1 ..< 10) {
        println(i)
    }

    println("Exclusive range v2.")
    for (i in 1 until  10) {
        println(i)
    }

    println("Inclusive range with step 2.")
    for (i in 1 ..  10 step 2) {
        println(i)
    }

    println("Descending range.")
    for (i in 10 downTo  1) {
        println(i)
    }

    println("Iterating over collection.")
    val anArray = arrayOf(5,2,1,3,4,7,6)
    for (elem in anArray) {
        println(elem)
    }

    println("while loop.")
    var i = 1
    while (i <= 10) {
        println(i)
        i += 1
    }

    println("do-while loop.")
    var j = 10
    do {
        println(j)
        j -= 1
    } while (j >= 1)
}