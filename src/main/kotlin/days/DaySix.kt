package days

import Challenge

fun main() {
    DaySixPartTwo().execute()
}

class DaySixPartOne : Challenge(2024, 6, sample = false) {

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

class DaySixPartTwo : Challenge(2024, 6, sample = false) {

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

    // -1 = looped, 0 = exited
    fun getResult(localBlocks: List<Pair<Int, Int>>): Int {
        val maxY = readingY

        var localX = currentX
        var localY = currentY

        var directionX = 0
        var directionY = -1

        val steps = mutableListOf<Pair<Int, Int>>()
        val blockedBy = mutableListOf<Pair<Int, Int>>()

        while (true) {
            val nextX = localX + directionX
            val nextY = localY + directionY

            if (nextX < 0 || nextX >= maxX || nextY < 0 || nextY > maxY) {
                return 0
            }

            val blocked = localBlocks.firstOrNull { p -> p.first == nextX && p.second == nextY }
            if (blocked != null) {
                if (blockedBy.count { p -> p.first == blocked.first && p.second == blocked.second } > 4) {
                    return -1
                }

                blockedBy.add(blocked)

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

            localX = nextX
            localY = nextY
            steps.add(localX to localY)
        }
    }

    override fun finish() {
        val maxY = readingY
        val works = mutableListOf<Pair<Int, Int>>()

        for (y in 0..maxY) {
            for (x in 0..maxX) {
                if (works.any { p -> p.first == x && p.second == y }) {
                    continue
                }

                val localBlocked = blocks.toMutableList()
                val blocker = x to y

                localBlocked.add(blocker)
                if (getResult(localBlocked) == -1) {
                    works.add(blocker)
                }
            }
        }

        println(works.size)

    }
}
