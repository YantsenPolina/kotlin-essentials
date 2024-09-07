package com.asya.oopfun

object DataClasses {
    class CityNaive(val name: String, val country: String, val population: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CityNaive

            if (name != other.name) return false
            if (country != other.country) return false
            if (population != other.population) return false

            return true
        }

        override fun hashCode(): Int {
            var result = name.hashCode()
            result = 31 * result + country.hashCode()
            result = 31 * result + population
            return result
        }

        override fun toString(): String {
            return "City($name, $country, $population)"
        }
    }

    // We have all the methods above automatically.
    // equals, hashCode, toString are made by the compiler.
    // Meant to be passed around and stored.
    // All the constructor arguments must be properties.
    // Must have at least one property.
    // Cannot inherit a data class (it is final by default).
    data class City(val name: String, val country: String, val population: Int) {
        // New properties and methods.
    }

    data object NoOperation

    sealed interface Message
    data class Join(val player: String): Message
    data class Ping(val from: String, val to: String): Message
    data class Exit(val player: String): Message
    data object TerminateGame: Message

    @JvmStatic
    fun main(args: Array<String>) {
        val massanassa = City("Massanassa", "Spain", 10000)
        val massanassa_v2 = City("Massanassa", "Spain", 10000)
        val grownMassanassa = massanassa.copy(population = 20000)

        println(massanassa)
        println(grownMassanassa)
        println(massanassa == massanassa_v2)

        val (name, country, population) = massanassa
        println("$name, $country, $population")
    }
}