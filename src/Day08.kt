fun main() {

  fun createForestMap(input: List<String>): ArrayList<ArrayDeque<Int>> {
    val forestMap = arrayListOf<ArrayDeque<Int>>()
    for (row in input) {
      forestMap.add(ArrayDeque(row.map { it.digitToInt() }))
    }
    return forestMap
  }

  fun isSeen(treePosX: Int, treesPosY: Int, forestMap: ArrayList<ArrayDeque<Int>>): Boolean {
    val curTreeHeight = forestMap[treesPosY][treePosX]
    if (forestMap[treesPosY].subList(0, treePosX).max() < curTreeHeight) {
      return true
    }
    if (forestMap[treesPosY].subList(treePosX + 1, forestMap.size).max() < curTreeHeight) {
      return true
    }
    for (i in 0 until treesPosY) {
      if (forestMap[i][treePosX] >= curTreeHeight) {
        break
      }

      if (i == treesPosY - 1) {
        return true
      }
    }
    for (i in (treesPosY + 1) until forestMap.size) {
      if (forestMap[i][treePosX] >= curTreeHeight) {
        break
      }
      if (i == forestMap.size - 1) {
        return true
      }
    }

    return false
  }

  fun isSeenWithScore(treePosX: Int, treesPosY: Int, forestMap: ArrayList<ArrayDeque<Int>>): Int {
    val curTreeHeight = forestMap[treesPosY][treePosX]
    val treesSeen = arrayListOf(0, 0, 0, 0)

    for (i in treePosX - 1 downTo 0) {
      treesSeen[0]++
      if (forestMap[treesPosY][i] >= curTreeHeight) {
        break
      }
    }
    for (i in (treePosX + 1) until forestMap.size) {
      treesSeen[1]++
      if (forestMap[treesPosY][i] >= curTreeHeight) {
        break
      }
    }
    for (i in treesPosY - 1 downTo 0) {
      treesSeen[2]++
      if (forestMap[i][treePosX] >= curTreeHeight) {
        break
      }
    }
    for (i in (treesPosY + 1) until forestMap.size) {
      treesSeen[3]++
      if (forestMap[i][treePosX] >= curTreeHeight) {
        break
      }
    }

    return treesSeen.fold(1) { acc, i -> acc * i }
  }

  fun part1(input: List<String>): Int {
    val forestMap = createForestMap(input)

    var score = forestMap.size * 4 - 4

    for (i in 1 until forestMap.size - 1) {
      for (j in 1 until forestMap.size - 1) {
        if (isSeen(i, j, forestMap)) score++
      }
    }
    return score
  }

  fun part2(input: List<String>): Int {
    val forestMap = createForestMap(input)

    var score = 0
    for (i in 1 until forestMap.size - 1) {
      for (j in 1 until forestMap.size - 1) {
        val curScore = isSeenWithScore(i, j, forestMap)
        if (curScore > score) {
          score = curScore
        }
      }
    }
    return score
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day08_test")
    println(part1(testInput))
    check(part1(testInput) == 21)
    println(part2(testInput))
    check(part2(testInput) == 8)

  val input = readInput("Day08")
    println(part1(input)) // 1794
    println(part2(input)) // 199272
}
