import java.io.File
import kotlin.math.abs

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    task2()
}

fun task2() {
    val listPair = task1Data()
    val listOne = listPair.first
    val listTwo = listPair.second

    println(calcSimilarityScore(listOne, listTwo))
}

fun task1() {
    val listPair = task1Data()
    val listOne = listPair.first
    val listTwo = listPair.second

    println(sortAndCalcl1Distance(listOne, listTwo))
}

fun calcSimilarityScore(listOne: List<Int>, listTwo: List<Int>): Int {
    var similarityScore = 0
    listOne.forEach { numberInListOne ->
        similarityScore += numberInListOne * listTwo.count{n -> n == numberInListOne}
    }
    return similarityScore
}

fun sortAndCalcl1Distance(listOne: List<Int>, listTwo: List<Int>): Int {
    val listOneSorted = listOne.sorted()
    val listTwoSorted = listTwo.sorted()

    var l1Distance: Int = 0
    for (i in listOne.indices) {
        l1Distance += abs(listOneSorted[i] - listTwoSorted[i])
    }
    return l1Distance
}

fun testListOne(): List<Int> {
    return listOf(3, 4, 2, 1, 3, 3)
}

fun testListTwo(): List<Int> {
    return listOf(4, 3, 5, 3, 9, 3)
}


fun task1Data(): Pair<List<Int>, List<Int>> {
    val content = File("./src/task_1_data.txt").readText()
    val contentList = content.trim().split("\n")

    val firstList = mutableListOf<Int>()
    val secondList = mutableListOf<Int>()

    for (line in contentList) {
        val numbers = line.trim().split("\\s+".toRegex())
        if (numbers.size == 2) {
            firstList.add(numbers[0].toInt())
            secondList.add(numbers[1].toInt())
        }
    }

    return Pair(firstList, secondList)
}