package com.asya.fp

object SAMInterfaces {
    fun runNTimes(n: Int, runnable: Runnable) {
        (1 .. n).forEach { _ ->
            runnable.run()
        }
    }

    fun runNTimesFunc(n: Int, func: () -> Unit) {
        (1 .. n).forEach { _ ->
            func()
        }
    }

    fun demoRunnable() {
        runNTimes(10, object: Runnable {
            override fun run() {
                println("This is a runnable.")
            }
        })

        runNTimesFunc(10) { println("This is a function.") }
    }

    // SAM = single abstract method.

    // Syntax sugar granted by Kotlin.
    fun demoRunnable_v2() {
        runNTimes(10) { println("This is a function.") }
        /*
            Rewritten by the compiler to
                runNTimes(10, object: Runnable {
                    override fun run() {
                        println("This is a runnable.")
                    }
                })
         */
        // Works automatically with SAM interfaces from Java.
    }

    fun interface Transformer { // SAM interface.
        // Can only have one abstract method.
        fun process(n: Int): Int
        // Compiler can replace a Transformer with (Int) -> Int.
    }

    fun processNTimes(seed: Int, n: Int, transformer: Transformer): Int =
        if (n <= 0) seed
        else processNTimes(transformer.process(seed), n - 1, transformer)

    @JvmStatic
    fun main(args: Array<String>) {
//        println(processNTimes(0, 10, object: Transformer {
//            override fun process(n: Int): Int = n + 1
//        }))
        println(processNTimes(0, 10) { it + 1 })
    }
}