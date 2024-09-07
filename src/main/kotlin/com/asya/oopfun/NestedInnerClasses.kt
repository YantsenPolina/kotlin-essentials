package com.asya.oopfun

object NestedInnerClasses {
    class Outer {
        val aProperty = 4

        // Nested class == "static" class.
        // Depends on the Outer type.
        // No connection with any instance of Outer.
        class Nested {
            val nestedProperty = 42
            // val summedProperty = aProperty + 10
        }

        // Depends on the current instance that created Inner.
        inner class Inner {
            val innerProperty = aProperty + 10
            val outerInstance = this@Outer // Qualified this.
        }
    }

    fun demoClasses() {
        val nested = Outer.Nested()
        println(nested.nestedProperty)

        val outerInstance = Outer()
        val inner = outerInstance.Inner()
        println(inner.innerProperty)
    }

    // Nested classes are useful when the nested types are conceptually tied to the wrapping (outer) type,
    // and they are relevant for the definition of a particular service.
    interface MyProtocol {
        sealed class Message
        data class Start(val nPlayers: Int): Message()
        data class GameEvent(val type: String, val playerId: String): Message()
    }

    // Inner classes are useful when the types are tied to the implementation of the wrapping instance.
    class MyPermissionsService {
        // Only relevant in this instance.
        open inner class Role(name: String)
        inner class Admin: Role("ADMIN")
        inner class Moderator: Role("MODERATOR")
        inner class Player: Role("PLAYER")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        demoClasses()
    }
}