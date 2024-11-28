package example

import Challenge

fun main() {
    Example()
}

/**
 * Example for aoc day 2 of 2023
 */
class Example : Challenge(2023, 2) {

    val allowedRed = 12
    val allowedGreen = 13
    val allowedBlue = 14

    var sum = 0

    override fun readLine(line: String) {
        val (gameIdStr, payload) = line.split(":")

        var maxRed = 0
        var maxGreen = 0
        var maxBlue = 0

        val gameId = gameIdStr.split(" ")[1].toInt()
        var failed = false

        payload.split(";").forEach { load ->
            val inputs = load.split(",")

            inputs.forEach { input ->
                val (valueStr, color) = input.trim().split(" ")

                val value = valueStr.toInt()
                
                when (color) {
                    "red" -> {
                        if (value > allowedRed) {
                            failed = true
                        }

                        if (value > maxRed) {
                            maxRed = value
                        }
                    }

                    "green" -> {
                        if (value > allowedGreen) {
                            failed = true
                        }

                        if (value > maxGreen) {
                            maxGreen = value
                        }
                    }

                    "blue" -> {
                        if (value > allowedBlue) {
                            failed = true
                        }

                        if (value > maxBlue) {
                            maxBlue = value

                        }
                    }
                }
            }
        }
        
        sum += maxRed * maxGreen * maxBlue
    }

    override fun finish() {
        println(sum)
    }

}