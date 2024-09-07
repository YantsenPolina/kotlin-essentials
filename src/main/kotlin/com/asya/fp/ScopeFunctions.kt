package com.asya.fp

object ScopeFunctions {
    fun obtainExternalList() = listOf(1, 2, 3, 4, 5)

    fun demoLet() {
        val numbers = obtainExternalList()
        val tenxNumbers = numbers.map { n -> n * 10 }
        println("The 10x values are $tenxNumbers.")
        val sumNumbers = numbers.reduce {  a, b -> a + b }
        println("The sum of 10x numbers are $sumNumbers.")

        // let allows to use the old computation for the next step.
        obtainExternalList().let { numbers -> // let is a scope function.
            val tenxNumbers = numbers.map { n -> n * 10 }
            println("The 10x values are $tenxNumbers.")
            val sumNumbers = numbers.reduce {  a, b -> a + b }
            println("The sum of 10x numbers are $sumNumbers.")
        }

        val result = obtainExternalList()
            .filter { n -> n % 2 == 0 }
            .map { n -> n * 10 }
            .let { list -> list.sum() / (list.size + 1) }

        val step1 = obtainExternalList()
        val step2 = step1.filter { n -> n % 2 == 0 }
        val step3 = step2.map { n -> n * 10 }
        val step4 = step3.sum() / (step3.size + 1)
    }

    // run is the same as let, but has access to the internal scope of the object on which you are running.
    data class Person(var name: String, var age: Int)
    fun demoRun() {
        val masterYoda = Person("Master Yoda", 800)
        masterYoda.age = 850
        masterYoda.name = "Yoda the Master Jedi"
        val result = "${masterYoda.name} (${masterYoda.age})"

        val result_v2 = masterYoda.run {
            age = 850
            name = "Yoda the Master Jedi"
            "$name ($age)"
        }

        println(result)
        println(result_v2)
    }

    // with is similar to run, but it is not an extension method.
    data class GamingChannel(val playerA: String, val playerB: String, var open: Boolean) {
        fun msg(content: String) = println("[$playerA][to $playerB] $content")
    }
    fun demoWith() {
        val channel = GamingChannel("Asya", "Floki", true)
        channel.msg("Build up your forces!")
        channel.msg("Attach here!")
        channel.open = false

        // Useful when we use resources.
        with(channel) {
            msg("Build up your forces!")
            msg("Attach here!")
            open = false
        }
    }

    // Apply is the same as run, but returns the object that you are processing.
    fun demoApply() {
        val asya = Person("Asya", 16)
        asya.name = "Asya the Best Cat"
        asya.age = 17

        val modifiedAsya = asya.apply {
            name = "Asya the Best Cat"
            age = 17
        }

        println(modifiedAsya == asya) // Same instance.
    }

    // also is the same as let, but the lambda does not return anything, the expressions returns the same instance.
    // Useful for side effects.
    fun demoAlso() {
        obtainExternalList()
            .filter { it % 2 == 0 }
            .map { it % 10 }
            .also { list -> println(list.sum() / (list.size + 1)) }
            .forEach { println(it) }
    }

    // Context object = the instance subject to the scope function.

    @JvmStatic
    fun main(args: Array<String>) {
        demoRun()
    }
}