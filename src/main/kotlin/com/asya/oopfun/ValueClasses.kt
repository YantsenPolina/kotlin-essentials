package com.asya.oopfun

object ValueClasses {
    // Value class.
    // Memory overhead.
    // @JvmInline value classes do not do any boxing.
    // Necessary if the compile target is the JVM.
    @JvmInline value class ProductName(val name: String)
    @JvmInline value class ProductDescription(val description: String)

    data class Product(val name: ProductName, val description: ProductDescription)

    val kotlinCourse = Product(
        ProductName("Kotlin Essentials"),
        ProductDescription("Learn Kotlin for any kind of platform, including Android, JVM, and web!")
        // Other properties.
    )

    @JvmStatic
    fun main(args: Array<String>) {
        println(kotlinCourse)
    }
}