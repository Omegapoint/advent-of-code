import java.io.File

fun main() {
    val filename = "src/data.txt"
    val testInput = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")

    val fileLines: List<String> = File(filename).readLines()
    val matchesFromFile: List<Int> = fileLines.flatMap { line -> extractMul(line, regex) }

    val matchesFromTestInput: List<Int> = extractMul(testInput, regex)
    println(matchesFromTestInput.sum())
    println(matchesFromFile.sum())
}

fun extractMul(input: String, regex: Regex): List<Int> {
    return regex.findAll(input).toList().map { it.value }.map { match -> doMul(match) }
}

fun doMul(input: String): Int {
    val regex = Regex("\\d+")
    val integersAsList = regex.findAll(input).toList().map { it.value.toInt() }
    return integersAsList[0] * integersAsList[1]
}