package advent.jonyas

import java.io.File

private fun getRealListIndex(mazeIndex: Int) = mazeIndex - 1

private fun getNewIndexAndChangeItemInList(currentMaze: IntArray,
                                           currentIndex: Int,
                                           indexModifier: (Int) -> Int): Int {
  val currentOffset = currentMaze[getRealListIndex(currentIndex)]
  currentMaze[getRealListIndex(currentIndex)] = indexModifier(currentOffset)
  return currentOffset + currentIndex
}

private fun findMazeSolution(items: IntArray,
                             indexModifier: (IntArray, Int) -> Int): Int {

  var counter = 0
  var currentMazeIndex = 1
  while (getRealListIndex(currentMazeIndex) < items.size) {
    currentMazeIndex = indexModifier(items, currentMazeIndex)
    counter++
  }
  return counter
}

/*
The message includes a list of the offsets for each jump. Jumps are relative: -1 moves to the previous instruction, and 2 skips the next one. Start at the first instruction in the list. The goal is to follow the jumps until one leads outside the list.

In addition, these instructions are a little strange; after each jump, the offset of that instruction increases by 1. So, if you come across an offset of 3, you would move three instructions forward, but change it to a 4 for the next time it is encountered.
 */
private fun part1(fileName: String) {

  val itemsList = File(fileName).readLines().map { it.toInt() }.toIntArray()

  println(
      findMazeSolution(
          itemsList,
          { array, index ->
            getNewIndexAndChangeItemInList(array, index)
            {
              it + 1
            }
          }
      )
  )
}

/*
Now, the jumps are even stranger: after each jump, if the offset was three or more, instead decrease it by 1. Otherwise, increase it by 1 as before.

Using this rule with the above example, the process now takes 10 steps, and the offset values after finding the exit are left as 2 3 2 3 -1.
 */
private fun part2(fileName: String) {

  val itemsList = File(fileName).readLines().map { it.toInt() }.toIntArray()

  println(
      findMazeSolution(
          itemsList,
          { array, index ->
            getNewIndexAndChangeItemInList(array, index)
            {
              if (it >= 3) it - 1 else it + 1
            }
          }
      )
  )
}

fun main(args: Array<String>) {

  if (args.isEmpty()) {
    throw IllegalArgumentException()
  }

  part1(args[0])
  part2(args[0])
}
