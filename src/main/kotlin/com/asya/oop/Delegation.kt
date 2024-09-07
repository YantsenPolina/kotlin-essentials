package com.asya.oop

import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

object Delegation {
    interface TextTransformer {
        val id: String
        fun transform(text: String): String
        fun getDescription(): String
    }

    open class Translator(open val from: String, open val to: String): TextTransformer {
        override val id: String = "Translator $from -> $to"
        override fun getDescription(): String = "Translator $from -> $to"
        override fun transform(text: String): String =
            "[${getDescription()}] Translating from $from to $to: $text."
    }

    class QuickTranslator(override val from: String, override val to: String): Translator(from, to) {
        override fun getDescription(): String = "Quick Translator $from -> $to"
    }

    class GPT4: TextTransformer {
        override val id: String = "GPT-4"
        override fun getDescription(): String = "GPT-4"
        override fun transform(text: String): String =
            "[$id] Something an AI would say."
    }

    val transformer: TextTransformer = Translator("English", "Spanish")
    val transformedText = transformer.transform("This is a Kotlin lesson")

    // Composition vs Inheritance.
    // Decorator pattern.
    class TextProcessor(private val t: TextTransformer): TextTransformer {
        override val id: String = t.id
        override fun getDescription(): String = t.getDescription()
        override fun transform(text: String): String = t.transform(text) // Delegation to t.
    }
    // Example: Java Stream API - InputStream.
    val bufferedInputStream = DataInputStream(BufferedInputStream(FileInputStream(File("src/main/kotlin/com/asya/oop/Delegation.kt"))))

    val processor = TextProcessor(Translator("English", "Spanish"))
    val transformedText_v2 = processor.transform("This is a Kotlin lesson")

    class TextProcessor_v2(private val t: TextTransformer): TextTransformer by t { // Same as TextProcessor.
        // Can override any method.
        // override fun transform(text: String): String = "Grammar autocorrect for $text"
        // Be careful with overridden properties/methods.
        override fun getDescription(): String = "Grammar autocorrector"
    }

    val processor_v2 = TextProcessor_v2(Translator("English", "Spanish"))
    val transformedText_v3 = processor_v2.transform("This is a Kotlin lesson")


    @JvmStatic
    fun main(args: Array<String>) {
        println(transformedText)
        println(transformedText_v2)
        println(transformedText_v3)

        println(QuickTranslator("English", "Spanish").transform("This is a Kotlin lesson"))
    }
}