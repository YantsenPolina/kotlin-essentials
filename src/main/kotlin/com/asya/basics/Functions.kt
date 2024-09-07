package com.asya.basics

// Unit is equivalent to "void" in other languages.
fun simpleFunction(arg: String): Unit {
    println("Just passed an argument: $arg") // String template or interpolation.
}

fun printHello() {
    println("I am a simple no-arg function.")
}

fun concatenateString(aString: String, count: Int): String {
    var result = ""
    for (i in 1 .. count)
        result += aString
    return result
}

// Special syntax for single-expression functions.
fun combineStrings(strA: String, strB: String): String =
    "$strA---$strB"

// Recursion (functional programming).
fun concatenateStringRecursive(aString: String, count: Int): String =
    if (count <= 0) ""
    else aString + concatenateStringRecursive(aString, count - 1)

// Default arguments.
fun demoDefaultArgument(regularArg: String = "Kotlin", intArg: Int = 0) =
    "$regularArg------$intArg"

// Nested function calls.
fun complexFunction(someArg: String) {
    fun innerFunction(innerArg: Int) {
        println("Outer argument: $someArg, inner argument: $innerArg")
    }

    innerFunction(45)
}

/**
 * Exercises:
 * 1. A greeting function (name, age) => "Hi, my name is ___ and I am ___ years old".
 * 2. A factorial function n => 1 * 2 * 3 * ... * n.
 * 3. A Fibonacci function n => nth Fibonacci number.
 *      1 2 3 5 8 13 21 34 ...
 * 4. A function that tests whether a number is prime n => boolean whether that n is prime.
 *      isPrime(8) = false
 *      isPrime(7) = true
 *      isPrime(2003) = true
 *
 * BONUS if you also do recursive versions of ex 2-4.
 */

// 1
fun greeting(name: String, age: Int): String = "Hi, my name is $name and I am $age years old."

// 2
fun factorial(n: Int): Long {
    if (n <= 0) return 0

    var product = 1L
    for (i in 1 .. n)
        product *= i
    return product
}

fun factorialRec(n: Int): Long =
    if (n <= 0) 0
    else if (n == 1) 1
    else n * factorialRec(n - 1)

// 3
fun fibonacci(n: Int): Long {
    var smaller = 1L
    var larger = 1L

    if (n <= 0) return -1
    if (n <= 2) return smaller

    for (i in 3 .. n) {
        val next = smaller + larger
        smaller = larger
        larger = next
    }

    return larger
}

fun fibonacciRec(n: Int): Long =
    if (n <= 0) -1
    else if (n <= 2) 1
    else fibonacciRec(n - 2) + fibonacciRec(n - 1)

// 4
fun isPrime(n: Int): Boolean {
    for (d in 2 .. n/2) {
        if (n % d == 0) return false
    }

    return true
}

// There is no need to allocate stack frames.
// Recursive call is computed last on this branch. It is in a tail position (tail recursive).
tailrec fun isPrimeRec(n: Int, d: Int = 2): Boolean =
    if (n % d == 0) false
    else if (d > n/2) true
    else isPrimeRec(n, d + 1)

fun main() {
    simpleFunction("Kotlin")
    simpleFunction("Scala")
    println(concatenateString("Kotlin", 3))
    println(concatenateString("Kotlin", 10))
    println(concatenateStringRecursive("Kotlin", 3))
    println(concatenateStringRecursive("Kotlin", 10))

    // Default argument demo.
    val normalCall = demoDefaultArgument("Kotlin", 32)
    val defaultCall = demoDefaultArgument("Kotlin")
    val multipleDefaultCall = demoDefaultArgument()
    val secondNormalCall = demoDefaultArgument(intArg = 99)
    val kotlinx3 = concatenateString(aString = "Kotlin", count = 3)

    println(greeting("Asya", 16))
    println(factorial(5))
    println(factorialRec(5))
    println("Fibonacci - iterative.")
    for (i in 1 .. 10)
        println(fibonacci(i))
    println("Fibonacci - recursive.")
    for (i in 1 .. 10)
        println(fibonacciRec(i))
    println("Prime test - iterative.")
    println("81 - ${isPrime(81)}")
    println("7 - ${isPrime(7)}")
    println("2003 - ${isPrime(2003)}")
    println("Prime test - recursive.")
    println("81 - ${isPrimeRec(81)}")
    println("7 - ${isPrimeRec(7)}")
    println("2003 - ${isPrimeRec(2003)}")
}