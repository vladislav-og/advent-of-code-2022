fun main() {
    fun checkIfUniqueMarker(currentMarker: ArrayDeque<Char>): Boolean {
        val distinctMarker = currentMarker.distinct()
        return distinctMarker.size == currentMarker.size
    }

    fun solvePuzzle(input: List<String>, markerLength: Int): Int {
        for (datastream in input) {
            val currentMarker: ArrayDeque<Char> = datastream.subSequence(0, markerLength).toCollection(ArrayDeque())
            var pointer = markerLength
            for (element in datastream.subSequence(markerLength, datastream.length)) {
                if (checkIfUniqueMarker(currentMarker)) {
                    return pointer
                }
                currentMarker.removeFirst()
                currentMarker.add(element)
                pointer++
            }
        }
        return 1
    }

    fun part1(input: List<String>): Int {
        return solvePuzzle(input, 4)
    }

    fun part2(input: List<String>): Int {
        return solvePuzzle(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    println(part1(testInput))
    check(part1(testInput) == 7)
    println(part2(testInput))
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
