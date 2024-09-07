package com.asya.oopfun

object AnonymousClasses {
    abstract class Plant {
        abstract fun grow(): String
    }

    class Rose(val color: String): Plant() {
        override fun grow(): String =
            "Nice flowers of color $color."
    }

    class Pepper(val spicyFactor: Int): Plant() {
        override fun grow(): String =
            "Peppers that are spicy $spicyFactor/10."
    }

    // Only relevant here.
    // Only need one instance.
//    object WeirdPlant: Plant() {
//        override fun grow(): String =
//            "Weird flowers that no one has seen before."
//    }
//    val weirdPlant = WeirdPlant

    // Anonymous class.
    val weirdPlant = object:Plant() {
        override fun grow(): String =
            "Weird flowers that no one has seen before."
    }

    val myRose = Rose("red")
    val pepper = Pepper(7)

    val myDatabase = listOf(myRose, pepper, weirdPlant)

    open class Instructor(val type: String) {
        open fun encourage(name: String) =
            "Come on, $name, you can do it!"
    }

    val jocko = object:Instructor("SEAL") {
        override fun encourage(name: String): String =
            "DO IT OR YOU ARE DEAD!"
    }

    @JvmStatic
    fun main(args: Array<String>) {

    }
}