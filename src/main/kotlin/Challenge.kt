import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet
import java.io.File
import java.util.Scanner
import kotlin.system.exitProcess

abstract class Challenge(val year: Int, val day: Int, val sample: Boolean = false) {

    abstract fun readLine(str: String)
    abstract fun finish()

    fun execute() {
        println("[Advent of Code] $year: Day $day")

        if (sample) {
            println("Running sample input..")
            val file = File("sample.txt")
            if (!file.exists()) {
                println("Sample file not found.")
                println("Put your sample input in sample.txt.")
                exitProcess(1)
            }

            val scanner = Scanner(file)
            while (scanner.hasNextLine()) {
                readLine(scanner.nextLine())
            }

            finish()
            return
        }

        println("Fetching real input..")
        val time = System.currentTimeMillis()

        val (_, response, result) = "https://adventofcode.com/$year/day/$day/input".httpGet()
            .header(Headers.COOKIE to "session=${Config.session}")
            .response()

        val responseString = response.data.toString(Charsets.UTF_8)

        result.fold(
            success = {
                val lines = responseString.split("\n")
                println("Fetched input in ${System.currentTimeMillis() - time}ms")
                val programTime = System.currentTimeMillis()
                lines.forEach { line -> readLine(line) }
                finish()
                println("Program took ${System.currentTimeMillis() - programTime}ms")
            },

            failure = {
                println("aoc fail: $responseString")
            }
        )
    }
}
