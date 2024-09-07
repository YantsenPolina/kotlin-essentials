package com.asya.fp

object FunctionalCollections {
    fun concatenate(n: Int, s: String): String =
        if (n <= 0) ""
        else s + concatenate(n - 1, s)

    fun demoLists() {
        val numbers = listOf(1, 2, 3, 4, 5)
        val tenxNumbers = numbers.map { x -> x * 10 }
        val kotlinRepeated = numbers.map { x -> concatenate(x, "Kotlin") }

        val evenNumbers = numbers.filter { x -> x % 2 == 0 }

        evenNumbers.forEach { x ->
            println(x)
        }

        val expandedList = numbers.flatMap { x -> (1 .. x).toList() }
        println(expandedList)

        val numbersSum = numbers.fold(0) { a, b -> a + b }
        val numbersSum_v2 = numbers.reduce { a, b -> a + b }

        val firstEven = numbers.find { x -> x % 2 == 0 } // Returns Nullable.
        val evenPrefix = numbers.takeWhile { x -> x % 2 == 0 }
        val evenCount = numbers.count { x -> x % 2 == 0 }

        val stringRep = numbers.joinToString("|", "{", "}") { x -> x.toString() }
        println(stringRep)
    }

    fun demoSets() {
        val numbers = setOf(1, 2, 3, 4, 5)
        val lt10 = numbers.all { x -> x < 10 }
        val lt100 = numbers.none { x -> x >= 100 }
    }

    fun demoMaps() {
        val phonebook = mapOf(
            "Asya" to 1008,
            "Floki" to 604
        )

        val sectionA = phonebook.filterKeys { it.startsWith("A") }

        val addSuffix = phonebook.mapValues { pair -> pair.value * 10 }

        val phonebookWithDefault = phonebook.withDefault { invalidKey -> -9000 }
    }

    /**
     * Exercises:
     * 1. You have a list of strings -> return a list of those strings' length.
     *  ["kotlin", "is", "cool"] -> [6,2,4]
     * 2. You have two lists of numbers of the same size, return a sum of corresponding elements.
     *  [1,2,3,4], [5,6,7,8] -> [6, 8, 10, 12]
     *   Use the function .zip.
     * 3. Two lists of things, return all the combinations as strings, feel free to choose the format.
     *  [1,2,3], ["black", "white", "red", "blue"] -> ["1-black", "1-white", "1-red", "1-blue", ...]
     * 4. List of strings, return the concatenation of all the strings.
     *  ["kotlin", "is", "cool"] -> "kotliniscool"
     *  - reduce
     *  - fold
     * 5. Concatenate "kotlin" a number of times, by using just the standard library API, NOT concatenate(n, s).
     */
    fun exercises() {
        val strings = listOf("kotlin", "is", "cool")
        val stringsLengths = strings.map { it.length }
        println(stringsLengths)

        val numbers1 = listOf(1, 2, 3, 4)
        val numbers2 = listOf(5, 6, 7, 8)
        val sums = numbers1.zip(numbers2).map { it.first + it.second }
        val sums_v2 = numbers1.zip(numbers2) { a, b -> a + b}
        println(sums)
        println(sums_v2)

        val numbers = listOf(1, 2, 3)
        val colors = listOf("black", "white", "red", "blue")
        val combinations = numbers.flatMap { number -> colors.map { color -> "$number-$color" } }
        println(combinations)

        val statement = strings.joinToString("")
        println(statement)

        val statementReduce = strings.reduce { a, b -> a + b  }
        val statementFold = strings.fold("") { a, b -> a + b  }
        println(statementReduce)
        println(statementFold)

        val kotlinx5 = (1 .. 5).joinToString("") { _ -> "kotlin"}
        val kotlinx5_v2 = (1 .. 5).toList().map { x -> "kotlin"}.reduce { a, b -> a + b  }
        println(kotlinx5)
        println(kotlinx5_v2)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        exercises()
    }
}