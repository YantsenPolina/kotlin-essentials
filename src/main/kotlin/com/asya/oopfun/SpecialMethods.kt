package com.asya.oopfun

object SpecialMethods {
    class Person(val name: String, val age: Int) {
        override fun equals(other: Any?): Boolean = when(other) {
            is Person -> name == other.name && age == other.age
            else -> false
        }

        // A "unique" number for this instance.
        // Two "equal" instances should produce the same hashCode.
        // Used in hash-based data structures (sets, maps).
        override fun hashCode(): Int =
            name.hashCode() * 31 + age

        override fun toString(): String =
            "Person($name, $age)"

        // Infix methods - syntax sugar.
        // Infix methods only work for methods with one argument.
        infix fun likes(movie: String) =
            "$name says: I love $movie!"

        /*
            this < another => this.compareTo(another) < 0
            this > another => this.compareTo(another) > 0

            If this.equals(another), then ,ake sure that this.compareTo(another) == 0
         */
        operator fun compareTo(another: Person): Int =
            this.age - another.age
    }

    class ComplexNumber(var x: Double, var y: Double) {
        // Operator overloading.
        // plus, minus, times, div, rem (%).
        operator fun plus(other: ComplexNumber) =
            ComplexNumber(x + other.x, y + other.y)

        operator fun plus(number: Double) =
            ComplexNumber(x + number, y)

        // Can overload compound operators.
        // Must return Unit.
        operator fun plusAssign(number: Double): Unit {
            x += number
        }

        operator fun inc(): ComplexNumber = ComplexNumber(x + 1, y)

        operator fun unaryMinus(): ComplexNumber = ComplexNumber(-x, -y)

        operator fun get(index: Int): Double =
            when(index) {
                0 -> x
                1 -> y
                else -> throw IllegalArgumentException("Complex numbers only have 2 fields.")
            }

        operator fun get(part: String): Double =
            when(part) {
                "real" -> x
                "imaginary" -> y
                else -> throw IllegalArgumentException("Invalid field.")
            }

        operator fun set(index: Int, value: Double): Unit {
            when(index) {
                0 -> x = value
                1 -> y = value
                else -> throw IllegalArgumentException("Complex numbers only have 2 fields.")
            }
        }

        operator fun contains(value: Double): Boolean =
            x == value || y == value

        operator fun component1() = x
        operator fun component2() = y
        operator fun component3() = Math.sqrt(x * x + y * y)

        operator fun invoke(origin: ComplexNumber): ComplexNumber =
            ComplexNumber(x - origin.x, y - origin.y)

        override fun toString(): String =
            "($x, $y)"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val asya1 = Person("Asya", 16)
        val asya2 = Person("Asya", 16)
        val floki = Person("Floki", 1)
        println(asya1 == asya2) // person1.equals(person2)
        println(asya1 === asya2) // Reference equality.
        println(asya1)
        println(asya1.likes("Forrest Gump"))
        println(asya1 likes "Forrest Gump") // likes method is in infix position.
        println(floki < asya1)

        val complexNumber1 = ComplexNumber(1.2, 2.6)
        val complexNumber2 = ComplexNumber(0.6, 2.9)
        println(complexNumber1.plus(complexNumber2))
        println(complexNumber1 + complexNumber2)
        println(complexNumber1 + 6.7)
        complexNumber1 += 8.9
        println(-complexNumber1)
        println(complexNumber1[0])
        println(complexNumber1["real"])
        complexNumber1[1] = 4.3
        println(2.0 in complexNumber1)
        val (x, y, l) = complexNumber1
        println(x)
        println(y)
        println(l)
        val relative = complexNumber1(complexNumber2)
    }
}