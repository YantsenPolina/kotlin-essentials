package com.asya.fp

object HOFs {
    // Higher-order function = function that takes other function as argument or returns other function as a result.
    val aHof: (Int, (Int) -> Int) -> Int = { x, func -> x + func(1) }
    val anotherHof: (Int) -> ((Int) -> Int) = { x -> { y -> y + 2 * x } }

    val four = aHof(2) { arg -> arg + 1 }
    val four_v2 = anotherHof(1)(2) // Currying.

    // Curried functions.
    val superAdder: (Int) -> (Int) -> Int = { x -> { y -> x + y } }
    val add3: (Int) -> Int = superAdder(3)
    val seven = add3(4)

    /*
        Exercises:
        1. Conversion methods from curried and uncurried functions.
        2. Compose function values.
     */
    fun <A, B, C> toCurry(f: (A, B) -> C): (A) -> (B) -> C = { x -> { y -> f(x, y) } }
    fun <A, B, C> fromCurry(f: (A) -> (B) -> C): (A, B) -> C = { x, y -> f(x)(y) }

    val regularAdder = { x: Int, y: Int -> x + y }
    val superAdder_v2 = toCurry(regularAdder)
    val seven_v2 = superAdder_v2(3)(4)
    val regularAdder_v2 = fromCurry(superAdder_v2)
    val seven_v3 = regularAdder_v2(3, 4)

    // Another function that given an argument x returns f(g(x)).
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { x -> f(g(x)) }
    // Another function that given an argument x returns g(f(x)).
    fun <A, B, C> andThen(f: (A) -> B, g: (B) -> C): (A) -> C = { x -> g(f(x)) }

    val incrementer = { x: Int -> x + 1 }
    val doubler = { x: Int -> 2 * x }
    val composed = compose(incrementer, doubler) // 2x + 1
    val chained = andThen(incrementer, doubler) // (x + 1) * 2

    @JvmStatic
    fun main(args: Array<String>) {
        println(aHof(10) { arg -> arg + 10 })
        println(anotherHof(5)(67))
        println(four)
        println(four_v2)
        println(seven)
        println(seven_v2)
        println(seven_v3)
        println(composed(10))
        println(chained(10))
    }
}