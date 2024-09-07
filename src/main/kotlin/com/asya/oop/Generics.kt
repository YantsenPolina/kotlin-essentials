package com.asya.oop

object Generics {
    // Goal is to reuse code/logic on many (potentially unrelated) types.

    interface MyIntList {
        fun head(): Int
        fun tail(): MyIntList
        // Many more methods.
        // fun add(element: Int): MyLinkedList
        // fun contains(element: Int): Boolean
    }

    class EmptyIntList: MyIntList {
        override fun head(): Int = throw NoSuchElementException()
        override fun tail(): MyIntList = throw NoSuchElementException()
        // override fun add(element: Int): MyLinkedList = NonEmptyList(element, this)
        // override fun contains(element: Int): Boolean = false
    }

    class NonEmptyIntList(private val h: Int, private val t: MyIntList): MyIntList {
        override fun head(): Int = h
        override fun tail(): MyIntList = t
    }

    // How to add a linked list of Strings?
    // Option 1 - duplicate the code for other types.
    interface MyStringList {
        fun head(): String
        fun tail(): MyStringList
        // Many more methods.
    }

    class EmptyStringList: MyStringList {
        override fun head(): String = throw NoSuchElementException()
        override fun tail(): MyStringList = throw NoSuchElementException()
    }

    class NonEmptyStringList(private val h: String, private val t: MyStringList): MyStringList {
        override fun head(): String = h
        override fun tail(): MyStringList = t
    }

    // Option 2 - change signatures to use Any.
    // Cons: can store String, Person, Int, Animal in the same list (lost type safety).
    interface MyGeneralList {
        fun head(): Any
        fun tail(): MyGeneralList
    }

    class EmptyGeneralList: MyGeneralList {
        override fun head(): Any = throw NoSuchElementException()
        override fun tail(): MyGeneralList = throw NoSuchElementException()
    }

    class NonGeneralList(private val h: Any, private val t: MyGeneralList): MyGeneralList {
        override fun head(): Any = h
        override fun tail(): MyGeneralList = t
    }

    // Generic type/type argument.
    // No code duplication.
    // Can support any type, even unrelated types.
    // Maintained type safety. Homogeneous lists. Correct type returned. Enforce correct types.
    interface MyLinkedList<A> {
        fun head(): A
        fun tail(): MyLinkedList<A>

        // Objects cannot have generic type arguments.
        companion object {
            fun <A> single(element: A): MyLinkedList<A> =
                NonEmptyList(element, EmptyList())
        }
    }

    class EmptyList<A>: MyLinkedList<A> {
        override fun head(): A = throw NoSuchElementException()
        override fun tail(): MyLinkedList<A> = throw NoSuchElementException()
    }

    class NonEmptyList<A>(private val h: A, private val t: MyLinkedList<A>): MyLinkedList<A> {
        override fun head(): A = h
        override fun tail(): MyLinkedList<A> = t
    }

    // Can specify multiple type arguments.
    interface MyMap<K, V>

    // Generic functions.
    fun <A> singleElement(element: A): MyLinkedList<A> =
        NonEmptyList(element, EmptyList())

    @JvmStatic
    fun main(args: Array<String>) {
        val simpleNumbers = NonEmptyIntList(1, NonEmptyIntList(2, NonEmptyIntList(3, NonEmptyIntList(4, EmptyIntList()))))

        val simpleNumbers_v2: MyLinkedList<Int> = NonEmptyList(1, NonEmptyList(2, NonEmptyList(3, NonEmptyList(4, EmptyList()))))
        val meaningOfLife = simpleNumbers_v2.head() + 41

        val simpleStrings = NonEmptyList("I", NonEmptyList("love", NonEmptyList("Kotlin", EmptyList())))
        val firstLength = simpleStrings.head().length

        val singleNumber = singleElement(42)
        val singleNumber_v2 = MyLinkedList.single(42)
    }
}