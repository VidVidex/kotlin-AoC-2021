import kotlin.math.max

fun main() {

    data class P(val x: Int, val y: Int)
    data class I(val axis: Char, val value: Int)
    data class PrepareInputResult(
        val points: MutableList<P>,
        val instructions: MutableList<I>,
        val grid: Array<CharArray>,
        var width: Int,
        var height: Int
    )

    fun prepareInput(input: List<String>): PrepareInputResult {

        val points = mutableListOf<P>()
        val instructions = mutableListOf<I>()

        var width = 0
        var height = 0

        var processingPoints = true
        for (row in input) {

            if (row == "") {
                processingPoints = false
                continue
            }

            if (processingPoints) {

                val x = row.split(",")[0].toInt()
                val y = row.split(",")[1].toInt()

                width = max(width, x)
                height = max(height, y)

                points.add(P(y, x))

            } else {

                val instructionString = row.split(" ")[2]
                val parts = instructionString.split("=")
                instructions.add(I(parts[0][0], parts[1].toInt()))

            }
        }

        width++
        height++

        val grid = Array(height) { CharArray(width) { '.' } }

        points.forEach { grid[it.x][it.y] = '#' }

        return PrepareInputResult(points, instructions, grid, width, height)
    }

    fun execute(input: List<String>, part1: Boolean): Int {

        val r = prepareInput(input)

        var count = r.points.size

        for (instruction in r.instructions) {

            if (instruction.axis == 'x') {
                var newX = instruction.value

                for (x in instruction.value + 1 until r.width) {
                    newX--

                    for (y in 0 until r.height) {

                        if (r.grid[y][x] == '#')
                            count--

                        if ((r.grid[y][x] == '.' && r.grid[y][newX] == '#') || (r.grid[y][x] == '#' && r.grid[y][newX] == '.')) {

                            if (r.grid[y][x] == '#' && r.grid[y][newX] == '.')
                                count++

                            r.grid[y][newX] = '#'
                        }
                    }
                }

                r.width = instruction.value

            } else {

                var newY = instruction.value

                for (y in instruction.value + 1 until r.height) {
                    newY--

                    for (x in 0 until r.width) {
                        if (r.grid[y][x] == '#')
                            count--

                        if ((r.grid[y][x] == '.' && r.grid[newY][x] == '#') || (r.grid[y][x] == '#' && r.grid[newY][x] == '.')) {

                            if (r.grid[y][x] == '#' && r.grid[newY][x] == '.')
                                count++

                            r.grid[newY][x] = '#'
                        }
                    }
                }

                r.height = instruction.value
            }

            if (part1)
                break
        }

        if (!part1)
            printGrid(r.grid, r.height, r.width)

        return count
    }

    fun part1(input: List<String>): Int {
        return execute(input, true)
    }

    fun part2(input: List<String>): Int {
        return execute(input, false)
    }

    val testInput = readInput("Day13_test.txt")
    check(part1(testInput) == 17)
    part2(testInput)

    val input = readInput("Day13_input.txt")
    println(part1(input))
    part2(input)
}