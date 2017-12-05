package advent.jonyas

private fun Int.isOdd(): Boolean = this % 2 != 0

private fun Double.nextOddInt(): Int {

  if (this % 1.0 == 0.0) return this.toInt()

  with(this.toInt()) {
    return when {
      isOdd() -> this + 2
      else -> this + 1
    }
  }
}

private fun manhattanDistance(coordinates: Pair<Int, Int>,
                              matrixSize: Int): Int {
  return (Math.abs(coordinates.first - (matrixSize - 1) / 2)
      + Math.abs(coordinates.second - (matrixSize - 1) / 2))
}

private fun Direction.nextDirection(coordinates: Pair<Int, Int>,
                                    matrixSize: Int): Direction {
  return when (this) {
    Direction.LEFT -> if (coordinates.first == 0) Direction.UP else Direction.LEFT
    Direction.UP -> if (coordinates.second == 0) Direction.RIGHT else Direction.UP
    Direction.RIGHT -> if (coordinates.first == matrixSize - 1) Direction.DOWN else Direction.RIGHT
    Direction.DOWN -> if (coordinates.second == matrixSize - 1) Direction.LEFT else Direction.DOWN
  }
}

private enum class Direction {
  LEFT, UP, RIGHT, DOWN
}

/*
You come across an experimental new kind of memory stored on an infinite two-dimensional grid.

Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and then counting up while spiraling outward. For example, the first few squares are allocated like this:

17  16  15  14  13
18   5   4   3  12
19   6   1   2  11
20   7   8   9  10
21  22  23---> ...
While this is very space-efficient (no squares are skipped), requested data must be carried back to square 1 (the location of the only access port for this memory system) by programs that can only move up, down, left, or right. They always take the shortest path: the Manhattan Distance between the location of the data and square 1.

For example:

Data from square 1 is carried 0 steps, since it's at the access port.
Data from square 12 is carried 3 steps, such as: down, left, left.
Data from square 23 is carried only 2 steps: up twice.
Data from square 1024 must be carried 31 steps.
 */
private fun part1(number: Int) {

  val matrixSize = Math.sqrt(number.toDouble()).nextOddInt()
  val matrix = Array(matrixSize, { IntArray(matrixSize) })

  var lastValue = Math.pow(matrixSize.toDouble(), 2.toDouble()).toInt()

  // <X, Y>
  var coordinates = matrixSize - 1 to matrixSize - 1
  var currentDirection = Direction.LEFT
  while (lastValue != number) {
    matrix[coordinates.first][coordinates.second] = lastValue
    lastValue--
    currentDirection = currentDirection.nextDirection(coordinates, matrixSize)
    when (currentDirection) {
      Direction.LEFT -> coordinates = coordinates.first - 1 to coordinates.second
      Direction.UP -> coordinates = coordinates.first to coordinates.second - 1
      Direction.RIGHT -> coordinates = coordinates.first + 1 to coordinates.second
      Direction.DOWN -> coordinates = coordinates.first to coordinates.second + 1
    }
  }
  println(manhattanDistance(coordinates, matrixSize))
}

fun main(args: Array<String>) {

  if (args.isEmpty()) {
    throw IllegalArgumentException()
  }

  part1(args[0].toInt())
}
