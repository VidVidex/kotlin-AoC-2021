import java.util.*

fun main() {
    fun part1(numbers: List<Int>, grids: List<Grid>): Int {

        for (number in numbers) {
            for (grid in grids) {
                grid.markNumber(number)
                if (grid.isDone())
                    return grid.sumUnmarked() * number
            }
        }

        return -1
    }

    fun part2(numbers: List<Int>, grids: List<Grid>): Int {

        var gridsPlaying = grids.size

        for (number in numbers) {
            for (grid in grids) {

                if (grid.won)
                    continue

                grid.markNumber(number)

                if (grid.isDone()) {
                    if (gridsPlaying == 1)
                        return grid.sumUnmarked() * number

                    grid.won = true
                    gridsPlaying--
                }
            }
        }

        return -1
    }

    var numbers = mutableListOf<Int>()
    var grids = ArrayList<Grid>()

    fun parseInput(input: List<String>) {
        numbers = mutableListOf()
        grids = ArrayList<Grid>()

        var grid = Grid()
        for ((i, row) in input.withIndex()) {

            if (i == 0) {
                for (str in row.split(","))
                    numbers.add(str.toInt())
            } else if (i == 1) {
                // Skip first empty line
                continue
            } else {
                if (row == "") {
                    grids.add(grid)
                    grid = Grid()
                } else {
                    grid.addRow(row)
                }
            }
        }
        grids.add(grid)
    }

    // Test if implementation meets criteria from the description
    parseInput(readInputToStringArray("Day04_test.txt"))
    check(part1(numbers, grids) == 4512)
    check(part2(numbers, grids) == 1924)

    parseInput(readInputToStringArray("Day04_input.txt"))
    println("Solution to part 1: " + part1(numbers, grids))
    println("Solution to part 2: " + part2(numbers, grids))
}

class Grid {
    private var grid = Array(5) { IntArray(5) }
    private var marked = Array(5) { BooleanArray(5) }
    private var rowsFilled = 0
    var won = false

    fun addRow(row: String) {
        val scanner = Scanner(row)

        var i = 0
        while (scanner.hasNext()) {
            grid[rowsFilled][i++] = scanner.nextInt()
        }

        rowsFilled++
    }

    fun markNumber(number: Int) {
        for ((i, row) in grid.withIndex())
            for ((j, cell) in row.withIndex())
                if (cell == number)
                    marked[i][j] = true
    }

    fun sumUnmarked(): Int {
        var sum = 0
        for (i in grid.indices)
            for (j in grid[i].indices)
                if (!marked[i][j])
                    sum += grid[i][j]

        return sum
    }

    fun isDone(): Boolean {
        for (i in marked.indices) {
            var rowDone = true
            var columnDone = true

            for (j in marked[i].indices) {
                rowDone = rowDone && marked[i][j]
                columnDone = columnDone && marked[j][i]
            }

            if (rowDone || columnDone)
                return true
        }
        return false
    }
}