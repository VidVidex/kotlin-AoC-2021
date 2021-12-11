fun main() {

    fun flash(x: Int, y: Int, grid: Array<IntArray>, flashed: Array<BooleanArray>): Int {

        if (grid[x][y] <= 9)
            return 0

        var flashes = 0

        if (!flashed[x][y]) {

            flashes++
            grid[x][y] = 0
            flashed[x][y] = true

            for (i in x - 1..x + 1) {
                for (j in y - 1..y + 1) {

                    if (i == x && j == y)
                        continue

                    if (i < 0 || j < 0 || i > grid.size - 1 || j > grid[x].size - 1)
                        continue

                    if (!flashed[i][j])
                        grid[i][j]++
                    flashes += flash(i, j, grid, flashed)
                }
            }
        }
        return flashes
    }

    fun part1(grid: Array<IntArray>): Int {

        var flashes = 0

        for (loop in 0 until 100) {

            val flashed = Array(grid.size) { BooleanArray(grid[0].size) }

            for (i in grid.indices)
                for (j in grid[i].indices)
                    grid[i][j]++

            for (i in grid.indices)
                for (j in grid[i].indices)
                    flashes += flash(i, j, grid, flashed)

            println()
        }

        return flashes
    }

    fun allZero(grid: Array<IntArray>): Boolean {

        for (i in grid.indices)
            for (j in grid[i].indices)
                if (grid[i][j] != 0)
                    return false
        return true
    }

    fun part2(grid: Array<IntArray>): Int {

        var iteration = 0
        while (!allZero(grid)) {

            val flashed = Array(grid.size) { BooleanArray(grid[0].size) }

            for (i in grid.indices)
                for (j in grid[i].indices)
                    grid[i][j]++

            for (i in grid.indices)
                for (j in grid[i].indices)
                    flash(i, j, grid, flashed)

            iteration++
        }

        return iteration
    }

    // Test if implementation meets criteria from the description
    val testInput = createGrid(readInput("Day11_test.txt"))
    check(part1(testInput) == 1656)

    val testInput2 = createGrid(readInput("Day11_test.txt"))
    check(part2(testInput2) == 195)


    val input1 = createGrid(readInput("Day11_input.txt"))
    println("Solution to part 1: " + part1(input1))

    val input2 = createGrid(readInput("Day11_input.txt"))
    println("Solution to part 2: " + part2(input2))
}
