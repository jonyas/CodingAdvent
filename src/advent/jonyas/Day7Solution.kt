package advent.jonyas

import java.io.File

private data class Program(val name: String, val weight: Int, val subProgramNames: List<String>?)

private fun parse(fileName: String): List<Program> {

  return File(fileName).readLines().map(::parseLine)
}

private fun parseLine(line: String): Program {
  return line.replace(" ", "")
      .let {
        Program(
            it.split("(").first(),
            it.split("(")[1].split(")").first().toInt(),
            if (it.contains("->")) {
              it.split("(")[1].split(")")[1].split("->")[1].split(",")
            } else {
              null
            }
        )
      }
}

private fun findProgramRoot(programs: List<Program>): String {
  val programSetName = mutableSetOf<String>()
      .apply {
        programs.forEach({
          add(it.name)
        })
      }
  programs.forEach {
    it.subProgramNames?.forEach {
      programSetName.remove(it)
    }
  }
  return programSetName.first()
}

fun main(args: Array<String>) {

  if (args.isEmpty()) {
    throw IllegalArgumentException()
  }

  println(
      findProgramRoot(
          parse(args[0])
      )
  )
}
