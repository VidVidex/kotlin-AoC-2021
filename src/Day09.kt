fun main() {
    fun part1(input: List<String>): Int {

        fun checkNeighbors(i: Int, j: Int): Boolean {

            if (i == -1 || j == -1)
                return true

            var isMin = true
            if (i > 0)
                isMin = isMin && input[i][j] < input[i - 1][j]

            if (j > 0)
                isMin = isMin && input[i][j] < input[i][j - 1]

            if (i < input.size - 1)
                isMin = isMin && input[i][j] < input[i + 1][j]

            if (j < input[i].length - 1)
                isMin = isMin && input[i][j] < input[i][j + 1]

            return isMin
        }

        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {

                var isMin = true

                isMin = isMin && checkNeighbors(i, j)

                if (isMin)
                    sum += input[i][j].toString().toInt() + 1
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {

        val grid = mutableListOf<CharArray>()

        for (line in input)
            grid.add(line.toCharArray())


        val basins = mutableListOf<Basin>()

        for (i in grid.indices)
            for (j in grid[i].indices)
                if (grid[i][j] != 'a' && grid[i][j] != '9')
                    basins.add(Basin(grid, i, j))

        val sorted = basins.sortedDescending()

        return sorted[0].size * sorted[1].size * sorted[2].size
    }

    // Test if implementation meets criteria from the description
    val testInput = readInput("Day09_test.txt")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09_input.txt")
    println("Solution to part 1: " + part1(input))
    println("Solution to part 2: " + part2(input))
}

class Basin(private val grid: List<CharArray>, x: Int, y: Int) : Comparable<Basin> {
    var size = 0

    init {
        markNeighbor(x, y)
    }

    private fun markNeighbor(x: Int, y: Int) {
        if (grid[x][y] == 'a' || grid[x][y] == '9')
            return

        if (x == -1 || y == -1)
            return

        size++
        grid[x][y] = 'a' // Cells we already visited are marked with 'a'

        if (x > 0)
            markNeighbor(x - 1, y)

        if (y > 0)
            markNeighbor(x, y - 1)

        if (x < grid.size - 1)
            markNeighbor(x + 1, y)

        if (y < grid[x].size - 1)
            markNeighbor(x, y + 1)
    }

    override fun compareTo(other: Basin): Int = this.size - other.size
}
