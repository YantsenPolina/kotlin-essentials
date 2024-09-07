package com.asya.oopfun

object Enums {
    // Enumeration.
    sealed interface PermissionNaive {
        companion object {
            data object READ: PermissionNaive
            data object WRITE: PermissionNaive
            data object EXECUTE: PermissionNaive
            data object NONE: PermissionNaive
        }
    }

    // final.
    enum class Permission {
        READ, WRITE, EXECUTE, NONE;

        // Properties and methods.
        val bitSize = 4

        fun openDocument() {
            if (this == READ) println("Opening document...")
            else println("Reading is not allowed.")
        }
    }

    enum class PermissionBitMask(bits: Int) {
        READ(4), // 0100
        WRITE(2), // 0010
        EXECUTE(1), // 0001
        NONE(0) // 0000
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val readPermission = Permission.READ
        val readPermission_v2 = Permission.READ
        println(readPermission)
        println(readPermission == readPermission_v2)
        println(Permission.valueOf("READ") == readPermission)
        println(Permission.entries)
        println(readPermission.ordinal)
        println(readPermission.bitSize)
        readPermission.openDocument()
    }
}