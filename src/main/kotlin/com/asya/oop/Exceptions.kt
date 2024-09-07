package com.asya.oop

import kotlin.jvm.Throws

object Exceptions {
    fun maybeString(): String? = null

    fun demoExceptions() {
        // val division = 42 / 0 // ArithmeticException.

        val nullable: String? = maybeString()
        val theString = nullable!! // NullPointerException.
    }

    class Person private constructor(val name: String, val age: Int) {
        companion object {
            @Throws(IllegalArgumentException::class) // Can specify exceptions thrown, just a hint.
            fun create(name: String, age: Int): Person {
                if (age < 0) throw IllegalArgumentException("Age must be non-negative.")
                return Person(name, age)
            }
        }
    }

    // Put the most specific exceptions first.
    fun demoCatch() {
        val maybePerson: Person = try {
            Person.create("Asya", -10)
        } catch (e: IllegalArgumentException) {
            Person.create("Asya", 0)
        } catch (e: RuntimeException) {
            Person.create("Asya", 16)
        } finally {
            // Runs no matter what.
            // Release of resources.
            println("Something need to be released no matter what.")
        }

        val asyaAge = maybePerson.age
        println(asyaAge)
    }

    /*
        Throwable
            Exception - something wrong with the code, we can control.
            IOException, FileNotFoundException, ... (checked exceptions)
                RuntimeException (unchecked exceptions)
                    NullPointerException, IllegalArgumentException
            Error - something wrong with the JVM.
                StackOverflowError
                OutOfMemoryError

        In Kotlin all exceptions are "unchecked".
     */

    class MyException(val count: Int): RuntimeException("Something wrong.") {
        // Properties/methods.
    }

    fun demoMyException(): String =
        throw MyException(4)

    @JvmStatic
    fun main(args: Array<String>) {
        demoCatch()
    }
}