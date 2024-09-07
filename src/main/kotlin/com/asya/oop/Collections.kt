package com.asya.oop

object Collections {
    fun demoLists() {
        val aList: List<Int> = listOf(1, 2, 3) // Immutable list.
        val thirdElement: Int = aList.get(2) // 0-index.
        val thirdElement_v2: Int = aList[2] // C-style indexing syntax.
        val length = aList.size

        val find3 = aList.indexOf(3) // Index of 3, otherwise -1 if 3 does not exist in the list.
        val subList = aList.subList(1, 2) // From (inclusive), to (exclusive).
        val has3 = aList.contains(3)
        val with4 = aList.plus(4) // New list.

        val mutableList = mutableListOf(1, 2, 3)
        mutableList.add(0, 42)
        mutableList.removeAt(0)
        mutableList.set(1, 56)
        mutableList[1] = 56 // C-style syntax.
        println(mutableList)

        val uniqueItems = aList.toSet()
    }

    // Arrays - map to JVM arrays, which map to OS-level arrays (very fast).
    // Always mutable.
    fun demoArrays() {
        val anArray = arrayOf(1, 2, 3, 4)
        val secondItem = anArray[1]
        anArray[1] = 100
        val length = anArray.size

        for (element in anArray) {
            println(element)
        }
    }

    // The order of elements is not enforced.
    fun demoSets() {
        val aSet = setOf(1, 2, 3, 4, 1, 2, 3) // Will only contain 1, 2, 3, 4.
        val contains1 = aSet.contains(1)
        val contains1_v2 = 1 in aSet // Kotlin-specific syntax.

        val add7 = aSet.plus(7) // Another Set with 7 included.
        val add7_v2 = aSet + 7 // Syntax sugar.
        val without3 = aSet.minus(3)
        val without3_v2 = aSet - 3
        val combined = aSet.plus(setOf(7, 8, 9)) // Unions two sets.
        val diff = aSet.minus(setOf(3, 4, 5))
        val intersect = aSet.intersect(setOf(3, 4, 5))

        val mutableSet = mutableSetOf(1, 2, 3, 4, 1, 2, 3, 5, 6)
        mutableSet.add(9)
        mutableSet.remove(4)
        println(mutableSet)

        val numbers = aSet.toList()
    }

    fun demoMaps() {
        val phonebook = mapOf(
            Pair("Asya", 1008),
            "Floki" to 406 // Syntax sugar.
        )
        val asyaNumber = phonebook.get("Asya")
        val asyaNumber_v2 = phonebook["Asya"] // Will crash if the key does not exist.
        val hasAsya = phonebook.contains("Asya")

        val newMap = phonebook.plus("Amy" to 3006)
        val withoutAmy = phonebook.minus("Amy")
        val pairs = phonebook.toList()
        val pairs_v2 = listOf(Pair("Asya", 1008), "Floki" to 406)
        val phonebook_v2 = pairs_v2.toMap() // Only for lists of pairs.

        val mutablePhonebook = mutableMapOf(
            Pair("Asya", 1008),
            "Floki" to 406
        )
        mutablePhonebook.remove("Floki")
        mutablePhonebook.put("Floki", 406)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        demoLists()
        demoArrays()
        demoSets()
    }
}