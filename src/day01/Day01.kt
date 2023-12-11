package day01

import println
import readInput

val words: List<String> = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
val reversedWords: List<String> = words.map { it.reversed() }
val digits: List<String> = List(10) { "$it"}
/*val wordsMap: Map<String, Int> = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)*/

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { row: String -> calibrationValue(row) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { row: String -> getNumber(row, words) * 10 + getNumber(row.reversed(), reversedWords) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01_test")
    check(part1(testInput) == 142)
    //part1(testInput).println()
    val test2Input = readInput("day01/Day01_test2")
    check(part2(test2Input) == 281)
    //part2(test2Input).println()

    val input = readInput("day01/Day01")
    part1(input).println()
    part2(input).println()
}

private fun calibrationValue(row: String): Int {
    val firstDigit = row.first { it.isDigit() }
    val lastDigit = row.last { it.isDigit() }
    return "$firstDigit$lastDigit".toInt()
}

private fun getNumber(row: String, words: List<String>): Int {
    val (wordIdx: Int, word: String) = row.findAnyOf(words) ?: (Int.MAX_VALUE to "not found")
    val (digitIdx: Int, digit: String) = row.findAnyOf(digits) ?: (Int.MAX_VALUE to "not found")

    return if(digitIdx < wordIdx) {
        digit.toInt()
    } else {
        word.digitWordToInt(words)
    }
}

private fun String.digitWordToInt(wordList: List<String> = words): Int {
    val index: Int = wordList.indexOf(this)
    if (index==-1) error("$this is not a number word")
    return index + 1
}
