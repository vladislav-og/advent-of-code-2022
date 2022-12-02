fun main() {
    fun part1(input: List<String>): Int {
        var maxCalories = 0
        var caloriesCounter = 0
        for (row in input) {
            if (row == "") {
                if (maxCalories < caloriesCounter) {
                    maxCalories = caloriesCounter
                }
                caloriesCounter = 0
                continue
            }
            caloriesCounter += row.toInt()
        }
        return maxCalories
    }

    fun part2(input: List<String>): Int {
        val topThreeValues = arrayListOf(0, 0, 0)
        var caloriesCounter = 0
        for (row in input) {
            if (row == "") {
                if (caloriesCounter > topThreeValues[0]) {
                    topThreeValues[0] = caloriesCounter
                    topThreeValues.sort()
                }
                caloriesCounter = 0
                continue
            }
            caloriesCounter += row.toInt()
        }
        return topThreeValues.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    println(part1(testInput))
    println(part2(testInput))
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
