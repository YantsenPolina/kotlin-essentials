package com.asya.oop

class Person(val firstName: String, val lastName: String, age: Int) { // Primary constructor.
    // Can define properties (data = values, variables) and methods(behavior = functions).
    val fullName = "$firstName $lastName" // Property.

    var favouriteMovie: String = "As Good As It Gets"
        // No backing field = no memory zone for this variable.
        get() = field
        set(value: String) {
            // Run any code here.
            println("Setting the value of the favourite movie to $value...")
            field = value
        }

    /*
        Properties with get() and/or set(value) may or may not have backing fields (= memory zones for them).
        Create a backing field simply by using `field` in the implementation of get() or set().
        The compiler detects if you have a backing field or not.
        - If you have a backing field, you must initialize the property.
        - If you don't have a backing field, you cannot initialize the property.
     */

    lateinit var favouriteLanguage: String
    fun initializeFavouriteLanguage() {
        if (!this::favouriteLanguage.isInitialized)
            favouriteLanguage = "Kotlin"
    }

    // Can run multiple init blocks, they run one after another in the order they are defined in the class.
    init {
        // Run arbitrary code when this class is being instantiated.
        println("Initializing a Person with the name $firstName $lastName...")
    }

    init {
        // Run other arbitrary code when this class is being instantiated.
        println("Some other arbitrary code.")
    }

    fun greet() =
        "Hi everyone, my name is $firstName." // Method.

    // Overloading = multiple methods with the same name and different signatures.
    fun greet(firstName: String): String =
        "Hi $firstName, my name is ${this.firstName}, how do you do?"

    // Secondary (overloaded) constructors.
    // Must always invoke another constructor.
    constructor(firstName: String, lastName: String): this(firstName, lastName, 0)
    constructor(): this("Floki", "Janzen")

    // Immutable = data cannot be changed, must create another instance.
    // Mutable = data can be changed without allocating another instance.
}

fun main() {
    val asya = Person("Asya", "Janzen", 16) // Constructed/instantiated a Person instance.
    val asyaFullName = asya.fullName
    println(asyaFullName)
    println(asya.greet())
    println(asya.greet("Polina"))

    val floki = Person()
    println(floki.fullName)

    println(asya.favouriteMovie) // Calling get() method on the favouriteMovie property.
    asya.favouriteMovie = "The Lord Of The Rings" // Calling set("The Lord Of The Rings") method on the favouriteMovie property.
    println(asya.favouriteMovie)
}