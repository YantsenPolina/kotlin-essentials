package com.asya.oop

// Object in Kotlin = definition of a type + the only instance of that type.
// Singleton pattern.

object MySingleton {
    // Can add properties and methods.
    val aProperty = 42

    fun aMethod(arg: Int): Int {
        println("Hello from singleton: $arg.")
        return aProperty + arg
    }

    // Define entry points to a Kotlin application.
    // public static void main(String[] args) == equivalent Java syntax.
    @JvmStatic
    fun main(args: Array<String>) {
        println("Singleton entry point.")
    }
}

object ObjectsCompanions {
    class Guitar(val nStrings: Int, val type: String) {
        // Properties.
        // Methods.
        fun play() {
            println("$type guitar with $nStrings strings is playing.")
        }

        companion object {
            // Properties and methods specific to the type.
            val HAS_STRINGS = true

            fun createSimpleGuitar(type: String): Guitar = Guitar(6, type)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val gibson = Guitar(6, "electric")
        gibson.play()

        val guitarsHaveStrings = Guitar.HAS_STRINGS
        val simpleGuitar = Guitar.createSimpleGuitar("acoustic")
    }
}

fun main() {
    val theSingleton = MySingleton
    val anotherSingleton = MySingleton

    val singletonProperty = MySingleton.aProperty
    println(theSingleton == anotherSingleton)
    val result = MySingleton.aMethod(89)
    println("Result of singleton method: $result.")
}