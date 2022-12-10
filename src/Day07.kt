fun main() {

    var dirChildrenConnect = hashMapOf<String, HashSet<String>>()
    var dirFileSizes = hashMapOf<String, Int>()

    fun calculateDirTotalSize(dir: String): Int {
        var children = dirChildrenConnect[dir]!!.toList()
        var totalSize = 0

        while (children.isNotEmpty()) {
            val nextChildren = arrayListOf<String>()

            for (child in children) {
                if (dirChildrenConnect[child]!!.isEmpty()) {
                    totalSize += dirFileSizes[child]!!
                } else {
                    totalSize += dirFileSizes[child]!!
                    nextChildren.addAll(dirChildrenConnect[child]!!)
                }
            }

            children = nextChildren.toSet().toList()
        }
        totalSize += dirFileSizes[dir]!!
        return totalSize
    }

    fun mapDirectoryTree(input: List<String>) {
        dirChildrenConnect = hashMapOf()
        dirFileSizes = hashMapOf()
        var currentActiveCommand = ""
        val directoryQueue = ArrayDeque<String>()
        var currentActiveSubDirectory: String
        for (row in input) {
            val curDirectoryIdentifier = directoryQueue.joinToString("")
            if (row.startsWith("$")) {
                currentActiveCommand = row.substring(2)
                if (currentActiveCommand.startsWith("cd")) {
                    val dir = currentActiveCommand.split(" ")[1]
                    if (dir == "..") directoryQueue.removeLast() else directoryQueue.addLast(dir)
                }
                if (!dirChildrenConnect.keys.contains(curDirectoryIdentifier)) {
                    dirChildrenConnect[curDirectoryIdentifier] = hashSetOf()
                    dirFileSizes[curDirectoryIdentifier] = 0
                }
            } else {
                if (currentActiveCommand.startsWith("ls")) {
                    val (firstPart, dirNameOrSize) = row.split(" ")
                    if (firstPart == "dir") {
                        currentActiveSubDirectory = curDirectoryIdentifier + dirNameOrSize
                        dirChildrenConnect[curDirectoryIdentifier]!!.add(currentActiveSubDirectory)
                    } else {
                        dirFileSizes[curDirectoryIdentifier] = dirFileSizes[curDirectoryIdentifier]!! + firstPart.toInt()
                    }
                }

            }
        }
    }

    fun part1(input: List<String>): Int {
        mapDirectoryTree(input)
        var totalSize = 0

        for (dir in dirChildrenConnect.keys) {
            val dirTotalSize = calculateDirTotalSize(dir)
            if (dirTotalSize <= 100000) {
                totalSize += dirTotalSize
            }
        }

        return totalSize
    }

    fun part2(input: List<String>): Int {
        mapDirectoryTree(input)

        val dirSizes = hashMapOf<String, Int>()
        for (dir in dirChildrenConnect.keys) {
            val dirTotalSize = calculateDirTotalSize(dir)
            dirSizes[dir] = dirTotalSize
        }

        val spaceNeededToFreeUp = 70000000 - dirSizes["/"]!!
        val spaceToFreeUp = 30000000 - spaceNeededToFreeUp

        return dirSizes.values.sorted().first { it >= spaceToFreeUp }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    println(part1(testInput))
    check(part1(testInput) == 95437)
    println(part2(testInput))
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
