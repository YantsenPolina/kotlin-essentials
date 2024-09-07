package com.asya.oop

object Nullables {
    class Developer(val name: String, val favouriteLanguage: String) {
        fun writeCode(code: String = "") {
            println("$name is writing code in $favouriteLanguage: $code.")
        }
    }

    // val polina: Developer = null // This is not possible in Kotlin.
    val maybeDeveloper: Developer? = null // This is possible. Nullable type.

    fun createDeveloper(name: String): Developer? =
        if (name.isNotEmpty()) Developer(name, "Kotlin")
        else null

    val maybeDeveloper_v2: Developer? = createDeveloper("Master Yoda")
    // Once you have a nullable, you cannot access properties or methods.
    // val masterYoda = maybeDeveloper_v2.name

    fun makeDeveloperWriteCode(developer: Developer?, code: String) =
        // On this branch the compiler knows that the value is not null, so you can use properties/methods.
        if (developer != null) developer.writeCode(code) // "Flow" typing.
        else println("Error: developer is null.")

    // If you know, that a nullable is not null, then you can force the value to be a concrete type.
    val masterYoda = maybeDeveloper_v2!! // The type is now concrete.
    // If you are wrong, it will crash with NullPointerException.

    // Safe call operator.
    val maybeName: String? = maybeDeveloper?.name

    // Elvis operator.
    val definitiveDeveloper: Developer = maybeDeveloper ?: Developer("Asya", "Scala")

    fun makeDeveloperWriteCode_v2(developer: Developer?, code: String) =
        developer?.writeCode(code) ?: println("Error: developer is null.")

    // Safe casting.
    val something: Any = 42
    val anInt = something as Int // Crashes if you are wrong.
    val maybeInt = something as? Int

    @JvmStatic
    fun main(args: Array<String>) {
        makeDeveloperWriteCode(maybeDeveloper_v2, "val x = 42")
        makeDeveloperWriteCode(null, "val x = 42")
        masterYoda.writeCode("Some code in Kotlin")
        maybeDeveloper_v2!!.writeCode("Some code")
        maybeDeveloper_v2?.writeCode("Some code")
    }
}