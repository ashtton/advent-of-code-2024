package days

import Challenge

fun main() {
    DayThreePartTwo().execute()
}

class DayThreePartOne : Challenge(2024, 3, sample = false) {

    val goodString = "mul(".split("")
    val nums = mutableListOf<Int>()

    override fun readLine(str: String) {
        var builder = ""
        var numberOneString = ""
        var numberTwoString = ""

        var comma = false

        str.split("").forEach { s ->
            if (s.isEmpty()) {
                comma = false
                numberOneString = ""
                numberTwoString = ""
                builder = ""
                return@forEach
            }

            builder += s

            if (builder.length <= 4) {
                if (s != goodString[builder.length]) {
                    builder = s
                }
                return@forEach
            }

            if (s == ",") {
                comma = true
                return@forEach
            }

            if (s == ")") {
                val numOne = numberOneString.toIntOrNull()
                val numTwo = numberTwoString.toIntOrNull()

                comma = false
                numberOneString = ""
                numberTwoString = ""
                builder = ""


                if (numOne == null || numTwo == null) {
                    return@forEach
                }

                nums.add(numOne * numTwo)
                return@forEach
            }


            if (comma) {
                numberOneString += s
            } else {
                numberTwoString += s
            }

            if ((numberOneString.isNotEmpty() && numberOneString.toIntOrNull() == null) ||
                (numberTwoString.isNotEmpty() && numberTwoString.toIntOrNull() == null)) {
                comma = false
                numberOneString = ""
                numberTwoString = ""
                builder = s
            }
        }

    }

    override fun finish() {
        println(nums.sum())
    }
}

class DayThreePartTwo : Challenge(2024, 3, sample = false) {

    val mulString = "mul(".split("")

    val doString = "do()"
    val dontString = "don't()"

    val nums = mutableListOf<Int>()

    var builder = ""
    var numberOneString = ""
    var numberTwoString = ""

    var comma = false
    var enabled = true

    override fun readLine(str: String) {
        str.split("").forEach { s ->
            if (s.isEmpty()) {
                return@forEach
            }

            builder += s

            if (builder.startsWith("d")) {
                if (builder == doString) {
                    enabled = true
                    builder = ""
                    return@forEach
                }

                if (builder == dontString) {
                    enabled = false
                    builder = ""
                    return@forEach
                }

                if (!dontString.startsWith(builder) && !doString.startsWith(builder)) {
                    builder = s
                }

                return@forEach
            }

            if (builder.startsWith("m")) {
                if (builder.length <= 4) {
                    if (s != mulString[builder.length]) {
                        builder = s
                    }
                    return@forEach
                }

                if (s == ",") {
                    comma = true
                    return@forEach
                }

                if (s == ")") {
                    val numOne = numberOneString.toIntOrNull()
                    val numTwo = numberTwoString.toIntOrNull()

                    comma = false
                    numberOneString = ""
                    numberTwoString = ""
                    builder = ""

                    if (numOne == null || numTwo == null) {
                        return@forEach
                    }

                    if (enabled) {
                        nums.add(numOne * numTwo)
                    }
                    return@forEach
                }


                if (comma) {
                    numberOneString += s
                } else {
                    numberTwoString += s
                }

                if ((numberOneString.isNotEmpty() && numberOneString.toIntOrNull() == null) ||
                    (numberTwoString.isNotEmpty() && numberTwoString.toIntOrNull() == null)) {
                    comma = false
                    numberOneString = ""
                    numberTwoString = ""
                    builder = s
                }

                return@forEach
            }

            builder = s
        }

    }

    override fun finish() {
        println(nums.sum())
    }
}