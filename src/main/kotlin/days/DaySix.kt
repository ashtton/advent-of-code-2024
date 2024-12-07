package days

import Challenge

fun main() {
    DaySixPartOne().execute()
}

class DaySixPartOne : Challenge(2024, 6, sample = true) {

    var currentX = 0; var currentY = 0

    val blocks = mutableListOf<Pair<Int, Int>>()

    var readingY = 0
    var maxX = 0

    override fun readLine(str: String) {
        if (str.isEmpty()) {
            return
        }

        var readingX = 0

        str.split("").forEach { s ->
            if (s.isEmpty()) {
                return@forEach
            }

            if (s == "#") {
                blocks.add(readingX to readingY)
            }

            if (s == "^") {
                currentX = readingX
                currentY = readingY
            }

            readingX++
        }

        maxX = readingX
        readingY++
    }

    override fun finish() {
        val maxY = readingY

        var directionX = 0
        var directionY = -1

        var steps = mutableListOf<Pair<Int, Int>>()

        while (true) {
            val nextX = currentX + directionX
            val nextY = currentY + directionY

            if (nextX < 0 || nextX >= maxX || nextY < 0 || nextY > maxY) {
                break
            }

            val blocked = blocks.any { p -> p.first == nextX && p.second == nextY }
            if (blocked) {
                if (directionY == -1) {
                    directionY = 0
                    directionX = 1
                    continue
                }

                if (directionY == 1) {
                    directionY = 0
                    directionX = -1
                    continue
                }

                if (directionX == -1) {
                    directionX = 0
                    directionY = -1
                    continue
                }

                if (directionX == 1) {
                    directionX = 0
                    directionY = 1
                    continue
                }
            }

            currentX = nextX
            currentY = nextY
            steps.add(currentX to currentY)
        }

        println(steps.distinct().size)
    }
}

