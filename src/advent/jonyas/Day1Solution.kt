package advent.jonyas

import java.io.File

/*
You're standing in a room with "digitization quarantine" written in LEDs along one wall. The only door is locked, but it includes a small interface. "Restricted Area - Strictly No Digitized Users Allowed."

It goes on to explain that you may only leave by solving a captcha to prove you're not a human. Apparently, you only get one millisecond to solve the captcha: too fast for a normal human, but it feels like hours to you.

The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list. The list is circular, so the digit after the last digit is the first digit in the list.

For example:

1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
1111 produces 4 because each digit (all 1) matches the next.
1234 produces 0 because no digit matches the next.
91212129 produces 9 because the only digit that matches the next one is the last digit, 9.
 */
private fun indexForCircularString(string: String, index: Int): Int {
  if (index < 0) {
    throw IllegalArgumentException()
  }

  return when (index) {
    in 0 until string.length -> index
    else -> index % string.length
  }
}

private fun part1(text: String) {

  val consecutiveNumbers = mutableListOf<Int>()
  for (index in 0 until text.length) {
    // item in current index
    if (text[indexForCircularString(text, index)]
        // next item on circular list
        == text[indexForCircularString(text, index + 1)]) {
      consecutiveNumbers.add(Integer.valueOf(text[indexForCircularString(text, index)].toString()))
    }
  }

  println(consecutiveNumbers.sum())
}

private fun part2(text: String) {

  val consecutiveNumbers = mutableListOf<Int>()
  val indexAddition = text.length / 2
  for (index in 0 until text.length) {
    // item in current index
    if (text[indexForCircularString(text, index)]
        // next item on circular list
        == text[indexForCircularString(text, index + indexAddition)]) {
      consecutiveNumbers.add(Integer.valueOf(text[indexForCircularString(text, index)].toString()))
    }
  }

  println(consecutiveNumbers.sum())
}

fun main(args: Array<String>) {

  if (args.isEmpty()) {
    throw IllegalArgumentException()
  }

  // input given as list
  part1(File(args[0]).readLines().first())
  part2(File(args[0]).readLines().first())
}
