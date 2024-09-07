package com.asya.oop

object AccessModifiers {
    open class Person(open val name: String) {
        // Protected = accessible inside this class and all child types.
        protected fun sayHi() = "Hi, I am $name."

        // Private properties/methods can only be accessed inside this body.
        private fun watchNetflix(): String = "I am watching Netflix."

        // No modifier = public.

        // Property/method accessible inside the same compilation module.
        // Useful for libraries.
        internal val thing = 42
    }

    class Kid(override val name: String, age: Int): Person(name) {
        fun greetPolitely(): String =
            sayHi() + " I love to play!"
    }

    class KidWithParents(override val name: String, age: Int, val mom: Person, val dad: Person): Person(name) {
        // sayHi() is protected, so I should be able to access it.
        // But only on this instance, not on other instances.
        fun everyoneIntroduceThemselves(): String =
            // "${this.sayHi()}. Here are my parents! ${mom.sayHi()} ${dad.sayHi()}."
            "${this.sayHi()}. Here are my parents! ${mom.name} ${dad.name}."
    }

    class MyService private constructor(url: String) {
        companion object {
            fun local(): MyService =
                MyService("127.0.0.1")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val svetlana = Person("Svetlana")
        val polina = Person("Polina")
        val asya = KidWithParents("Asya", 16, svetlana, polina)

        // println(aPerson.sayHi()) // Is inaccessible.
        // val myService = MyService("127.0.0.1") // Cannot build directly.
        val myService = MyService.local()
    }
}