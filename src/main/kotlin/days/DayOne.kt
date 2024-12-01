package days

import Challenge
import kotlin.math.abs

fun main() {
    DayOnePartTwo()
}

var leftSide = mutableListOf<Int>()
var rightSide = mutableListOf<Int>()

class DayOnePartOne : Challenge(2024, 1, sample = false) {

    override fun readLine(str: String) {
        if (str.isEmpty()) {
            return
        }

        val split = str.split("   ")
        leftSide.add(split[0].toInt())
        rightSide.add(split[1].toInt())
    }

    override fun finish() {
        leftSide = leftSide.sorted().toMutableList()
        rightSide = rightSide.sorted().toMutableList()
        println(leftSide.mapIndexed { index, i -> abs(i - rightSide[index]) }.sum())
    }
}


class DayOnePartTwo : Challenge(2024, 1, sample = false) {

    override fun readLine(str: String) {
        if (str.isEmpty()) {
            return
        }

        val split = str.split("   ")
        leftSide.add(split[0].toInt())
        rightSide.add(split[1].toInt())
    }

    override fun finish() {
        var sum = 0

        leftSide.forEach {
            val appears = rightSide.filter { num -> num == it }.size
            sum += it * appears
        }

        println(sum)
    }
}



