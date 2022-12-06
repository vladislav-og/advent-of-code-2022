fun main() {
    fun extractData(row: String, stacks: ArrayList<ArrayDeque<String>>) {
        val regex = Regex("\\d+")
        val (n, stackFrom, stackTo) = regex.findAll(row).map { c -> c.value.toInt() - 1 }.toList()
        for (i in 0..n) {
            val removeFirst = stacks[stackFrom].removeFirst()
            stacks[stackTo].addFirst(removeFirst)
        }
    }

    fun part1(input: List<String>): String {
        val stacks = arrayListOf<ArrayDeque<String>>()

        var evaluateMoves = false
        for (row in input) {

            if (evaluateMoves) {
                extractData(row, stacks)
            } else {
                if (row.trim().isBlank()) {
                    evaluateMoves = true
                }
                var pointer = 0
                var stackNumber = 0
                for (char in row) {
                    if (stacks.size <= stackNumber){
                        stacks.add(ArrayDeque())
                    }
                    if (char.isLetter()) {
                        stacks[stackNumber].add(char.toString())
                    }
                    if (pointer == 3) {
                        stackNumber++
                        pointer = 0
                    } else {
                        pointer++
                    }
                }
            }
        }
        return stacks.stream().map { q -> q.first() }.toList().joinToString("")
    }

    fun part2(input: List<String>): String {
        val stacks = arrayListOf<ArrayDeque<String>>()

        var evaluateMoves = false
        for (row in input) {
            if (evaluateMoves) {
                extractData(row, stacks)
            } else {
                if (row.trim().isBlank()) {
                    evaluateMoves = true
                }
                var pointer = 0
                var stackNumber = 0
                for (char in row) {
                    if (stacks.size <= stackNumber){
                        stacks.add(ArrayDeque())
                    }
                    if (char.isLetter()) {
                        stacks[stackNumber].add(char.toString())
                    }
                    if (pointer == 3) {
                        stackNumber++
                        pointer = 0
                    } else {
                        pointer++
                    }
                }
            }
        }
        return stacks.stream().map { q -> q.first() }.toList().joinToString("")

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    println(part1(testInput))
    check(part1(testInput).equals("CMZ"))
    println(part2(testInput))
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
