fun main() {

  fun isTailTouchingHead(headPos: Pair<Int, Int>, tailPos: Pair<Int, Int>): Boolean {
    if (tailPos.first + 1 == headPos.first && tailPos.second in (headPos.second - 1)..(headPos.second + 1)) {
      return true
    }
    if (tailPos.first == headPos.first && tailPos.second in (headPos.second - 1)..(headPos.second + 1)) {
      return true
    }
    if (tailPos.first - 1 == headPos.first && tailPos.second in (headPos.second - 1)..(headPos.second + 1)) {
      return true
    }
    return false
  }

  fun part1(input: List<String>): Int {
    val tailSteps = hashSetOf(Pair(0, 0))
    var tailPosition = Pair(0, 0) // (x, y)
    var headPosition = Pair(0, 0) // (x, y)
    for (row in input) {
      val (direction, step) = row.split(" ")
      when(direction) {
        "R" -> {
          for ( i in 1..step.toInt()) {
            val newXposition = headPosition.first + 1
            headPosition = Pair(newXposition, headPosition.second)
            if (!isTailTouchingHead(headPosition, tailPosition)) {
              tailPosition = Pair(newXposition - 1, headPosition.second)
              tailSteps.add(tailPosition)
            }
          }
        }
        "L" -> {
          for ( i in 1..step.toInt()) {
            val newXposition = headPosition.first - 1
            headPosition = Pair(newXposition, headPosition.second)
            if (!isTailTouchingHead(headPosition, tailPosition)) {
              tailPosition = Pair(newXposition + 1, headPosition.second)
              tailSteps.add(tailPosition)
            }
          }
        }
        "U" -> {
          for ( i in 1..step.toInt()) {
            val newYposition = headPosition.second + 1
            headPosition = Pair(headPosition.first, newYposition)
            if (!isTailTouchingHead(headPosition, tailPosition)) {
              tailPosition = Pair(headPosition.first, headPosition.second - 1)
              tailSteps.add(tailPosition)
            }
          }
        }
        "D" -> {
          for ( i in 1..step.toInt()) {
            val newYposition = headPosition.second - 1
            headPosition = Pair(headPosition.first, newYposition)
            if (!isTailTouchingHead(headPosition, tailPosition)) {
              tailPosition = Pair(headPosition.first, headPosition.second + 1)
              tailSteps.add(tailPosition)
            }
          }
        }
      }
    }
    return tailSteps.size
  }

  fun part2(input: List<String>): Int {
    return 0
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day09_test")
  val testInput2 = readInput("Day09_test2")
//    println(part1(testInput))
//    check(part1(testInput) == 13)
    println(part2(testInput2))
    check(part2(testInput2) == 36)

  val input = readInput("Day09")
    println(part1(input)) // 5981
//    println(part2(input))
}
