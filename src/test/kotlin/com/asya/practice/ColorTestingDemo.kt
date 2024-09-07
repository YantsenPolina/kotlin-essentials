package com.asya.practice

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import kotlin.test.*

class ColorTestingDemo {
    /*
        Unit testing - tests individual components.
        Integration testing - tests a combination/interaction between components.
            End-to-end testing.
     */

    private val black = Color(0, 0, 0)
    private val red = Color(255, 0, 0)
    private val green = Color(0, 255, 0)
    private val blue = Color(0, 0, 255)
    private val yellow = Color(255, 255, 0)

    // Initialize before every test.
    private lateinit var colors: List<Color>

    @BeforeEach
    fun individualSetup() { // Runs before every test.
        println("Individual setup...")
        colors = listOf(red, green, blue, yellow)
    }

    @AfterEach
    fun individualCleanup() { // Runs after every test.
        println("Individual test cleanup...")
        colors = listOf()
    }

    @Test
    fun testCombine() {
        println("Testing the + operator on colors.")
        assertTrue {
            red + green == yellow
        }

        assertEquals(red + green, yellow, "Operator + should combine color channels.")
        // assertSame(red + green, yellow) // Testing the same instance (reference equality).
        assertNotSame(red + green, yellow) // And other negative assertions: not contains...
        // assertContains (collection contains an element).
        // assertThrows (tests whether the code throws an expected exception).

        // Can fail the test.
        if (red + green != yellow) {
            fail("The operator + does not work! Or maybe something else...")
        }
    }

    @Test
    fun simpleTest() {
        println("Simple test.")
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun suiteSetup() { // Runs before any function in the suite.
            println("Suite setting up...")
        }

        @JvmStatic
        @AfterAll
        fun suiteCleanup() { // Runs after any function in the suite.
            println("Happily ever after.")
        }
    }
}