package advent.jonyas

import java.io.File

private fun validateWords(words: String, condition: (String, String) -> Boolean): Boolean {
  val wordSet = mutableSetOf<String>()
  for (word in words.split(" ")) {
    wordSet.forEach {
      if (condition(it, word)) {
        return false
      }
    }
    wordSet.add(word)
  }
  return true
}

private fun wordsAreEqual(first: String, second: String) = first == second

private fun wordsAreAnagrams(first: String, second: String): Boolean {

  return first.toList().sorted() == second.toList().sorted()
}

/*
For added security, yet another system policy has been put in place. Now, a valid passphrase must contain no two words that are anagrams of each other - that is, a passphrase is invalid if any word's letters can be rearranged to form any other word in the passphrase.

For example:

abcde fghij is a valid passphrase.
abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.
a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.
iiii oiii ooii oooi oooo is valid.
oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.
 */
private fun part2(fileName: String) {

  val lines = File(fileName).readLines()

  println(
      lines.filter { validateWords(it, ::wordsAreAnagrams) }.count()
  )
}

/*
A new system policy has been put in place that requires all accounts to use a passphrase instead of simply a password. A passphrase consists of a series of words (lowercase letters) separated by spaces.

To ensure security, a valid passphrase must contain no duplicate words.

For example:

aa bb cc dd ee is valid.
aa bb cc dd aa is not valid - the word aa appears more than once.
aa bb cc dd aaa is valid - aa and aaa count as different words.

 */
private fun part1(fileName: String) {

  val lines = File(fileName).readLines()

  println(
      lines.filter { validateWords(it, ::wordsAreEqual) }.count()
  )
}

fun main(args: Array<String>) {

  if (args.isEmpty()) {
    throw IllegalArgumentException()
  }

  part1(args[0])
  part2(args[0])
}
