fun main() {

  fun calculateSignalStrength(cycleCount: Int, signalStrength: Int, xValue: Int): Int {
    var signalStrength1 = signalStrength
    if (cycleCount % 40 == 20) {
      signalStrength1 += xValue * cycleCount
    }
    return signalStrength1
  }

  fun part1(input: List<String>): Int {
    var cycleCount = 0
    var xValue = 1
    var signalStrength = 0
    for (command in input) {
      val row = command.split(" ")
      if (row.size == 2) {
        for (i in 0..1) {
          cycleCount++
          signalStrength = calculateSignalStrength(cycleCount, signalStrength, xValue)
        }
        xValue += row[1].toInt()
      } else {
        cycleCount++
        signalStrength = calculateSignalStrength(cycleCount, signalStrength, xValue)
      }
    }
    return signalStrength
  }

  fun part2(input: List<String>): Int {
    return 1
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day10_test")
  println(part1(testInput))
  check(part1(testInput) == 13140)
//    println(part2(testInput1))
//    check(part2(testInput1) == 36)

  val input = readInput("Day10")
  println(part1(input)) // 5981
//    println(part2(input))
}
