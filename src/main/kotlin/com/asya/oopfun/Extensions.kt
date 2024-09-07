package com.asya.oopfun

object Extensions {
    // Can add new methods and properties to existing types.
    // Extension method.
    fun Int.multiply(aString: String): String {
        var result = ""
        for (i in 1 .. this)
            result += aString
        return result
    }

    // Extension properties.
    val Int.nDigits: Int // Cannot have a backing field.
        get() {
            var result = 0
            var theNumber = this
            while(theNumber != 0) {
                theNumber /= 10
                result++
            }
            return result
        }

    class Person(val name: String, private val favouriteLanguage: String = "Kotlin") {
        fun greet() = "Hi everyone, I am $name."
    }

    // Can be shadowed (have the same signature) as a real method from the class.
    // In this case the real method is called.
    fun Person.greet() =
        "$name says: I hate everyone!"
    // Person is a receiver type.
    // Compiler creates new synthetic function (hidden).
    // fun greet($this: Person): String = ...

    // In the implementation of extension method you can only access public properties-methods of `this`.

    @JvmStatic
    fun main(args: Array<String>) {
        val kotlinx3 = 3.multiply("Kotlin")
        println(kotlinx3)
        println(123456.nDigits)

        val obiwan = Person("Obi-Wan Kenobi")
        println(obiwan.greet())
    }
}