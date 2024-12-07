import java.io.File
import kotlin.math.abs

fun main() {
    val fileName = "src/data.txt"

    val reports: List<List<Int>> =
        File(fileName).readLines().map { line -> line.split(" ").map { it.toInt() }.toList() }
    val safeReports = reports.filter { report -> isSafeReport(report) }
    val safeReportsWithDampner = reports.filter { report -> isSafeReportUsingProblemDampener(report) }

    println("Total number of safe reports: ${safeReports.size}")
    println("Total number of safe reports with dampener: ${safeReportsWithDampner.size}")
}

fun isSafeReport(report: List<Int>): Boolean {
    val isStrictlyIncreasing = report.zipWithNext().all { (a, b) -> a < b }
    val isStrictlyDecreasing = report.zipWithNext().all { (a, b) -> a > b }
    if (isStrictlyIncreasing || isStrictlyDecreasing) {
        return report.zipWithNext().all { (a, b) -> abs(a - b) in 1..3 }
    }
    return false
}

fun isSafeReportUsingProblemDampener(report: List<Int>): Boolean {
    return report.indices.any {
        val skipped = report.toMutableList().apply { removeAt(it) }
        isSafeReport(skipped)
    }
}

