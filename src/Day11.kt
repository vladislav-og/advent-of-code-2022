fun main() {

  fun extractNumbers(input: String): MutableList<String> {
    val regex = Regex("\\d+")
    return regex.findAll(input).map { c -> c.value }.toMutableList()
  }

  fun part1(input: List<String>): Int {
    var curMonkey = -1
    val monkeys = hashMapOf<Int, HashMap<String, MutableList<String>>>()
    val monkeysItemCount = mutableListOf<Int>()
    for (row in input) {
      if (row.isBlank()) {
        continue
      }
      if (row.startsWith("Monkey")) {
        curMonkey++
        monkeysItemCount.add(0)
        monkeys[curMonkey] = hashMapOf()
      } else {
        val trimmedInput = row.trim()
        if (trimmedInput.startsWith("S")) {
          monkeys[curMonkey]!!["items"] = extractNumbers(row)
        } else if (trimmedInput.startsWith("O")) {
          val number = trimmedInput.substring(23)
          val operation = trimmedInput.substring(21, 22)
          monkeys[curMonkey]!!["operation"] = mutableListOf(operation, number)
        } else if (trimmedInput.startsWith("T")) {
          monkeys[curMonkey]!!["divisible"] = mutableListOf(trimmedInput.substring(19))
        } else if (trimmedInput.startsWith("If t")) {
          monkeys[curMonkey]!!["true"] = mutableListOf(trimmedInput.substring(25))
        } else if (trimmedInput.startsWith("If f")) {
          monkeys[curMonkey]!!["false"] = mutableListOf(trimmedInput.substring(26))
        }
      }
    }

    for (i in 1..20) {
      for (j in 0..curMonkey) {
        for (item in monkeys[j]!!["items"]!!) {
          monkeysItemCount[j] = monkeysItemCount[j] + 1
          val operation = monkeys[j]!!["operation"]!!
          val worryLevel = when (operation[0]) {
            "*" -> {
              if (operation[1] == "old") {
                (item.toInt() * item.toInt()) / 3
              } else {
                (item.toInt() * operation[1].toInt()) / 3
              }
            }

            "+" -> (item.toInt() + operation[1].toInt()) / 3
            "-" -> (item.toInt() - operation[1].toInt()) / 3
            else -> 0
          }

          if (worryLevel % monkeys[j]!!["divisible"]!![0].toInt() == 0) {
            val monkeyToPassItem = monkeys[j]!!["true"]!![0]
            monkeys[monkeyToPassItem.toInt()]!!["items"]!!.add(worryLevel.toString())
          } else {
            val monkeyToPassItem = monkeys[j]!!["false"]!![0]
            monkeys[monkeyToPassItem.toInt()]!!["items"]!!.add(worryLevel.toString())
          }
        }
        monkeys[j]!!["items"] = mutableListOf()
      }
    }
    monkeysItemCount.sort()
    return monkeysItemCount[monkeysItemCount.size - 1] * monkeysItemCount[monkeysItemCount.size - 2]
  }

  fun part2(input: List<String>): Int {
    return 1
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day11_test")
  println(part1(testInput))
  check(part1(testInput) == 10605)
//    println(part2(testInput1))
//    check(part2(testInput1) == 2713310158)

  val input = readInput("Day11")
  println(part1(input))
//    println(part2(input))
}

