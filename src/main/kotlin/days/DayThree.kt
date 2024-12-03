package days

import Challenge

fun main() {
    DayThree().execute()
}

class DayThree : Challenge(2024, 3, sample = true) {


    val goodString = "mul(".split("")
    val nums = mutableListOf<Int>()

    var numberOneString = ""
    var numberTwoString = ""

    override fun readLine(str: String) {
        var builder = ""
        numberOneString = ""
        numberTwoString = ""



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
            println(builder)

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
                comma = false
                numberOneString = ""
                numberTwoString = ""
                builder = ""

                val numOne = numberOneString.toIntOrNull()
                val numTwo = numberTwoString.toIntOrNull()

                println("num: $numberOneString, numTwo: $numberTwoString, comma: $comma")

                if (numOne == null || numTwo == null) {
                    return@forEach
                }

                nums.add(numberOneString.toInt() * numberTwoString.toInt())
                return@forEach
            }


            if (comma) {
                numberOneString += s
                println(numberOneString)

            } else {
                numberTwoString += s
            }

            if ((numberOneString.isNotEmpty() && numberOneString.toIntOrNull() == null) ||
                (numberTwoString.isNotEmpty() && numberTwoString.toIntOrNull() == null)) {
                comma = false
                numberOneString = ""
                numberTwoString = ""
                builder = s
                return@forEach
            }



        }

    }

    override fun finish() {
        println(nums.sum())
    }
}