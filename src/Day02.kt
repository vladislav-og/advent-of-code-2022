fun main() {
  // key value match from left to right: lose, draw, win
  val winMapping = mapOf(
    Pair("A", "ZXY"), // rock -> scissors, rock, paper
    Pair("B", "XYZ"), // paper -> scissors
    Pair("C", "YZX"), // scissors -> rock
  )
  val myMoveScoreMapping = mapOf(
    Pair("X", 1), Pair("Y", 2), Pair("Z", 3),
  )

  fun calculateGamePoints(opponentsMove: String, myMove: String) = winMapping[opponentsMove]!!.indexOf(myMove) * 3

  fun extractData(row: String) = row.split(" ")

  fun part1(input: List<String>): Int {

    var score = 0
    for (row in input) {
      val (opponentsMove, myMove) = extractData(row)
      score += myMoveScoreMapping[myMove]!!
      score += calculateGamePoints(opponentsMove, myMove)
    }
    return score
  }

  fun part2(input: List<String>): Int {
    var score = 0
    for (row in input) {
      val (opponentsMove, outcome) = extractData(row)
      val myMove = winMapping[opponentsMove]!![myMoveScoreMapping[outcome]!! - 1].toString()
      score += myMoveScoreMapping[myMove]!!
      score += calculateGamePoints(opponentsMove, myMove)
    }
    return score
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day02_test")
  println(part1(testInput))
  check(part1(testInput) == 15)
  println(part2(testInput))
  check(part2(testInput) == 12)

  val input = readInput("Day02")
  println(part1(input))
  println(part2(input))
}
