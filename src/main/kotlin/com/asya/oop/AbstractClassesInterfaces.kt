package com.asya.oop

object AbstractClassesInterfaces {
    open class Animal

    // Abstract classes/properties/methods are automatically open.
    abstract class Plant(scientificName: String) { // Class with possibly abstract properties/methods.
        abstract val maxHeight: Int // Property with no value.
        abstract fun grow(): String // Method signature with no implementation.
        // Can define regular properties/methods.
        val growthMechanism: String = "Photosynthesis."
    }

    // Abstract classes cannot be instantiated.
    // val myPlant = Plant("rosa")

    class Strawberry: Plant("fragaria") {
        // Implement all abstract properties/methods.
        override val maxHeight: Int = 100
        override fun grow(): String = "Nice and tasty strawberries."
    }

    // Interface = ultimate abstract type.
    // May not have constructor arguments.
    interface Carnivore {
        // Can define properties/methods - automatically abstract and open.
        fun eat(animal: Animal): String
        // May only provide an implementation if the property has no backing field, and with get()/set() only.
        val preferredMeal: String

        // Can provide concrete properties/methods = default implementations.
    }

    interface Herbivore {
        fun eat(plant: Plant): String
    }

    // Inheritance model in Kotlin: extend one class, but maybe multiple interfaces.
    class Crocodile: Animal(), Carnivore {
        // If you inherit from class + interface(s), then the first thing you need to specify is the class.
        override val preferredMeal: String = "Gazelle."
        override fun eat(animal: Animal): String = "I am eating this animal."
    }

    class Human: Carnivore, Herbivore {
        override val preferredMeal: String = "Sugar."
        override fun eat(plant: Plant): String = "I am eating this plant."
        override fun eat(animal: Animal): String = "I am eating this meat."
    }

    // An interface can extend another interface.
    interface Omnivore: Carnivore, Herbivore
    // A class can extend just an interface.
    abstract class Human_v2: Omnivore

    // What if two interfaces have the same method?
    interface Instrument {
        fun play(): String
    }

    interface Game {
        fun play(): String
    }

    class GuitarApp: Instrument, Game {
        override fun play(): String = "Both an instrument and a game."
    }

    @JvmStatic
    fun main(args: Array<String>) {

    }
}