package advent.jonyas

import java.io.File

private fun minMax(numberList: List<Int>): Pair<Int, Int> {
  var min = Integer.MAX_VALUE
  var max = Integer.MIN_VALUE
  numberList.forEach {
    min = Math.min(min, it)
    max = Math.max(max, it)
  }
  return min to max
}

private fun findDivisible(numberList: List<Int>): Pair<Int, Int> {
  for (i in 0 until numberList.size) {
    for (j in 0 until numberList.size) {
      if (i == j) continue
      if (numberList[i] % numberList[j] == 0) {
        return numberList[i] to numberList[j]
      }
    }
  }
  throw IllegalStateException()
}

private inline fun applyOperatorOnPair(input: List<Int>,
                                       block: (List<Int>) -> Pair<Int, Int>,
                                       operator: (Pair<Int, Int>) -> Int): Int {
  return block(input).let { operator(it) }
}

/*
The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process is on the right track, they need you to calculate the spreadsheet's checksum. For each row, determine the difference between the largest value and the smallest value; the checksum is the sum of all of these differences.

For example, given the following spreadsheet:

5 1 9 5
7 5 3
2 4 6 8
The first row's largest and smallest values are 9 and 1, and their difference is 8.
The second row's largest and smallest values are 7 and 3, and their difference is 4.
The third row's difference is 6.
In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
 */
private fun part1(fileName: String) {
  // input given as list
  val minMaxLineDiffs = mutableListOf<Int>()
  File(fileName).readLines().forEach {
    minMaxLineDiffs.add(
        applyOperatorOnPair(
            it.split("\t").map { it.toInt() },
            ::minMax,
            { it.second - it.first }
        )
    )
  }

  println(minMaxLineDiffs.sum())
}

/*
"Based on what we're seeing, it looks like all the User wanted is some information about the evenly divisible values in the spreadsheet. Unfortunately, none of us are equipped for that kind of calculation - most of us specialize in bitwise operations."

It sounds like the goal is to find the only two numbers in each row where one evenly divides the other - that is, where the result of the division operation is a whole number. They would like you to find those numbers on each line, divide them, and add up each line's result.

For example, given the following spreadsheet:

5 9 2 8
9 4 7 3
3 8 6 5
In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4.
In the second row, the two numbers are 9 and 3; the result is 3.
In the third row, the result is 2.
 */
private fun part2(fileName: String) {
  // input given as list
  val divisibleLines = mutableListOf<Int>()
  File(fileName).readLines().forEach {
    it.split("\t").map { it.toInt() }
    divisibleLines.add(
        applyOperatorOnPair(
            it.split("\t").map { it.toInt() },
            ::findDivisible,
            { it.first / it.second }
        )
    )
  }

  println(divisibleLines.sum())
}

fun main(args: Array<String>) {

  if (args.isEmpty()) {
    throw IllegalArgumentException()
  }

  part1(args[0])
  part2(args[0])
}
