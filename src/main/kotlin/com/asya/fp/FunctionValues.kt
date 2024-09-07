package com.asya.fp

object FunctionValues {
    interface Transformation { // It is a function.
        operator fun invoke(n: Int): Int
    }

    fun transformList(list: List<Int>, transformation: Transformation): List<Int> {
        val result = mutableListOf<Int>()
        for (n in list)
            result.add(transformation(n))
        return result
    }

    fun transformList_v2(
        list: List<Int>,
        transformation: (Int) -> Int // Function type.
    ): List<Int> {
        val result = mutableListOf<Int>()
        for (n in list)
            result.add(transformation(n))
        return result
    }

    fun <A, B> transformList_v3(list: List<A>, transformation: (A) -> B): List<B> {
        val result = mutableListOf<B>()
        for (n in list)
            result.add(transformation(n))
        return result
    }

    fun <A> reduce(list: List<A>, seed: A, op: (A, A) -> A): A {
        var result = seed
        for (n in list)
            result = op(result, n)
        return result
    }

    tailrec fun <A> reduceRec(list: List<A>, seed: A, op: (A, A) -> A): A =
        if (list.isEmpty()) seed
        else reduceRec(list.drop(1), op(seed, list[0]), op)

    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = listOf(1, 2, 3, 4)
        val tenxTransformation = object:Transformation {
            override fun invoke(n: Int): Int = n * 10
        }
        println(transformList(numbers,tenxTransformation))
        val add5Transformation = object:Transformation {
            override fun invoke(n: Int): Int = n + 5
        }
        println(transformList(numbers, add5Transformation))

        // Functional programming = ability to pass functions as arguments, return functions as results.
        // Anonymous function = function value.
        val tenxFunction = fun (x: Int): Int { return x * 10 }
        val tenxFunction_v2 = { x: Int -> x * 10 } // Lambda.
        println(transformList_v2(numbers, tenxFunction_v2))

        val tenxNumbers = numbers.map({ x: Int -> x * 10 })
        val tenxNumbers_v2 = numbers.map { x: Int -> x * 10 } // When the last argument is a lambda.
        val tenxNumbers_v3 = numbers.map { x -> x * 10 } // Type inference.
        val tenxNumbers_v4 = numbers.map { it * 10 } // Only works for single argument lambdas.

        val adderFunction: (Int, Int) -> Int = { a, b -> a + b }

        /*
            Exercises:
            1. Write a function to combine all the elements of a list, using a combination function that you pass as argument.
                "reduce"
                reduce([1,2,3,4], +, 0) = 10
                reduce([1,2,3,4], a,b => a * b, 1) = 24
            2. Learn to use APIs that use lambdas - sort a list.
               Sort a list of Strings by their length.
         */
        println(reduce(numbers, 0) { a, b -> a + b })
        println(reduce(numbers, 1) { a, b -> a * b })
        println(reduce(listOf("I", "love", "Kotlin"), "") { a, b -> "$a $b" })

        println(reduceRec(numbers, 0) { a, b -> a + b })
        println(reduceRec(numbers, 1) { a, b -> a * b })
        println(reduceRec(listOf("I", "love", "Kotlin"), "") { a, b -> "$a $b" })

        val someStrings = listOf("I am learning about function values.", "I love Kotlin.", "I am writing a lot of code.")
        val sortedStrings = someStrings.sortedBy { it.length } // Ascending sort.
        println(sortedStrings)
    }
}