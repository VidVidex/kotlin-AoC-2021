fun main() {
    fun part1(input: List<String>): Int {

        return -1
    }

    fun part2(input: List<String>): Int {

        return -1
    }

    // Test if implementation meets criteria from the description
    val testInput = readInputToStringArray("Day00_test.txt")
    check(part1(testInput) == -1)
    check(part2(testInput) == -1)

    val input = readInputToStringArray("Day00_input.txt")
    println("Solution to part 1: " + part1(input))
    println("Solution to part 2: " + part2(input))
}
