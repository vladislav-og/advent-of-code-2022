fun main() {
    fun part1(input: List<String>): Int {
        var fullyContainsPairsCount = 0
        for (row in input) {
            val (x1, x2, y1, y2) = row.split(",").map {
                pair -> pair.split("-")
            }.flatten().map { i -> i.toInt() }

            if ((y1 in x1..x2 && y2 in x1..x2) || (x1 in y1..y2 && x2 in y1..y2)) {
                fullyContainsPairsCount++
            }

        }
        return fullyContainsPairsCount
    }

    fun part2(input: List<String>): Int {
        var fullyContainsPairsCount = 0
        for (row in input) {
            val (x1, x2, y1, y2) = row.split(",").map {
                pair -> pair.split("-")
            }.flatten().map { i -> i.toInt() }

            if (y1 in x1..x2 || y2 in x1..x2 || x1 in y1..y2 || x2 in y1..y2) {
                fullyContainsPairsCount++
            }

        }
        return fullyContainsPairsCount

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println(part1(testInput))
    check(part1(testInput) == 2)
    println(part2(testInput))
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
