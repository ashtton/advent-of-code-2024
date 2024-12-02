package days

import Challenge
import kotlin.math.abs

fun main() {
    DayTwoPartTwo()
}

var safeReports = 0


fun isSafe(arr: List<Int>): Boolean {
    val increasing = arr[1] - arr[0] > 0

    for (i in 0 until arr.size - 1) {
        val diff = arr[i + 1] - arr[i]

        if (diff > 0 && !increasing)
            return false

        if (diff < 0 && increasing)
            return false

        if (diff < -3 || diff > 3 || diff == 0) {
            return false
        }
    }

    return true
}

class DayTwoPartOne : Challenge(2024, 2, sample = false) {

    override fun readLine(str: String) {
        if (str.isEmpty()) {
            return
        }

        if (isSafe(str.split(" ").map { it.toInt() })) {
            safeReports++
        }
    }

    override fun finish() {
        println(safeReports.toString())
    }
}

class DayTwoPartTwo : Challenge(2024, 2, sample = false) {

    override fun readLine(str: String) {
        if (str.isEmpty()) {
            return
        }

        val split = str.trim().split(" ").map { it.toInt() }.toMutableList()

        if (isSafe(split)) {
            safeReports++
            return
        }

        for (i in split.indices) {
            val modified = split.toMutableList().apply { removeAt(i) }
            if (isSafe(modified)) {
                safeReports++
                return
            }
        }
    }

    override fun finish() {
        println(safeReports.toString())
    }
}


