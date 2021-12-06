import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1and2(input: List<String>, isPart2: Boolean): Int {

        val overlaps = mutableMapOf<Point, Int>()

        for (coordinates in input) {

            val from = coordinates.split(" -> ")[0]
            val to = coordinates.split(" -> ")[1]

            val line = Point(from).lineTo(Point(to), isPart2)

            for (point in line) {

                if (overlaps.containsKey(point))
                    overlaps[point] = overlaps[point]!! + 1
                else
                    overlaps[point] = 1
            }
        }

        var moreThan2Count = 0
        for (point in overlaps)
            if (point.value >= 2)
                moreThan2Count++

        return moreThan2Count
    }

    // Test if implementation meets criteria from the description
    val testInput = readInput("Day05_test.txt")
    check(part1and2(testInput, false) == 5)
    check(part1and2(testInput, true) == 12)

    val input = readInput("Day05_input.txt")
    println("Solution to part 1: " + part1and2(input, false))
    println("Solution to part 2: " + part1and2(input, true))
}

class Point {
    private val x: Int
    private val y: Int

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    constructor(coords: String) {
        this.x = coords.split(",")[0].toInt()
        this.y = coords.split(",")[1].toInt()
    }

    fun lineTo(point: Point, part2: Boolean = false): List<Point> {

        val line = mutableListOf<Point>()

        if (y == point.y) {
            val min = min(x, point.x)
            val max = max(x, point.x)
            for (i in min..max)
                line.add(Point(i, y))

        } else if (x == point.x) {
            val min = min(y, point.y)
            val max = max(y, point.y)
            for (i in min..max)
                line.add(Point(x, i))

        } else if (part2) {

            val xDirection1 = if (x > point.x) -1 else 1
            val yDirection1 = if (y > point.y) -1 else 1
            var currX = x
            var currY = y

            while (currX != point.x) {
                line.add(Point(currX, currY))
                currX += xDirection1
                currY += yDirection1
            }
            line.add(point)
        }

        return line
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        return 31 * x + y
    }
}