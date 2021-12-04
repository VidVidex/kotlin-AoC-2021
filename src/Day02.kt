fun main() {
    fun part1(input: List<String>): Int {
        var horizontal = 0
        var depth = 0

        for (value in input) {
            val parts = value.split(' ')

            when (parts[0]) {
                "forward" -> horizontal += parts[1].toInt()
                "down" -> depth += parts[1].toInt()
                "up" -> depth -= parts[1].toInt()
            }
        }
        return horizontal * depth;
    }

    fun part2(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        for (value in input) {
            val parts = value.split(' ')

            when (parts[0]) {
                "forward" -> {
                    horizontal += parts[1].toInt()
                    depth += aim * parts[1].toInt()
                }
                "down" -> aim += parts[1].toInt()
                "up" -> aim -= parts[1].toInt()
            }
        }

        return horizontal * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test.txt")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02.txt")
    println(part1(input))
    println(part2(input))
}
