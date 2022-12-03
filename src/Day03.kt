fun main() {
    fun calculatePriority(elementInBothRucksacks: Char) =
            if (elementInBothRucksacks.code > 96) elementInBothRucksacks.code - 96 else elementInBothRucksacks.code - 64 + 26

    fun part1(input: List<String>): Int {
        val items = arrayListOf<Int>()
        for (row in input) {
            val firsRucksack = row.substring(0, row.length / 2).asSequence()
            val secondRucksack = row.substring(row.length / 2, row.length).asSequence()

            val elementInBothRucksacks = firsRucksack.filter { c ->
                secondRucksack.contains(c)
            }.first()

            val priority = calculatePriority(elementInBothRucksacks)
            items.add(priority)
        }
        return items.sum()
    }

    fun part2(input: List<String>): Int {
        val items = arrayListOf<Int>()
        var rucksacks = arrayListOf<String>()
        for (row in input) {
            rucksacks.add(row)
            if (rucksacks.size == 3) {
                val rucksack1 = rucksacks[0]
                val rucksack2 = rucksacks[1]
                val rucksack3 = rucksacks[2]

                val elementInBothRucksacks = rucksack1.asSequence().filter { c ->
                    rucksack2.asSequence().contains(c)
                }.filter { c -> rucksack3.asSequence().contains(c) }.first()

                val priority = calculatePriority(elementInBothRucksacks)
                items.add(priority)
                rucksacks = arrayListOf()
            }
        }
        return items.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println(part1(testInput))
    check(part1(testInput) == 157)
    println(part2(testInput))
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
