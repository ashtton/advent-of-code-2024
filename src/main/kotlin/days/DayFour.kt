package days

import Challenge

fun main() {
    DayFourPartOne().execute()
}

class DayFourPartOne : Challenge(2024, 4, sample = false) {

    val grid = mutableListOf<MutableList<String>>()
    val matches = mutableListOf<List<Pair<Int, Int>>>()

    override fun readLine(str: String) {
        grid.add(str.split("").filter { it.isNotEmpty() }.toMutableList())
    }

    fun checkChar(x: Int, y: Int) {
        val char = getChar(x, y)

        listOf(
            listOf(char, getChar(x - 1, y), getChar(x - 2, y), getChar(x - 3, y)), // left
            listOf(char, getChar(x + 1, y), getChar(x + 2, y), getChar(x + 3, y)), // right
            listOf(char, getChar(x, y + 1), getChar(x, y + 2), getChar(x, y + 3)), // down
            listOf(char, getChar(x, y - 1), getChar(x, y - 2), getChar(x, y - 3)), // up
            listOf(char, getChar(x - 1, y + 1), getChar(x - 2, y + 2), getChar(x - 3, y + 3)), // Diagonal down left
            listOf(char, getChar(x + 1, y + 1), getChar(x + 2, y + 2), getChar(x + 3, y + 3)), // Diagonal down right
            listOf(char, getChar(x - 1, y - 1), getChar(x - 2, y - 2), getChar(x - 3, y - 3)), // Diagonal up left
            listOf(char, getChar(x + 1, y - 1), getChar(x + 2, y - 2), getChar(x + 3, y - 3)), // Diagonal up right
        )
            .filterNot { alreadyUsed(it.map { pair -> pair.first }) }
            .forEach { charList ->
                val word = charList[0].second + charList[1].second + charList[2].second + charList[3].second
                if (word != "XMAS" && word != "SAMX") {
                    return@forEach
                }

                matches.add(charList.map { pair -> pair.first })
            }
    }

    fun pairEquals(one: Pair<Int, Int>, two: Pair<Int, Int>) = one.first == two.first && one.second == two.second

    fun alreadyUsed(list: List<Pair<Int, Int>>): Boolean {
        return matches.any {
            list.all { pair -> it.any { twoPair -> pairEquals(twoPair, pair) } }
        }
    }

    fun getChar(x: Int, y: Int): Pair<Pair<Int, Int>, String> {
        if (x < 0 || y < 0) {
            return Pair(Pair(x, y), "")
        }

        val list = grid.getOrNull(y) ?: return Pair(Pair(x, y), "")
        val char = list.getOrNull(x) ?: return Pair(Pair(x, y), "")
        return Pair(Pair(x, y), char)
    }

    override fun finish() {
        grid.forEachIndexed { y, list -> list.forEachIndexed { x, _ -> checkChar(x, y) } }
        println(matches.size)
    }

}

class DayFourPartTwo : Challenge(2024, 4, sample = false) {

    val grid = mutableListOf<MutableList<String>>()
    val matches = mutableListOf<List<Pair<Int, Int>>>()

    var totalMatches = 0

    override fun readLine(str: String) {
        grid.add(str.split("").filter { it.isNotEmpty() }.toMutableList())
    }

    fun checkChar(x: Int, y: Int) {
        val char = getChar(x, y)

        val list = listOf(
            listOf(getChar(x - 1, y + 1), char, getChar(x + 1, y - 1)), // down left and up right
            listOf(getChar(x - 1, y - 1), char, getChar(x + 1, y + 1)), // up left and down right
        )

        if (!list.all { charList ->
                val word = charList[0].second + charList[1].second + charList[2].second
                return@all word == "MAS" || word == "SAM"
            }) {
            return
        }

        val usedFirst = alreadyUsed(list[0].map { it.first })
        val usedSecond = alreadyUsed(list[1].map { it.first })

        if (usedFirst && usedSecond) {
            return
        }

        totalMatches++

        if (!usedFirst) {
            matches.add(list[0].map { it.first })
        }

        if (!usedSecond) {
            matches.add(list[1].map { it.first })
        }
    }

    fun pairEquals(one: Pair<Int, Int>, two: Pair<Int, Int>) = one.first == two.first && one.second == two.second

    fun alreadyUsed(list: List<Pair<Int, Int>>): Boolean {
        return matches.any {
            list.all { pair -> it.any { twoPair -> pairEquals(twoPair, pair) } }
        }
    }

    fun getChar(x: Int, y: Int): Pair<Pair<Int, Int>, String> {
        if (x < 0 || y < 0) {
            return Pair(Pair(x, y), "")
        }

        val list = grid.getOrNull(y) ?: return Pair(Pair(x, y), "")
        val char = list.getOrNull(x) ?: return Pair(Pair(x, y), "")
        return Pair(Pair(x, y), char)
    }

    override fun finish() {
        grid.forEachIndexed { y, list -> list.forEachIndexed { x, _ -> checkChar(x, y) } }
        println(totalMatches)
    }

}