import java.io.File
import kotlin.math.abs

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val fileName1 = "src/inputlist1.txt"
    val fileName2 = "src/inputlist2.txt"
    //TIP `it` is an implicit name for a single parameter in a lambda expression. It is used as a shorthand to refer to the parameter when no explicit name is provided.
    val list1: List<Int> = File(fileName1).readLines().map { it.toInt() }.sorted()
    val list2: List<Int> = File(fileName2).readLines().map { input -> input.toInt() }.sorted()

    val distances: List<Int> = list1.zip(list2) { a, b -> abs(a-b)}

    val totalDistance = distances.sum()

    println("The total distance is $totalDistance units")

}
