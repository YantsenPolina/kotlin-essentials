package com.asya.oop

object Inheritance {
    open class Animal { // Class can be inherited.
        open fun eat() { // Can be overridden in child classes.
            println("I am eating...")
        }
    }

    class Dog: Animal() {
        // Dog "extends"/inherits from Animal
        // Dog is a subtype of Animal.
        // Dog is an Animal.
        // Method eat() is available.

        // Override = change the behavior of the method.
        override fun eat() {
            println("I am a dog, I chew things.")
        }
    }

    val busya = Dog()
    val anAnimal: Animal = Dog() // Subtype polymorphism.

    // Need to provide the constructor for the parent class.
    open class Person(open val name: String, open val age: Int)
    class Adult(override val name: String, override val age: Int, idCard: String): Person(name, age)

    // Restrict inheritance with the final keyword.
    open class Travel(val destination: String) {
        final fun confirmTravel(): String = "Congratulations! You are going to $destination."
        // final = cannot be overridden.
    }

    open class Leisure {
        open fun confirmExperience(): String = "Confirmed."
    }

    open class Travel_v2(val destination: String): Leisure() {
        final override fun confirmExperience(): String =
            "Congratulations! You are going to $destination."
    }

    class SpecialTickets: Travel_v2("Alicante") {
        // Overriding stopped at Travel_v2.
//        override fun confirmExperience(): String =
//            "Listening to Zemfira."
    }

    // Sealing a type hierarchy = restricts inheritance to this file only.
    sealed class ProtocolMessage(contents: String) // Automatically open.
    class BeginningExchange(flag: String, contents: String): ProtocolMessage(contents)
    class Exchange(sender: String, receiver: String, contents: String): ProtocolMessage(contents)
    object EndExchange: ProtocolMessage("")
    // No other subtypes of the ProtocolMessage may exist outside this file.

    /*
        Any is a parent for the all possible types.

        Any     -> Any?
        Animal  -> Animal?
        Dog     -> Dog?
        Nothing -> Nothing?
     */

    // val nothing: Nothing = throw RuntimeException("Nothing.")


    @JvmStatic
    fun main(args: Array<String>) {
        busya.eat()
        anAnimal.eat()
    }
}