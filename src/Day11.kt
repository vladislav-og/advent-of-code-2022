fun main() {

  fun extractNumbers(input: String): MutableList<String> {
    val regex = Regex("\\d+")
    return regex.findAll(input).map { c -> c.value }.toMutableList()
  }

  fun extractMonkeysInitialData(input: List<String>): Pair<HashMap<Int, HashMap<String, MutableList<String>>>, MutableList<Int>> {
    val monkeysItemCount = mutableListOf<Int>()
    val monkeys = hashMapOf<Int, HashMap<String, MutableList<String>>>()
    var curMonkey = -1

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
    return Pair(monkeys, monkeysItemCount)
  }

  fun getWorryLevel(operation: MutableList<String>, item: String, divider: Int): Long {
    val worryLevel = when (operation[0]) {
      "*" -> {
        if (operation[1] == "old") {
          item.toLong() * item.toLong()
        } else {
          item.toLong() * operation[1].toLong()
        }
      }
      "+" -> {
        item.toLong() + operation[1].toLong()
      }
      else -> 0L
    }
    return if (divider == 3) worryLevel / 3 else worryLevel % divider
  }

  fun runGame(monkeys: HashMap<Int, HashMap<String, MutableList<String>>>, monkeysItemCount: MutableList<Int>, commonModulus: Int, rounds: Int) {
    for (i in 1..rounds) {
      for (j in 0 until monkeys.size) {
        for (item in monkeys[j]!!["items"]!!) {
          monkeysItemCount[j] = monkeysItemCount[j] + 1
          val operation = monkeys[j]!!["operation"]!!
          val worryLevel = getWorryLevel(operation, item, commonModulus)

          if (worryLevel % monkeys[j]!!["divisible"]!![0].toInt() == 0L) {
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
  }

  fun part1(input: List<String>): Long {
    val (monkeys, monkeysItemCount) = extractMonkeysInitialData(input)

    runGame(monkeys, monkeysItemCount, 3, 20)

    monkeysItemCount.sort()
    return monkeysItemCount[monkeysItemCount.size - 1].toLong() * monkeysItemCount[monkeysItemCount.size - 2].toLong()
  }

  fun part2(input: List<String>): Long {
    val (monkeys, monkeysItemCount) = extractMonkeysInitialData(input)
    val commonModulus = monkeys.values.map { it["divisible"]!![0].toInt() }
      .reduce { acc, it -> if (acc % it == 0) acc else acc * it }

    runGame(monkeys, monkeysItemCount, commonModulus, 10000)

    monkeysItemCount.sort()
    return monkeysItemCount[monkeysItemCount.size - 1].toLong() * monkeysItemCount[monkeysItemCount.size - 2].toLong()
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day11_test")
//  println(part1(testInput))
  check(part1(testInput) == 10605L)
//    println(part2(testInput))
    check(part2(testInput) == 2713310158)

  val input = readInput("Day11")
//  println(part1(input))
//    println(part2(input))
}

