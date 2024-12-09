import java.io.File

fun main() {
    val filename = "src/data.txt"

    val testInput = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    val testInput2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

    val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
    val controlRegex = Regex("do\\(\\)|don't\\(\\)")
    val mulAndInstructionsRegex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)")

    val fileLines: List<String> = File(filename).readLines()
    val matchesFromFile: List<Int> = fileLines.flatMap { line -> extractMulAndDoMultiplication(line, regex) }
    val matchesFromTestInput: List<Int> = extractMulAndDoMultiplication(testInput, regex)
    println(matchesFromTestInput.sum())
    println(matchesFromFile.sum())

    var r = controll(testInput2, mulAndInstructionsRegex, controlRegex)
    val mathcesWithControl: List<Int> = fileLines.flatMap { line -> controll(line, mulAndInstructionsRegex, controlRegex) }
    println(r.sum())
    println(mathcesWithControl.sum())
}

fun extractMulAndDoMultiplication(input: String, regex: Regex): List<Int> {
    return regex.findAll(input).toList().map { it.value }.map { match -> doMul(match) }
}

fun doMul(input: String): Int {
    val regex = Regex("\\d+")
    val integersAsList = regex.findAll(input).toList().map { it.value.toInt() }
    return integersAsList[0] * integersAsList[1]
}

fun controll(input: String, regex: Regex, controlRegex: Regex): List<Int> {
    val result: MutableList<Int> = mutableListOf<Int>()
    var canProcess: Boolean = true
    val mulAndInstructions: List<MatchResult> = regex.findAll(input).toList()

    for (matchResult in mulAndInstructions) {
        val value = matchResult.value
        println("Processing token: $value, canProcess: $canProcess")
        if (controlRegex.matches(value)) {
            canProcess = value == "do()"
            println("Control instruction: $value, updated canProcess to: $canProcess")
        }else if(canProcess ) {
            result.add(doMul(value))
            println("Performed multiplication for: $value")
        }
    }
    return result
}