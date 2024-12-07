import java.io.File
import kotlin.math.abs

fun main() {
val fileName = "src/data.txt"


    val reports = File(fileName).readLines().map { line -> line.split(" ").map { it.toInt() }.toIntArray() }
    println(reports[0].contentToString())
    val safeReports = reports.filter { report -> isSafeReport(report.toList())}
println(safeReports.size)
}

fun isSafeReport(report: List<Int>): Boolean {
    val isStrictlyIncreasing = report.zipWithNext().all { (a,b) -> a < b }
    val isStrictlyDecreasing = report.zipWithNext().all { (a,b) -> a > b }
    if (isStrictlyIncreasing || isStrictlyDecreasing) {
       return report.zipWithNext().all { (a,b) -> abs(a-b) in 1..3 }
    }
    return false
}