import java.io.File
import java.util.Scanner
import kotlin.system.exitProcess

object Config {

    var session = ""

    init {
        val sessionFile = File(".session")

        if (!sessionFile.exists()) {
            println("Session file not found.")
            println("Put your session token in .session.")
            exitProcess(1)
        }

        session = Scanner(sessionFile).nextLine()
    }
}