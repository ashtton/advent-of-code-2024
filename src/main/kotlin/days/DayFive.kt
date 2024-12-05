package days

import Challenge

fun main() {
    DayFivePartTwo().execute()
}

class DayFivePartOne : Challenge(2024, 5, sample = false) {
    var readingRules = true

    val rules = mutableMapOf<Int, MutableList<Int>>()
    val printer = mutableListOf<List<Int>>()

    override fun readLine(str: String) {
        if (str.isEmpty()) {
            readingRules = false
            return
        }

        if (readingRules) {
            val split = str.split("|")

            val x = split[0].toInt()
            val y = split[1].toInt()

            val rulesExist = rules.getOrDefault(y, mutableListOf())
            rulesExist.add(x)
            rules[y] = rulesExist
        }

        else printer.add(str.split(",").map { it.toInt() })
    }

    override fun finish() {
        var sum = 0
        printer.forEach { attempt ->
            val fail = attempt.filter { num -> rules.containsKey(num) }.any { num -> rules[num]!!.any { attempt.indexOf(it) > attempt.indexOf(num) } }

            if (fail) {
                return@forEach
            }

            sum += attempt[(attempt.size / 2)]
        }

        println(sum)
    }
}

class DayFivePartTwo : Challenge(2024, 5, sample = false) {
    var readingRules = true

    val rules = mutableMapOf<Int, MutableList<Int>>()
    val printer = mutableListOf<List<Int>>()

    override fun readLine(str: String) {
        if (str.isEmpty()) {
            readingRules = false
            return
        }

        if (readingRules) {
            val split = str.split("|")

            val x = split[0].toInt()
            val y = split[1].toInt()

            val rulesExist = rules.getOrDefault(y, mutableListOf())
            rulesExist.add(x)
            rules[y] = rulesExist
        }

        else printer.add(str.split(",").map { it.toInt() })
    }

    override fun finish() {
        var sum = 0
        printer.forEach { attempt ->
            val fail = attempt.filter { num -> rules.containsKey(num) }.any { num -> rules[num]!!.any { attempt.indexOf(it) > attempt.indexOf(num) } }

            if (!fail) {
                return@forEach
            }

            val sortedCorrect = attempt.sortedWith { o1, o2 ->
                if (rules.getOrDefault(o2, mutableListOf()).contains(o1)) {
                    return@sortedWith -1
                }

                return@sortedWith 1
            }

            sum += sortedCorrect[(sortedCorrect.size / 2)]
        }

        println(sum)
    }
}